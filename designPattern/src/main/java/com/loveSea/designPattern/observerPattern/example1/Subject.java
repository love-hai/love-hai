package com.loveSea.designPattern.observerPattern.example1;


import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

/**
 * @author xiahaifeng
 */
public class Subject {
    private String property;
    private PropertyChangeSupport support;

    public Subject() {
        support = new PropertyChangeSupport(this);
    }

    public void addPropertyChangeListener(PropertyChangeListener pcl) {
        support.addPropertyChangeListener(pcl);
    }

    public void removePropertyChangeListener(PropertyChangeListener pcl) {
        support.removePropertyChangeListener(pcl);
    }

    public void setProperty(String value) {
        String oldValue = this.property;
        this.property = value;
        support.firePropertyChange("property", oldValue, value);
    }
}