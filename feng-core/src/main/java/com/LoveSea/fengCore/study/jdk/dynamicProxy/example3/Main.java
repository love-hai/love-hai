package com.LoveSea.fengCore.study.jdk.dynamicProxy.example3;

import com.LoveSea.fengCore.study.jdk.dynamicProxy.example1.Customer;
import com.LoveSea.fengCore.study.jdk.dynamicProxy.example1.Person;

/**
 * @author xiahaifeng
 */

public class Main {
    public static void main(String[] args) {
        try {
            Person person = (Person) GPMeipo.getInstance(new Customer());
            System.out.println(person.getClass());
            person.findLove();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}