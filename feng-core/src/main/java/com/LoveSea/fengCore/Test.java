/*
 * The MIT License (MIT)
 * Copyright © 2025 love-hai <xiahaifeng2000@gmail.com>
 * See the LICENSE file for details.
 */
package com.LoveSea.fengCore;

import lombok.extern.slf4j.Slf4j;

import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.UnknownHostException;

/**
 * @author xiahaifeng
 */
@Slf4j
public class Test {
    public static void main(String[] args) throws UnknownHostException {
        String name = "xiahaifeng";
        InetAddress inetAddress = InetAddress.getByName(name);
        String host = inetAddress.getHostAddress();
        log.info("host:{}", host);
    }
}
