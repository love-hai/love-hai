package com.loveSea.nettyShow.tomcat;

/**
 * @author xiahaifeng
 */

public class SecondServlet extends GPServlet{

    @Override
    public void doGet(GPRequest request, GPResponse response) throws Exception {
        this.doPost(request, response);
    }

    @Override
    public void doPost(GPRequest request, GPResponse response) throws Exception {
        response.write("This is Second Servlet");
    }
}