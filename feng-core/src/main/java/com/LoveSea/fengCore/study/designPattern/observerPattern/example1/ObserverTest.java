package com.LoveSea.fengCore.study.designPattern.observerPattern.example1;

/**
 * @author xiahaifeng
 */

public class ObserverTest {
    public static void main(String[] args) {
        GPer gPer = GPer.getInstance();
        Teacher teacher1 = new Teacher("Tom");
        Teacher teacher2 = new Teacher("Mic");

        gPer.addPropertyChangeListener(teacher1);
        gPer.addPropertyChangeListener(teacher2);

        Question question = new Question();
        question.setUserName("小夏");
        question.setContent("观察者模式适用于哪些场景？");
        gPer.publishQuestion(question);
    }
}