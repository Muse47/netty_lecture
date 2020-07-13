package com.muse47.netty.secondexample;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

import java.util.UUID;

//定义了客户端和服务端是通过字符串通信，所以泛型用String
public class MyServerHandler extends SimpleChannelInboundHandler<String> {

    /**
     * @param: ctx 上下文，可以通过它获取远程地址是什么，连接对象是什么
     * @param: msg 接收到的客户端发送过来的数据
     * @date:  
     */
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, String msg) throws Exception {
        //打印客户端地址
        System.out.println(ctx.channel().remoteAddress() + ", " + msg);
        ctx.channel().writeAndFlush("from server: " + UUID.randomUUID());
    }



    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        cause.printStackTrace();
        ctx.close();
    }
}
