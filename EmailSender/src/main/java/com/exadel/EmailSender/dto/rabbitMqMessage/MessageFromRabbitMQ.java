package com.exadel.EmailSender.dto.rabbitMqMessage;


import com.exadel.EmailSender.dto.EmailDto;
import lombok.ToString;

@ToString
public class MessageFromRabbitMQ {
    private Boolean isToUser;

    private EmailDto emailDto;

    public MessageFromRabbitMQ() {
    }

    public MessageFromRabbitMQ(Boolean isToUser, EmailDto emailDto) {
        this.isToUser = isToUser;
        this.emailDto = emailDto;
    }

    public Boolean getToUser() {
        return isToUser;
    }

    public void setToUser(Boolean toUser) {
        isToUser = toUser;
    }

    public EmailDto getEmailDto() {
        return emailDto;
    }

    public void setEmailDto(EmailDto emailDto) {
        this.emailDto = emailDto;
    }
}
