package com.loveSea.designPattern.proxyPattern;

/**
 * @author xiahaifeng
 */

public class main {
    public static void main(String[] args) {
        Painting painting = new ProxyPainting();
        painting.make();
    }
}
