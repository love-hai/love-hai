package com.LoveSea.fengCore.study.netty.tomcat;

import lombok.extern.slf4j.Slf4j;

import java.io.InputStream;

/**
 * @author xiahaifeng
 */
@Slf4j
public class GPRequest {
    private String method;

    public String getMethod() {
        return method;
    }

    private String url;

    public String getUrl() {
        return url;
    }

    public GPRequest(InputStream in) {
        try {
            String content = "";
            byte[] buffer = new byte[1024];
            int len;
            if ((len = in.read(buffer)) > 0) {
                content = new String(buffer, 0, len);
            }
            String line = content.split("\\n")[0];
            String[] arr = line.split(" ");
            this.method = arr[0];
            this.url = arr[1].split("\\?")[0];
        } catch (Exception e) {
            log.error("GPRequest error", e);
        }
    }
}