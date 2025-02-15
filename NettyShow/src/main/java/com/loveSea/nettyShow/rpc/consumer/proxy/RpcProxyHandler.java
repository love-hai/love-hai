package com.loveSea.nettyShow.rpc.consumer.proxy;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

/**
 * @author xiahaifeng
 */

public class RpcProxyHandler extends ChannelInboundHandlerAdapter {
    private Object response;

    public Object getResponse() {
        return response;
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        cause.printStackTrace();
        ctx.close();
    }


    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        response = msg;
    }
}