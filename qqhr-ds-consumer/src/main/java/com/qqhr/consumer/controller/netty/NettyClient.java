package com.qqhr.consumer.controller.netty;

import io.netty.bootstrap.Bootstrap;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.concurrent.TimeUnit;
public class NettyClient {

    private EventLoopGroup group = new NioEventLoopGroup();
   // @Value("8888")
    private int port = 8888;
   // @Value("${netty.host}")
    private String host = "127.0.0.1";
    private SocketChannel socketChannel;

    public void sendMsg(String sendMessage) {
        //通过ctx向服务端发请求
        ByteBuf resp = Unpooled.copiedBuffer(sendMessage.getBytes());
        socketChannel.writeAndFlush(resp);
    }

    //@PostConstruct
    public void start()  {
        //创建NIO处理线程
        NioEventLoopGroup eventLoopGroup  = new NioEventLoopGroup();
        //实例化Bootstrap
        Bootstrap bootstrap = new Bootstrap();
        //配置NIO处理线程
        bootstrap.group(eventLoopGroup)
                //设置并绑定客户端Channel
                .channel(NioSocketChannel.class)
                //TCP参数配置，TCP_NODELAY为低延迟
                .option(ChannelOption.TCP_NODELAY,true)
                //绑定事件处理类
                .handler(new ChannelInitializer<SocketChannel>() {
                    protected void initChannel(SocketChannel socketChannel) throws Exception {
                        //绑定处理事件Handler
                        socketChannel.pipeline().addLast(new EchoClientHandler());
                    }
                });


       /* ChannelFuture future = bootstrap.connect();

        socketChannel = (SocketChannel) future.channel();*/
        try {
            //发起连接，将异步操作转为同步阻塞
            ChannelFuture cf = bootstrap.connect(host,port);

            socketChannel = (SocketChannel) cf.channel();
            //同步阻塞至channle关闭后退出
           // cf.channel().closeFuture().sync();

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            //优雅退出，释放线程资源
            eventLoopGroup.shutdownGracefully();
        }
    }
}
