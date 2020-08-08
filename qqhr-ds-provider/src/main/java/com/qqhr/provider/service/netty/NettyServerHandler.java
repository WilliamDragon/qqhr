package com.qqhr.provider.service.netty;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import org.springframework.stereotype.Component;

@Component
public class NettyServerHandler extends ChannelInboundHandlerAdapter {

    //收到数据
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {

        System.out.println("收到数据!");

        ByteBuf buf = (ByteBuf) msg;
        byte[] req = new byte[buf.readableBytes()];
        buf.readBytes(req);

        String request = new String(req,"utf-8");

        //String retStr = doMciscall(request);

        //System.out.println("Client Request : " + request);

        ByteBuf resp = Unpooled.copiedBuffer(request.getBytes());
        ctx.writeAndFlush(resp);
    }


    //异常处理
    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
//        super.exceptionCaught(ctx, cause);
        cause.printStackTrace();
        ctx.close();
    }
}
