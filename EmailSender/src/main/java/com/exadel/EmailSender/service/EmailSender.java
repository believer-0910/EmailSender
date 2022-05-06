package com.exadel.EmailSender.service;

import com.exadel.EmailSender.dto.UserDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Service
public class EmailSender {
    @Autowired
    private EmailCRUD emailCRUD;

    @Autowired
    private UserService userService;

    @Autowired
    private JavaMailSender javaMailSender;

    public EmailSender() {
    }

    public void sendEmailAllUsers() {
        List<UserDto> users = userService.getAllUsers();
        ExecutorService executorService = Executors.newFixedThreadPool(10);
        executorService.submit(() -> {
            for (UserDto user : users) {
                javaMailSender.send(emailCRUD.makeEmail(user.getEmail(), "Hello", "Hello"));
            }
        });
    }

    public static void main(String[] args) {
        EmailSender emailSender = new EmailSender();
        emailSender.sendEmailAllUsers();
    }
}
