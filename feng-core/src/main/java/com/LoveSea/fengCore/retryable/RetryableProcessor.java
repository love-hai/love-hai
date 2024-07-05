package com.LoveSea.fengCore.retryable;

import com.LoveSea.fengCore.retryable.xml.Annotation;
import com.LoveSea.fengCore.retryable.xml.AnnotationParameter;
import com.LoveSea.fengCore.retryable.xml.ParamType;
import com.LoveSea.fengCore.retryable.xml.RetryMethod;
import com.google.common.collect.Lists;
import com.sun.xml.bind.v2.ContextFactory;
import lombok.extern.slf4j.Slf4j;
import org.codehaus.plexus.util.CollectionUtils;

import javax.annotation.processing.*;
import javax.lang.model.SourceVersion;
import javax.lang.model.element.*;
import javax.lang.model.type.TypeMirror;
import javax.lang.model.util.Elements;
import javax.lang.model.util.Types;
import javax.tools.FileObject;
import javax.tools.StandardLocation;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
import java.io.Writer;
import java.util.*;
import java.util.stream.Collectors;

/**
 * 重试方法处理器
 */
@Slf4j
@SupportedAnnotationTypes("com.LoveSea.fengCore.retryable.Retryable")
@SupportedSourceVersion(SourceVersion.RELEASE_17)
public class RetryableProcessor extends AbstractProcessor {
    private static List<RetryMethod> retryMethods;
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
        retryMethods = new ArrayList<>();
    }

    boolean isEmpty(Collection<?> collection) {
        if(null == collection) {
            return true;
        }
        return collection.isEmpty();
    }
    boolean isEmpty(Map<?, ?> map) {
        if(null == map) {
            return true;
        }
        return map.isEmpty();
    }

    List<AnnotationParameter> getAnnotationParameters(Map<? extends ExecutableElement, ? extends AnnotationValue> annotationMirrors) {
        if (isEmpty(annotationMirrors)) {
            return null;
        }
        log.info("annotationMirrors:{}", annotationMirrors);
        return annotationMirrors.entrySet().stream().map(annotationMirror -> {
            AnnotationParameter annotationParameter = new AnnotationParameter();
            annotationParameter.setName(annotationMirror.getKey().getSimpleName().toString());
            annotationParameter.setValue(annotationMirror.getValue().toString());
            return annotationParameter;
        }).collect(Collectors.toList());
    }
    List<Annotation> getAnnotations(List<? extends AnnotationMirror> annotationMirrors) {
        if (isEmpty(annotationMirrors)) {
            return null;
        }
      return annotationMirrors.stream().map(annotationMirror -> {
            Annotation methodAnnotation = new Annotation();
            methodAnnotation.setType(annotationMirror.getAnnotationType().toString());
            methodAnnotation.setAnnotationParameters(getAnnotationParameters(annotationMirror.getElementValues()));
            return methodAnnotation;
        }).collect(Collectors.toList());
    }

    List<ParamType> getParamTypes(List<? extends VariableElement> parameters) {
        if (isEmpty(parameters)) {
            return null;
        }
        return parameters.stream().map(parameter -> {
            ParamType paramType = new ParamType();
            TypeMirror typeMirror = parameter.asType();
            paramType.setType(typeMirror.toString());
            paramType.setName(parameter.getSimpleName().toString());
            paramType.setAnnotations(getAnnotations(parameter.getAnnotationMirrors()));
            return paramType;
        }).collect(Collectors.toList());
    }

    @Override
    public boolean process(Set<? extends TypeElement> annotations, RoundEnvironment roundEnv) {
        try {
            for (Element element : roundEnv.getElementsAnnotatedWith(Retryable.class)) {
                if (element.getKind() != ElementKind.METHOD) {
                    continue;
                }
                RetryMethod retryMethod = new RetryMethod();

                ExecutableElement methodElement = (ExecutableElement) element;
                Retryable retryable = methodElement.getAnnotation(Retryable.class);
                int maxRetries = retryable.maxRetries();
                long delay = retryable.delay();
                // 最大重试次数
                retryMethod.setMaxRetries(maxRetries);
                // 重试间隔
                retryMethod.setDelay(delay);

                TypeElement classElement = (TypeElement) methodElement.getEnclosingElement();
                String className = elementUtils.getBinaryName(classElement).toString();
                String methodName = methodElement.getSimpleName().toString();
                // 类名
                retryMethod.setClassName(className);
                // 方法名
                retryMethod.setMethodName(methodName);

                // 得到这个方法的注解
                retryMethod.setAnnotations(getAnnotations(methodElement.getAnnotationMirrors()));

                retryMethod.setParamTypes(getParamTypes(methodElement.getParameters()));

                Set<Modifier> modifiers = methodElement.getModifiers();
                Set<String> otherModifiers = modifiers.stream().map(Modifier::toString).collect(Collectors.toSet());

                for (String modifier : otherModifiers) {
                    if (RetryMethod.accessModifierSet.contains(modifier)) {
                        retryMethod.setAccessModifier(modifier);
                        break;
                    }
                }
                otherModifiers.remove(retryMethod.getAccessModifier());
                retryMethod.setOtherModifiers(otherModifiers);

                TypeMirror returnType = methodElement.getReturnType();
                retryMethod.setReturnType(returnType.toString());
                retryMethods.add(retryMethod);
            }
        } catch (Exception e) {
            log.error("获取重试方法异常", e);
            throw new RuntimeException("获取重试方法异常", e);
        }
        if (!roundEnv.processingOver()) {
            return true;
        }
        if (retryMethods.isEmpty()) {
            return true;
        }
        try {
            FileObject file = filer.createResource(StandardLocation.CLASS_OUTPUT, "", "META-INF/retryMethods.xml");
            try (Writer writer = file.openWriter()) {
                JAXBContext context = ContextFactory.createContext(new Class[]{RetryMethodsWrapper.class}, null);
                Marshaller marshaller = context.createMarshaller();
                marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
                RetryMethodsWrapper wrapper = new RetryMethodsWrapper();
                wrapper.setRetryMethods(retryMethods);
                marshaller.marshal(wrapper, writer);
            }
        } catch (Exception e) {
            throw new RuntimeException("记录重试方法错误", e);
        }
        return true;
    }
}