package com.loveSea.nettyShow.nettyTomcat;

/**
 * @author xiahaifeng
 */

public abstract class NettyGPServlet {
    public void service(NettyGPRequest request, NettyGPResponse response) throws Exception {
        if ("GET".equalsIgnoreCase(request.getMethod())) {
            doGet(request, response);
        } else {
            doPost(request, response);
        }
    }

    public abstract void doGet(NettyGPRequest request, NettyGPResponse response) throws Exception;

    public abstract void doPost(NettyGPRequest request, NettyGPResponse response) throws Exception;
}