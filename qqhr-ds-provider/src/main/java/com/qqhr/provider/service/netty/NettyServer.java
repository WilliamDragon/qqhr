package com.qqhr.provider.service.netty;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import org.springframework.stereotype.Component;

import java.net.InetSocketAddress;

@Component
public class NettyServer {


    public static void startServer(String hostName, int port){
        startServer0(hostName, port);
    }

    //完成对NettyServer的初始化和启动
    private static void startServer0(String hostname, int port){

        //配置reactor 线程池
        EventLoopGroup bossGroup = new NioEventLoopGroup(); //线程组用于处理连接工作
        EventLoopGroup workerGroup = new NioEventLoopGroup(); //线程组用于数据处理

        //创建ServerBootstrap 实例
        ServerBootstrap bootstrap = new ServerBootstrap();

        //绑定线程池
        bootstrap.group(bossGroup, workerGroup)
                //设置绑定服务端Channel
                .channel(NioServerSocketChannel.class)
                //使用指定的端口设置套接字地址
                .localAddress(new InetSocketAddress(port))
                //设置TCP参数， backlog表示等待队列长度
                .option(ChannelOption.SO_BACKLOG, 1024)
                //设置TCP长连接,一般如果两个小时内没有数据的通信时,TCP会自动发送一个活动探测数据报文
                .childOption(ChannelOption.SO_KEEPALIVE, true)
                //将小的数据包包装成更大的帧进行传送，提高网络的负载
                .childOption(ChannelOption.TCP_NODELAY, true)
                .childHandler(new NettyServerHandlerInitializer());
                 /*.childHandler(new ChannelInitializer<SocketChannel>() {
                     protected void initChannel(SocketChannel socketChannel) throws Exception{
                         //绑定处理事件的Handler，这里可以设置多个
                         socketChannel.pipeline().addLast(new EchoServerHandler());
                     }
                 })*/;

        try {
            //绑定本是异步操作,这里将其变为同步阻塞
            ChannelFuture cf = bootstrap.bind(port).sync();
            System.out.println("netty系统提供方开始监听");
            if (cf.isSuccess()) {
                //log.info("启动 Netty Server");
                System.out.println("netty系统成功");
            }

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
