package com.LoveSea.fengCore.retryable;

import javassist.ClassPool;
import javassist.CtClass;
import javassist.CtMethod;
import javassist.CtNewMethod;

import java.io.File;
import java.net.URL;
import java.security.CodeSource;
import java.util.ArrayList;
import java.util.List;

/**
 * 重试方法修改器
 *
 * @author xiahaifeng
 */

public class RetryMethodModifier {
    public static final List<RetryMethod> retryMethods = new ArrayList<>();

    public static void main(String[] args) {
        try {
            modifyMethod();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void modifyMethod() {
        try {
            modifyMethod(retryMethods);
        } catch (Exception e) {
            System.out.println("Error in modifyMethod: " + e.getMessage());
        }
    }

    public static void modifyMethod(List<RetryMethod> retryMethods) throws Exception {
        for (RetryMethod retryMethod : retryMethods) {
            modifyMethod(retryMethod);
        }
    }

    public static void modifyMethod(RetryMethod retryMethod) throws Exception {
        modifyMethod(retryMethod.getClassName(), retryMethod.getMethodName(), retryMethod.getParameters(), retryMethod.getMaxRetries(), retryMethod.getDelay());
    }

    public static void modifyMethod(String className, String methodName, List<String> parameters, int maxRetries, long delay) throws Exception {
        ClassPool pool = ClassPool.getDefault();
        CtClass ctClass = pool.get(className);
        // 得到原类的路径
        CtClass[] params = new CtClass[parameters.size()];
        for (int i = 0; i < parameters.size(); i++) {
            params[i] = pool.get(parameters.get(i));
        }
        // 创建重试逻辑的方法
        String newMethodBody = """
                {
                    try{
                        com.lalang.browser.retryable.RetryManagementImpl.pushRetryCount(Integer.valueOf(-1));
                        int attempts = 0;
                        com.lalang.browser.retryable.REException reException = null;
                        do {
                            try {
                                attempts++;
                                com.lalang.browser.retryable.RetryManagementImpl.addRetryCount();
                                %s;
                            } catch (com.lalang.browser.retryable.REException e) {
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
                        com.lalang.browser.retryable.RetryManagementImpl.popRetryCount();
                    }
                }
                """;
        // 判断ctClass是否被冻结，如果被冻结则解冻
        if (ctClass.isFrozen()) {
            ctClass.defrost();
        }
        // 获取原方法
        CtMethod method = ctClass.getDeclaredMethod(methodName, params);
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
        newMethodBody = String.format(newMethodBody, returnOriginalMethod, delay, maxRetries);
        ctClass.addMethod(originalMethod);
        method.setBody(newMethodBody);
        // 写入修改后的类文件
        ctClass.writeFile(getCompiledDirectory(className));
    }

    private static String getCompiledDirectory(String className) throws Exception {
        Class<?> cls = Class.forName(className);
        CodeSource codeSource = cls.getProtectionDomain().getCodeSource();
        if (codeSource != null) {
            URL location = codeSource.getLocation();
            return new File(location.toURI()).getPath();
        }
        throw new IllegalStateException("Unable to determine the compiled directory for class: " + className);
    }
}
