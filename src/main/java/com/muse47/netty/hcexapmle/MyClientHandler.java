package com.muse47.netty.hcexapmle;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.Date;

/**
 * @program: netty_lecture
 * @description:
 * @author: Muse47
 * @create: 2020-04-10 15:11
 **/
public class MyClientHandler extends SimpleChannelInboundHandler<String> {

    int count = 0;
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, String msg) throws Exception {

        Date d = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");

        if (msg.length() > 3) {

            System.out.println(msg + ":::::::::::::::::::::::::::::::::::" + ++count);
        }

        //System.out.println("Count: " + ++count + "    Time: " + sdf.format(d));
        Thread.sleep(500);
        ctx.writeAndFlush("1|9");
    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        ctx.writeAndFlush("1|9");
        //System.out.println("Client link :::::" + System.currentTimeMillis());

    }

    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
        Date d = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
        System.out.println("Client inactive :::::" + sdf.format(d));
        super.channelInactive(ctx);
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        Date d = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
        System.out.println("Client error :::::" + sdf.format(d));
        ctx.close();
    }
}
