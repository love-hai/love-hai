package com.LoveSea.fengCore.study.netty.tomcat;

/**
 * @author xiahaifeng
 */

public class FirstServlet extends GPServlet{

    @Override
    public void doGet(GPRequest request, GPResponse response) throws Exception {
        this.doPost(request, response);
    }

    @Override
    public void doPost(GPRequest request, GPResponse response) throws Exception {
        response.write("This is First Servlet");
    }
}