package com.loveSea.spring.framework.beans.support;

import lombok.extern.slf4j.Slf4j;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

/**
 * @author xiahaifeng
 */
@Slf4j
public class LSBeanDefinitionReader {
    private List<String> registryBeanClasses = new ArrayList<>();
    private Properties config = new Properties();
    private String SCAN_PACKAGE = "scanPackage";

    public LSBeanDefinitionReader(String... locations) {
        String scanPackage = locations[0].replace("classpath:", "");
        try (InputStream is = this.getClass().getClassLoader().getResourceAsStream(scanPackage)) {
            config.load(is);
        } catch (IOException e) {
            log.error("load config error", e);
        }
        // 扫描配置文件中的配置的相关的类
        doScanner(config.getProperty(SCAN_PACKAGE));
    }

    private void doScanner(String scanPackage) {
        URL url = this.getClass().getClassLoader().getResource("/" + scanPackage.replaceAll("\\.", "/"));
        if (null == url) {
            return;
        }
        File classDir = new File(url.getFile());
        File[] files = classDir.listFiles();
        if (!classDir.exists() || null == files) {
            return;
        }
        for (File file : files) {
            if (file.isDirectory()) {
                doScanner(scanPackage + "." + file.getName());
            } else {
                if (!file.getName().endsWith(".class")) {
                    continue;
                }
                String className = (scanPackage + "." + file.getName().replace(".class", ""));
                registryBeanClasses.add(className);
            }
        }
    }

    public Properties getConfig() {
        return this.config;
    }

    public List<LSBeanDefinition> loadBeanDefinitions() {
        List<LSBeanDefinition> result = new ArrayList<>();
        try {
            for (String className : registryBeanClasses) {
                Class<?> beanClass = Class.forName(className);
                if (beanClass.isInterface()) {
                    continue;
                }
                result.add(doCreateBeanDefinition(toLowerFirstCase(beanClass.getSimpleName()), beanClass.getName()));
                for (Class<?> i : beanClass.getInterfaces()) {
                    result.add(doCreateBeanDefinition(i.getName(), beanClass.getName()));
                }
            }
        } catch (Exception e) {
            log.error("load beanDefinitions error", e);
        }
        return result;
    }

    private LSBeanDefinition doCreateBeanDefinition(String factoryBeanName, String beanClassName) {
        LSBeanDefinition beanDefinition = new LSBeanDefinition();
        beanDefinition.setBeanClassName(beanClassName);
        beanDefinition.setFactoryBeanName(factoryBeanName);
        return beanDefinition;
    }

    private String toLowerFirstCase(String simpleName) {
        char[] chars = simpleName.toCharArray();
        if (chars[0] >= 'A' && chars[0] <= 'Z') {
            chars[0] += 32;
            return String.valueOf(chars);
        } else {
            return simpleName;
        }
    }

}