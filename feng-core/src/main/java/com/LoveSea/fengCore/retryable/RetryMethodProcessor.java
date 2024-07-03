package com.LoveSea.fengCore.retryable;

import javax.annotation.processing.*;
import javax.lang.model.SourceVersion;
import javax.lang.model.element.Element;
import javax.lang.model.element.ExecutableElement;
import javax.lang.model.element.TypeElement;
import javax.lang.model.element.VariableElement;
import javax.lang.model.type.TypeMirror;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * 重试方法处理器
 *
 * @author xiahaifeng
 */
@Deprecated
@SupportedAnnotationTypes("com.LoveSea.fengCore.retryable.Retryable")
@SupportedSourceVersion(SourceVersion.RELEASE_17)
public class RetryMethodProcessor extends AbstractProcessor {
    public static final List<RetryMethod> retryMethods = new ArrayList<>();

    @Override
    public synchronized void init(ProcessingEnvironment processingEnvironment) {
        super.init(processingEnvironment);
        System.out.println("--------------------------processor init---------------------------");
    }

    @Override
    public boolean process(Set<? extends TypeElement> annotations, RoundEnvironment roundEnv) {
        for (Element element : roundEnv.getElementsAnnotatedWith(Retryable.class)) {
            if (element instanceof ExecutableElement methodElement) {
                // 获取方法名称
                String methodName = methodElement.getSimpleName().toString();
                // 获取类名称，包名
                TypeElement classElement = (TypeElement) methodElement.getEnclosingElement();
                String className = classElement.getQualifiedName().toString();
                // 获取方法参数
                List<? extends VariableElement> parameters = methodElement.getParameters();
                // 获取方法注解
                Retryable retryableAnnotation = methodElement.getAnnotation(Retryable.class);
                int maxRetries = retryableAnnotation.maxRetries();
                long delay = retryableAnnotation.delay();
                try {
                    RetryMethod retryMethod = new RetryMethod();
                    retryMethod.setMaxRetries(maxRetries);
                    retryMethod.setDelay(delay);
                    retryMethod.setClassName(className);
                    retryMethod.setMethodName(methodName);
                    List<String> parameterTypes = new ArrayList<>();
                    for (VariableElement parameter : parameters) {
                        TypeMirror typeMirror = parameter.asType();
                        parameterTypes.add(typeMirror.toString());
                    }
                    retryMethods.add(retryMethod);
                } catch (Exception e) {
                    System.out.println("重试方法处理器异常");
                }
            }
        }
        return true;
    }
}