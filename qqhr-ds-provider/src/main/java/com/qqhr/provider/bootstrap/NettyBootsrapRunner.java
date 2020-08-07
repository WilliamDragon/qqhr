package com.qqhr.provider.bootstrap;

import com.qqhr.provider.service.netty.EchoServerHandler;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextClosedEvent;
import org.springframework.stereotype.Component;

public class NettyBootsrapRunner{

}

/*
@Component
public class NettyBootsrapRunner implements ApplicationRunner, ApplicationListener<ContextClosedEvent>, ApplicationContextAware {

    @Value("${netty.port}")
    private int nettyPort;

    private ApplicationContext applicationContext;

    @Override
    public void run(ApplicationArguments args) throws Exception {

        //配置reactor 线程池
        NioEventLoopGroup bossGroup = new NioEventLoopGroup();
        NioEventLoopGroup workerGroup = new NioEventLoopGroup();

        //创建ServerBootstrap 实例
        ServerBootstrap bootstrap = new ServerBootstrap();

        //绑定线程池
        bootstrap.group(bossGroup, workerGroup)
                //设置绑定服务端Channel
                .channel(NioServerSocketChannel.class)
                //设置TCP参数， backlog表示等待队列长度
                .option(ChannelOption.SO_BACKLOG, 1024)
                .childHandler(new ChannelInitializer<SocketChannel>() {
                    protected void initChannel(SocketChannel socketChannel) throws Exception{
                        //绑定处理事件的Handler，这里可以设置多个
                        socketChannel.pipeline().addLast(applicationContext.getBean(EchoServerHandler.class));
                    }
                });

        try {
            //绑定本是异步操作,这里将其变为同步阻塞
            ChannelFuture cf = bootstrap.bind(nettyPort).sync();
            //promise模式，阻塞至channel关闭后才退出
            cf.channel().closeFuture().sync();

        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            //优雅退出，释放线程资源
            bossGroup.shutdownGracefully();
            workerGroup.shutdownGracefully();
        }
    }

    @Override
    public void onApplicationEvent(ContextClosedEvent contextClosedEvent) {

    }

    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }
}
*/
