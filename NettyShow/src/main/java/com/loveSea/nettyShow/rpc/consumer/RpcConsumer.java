package com.loveSea.nettyShow.rpc.consumer;

import com.loveSea.nettyShow.rpc.api.IRpcHelloService;
import com.loveSea.nettyShow.rpc.consumer.proxy.RpcProxy;
import lombok.extern.slf4j.Slf4j;

/**
 * @author xiahaifeng
 */
@Slf4j
public class RpcConsumer {
    public static void main(String[] args) {
        IRpcHelloService service = RpcProxy.create(IRpcHelloService.class);
        log.info("8+2=" + service.add(8, 2));
        log.info("8-2=" + service.sub(8, 2));
        log.info("8*2=" + service.mult(8, 2));
        log.info("8/2=" + service.div(8, 2));
    }
}