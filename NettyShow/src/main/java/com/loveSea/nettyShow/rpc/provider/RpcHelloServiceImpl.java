package com.loveSea.nettyShow.rpc.provider;

import com.loveSea.nettyShow.rpc.api.IRpcHelloService;

/**
 * @author xiahaifeng
 */

public class RpcHelloServiceImpl implements IRpcHelloService {

    @Override
    public int add(int a, int b) {
        return a + b;
    }

    @Override
    public int sub(int a, int b) {
        return a - b;
    }

    @Override
    public int mult(int a, int b) {
        return a * b;
    }

    @Override
    public int div(int a, int b) {
        return a / b;
    }
}