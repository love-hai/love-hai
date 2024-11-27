package com.LoveSea.fengCore.shadowsocks;

import lombok.Data;

import java.util.Optional;

/**
 * @author xiahaifeng
 */
@Data
public class SSRUrlItem {
    final String defaultProtocol = "origin";
    final String defaultObfs = "plain";
    final String defaultEncodeMethod = "none";


    String group;
    String remarks;
    String server;
    Integer port;
    String password;
    String protocol;

    String protocolParam;
    String obfs;
    String obfsparam;
    String encodeMethod;


    public String getProtocol() {
        return Optional.ofNullable(protocol).orElse(defaultProtocol);
    }

    public String getObfs() {
        return Optional.ofNullable(obfs).orElse(defaultObfs);
    }

    public String getEncodeMethod() {
        return Optional.ofNullable(encodeMethod).orElse(defaultEncodeMethod);
    }
}