package com.LoveSea.fengCore.retryable;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import java.util.List;

/**
 * @author xiahaifeng
 */


@XmlRootElement(name = "retryMethod")
@XmlType(propOrder = {"className", "methodName", "maxRetries", "delay", "paramTypes"})
public class RetryMethod {


    public static class ParamType {
        private String type;
        private String name;
        private String annotation;
        @XmlElement
        public String getType() {
            return type;
        }
        public void setType(String type) {
            this.type = type;
        }
        @XmlElement
        public String getName() {
            return name;
        }
        public void setName(String name) {
            this.name = name;
        }
        @XmlElement
        public String getAnnotation() {
            return annotation;
        }
        public void setAnnotation(String annotation) {
            this.annotation = annotation;
        }
    }
    private String className;
    private String methodName;
    private int maxRetries;
    private long delay;
    private List<ParamType> paramTypes;

    private String returnType;

    private String accessModifier = "default";

    private List<String> otherModifiers;

    private List<String> annotations;

    @XmlElement
    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    @XmlElement
    public String getMethodName() {
        return methodName;
    }

    public void setMethodName(String methodName) {
        this.methodName = methodName;
    }

    @XmlElement
    public int getMaxRetries() {
        return maxRetries;
    }

    public void setMaxRetries(int maxRetries) {
        this.maxRetries = maxRetries;
    }

    @XmlElement
    public long getDelay() {
        return delay;
    }

    public void setDelay(long delay) {
        this.delay = delay;
    }

    @XmlElement
    public List<ParamType> getParamTypes() {
        return paramTypes;
    }

    public void setParamTypes(List<ParamType> paramTypes) {
        this.paramTypes = paramTypes;
    }

    @XmlElement
    public String getReturnType() {
        return returnType;
    }
    public void setReturnType(String returnType) {
        this.returnType = returnType;
    }

    @XmlElement
    public String getAccessModifier() {
        return accessModifier;
    }
    public void setAccessModifier(String accessModifier) {
        this.accessModifier = accessModifier;
    }

    @XmlElement
    public List<String> getOtherModifiers() {
        return otherModifiers;
    }
    public void setOtherModifiers(List<String> otherModifiers) {
        this.otherModifiers = otherModifiers;
    }

    @XmlElement
    public List<String> getAnnotations() {
        return annotations;
    }
    public void setAnnotations(List<String> annotations) {
        this.annotations = annotations;
    }
}

