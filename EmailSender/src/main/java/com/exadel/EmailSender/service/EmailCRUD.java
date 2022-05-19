package com.exadel.EmailSender.service;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Service;

@Service
public class EmailCRUD {

    @Value("${spring.mail.username}")
    private String fromEmail;
    private final static Logger log = LogManager.getLogger(EmailCRUD.class);
    public SimpleMailMessage makeEmail(String email, String subject, String text) {
        log.info("Email made");
        SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
        simpleMailMessage.setFrom(fromEmail);
        simpleMailMessage.setTo(email);
        simpleMailMessage.setSubject(subject);
        simpleMailMessage.setText(text);
        return simpleMailMessage;
    }
}
