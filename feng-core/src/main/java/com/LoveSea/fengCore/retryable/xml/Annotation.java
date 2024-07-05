package com.LoveSea.fengCore.retryable.xml;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;
import java.util.List;

/**
 * @author xiahaifeng
 */
@XmlType(propOrder = {"type", "annotationParameters"})
public class Annotation {
    private String type;
    private List<AnnotationParameter> annotationParameters;

    @XmlElement(name = "type")
    public String getType() {
        return type;
    }

    public void setType(String value) {
        this.type = value;
    }

    @XmlElement(name = "annotationParameter")
    public List<AnnotationParameter> getAnnotationParameters() {
        return annotationParameters;
    }

    public void setAnnotationParameters(List<AnnotationParameter> value) {
        this.annotationParameters = value;
    }
}
