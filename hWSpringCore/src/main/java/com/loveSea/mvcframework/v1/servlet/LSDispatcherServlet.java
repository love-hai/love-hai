package com.loveSea.mvcframework.v1.servlet;

import com.loveSea.mvcframework.annotation.*;
import com.loveSea.mvcframework.v1.servlet.handlerAdapter.RequestMethod;
import com.loveSea.mvcframework.v1.servlet.handlerAdapter.RequestMethodParam;
import jakarta.servlet.ServletConfig;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.net.URL;
import java.util.*;

/**
 * @author xiahaifeng
 */
@Slf4j
public class LSDispatcherServlet extends HttpServlet {
    private final Map<String, Object> BeanMapping = new HashMap<>();
    private final UrlHandlerNode urlHandlerNode = new UrlHandlerNode();
    private final List<Object> controllerList = new ArrayList<>();
    private Properties config;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        this.doPost(req, resp);
    }

    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        try {
            doDispatch(req, resp);
        } catch (Exception e) {
            resp.getWriter().write("500 Exception, Details:\r\n" + e.getMessage());
        }
    }

    private void doDispatch(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        String url = req.getRequestURI();
        url = url.replace(req.getContextPath(), "").replaceAll("/+", "/");
        String[] urlSlices = url.split("/");

        UrlHandlerNode curUrlNode = urlHandlerNode;
        for (String urlSlice : urlSlices) {
            if (urlSlice.trim().isEmpty()) {
                continue;
            }
            curUrlNode = curUrlNode.getHandlerNode(urlSlice);
            if (null == curUrlNode) {
                resp.getWriter().write("404 Not Found");
                return;
            }
        }
        // 查看requestMethod
        String methodAction = req.getMethod();
        RequestActionType requestActionType = RequestActionType.getActionType(methodAction);
        if (null == requestActionType) {
            resp.getWriter().write("405 Method Not Allowed");
            return;
        }
        RequestMethod requestMethod = curUrlNode.getHandlerNodeByMethod(requestActionType);
        if (null == requestMethod) {
            resp.getWriter().write("405 Method Not Found");
            return;
        }
        requestMethod.accept(req, resp);
    }


    private void doLoadConfig(String location) throws Exception {
        try (InputStream is = this.getClass().getClassLoader().getResourceAsStream(location)) {
            config = new Properties();
            config.load(is);
        }
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
                BeanMapping.put(className, null);
            }
        }
    }

    private void doInstance() throws Exception {
        if (BeanMapping.isEmpty()) {
            return;
        }
        for (String className : BeanMapping.keySet()) {
            Class<?> clazz = Class.forName(className);
            if (clazz.isAnnotationPresent(LSController.class)) {
                BeanMapping.put(className, clazz.getDeclaredConstructor().newInstance());
                controllerList.add(BeanMapping.get(className));
            } else if (clazz.isAnnotationPresent(LSService.class)) {
                LSService service = clazz.getAnnotation(LSService.class);
                String beanName = service.value();
                if (beanName.trim().isEmpty()) {
                    beanName = clazz.getName();
                }
                BeanMapping.put(beanName, clazz.getDeclaredConstructor().newInstance());
                Class<?>[] interfaces = clazz.getInterfaces();
                for (Class<?> i : interfaces) {
                    BeanMapping.put(i.getName(), clazz.getDeclaredConstructor().newInstance());
                }
            } else {
                continue;
            }
        }
    }

    private void doAutowired() throws Exception {
        if (BeanMapping.isEmpty()) {
            return;
        }
        for (Object object : BeanMapping.values()) {
            if (null == object) {
                continue;
            }
            Class<?> clazz = object.getClass();
            Field[] fields = clazz.getDeclaredFields();
            for (Field field : fields) {
                if (!field.isAnnotationPresent(LSAutowired.class)) {
                    continue;
                }
                LSAutowired autowired = field.getAnnotation(LSAutowired.class);
                String beanName = autowired.value();
                if (beanName.trim().isEmpty()) {
                    beanName = field.getType().getName();
                }
                field.setAccessible(true);
                field.set(object, BeanMapping.get(beanName));
            }
        }
    }

    private final List<Class<? extends Annotation>> annotations = Arrays.asList(
            LSRequestMapping.class,
            LSGetMapping.class,
            LSPostMapping.class,
            LSPutMapping.class,
            LSDeleteMapping.class
    );

    private void initHandlerMapping() throws IllegalAccessException, NoSuchMethodException, InvocationTargetException {
        if (controllerList.isEmpty()) {
            return;
        }
        for (Object object : controllerList) {
            if (null == object) {
                continue;
            }
            Class<?> clazz = object.getClass();
            if (!clazz.isAnnotationPresent(LSController.class)) {
                continue;
            }
            String baseUrl = "";
            if (clazz.isAnnotationPresent(LSRequestMapping.class)) {
                LSRequestMapping requestMapping = clazz.getAnnotation(LSRequestMapping.class);
                baseUrl = requestMapping.value();
            }
            Method[] methods = clazz.getMethods();
            for (Method method : methods) {
                UrlHandlerNode curUrlNode = urlHandlerNode;
                UrlHandlerNode urlNode;
                for (Class<? extends Annotation> annotation : annotations) {
                    if (!method.isAnnotationPresent(annotation)) {
                        continue;
                    }
                    Annotation requestMapping = method.getAnnotation(annotation);
                    // 获取annotation的value
                    String urlSuffix;
                    RequestActionType[] requestActionTypes;
                    if (requestMapping instanceof LSRequestMapping lsRequestMapping) {
                        urlSuffix = lsRequestMapping.value();
                        requestActionTypes = lsRequestMapping.method();
                    } else {
                        requestActionTypes = annotation.getAnnotationsByType(LSRequestMapping.class)[0].method();
                        urlSuffix = annotation.getMethod("value").invoke(requestMapping).toString();
                    }
                    String url = (baseUrl + urlSuffix).replaceAll("/+", "/");
                    String[] urlSlices = url.split("/");
                    for (String urlSlice : urlSlices) {
                        if (urlSlice.trim().isEmpty()) {
                            continue;
                        }
                        urlNode = curUrlNode.getHandlerNode(urlSlice);
                        if (null == urlNode) {
                            urlNode = new UrlHandlerNode();
                            curUrlNode.addHandlerNode(urlSlice, urlNode);
                        }
                        curUrlNode = urlNode;
                    }
                    // 查看参数
                    Parameter[] parameters = method.getParameters();
                    RequestMethod requestMethod = new RequestMethod();
                    for (RequestActionType requestActionType : requestActionTypes) {
                        curUrlNode.addHandlerNodeByMethod(requestActionType, requestMethod);
                    }
                    requestMethod.setObject(object);
                    requestMethod.setMethod(method);
                    RequestMethodParam[] params = new RequestMethodParam[parameters.length];
                    requestMethod.setParams(params);
                    int paramIndex = -1;
                    for (Parameter parameter : parameters) {
                        paramIndex++;
                        RequestMethodParam requestMethodParam = new RequestMethodParam();
                        params[paramIndex] = requestMethodParam;
                        requestMethodParam.setType(parameter.getType());
                        if (parameter.getType() == HttpServletRequest.class) {
                            requestMethodParam.setRequestMethodParamType(RequestMethodParam.RequestMethodParamType.HttpServletRequest);
                        } else if (parameter.getType() == HttpServletResponse.class) {
                            requestMethodParam.setRequestMethodParamType(RequestMethodParam.RequestMethodParamType.HttpServletResponse);
                        } else {
                            if (parameter.isAnnotationPresent(LSRequestParam.class)) {
                                LSRequestParam requestParam = parameter.getAnnotation(LSRequestParam.class);
                                requestMethodParam.setName(requestParam.value());
                                requestMethodParam.setRequestMethodParamType(RequestMethodParam.RequestMethodParamType.RequestParam);
                            } else if (parameter.isAnnotationPresent(LSRequestBody.class)) {
                                requestMethodParam.setRequestMethodParamType(RequestMethodParam.RequestMethodParamType.RequestBody);
                            }
                        }
                    }
                }
            }
        }
    }

    @Override
    public void init(ServletConfig servletConfig) {
        try {
            doLoadConfig(servletConfig.getInitParameter("contextConfigLocation"));
            String scanPackage = config.getProperty("scanPackage");
            doScanner(scanPackage);
            doInstance();
            doAutowired();
            initHandlerMapping();
        } catch (Exception e) {
            log.error("init error", e);
        }
    }
}