package com.LoveSea.fengCore.retryable;

import com.sun.xml.bind.v2.ContextFactory;

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
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * 重试方法处理器
 */
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

            TypeElement classElement = (TypeElement) methodElement.getEnclosingElement();
            String className = elementUtils.getBinaryName(classElement).toString();
            String methodName = methodElement.getSimpleName().toString();
            List<? extends VariableElement> parameters = methodElement.getParameters();
            TypeMirror returnType = methodElement.getReturnType();
            Set<Modifier> modifiers = methodElement.getModifiers();
            // 得到这个方法的注解
            List<? extends AnnotationMirror> annotationMirrors = methodElement.getAnnotationMirrors();

            RetryMethod retryMethod = new RetryMethod();
            retryMethod.setMaxRetries(maxRetries);
            retryMethod.setDelay(delay);
            retryMethod.setClassName(className);
            retryMethod.setMethodName(methodName);
            List<RetryMethod.ParamType> paramTypes = new ArrayList<>();
            for (VariableElement parameter : parameters) {
                RetryMethod.ParamType paramType = new RetryMethod.ParamType();
                TypeMirror typeMirror = parameter.asType();
                paramType.setType(typeMirror.toString());
                paramType.setName(parameter.getSimpleName().toString());
                paramTypes.add(paramType);
            }
            retryMethod.setParamTypes(paramTypes);
            retryMethod.setReturnType(returnType.toString());


            retryMethods.add(retryMethod);
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
            throw new RuntimeException("Error writing XML file", e);
        }
        return true;
    }
}