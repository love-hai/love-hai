package com.LoveSea.fengCore.shadowsocks;

import lombok.Data;

/**
 * @author xiahaifeng
 */
@Data
public class SSRUrlItem {
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
}