package com.loveSea.designPattern.adapterPattern;

/**
 * 适配器模式测试类
 * @author xiahaifeng
 */

public class AdapterTest {
    public static void main(String[] args) {
        PowerAdapter powerAdapter = new PowerAdapter(new AC220());
        System.out.println("输出直流电" + powerAdapter.outputDC5V() + "V");
    }
}