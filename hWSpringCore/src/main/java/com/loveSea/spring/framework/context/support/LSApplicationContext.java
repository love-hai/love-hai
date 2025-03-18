package com.loveSea.spring.framework.context.support;

import com.loveSea.mvcframework.annotation.LSAutowired;
import com.loveSea.mvcframework.annotation.LSController;
import com.loveSea.mvcframework.annotation.LSService;
import com.loveSea.spring.framework.beans.support.LSBeanDefinitionReader;
import lombok.extern.slf4j.Slf4j;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author xiahaifeng
 */
@Slf4j
public class LSApplicationContext extends LSDefaultListableApplicationContext implements LSBeanFactory {
    private String[] configLocations;
    private LSBeanDefinitionReader reader;

    private Map<String, Object> factoryBeanObjectCache = new ConcurrentHashMap<>();
    private Map<String, LSBeanWrapper> factoryBeanInstanceCache = new ConcurrentHashMap<>();

    public LSApplicationContext(String... configLocations) {
        this.configLocations = configLocations;
        try {
            refresh();
        } catch (Exception e) {
            log.error("refresh error", e);
        }
    }

    @Override
    public void refresh() throws Exception {
        // 1.定位 配置文件
        reader = new LSBeanDefinitionReader(this.configLocations);

        // 2.加载配置文件，扫描相关的类，把它们封装成BeanDefinition
        List<LSBeanDefinition> beanDefinitions = reader.loadBeanDefinitions();

        // 3.注册
        doRegisterBeanDefinition(beanDefinitions);

        // 4.依赖注入
        doAutowired();
    }

    private void doAutowired() {
        for (Map.Entry<String, LSBeanDefinition> beanDefinitionEntry : super.beanDefinitionMap.entrySet()) {
            String beanName = beanDefinitionEntry.getKey();
            if (!beanDefinitionEntry.getValue().isLazyInit()) {
                try {
                    getBean(beanName);
                } catch (Exception e) {
                    log.error("getBean error", e);
                }
            }
        }
    }

    private void doRegisterBeanDefinition(List<LSBeanDefinition> beanDefinitions) throws Exception {
        for (LSBeanDefinition beanDefinition : beanDefinitions) {
            if (super.beanDefinitionMap.containsKey(beanDefinition.getFactoryBeanName())) {
                throw new Exception("The " + beanDefinition.getFactoryBeanName() + " is exists!!");
            }
            super.beanDefinitionMap.put(beanDefinition.getFactoryBeanName(), beanDefinition);
        }
        // 到这里为止，容器初始化完毕
    }


    @Override
    public Object getBean(String beanName) throws Exception {
        LSBeanDefinition beanDefinition = super.beanDefinitionMap.get(beanName);
        try {
            //生成通知事件
            LSBeanPostProcessor beanPostProcessor = new LSBeanPostProcessor();
            Object instance = instantiateBean(beanDefinition);
            if (null == instance) {
                return null;
            }
            //在实例初始化以前调用一次
            beanPostProcessor.postProcessBeforeInitialization(instance, beanName);
            LSBeanWrapper beanWrapper = new LSBeanWrapper(instance);
            this.factoryBeanInstanceCache.put(beanName, beanWrapper);
            //在实例初始化以后调用一次
            beanPostProcessor.postProcessAfterInitialization(instance, beanName);
            //注入
            populateBean(beanName, instance);
            return this.factoryBeanInstanceCache.get(beanName).getWrappedInstance();
        } catch (Exception e) {
            log.error("getBean error", e);
        }
        return null;
    }

    private void populateBean(String beanName, Object instance) {
        Class<?> clazz = instance.getClass();
        if (!(clazz.isAnnotationPresent(LSController.class) || clazz.isAnnotationPresent(LSService.class))) {
            return;
        }
        for (Field field : clazz.getDeclaredFields()) {
            if (!field.isAnnotationPresent(LSAutowired.class)) {
                continue;
            }
            LSAutowired autowired = field.getAnnotation(LSAutowired.class);
            String autowiredBeanName = autowired.value().trim();
            if (autowiredBeanName.isEmpty()) {
                autowiredBeanName = field.getType().getName();
            }
            field.setAccessible(true);
            try {
                field.set(instance, this.factoryBeanInstanceCache.get(autowiredBeanName).getWrappedInstance());
            } catch (IllegalAccessException e) {
                log.error("populateBean error", e);
            }
        }
    }

    public Object getBean(Class<?> beanClazz) throws Exception {
        return getBean(beanClazz.getName());
    }

    public String[] getBeanDefinitionNames() {
        return super.beanDefinitionMap.keySet().toArray(new String[0]);
    }

    public int getBeanDefinitionCount() {
        return super.beanDefinitionMap.size();
    }

    public Properties getConfig() {
        return this.reader.getConfig();
    }
}