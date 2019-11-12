package com.feng.netty_demo.server;

import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

/**
 * 客户端事务处理
 * Create by east on 2019/11/4 10:09
 */
public class ClientHandler extends SimpleChannelInboundHandler {
    
    @Override
    public void channelActive(ChannelHandlerContext ctx) {
        String msg = "{\"battery_level\": 53,\n" +
                "\"one_click_locking\": 0,\n" +
                "\"switch\": 1,\n" +
                "\"three_error_code\": 0,\n" +
                "\"low_power_alarm\": 0,\n" +
                "\"user_code\": 0,\n" +
                "\"vibration_alarm\": 0,\n" +
                "\"timeout\": 0,\n" +
                "\"five_error_code\": 0}";
        System.out.println("Channel Active");
        ctx.writeAndFlush(Unpooled.copiedBuffer((msg).getBytes()));
    }

    @Override
    public void channelRead0(ChannelHandlerContext ctx, Object msg) {
        String inStr = (String) msg;
        System.out.println("Received: " + inStr);
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable e) { // (4)
        // Close the connection when an exception is raised.
        e.printStackTrace();
        ctx.close();
    }
}
