package com.exadel.EmailSender.dto;

public class EmailDto {
    private Long userId;
    private String subject;
    private String text;

    public EmailDto(Long userId, String subject, String text) {
        this.userId = userId;
        this.subject = subject;
        this.text = text;
    }

    public EmailDto() {
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}