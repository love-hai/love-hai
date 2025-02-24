package com.LoveSea.fengCore.study.jdk.dynamicProxy.example2;

import java.util.ArrayList;

/**
 * @author xiahaifeng
 */

public interface OrderService {

    void query(OrderPm orderPm);

    public static void main(String[] args) {
        OrderService orderService = (OrderService)OrderServiceDynamicProxy.getInstance(new OrderServiceImpl() );
        OrderPm orderPm = new OrderPm();
        orderPm.setCreateTime(System.currentTimeMillis());
        orderService.query(orderPm);

    }
}