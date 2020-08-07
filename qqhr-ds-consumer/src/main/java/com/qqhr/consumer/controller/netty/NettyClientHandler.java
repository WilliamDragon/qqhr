package com.qqhr.consumer.controller.netty;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

import java.util.Scanner;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class NettyClientHandler extends ChannelInboundHandlerAdapter {
    /*private ChannelHandlerContext ctx;
    private EchoClientHandler.ScannerInRunnable scannerInRunnable;

    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
        stopComandIn(scannerInRunnable);
    }

    private void startCommandIn(EchoClientHandler.ScannerInRunnable scannerInRunnable) {
        commandTaskThreadPool.execute(scannerInRunnable);
    }
    private void stopComandIn(EchoClientHandler.ScannerInRunnable scannerInRunnable) {
        commandTaskThreadPool.remove(scannerInRunnable);
    }

    private static LinkedBlockingQueue<Runnable> commandQueue = new LinkedBlockingQueue<Runnable>(100);
    private static ThreadPoolExecutor commandTaskThreadPool = new ThreadPoolExecutor(3, 7, 10, TimeUnit.MINUTES, commandQueue);

    //发送连接就会触发channelActive()
    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        //缓存ctx
        this.ctx = ctx;
        //发起循环读取命令行
        scannerInRunnable = new EchoClientHandler.ScannerInRunnable();
        startCommandIn(scannerInRunnable);
    }

    private class ScannerInRunnable implements Runnable {
        private Scanner scanner = new Scanner(System.in);
        public void run() {
            while(true) {
                String cmdStr = scanner.next();
                doRequest(cmdStr);
            }
        }
    }
    private void doRequest(String request) {

        //通过ctx向服务端发请求
        ByteBuf resp = Unpooled.copiedBuffer(request.getBytes());
        ctx.writeAndFlush(resp);
    }*/
    private ChannelHandlerContext ctx;
    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        //缓存ctx
        this.ctx = ctx;
        //通过ctx向服务端发请求
        //ByteBuf resp = Unpooled.copiedBuffer(request.getBytes());
       // ctx.writeAndFlush(resp);
    }
    //获取服务端返回的数据
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        ByteBuf buf = (ByteBuf) msg;
        byte[] req = new byte[buf.readableBytes()];
        buf.readBytes(req);

        //打印出收到的回显响应
        String request = new String(req,"utf-8");
        System.out.println("收到数据 : " + request);
    }
    //命令行输入实现=========================================

}

