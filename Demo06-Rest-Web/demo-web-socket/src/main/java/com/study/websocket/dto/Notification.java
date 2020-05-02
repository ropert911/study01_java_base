package com.study.websocket.dto;

/**
 * 通知消息
 */
public class Notification {
    private String message;

    public Notification(String content) {
        this.message = content;
    }

    public String getContent() {
        return message;
    }
}
