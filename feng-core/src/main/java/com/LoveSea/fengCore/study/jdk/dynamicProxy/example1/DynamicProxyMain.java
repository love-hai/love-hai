package com.LoveSea.fengCore.study.jdk.dynamicProxy.example1;

import aj.org.objectweb.asm.ClassReader;
import lombok.extern.slf4j.Slf4j;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * @author xiahaifeng
 */
@Slf4j
public class DynamicProxyMain {
    public static void main(String[] args) {
        try {
            Object person = new JDKMeipo().getInstance(new Customer());
            if (person instanceof Customer person1) {
                person1.findLove();
            }
            // FIXME xia 2025/2/24 09:04 : 暂时无法获取生成的代理类的字节码。
            // 找到Person 的代理类的文件字节码
            Class<?> proxyClass = person.getClass();
            System.out.println("代理类的类名: " + proxyClass.getName());

            // 获取代理类的字节码信息
            String proxyClassName = proxyClass.getName();
            String proxyClassPath = proxyClassName.replace('.', '/').concat(".class");
            byte[] bytecode;
            try (InputStream inputStream = proxyClass.getClassLoader().getResourceAsStream(proxyClassPath)) {
                if (inputStream == null) {
                    log.info("无法查找到{}", proxyClassPath);
                    return;
                }
                bytecode = inputStream.readAllBytes();
            }
            try (FileOutputStream fos = new FileOutputStream("$Proxy0.class")) {
                fos.write(bytecode);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}