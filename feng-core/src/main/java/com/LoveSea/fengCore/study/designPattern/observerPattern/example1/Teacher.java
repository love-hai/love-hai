package com.LoveSea.fengCore.study.designPattern.observerPattern.example1;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

/**
 * @author xiahaifeng
 */
public class Teacher implements PropertyChangeListener {
    private String name;

    public Teacher(String name) {
        this.name = name;
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        String propertyName = evt.getPropertyName();
        if (!"question".equals(propertyName)) {
            return;
        }
        Question question = (Question) evt.getNewValue();
        GPer gper = (GPer) evt.getSource();
        System.out.println(name + "老师，你好！\n" +
                "您收到了一个来自“" + gper.getName() + "”的提问，希望您解答，问题内容如下：\n" +
                question.getContent() + "\n" +
                "提问者：" + question.getUserName());
    }
}