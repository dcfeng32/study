package com.feng.netty_demo.server;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

/**
 * Server事务处理
 * Create by east on 2019/11/4 9:16
 */
@ChannelHandler.Sharable
public class ServerHandler extends ChannelInboundHandlerAdapter {

    @Override
    public void channelRead(ChannelHandlerContext channelHandlerContext, Object msg) {
        String inStr = (String) msg;
        System.out.println("Revice:" + inStr);
        String outStr = "From Server:" + inStr + System.lineSeparator();

        ByteBuf byteBuf = Unpooled.copiedBuffer(outStr.getBytes());
        channelHandlerContext.write(byteBuf);
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext channelHandlerContext, Throwable cause) {
        cause.printStackTrace();
        channelHandlerContext.close();
    }

    @Override
    public void channelReadComplete(ChannelHandlerContext channelHandlerContext) throws Exception {
        channelHandlerContext.close();
    }

}
