package com.LoveSea.fengCore.study.designPattern.delegation;

/**
 * @author xiahaifeng
 */

public class DelegationTest {
    public static void main(String[] args) {
        Leader boss = new Leader();
        boss.doing("加密");
        boss.doing("登陆");
    }
}