package com.loveSea.nettyShow.nettyTomcat;

import com.loveSea.nettyShow.tomcat.GPRequest;
import com.loveSea.nettyShow.tomcat.GPResponse;
import com.loveSea.nettyShow.tomcat.GPServlet;

/**
 * @author xiahaifeng
 */

public class FirstServlet extends NettyGPServlet {
    @Override
    public void doGet(NettyGPRequest request, NettyGPResponse response) throws Exception {
        this.doPost(request, response);
    }

    @Override
    public void doPost(NettyGPRequest request, NettyGPResponse response) throws Exception {
        response.write("This is First Servlet");
    }
}