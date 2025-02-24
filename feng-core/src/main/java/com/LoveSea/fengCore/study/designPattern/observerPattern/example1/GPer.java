package com.LoveSea.fengCore.study.designPattern.observerPattern.example1;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

/**
 * @author xiahaifeng
 */

public class GPer {
    private String name = "GPer生态圈";
    private PropertyChangeSupport support;
    private static GPer gPer = null;

    private GPer() {
        support = new PropertyChangeSupport(this);
    }

    public static GPer getInstance() {
        if (gPer == null) {
            gPer = new GPer();
        }
        return gPer;
    }

    public String getName() {
        return name;
    }

    public void addPropertyChangeListener(PropertyChangeListener pcl) {
        support.addPropertyChangeListener(pcl);
    }

    public void removePropertyChangeListener(PropertyChangeListener pcl) {
        support.removePropertyChangeListener(pcl);
    }

    public void publishQuestion(Question question) {
        System.out.println(question.getUserName() + "在" + this.name + "上提交了一个问题。");
        System.out.println("问题内容是：" + question.getContent());
        support.firePropertyChange("question", null, question);
    }
}