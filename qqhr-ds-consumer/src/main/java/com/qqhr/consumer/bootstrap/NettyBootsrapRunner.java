package com.qqhr.consumer.bootstrap;

import com.qqhr.consumer.controller.netty.EchoClientHandler;

import io.netty.bootstrap.Bootstrap;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextClosedEvent;
import org.springframework.stereotype.Component;


//@Component
public class NettyBootsrapRunner implements ApplicationRunner, ApplicationListener<ContextClosedEvent>, ApplicationContextAware {

    @Value("${netty.port}")
    private int port;

    @Value("${netty.host}")
    private String host;

    private ApplicationContext applicationContext;

    @Override
    public void run(ApplicationArguments args) throws Exception {

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
                        socketChannel.pipeline().addLast(applicationContext.getBean(EchoClientHandler.class));
                    }
                });
        try {
            //发起连接，将异步操作转为同步阻塞
            ChannelFuture cf = bootstrap.connect(host,port).sync();
            //同步阻塞至channle关闭后退出
            cf.channel().closeFuture().sync();

        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            //优雅退出，释放线程资源
            eventLoopGroup.shutdownGracefully();
        }

    }

    @Override
    public void onApplicationEvent(ContextClosedEvent contextClosedEvent) {

    }

    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }
}

