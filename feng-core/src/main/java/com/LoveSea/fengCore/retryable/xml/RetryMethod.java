package com.LoveSea.fengCore.retryable.xml;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import java.util.List;
import java.util.Set;

/**
 * @author xiahaifeng
 */


@XmlRootElement(name = "retryMethod")
@XmlType(propOrder = {"className", "methodName", "maxRetries", "delay", "paramTypes", "returnType", "accessModifier", "otherModifiers", "annotations"})
public class RetryMethod {
    public static Set<String> accessModifierSet = Set.of("public", "protected", "private", "default");

    private String className;
    private String methodName;
    private int maxRetries;
    private long delay;
    private List<ParamType> paramTypes;

    private String returnType;

    private String accessModifier = "default";

    private Set<String> otherModifiers;

    private List<Annotation> annotations;

    @XmlElement(name = "className")
    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    @XmlElement(name = "methodName")
    public String getMethodName() {
        return methodName;
    }

    public void setMethodName(String methodName) {
        this.methodName = methodName;
    }

    @XmlElement(name = "maxRetries")
    public int getMaxRetries() {
        return maxRetries;
    }

    public void setMaxRetries(int maxRetries) {
        this.maxRetries = maxRetries;
    }

    @XmlElement(name = "delay")
    public long getDelay() {
        return delay;
    }

    public void setDelay(long delay) {
        this.delay = delay;
    }

    @XmlElement(name = "paramType")
    public List<ParamType> getParamTypes() {
        return paramTypes;
    }

    public void setParamTypes(List<ParamType> paramTypes) {
        this.paramTypes = paramTypes;
    }

    @XmlElement(name = "returnType")
    public String getReturnType() {
        return returnType;
    }

    public void setReturnType(String returnType) {
        this.returnType = returnType;
    }

    @XmlElement(name = "accessModifier")
    public String getAccessModifier() {
        return accessModifier;
    }

    public void setAccessModifier(String accessModifier) {
        this.accessModifier = accessModifier;
    }

    @XmlElement(name = "otherModifier")
    public Set<String> getOtherModifiers() {
        return otherModifiers;
    }

    public void setOtherModifiers(Set<String> otherModifiers) {
        this.otherModifiers = otherModifiers;
    }

    @XmlElement(name = "annotation")
    public List<Annotation> getAnnotations() {
        return annotations;
    }

    public void setAnnotations(List<Annotation> annotations) {
        this.annotations = annotations;
    }
}

