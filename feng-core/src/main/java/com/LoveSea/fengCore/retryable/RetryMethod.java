package com.LoveSea.fengCore.retryable;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import java.util.List;

/**
 * @author xiahaifeng
 */


@XmlRootElement(name = "retryMethod")
@XmlType(propOrder = {"className", "methodName", "maxRetries", "delay", "parameters"})
public class RetryMethod {
    private String className;
    private String methodName;
    private int maxRetries;
    private long delay;
    private List<String> parameters;

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
    public List<String> getParameters() {
        return parameters;
    }

    public void setParameters(List<String> parameters) {
        this.parameters = parameters;
    }
}

