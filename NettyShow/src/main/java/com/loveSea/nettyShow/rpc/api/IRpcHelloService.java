package com.loveSea.nettyShow.rpc.api;

/**
 * @author xiahaifeng
 */

public interface IRpcHelloService {
    public int add(int a, int b);
    public int sub(int a, int b);
    public int mult(int a, int b);
    public int div(int a, int b);
}