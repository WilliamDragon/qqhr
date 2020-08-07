package com.qqhr.provider.service.netty;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.sctp.nio.NioSctpServerChannel;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
//@Service
public class EchoServer {
    //初步测试netty方法, 系统启动不用此方法
    public static void main(String[] args) {

        new EchoServer().bind(6666);
    }
    //@PostConstruct 随项目启动而启动
     public void bind(int port) {
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
                         socketChannel.pipeline().addLast(new EchoServerHandler());
                     }
                 });

         try {
             //绑定本是异步操作,这里将其变为同步阻塞
             ChannelFuture cf = bootstrap.bind(port).sync();
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
}
