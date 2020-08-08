package com.qqhr.provider.bootstrap;

import com.qqhr.provider.service.netty.EchoServerHandler;
import com.qqhr.provider.service.netty.NettyServer;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextClosedEvent;
import org.springframework.stereotype.Component;


//public class ServerBootstrap{
//
//}

@Component
public class ServerBootstrap implements ApplicationRunner, ApplicationListener<ContextClosedEvent>, ApplicationContextAware{

    @Value("${netty.port}")
    private int nettyPort;
    @Value("${netty.host}")
    private String hostname;
    private ApplicationContext applicationContext;
    @Override
    public void run(ApplicationArguments args) throws Exception {
        applicationContext.getBean(NettyServer.class).startServer(hostname,nettyPort);
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }

    @Override
    public void onApplicationEvent(ContextClosedEvent contextClosedEvent) {

    }
}

