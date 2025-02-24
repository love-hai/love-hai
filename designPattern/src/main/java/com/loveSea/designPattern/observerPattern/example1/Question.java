package com.loveSea.designPattern.observerPattern.example1;

/**
 * @author xiahaifeng
 */

public class Question {
    private String userName;
    private String content;

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public void setContent(String content) {
        this.content = content;
    }


    public String getUserName() {
        return userName;
    }

    public String getContent() {
        return content;
    }
}