package com.LoveSea.fengCore.retryable;

import javassist.*;

import java.io.File;
import java.net.URL;
import java.security.CodeSource;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * 重试方法修改器
 *
 * @author xiahaifeng
 */

public class RetryMethodModifier {
    static String RetryMethodBody = """
            {
                try{
                    com.LoveSea.fengCore.retryable.RetryManagementImpl.pushRetryCount(Integer.valueOf(-1));
                    int attempts = 0;
                    com.LoveSea.fengCore.retryable.REException reException = null;
                    do {
                        try {
                            attempts++;
                            com.LoveSea.fengCore.retryable.RetryManagementImpl.addRetryCount();
                            %s;
                        } catch (com.LoveSea.fengCore.retryable.REException e) {
                            reException = e;
                            System.out.println("重试：" + attempts + " 次，异常信息：" + e.getMessage());
                            try {
                                Thread.sleep(%dL);
                            } catch (InterruptedException ie) {
                                System.out.println("Thread sleep error");
                            }
                        }
                    } while (attempts < %d);
                    if (reException != null) {
                        throw new Exception(reException.getMessage(), reException.getCause());
                    }
                    throw new RuntimeException("重试次数用完");
                } finally {
                    // 弹出重试次数
                    com.LoveSea.fengCore.retryable.RetryManagementImpl.popRetryCount();
                }
            }
            """;
    ClassPool pool;
    String outputDirectory;


    public RetryMethodModifier(String classPath) throws NotFoundException {
        pool = ClassPool.getDefault();
        pool.appendClassPath(classPath);
        outputDirectory = classPath + "target/classes";
    }

    public void modifyMethod(List<RetryMethod> retryMethods) throws Exception {
        // 根据classname 分组
        Map<String, List<RetryMethod>> retryMethodMap = retryMethods.stream().collect(Collectors.groupingBy(RetryMethod::getClassName));
        for (Map.Entry<String, List<RetryMethod>> entry : retryMethodMap.entrySet()) {
            String className = entry.getKey();
            CtClass ctClass = pool.get(className);
            List<RetryMethod> methods = entry.getValue();
            for (RetryMethod retryMethod : methods) {
                modifyMethod(ctClass, retryMethod);
            }
            ctClass.writeFile(outputDirectory);
        }
    }

    CtMethod getMethod(CtClass ctClass, String methodName, List<String> parameters) throws NotFoundException {
        CtMethod[] methods = ctClass.getDeclaredMethods(methodName);
        if (null == methods || methods.length == 0) {
            return null;
        }
        if (methods.length == 1) {
            return methods[0];
        }
        for (CtMethod method : methods) {
            // (Ljava/lang/Integer;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)J
            String descriptor = method.getMethodInfo().getDescriptor();
            if (!descriptor.startsWith("(")) {
                continue;
            }
            descriptor = descriptor.substring(descriptor.indexOf("(") + 1, descriptor.indexOf(")"));
            String[] split = descriptor.split(";");
            List<String> methodParameters = new ArrayList<>();
            for (String s : split) {
                methodParameters.add(s.substring(1).replace("/", "."));
            }
            if (methodParameters.size() != parameters.size()) {
                continue;
            }
            boolean isMatch = true;
            for (int i = 0; i < parameters.size(); i++) {
                if (!methodParameters.get(i).equals(parameters.get(i))) {
                    isMatch = false;
                    break;
                }
            }
            if (isMatch) {
                return method;
            }
        }
        return null;
    }


    public void modifyMethod(CtClass ctClass, RetryMethod retryMethod) throws Exception {
        modifyMethod(ctClass, retryMethod.getClassName(), retryMethod.getMethodName(), retryMethod.getParameters(), retryMethod.getMaxRetries(),
                retryMethod.getDelay());
    }

    public void modifyMethod(CtClass ctClass, String className, String methodName, List<String> parameters, int maxRetries, long delay) throws Exception {
        // 判断ctClass是否被冻结，如果被冻结则解冻
        if (ctClass.isFrozen()) {
            ctClass.defrost();
        }
        // 获取原方法
        CtMethod method = getMethod(ctClass, methodName, parameters);
        // 将原方法重命名
        String originalMethodName = "original_" + methodName;
        // 创建新方法,命名为original + 原方法名 ，方法体为原代码
        CtMethod originalMethod = CtNewMethod.copy(method, originalMethodName, ctClass, null);
        // 重试逻辑中的return语句，如果原方法返回值为void 则不要加return
        String returnOriginalMethod = originalMethodName + "($$);";
        if (!CtClass.voidType.equals(method.getReturnType())) {
            returnOriginalMethod = "return " + returnOriginalMethod;
        } else {
            returnOriginalMethod = returnOriginalMethod + "; return";
        }
        String newMethodBody = String.format(RetryMethodBody, returnOriginalMethod, delay, maxRetries);
        ctClass.addMethod(originalMethod);
        method.setBody(newMethodBody);
        // 查看method的是否有注解，如果有则将注解添加到新方法上，并删除原方法的注解
        // 写入修改后的类文件
    }
}
