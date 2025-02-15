package com.loveSea.nettyShow.nettyTomcat;

import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.handler.codec.http.HttpRequest;
import io.netty.handler.codec.http.QueryStringDecoder;

import java.util.List;
import java.util.Map;


/**
 * @author xiahaifeng
 */

public class NettyGPRequest {
    private ChannelHandlerContext ctx;
    private HttpRequest req;
    public NettyGPRequest(ChannelHandlerContext ctx, HttpRequest req) {
        this.ctx = ctx;
        this.req = req;
    }
    public String getMethod() {
        return req.method().name();
    }

    public String getUrl() {
        return req.uri();
    }
    public Map<String, List<String>> getParameters() {
        QueryStringDecoder decoder  = new QueryStringDecoder(req.uri());
        return decoder.parameters();
    }

    public String getParameter(String name) {
        Map<String, List<String>> params = getParameters();
        List<String> param = params.get(name);
        if (null == param) {
            return null;
        } else {
            return param.get(0);
        }
    }
}