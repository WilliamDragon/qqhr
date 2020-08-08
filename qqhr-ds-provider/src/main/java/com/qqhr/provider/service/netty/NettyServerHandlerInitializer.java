package com.qqhr.provider.service.netty;

import io.netty.channel.Channel;
import io.netty.channel.ChannelInitializer;
import io.netty.handler.codec.protobuf.ProtobufDecoder;
import io.netty.handler.codec.protobuf.ProtobufEncoder;
import io.netty.handler.codec.protobuf.ProtobufVarint32FrameDecoder;
import io.netty.handler.codec.protobuf.ProtobufVarint32LengthFieldPrepender;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;

public class NettyServerHandlerInitializer extends ChannelInitializer<Channel> {
    @Override
    protected void initChannel(Channel channel) throws Exception {

        channel.pipeline()
                //空闲检测
                //.addLast(new ServerIdleStateHandler())
                //.addLast(new ProtobufVarint32FrameDecoder())
                //.addLast(new ProtobufDecoder(MessageBase.Message.getDefaultInstance()))
                //.addLast(new ProtobufVarint32LengthFieldPrepender())
                //.addLast(new ProtobufEncoder())
                //.addLast(new StringDecoder())
                //.addLast(new StringEncoder())
                .addLast(new NettyServerHandler());
    }
}
