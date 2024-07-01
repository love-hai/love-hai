package com.LoveSea.fengCore.retryable;

import javax.annotation.processing.Filer;
import javax.annotation.processing.Messager;
import javax.annotation.processing.ProcessingEnvironment;
import javax.annotation.processing.Processor;
import javax.tools.*;
import java.io.IOException;
import java.util.Arrays;
import java.util.Map;
import java.util.ServiceLoader;

/**
 * 重试编译处理器
 *
 * @author xiahaifeng
 */

public class RetryCompilerPlugin {
    public static void main(String[] args) throws IOException {
        // 获取系统Java编译器
        JavaCompiler compiler = ToolProvider.getSystemJavaCompiler();
        if (compiler == null) {
            System.err.println("No Java compiler available");
            System.exit(1);
        }

        // 创建一个文件管理器
        StandardJavaFileManager fileManager = compiler.getStandardFileManager(null, null, null);
        Iterable<? extends JavaFileObject> compilationUnits = fileManager.getJavaFileObjectsFromStrings(Arrays.asList(args));


        // 创建编译任务并设置注解处理器
        JavaCompiler.CompilationTask task = compiler.getTask(null, fileManager, null, null, null, compilationUnits);
        // 执行编译任务
        boolean success = task.call();
        // 在编译完成后执行静态方法method2
        if (success) {
            RetryMethodModifier.modifyMethod();
        } else {
            System.err.println("Compilation failed");
        }

        fileManager.close();
    }
}
