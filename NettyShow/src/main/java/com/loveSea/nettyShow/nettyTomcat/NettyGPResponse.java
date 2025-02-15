package com.loveSea.nettyShow.nettyTomcat;

import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.http.*;

import java.nio.charset.StandardCharsets;


/**
 * @author xiahaifeng
 */

public class NettyGPResponse {
    private ChannelHandlerContext ctx;
    private HttpRequest req;

    public NettyGPResponse(ChannelHandlerContext ctx, HttpRequest req) {
        this.ctx = ctx;
        this.req = req;
    }

    public void write(String out) throws Exception {
        if (out == null || out.isEmpty()) {
            return;
        }
        try {
            FullHttpResponse response = new DefaultFullHttpResponse(
                    HttpVersion.HTTP_1_1,
                    HttpResponseStatus.OK,
                    Unpooled.wrappedBuffer(out.getBytes(StandardCharsets.UTF_8)));
            response.headers().set("Content-Type", "text/html;");
            ctx.write(response);

        } finally {
            ctx.flush();
            ctx.close();
        }
    }

}