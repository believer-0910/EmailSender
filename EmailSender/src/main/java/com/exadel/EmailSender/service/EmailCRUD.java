package com.exadel.EmailSender.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Service;

@Service
public class EmailCRUD {

    @Value("${spring.mail.username}")
    private String fromEmail;

    public SimpleMailMessage makeEmail(String email, String subject, String text) {
        SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
        simpleMailMessage.setFrom(fromEmail);
        simpleMailMessage.setTo(email);
        simpleMailMessage.setSubject(subject);
        simpleMailMessage.setText(text);
        return simpleMailMessage;
    }
}
