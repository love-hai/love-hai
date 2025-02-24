package com.LoveSea.fengCore.study.designPattern.decoratorPattern.example2;

/**
 * @author xiahaifeng
 */

public class BatterCakeTest {
    public static void main(String[] args) {
        // 先买一个煎饼
        BatterCake batterCake = new BaseBatterCake();
        print(batterCake);
        // 加一个鸡蛋
        batterCake= new EggDecorator(batterCake);
        print(batterCake);

        // 加一个香肠
        batterCake = new SausageDecorator(batterCake);
        print(batterCake);

        // 加一个鸡蛋
        batterCake = new EggDecorator(batterCake);
        print(batterCake);

    }

    public static void print(BatterCake batterCake){
        System.out.println(batterCake.getMsg() + ",总价：" + batterCake.getPrice());
    }
}