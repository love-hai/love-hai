package com.loveSea.nettyShow.tomcat;

import java.io.OutputStream;

/**
 * @author xiahaifeng
 */

public class GPResponse {
    private OutputStream out;
    public GPResponse(OutputStream out) {
        this.out = out;
    }
    public void write(String s) throws Exception {
        // HTTP/1.1 200 OK
        // Content-Type: text/html
        // \r\n
        // s
        StringBuilder sb = new StringBuilder();
        sb.append("HTTP/1.1 200 OK\n")
                .append("Content-Type: text/html\n")
                .append("\r\n")
                .append(s);
        out.write(sb.toString().getBytes());
    }
}