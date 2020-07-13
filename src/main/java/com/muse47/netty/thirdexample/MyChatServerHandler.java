package com.muse47.netty.thirdexample;

import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.group.ChannelGroup;
import io.netty.channel.group.DefaultChannelGroup;
import io.netty.util.concurrent.GlobalEventExecutor;

/**
 * @program: netty_lecture
 * @description:
 * @author: Muse47
 * @create: 2020-04-10 21:11
 **/
public class MyChatServerHandler extends SimpleChannelInboundHandler<String> {

    //channel组保存连接上的channel
    private static ChannelGroup channelGroup = new DefaultChannelGroup(GlobalEventExecutor.INSTANCE);

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, String msg) throws Exception {
        Channel channel = ctx.channel();

        channelGroup.forEach(ch ->{
            if (channel != ch) {
                ch.writeAndFlush(channel.remoteAddress() + " 发送的消息： " + msg + "\n");
            } else {
                ch.writeAndFlush("【自己】" + msg + "\n");
            }
        });
    }

    /**
     * 服务端与客户端连接
     *
     * @param ctx
     * @throws Exception
     */
    @Override
    public void handlerAdded(ChannelHandlerContext ctx) throws Exception {
        Channel channel = ctx.channel();

        //Netty给channel组也提供了writeAndFlush方法,给当前channel组中的所有连接发送消息
        channelGroup.writeAndFlush("【服务器】 - " + channel.remoteAddress() + " 加入\n");

        //消息发送完毕后，将最新的连接加入到channel组中
        channelGroup.add(channel);
    }

    /**
     * 客户端与服务端断开连接
     * @param ctx
     * @throws Exception
     */
    @Override
    public void handlerRemoved(ChannelHandlerContext ctx) throws Exception {
        Channel channel = ctx.channel();

        //channelGroup会自己调用remove，无需我们自己写
        //channelGroup.remove(channel);

        channelGroup.writeAndFlush("【服务器】 - " + channel.remoteAddress() + " 离开\n");

        //打印客户端退出后还有多少个连接
        System.out.println(channelGroup.size());

    }

    /**
     * 连接属于活动状态
     * @param ctx
     * @throws Exception
     */
    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        Channel channel = ctx.channel();
        //channelGroup.writeAndFlush(channel.remoteAddress() + " 上线\n");
        System.out.println(channel.remoteAddress() + " 上线\n");
    }

    /**
     * 连接属于非活动状态
     * @param ctx
     * @throws Exception
     */
    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
        Channel channel = ctx.channel();
        //channelGroup.writeAndFlush(channel.remoteAddress() + " 下线\n");
        System.out.println(channel.remoteAddress() + " 下线\n");
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        cause.printStackTrace();
        ctx.close();
    }
}
