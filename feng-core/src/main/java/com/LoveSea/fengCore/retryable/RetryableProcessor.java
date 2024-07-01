package com.LoveSea.fengCore.retryable;

import javax.annotation.processing.*;
import javax.lang.model.SourceVersion;
import javax.lang.model.element.*;
import javax.lang.model.type.TypeMirror;
import javax.lang.model.util.Elements;
import javax.lang.model.util.Types;
import javax.tools.Diagnostic;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 *  重试方法处理器
 */
@SupportedAnnotationTypes("com.LoveSea.fengCore.retryable.Retryable")
@SupportedSourceVersion(SourceVersion.RELEASE_17)
public class RetryableProcessor extends AbstractProcessor {
    private Types typeUtils;
    private Elements elementUtils;
    private Filer filer;
    private Messager messager;

    @Override
    public synchronized void init(ProcessingEnvironment processingEnv) {
        super.init(processingEnv);
        typeUtils = processingEnv.getTypeUtils();
        elementUtils = processingEnv.getElementUtils();
        filer = processingEnv.getFiler();
        messager = processingEnv.getMessager();
    }

    @Override
    public boolean process(Set<? extends TypeElement> annotations, RoundEnvironment roundEnv) {
        for (Element element : roundEnv.getElementsAnnotatedWith(Retryable.class)) {
            if (element.getKind() != ElementKind.METHOD) {
                continue;
            }

            ExecutableElement methodElement = (ExecutableElement) element;
            Retryable retryable = methodElement.getAnnotation(Retryable.class);
            int maxRetries = retryable.maxRetries();
            long delay = retryable.delay();

            // 获取包含该方法的类
            TypeElement classElement = (TypeElement) methodElement.getEnclosingElement();
            String className = elementUtils.getBinaryName(classElement).toString();
            String methodName = methodElement.getSimpleName().toString();
            // 获取方法参数类型并转换为 CtClass[]
            List<? extends VariableElement> parameters = methodElement.getParameters();
            // 调用方法修改器
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
                retryMethod.setParameters(parameterTypes);
                RetryMethodModifier.retryMethods.add(retryMethod);
            } catch (Exception e) {
                System.out.println("重试方法处理器异常");
            }
        }
        return true;
    }


}