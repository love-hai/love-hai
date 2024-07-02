package com.LoveSea.fengCore.retryable;

/**
 * @author xiahaifeng
 */


import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@XmlRootElement(name = "retryMethods")
public class RetryMethodsWrapper {
    private List<RetryMethod> retryMethods;

    @XmlElement(name = "retryMethod")
    public List<RetryMethod> getRetryMethods() {
        return retryMethods;
    }

    public void setRetryMethods(List<RetryMethod> retryMethods) {
        this.retryMethods = retryMethods;
    }
}

