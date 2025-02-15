package com.loveSea.nettyShow.nettyTomcat;

import com.loveSea.nettyShow.tomcat.GPRequest;
import com.loveSea.nettyShow.tomcat.GPResponse;
import com.loveSea.nettyShow.tomcat.GPServlet;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.handler.codec.http.HttpRequest;

import java.util.HashMap;
import java.util.Map;

/**
 * @author xiahaifeng
 */

public class NettyGPTomcatHandler extends ChannelInboundHandlerAdapter {
    public NettyGPTomcatHandler(Map<String, NettyGPServlet> servletMapping) {
        this.servletMapping = servletMapping;
    }

    private final Map<String, NettyGPServlet> servletMapping;

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        if (msg instanceof HttpRequest req) {
            NettyGPRequest request = new NettyGPRequest(ctx, req);
            NettyGPResponse response = new NettyGPResponse(ctx, req);
            String url = request.getUrl();
            if (servletMapping.containsKey(url)) {
                servletMapping.get(url).service(request, response);
            } else {
                response.write("404 - Not Found");
            }
        }
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        cause.printStackTrace();
        ctx.close();
    }
}