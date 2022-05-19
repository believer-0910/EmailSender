package com.exadel.EmailSender.service;

import com.exadel.EmailSender.dto.UserDto;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.internal.LogManagerStatus;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Service
public class EmailSender {
    private final EmailCRUD emailCRUD;
    private final UserService userService;
    private final JavaMailSender javaMailSender;
    private static final Logger log = LogManager.getLogger(EmailSender.class);

    public EmailSender(EmailCRUD emailCRUD, UserService userService, JavaMailSender javaMailSender) {
        this.emailCRUD = emailCRUD;
        this.userService = userService;
        this.javaMailSender = javaMailSender;
    }

    public void sendEmailToAllUsers(String subject, String text) {
        log.info("Send mail all users");
        List<UserDto> users = userService.getAllUsers();
        ExecutorService executorService = Executors.newFixedThreadPool(10);
        executorService.submit(() -> {
            for (UserDto user : users) {
                javaMailSender.send(emailCRUD.makeEmail(user.getEmail(), subject, text));
            }
        });
    }

    public void sendEmailToUser(Long id, String subject, String text) {
        log.info("Send to the user with id: " + id);
        UserDto user = userService.getUser(id);
        javaMailSender.send(emailCRUD.makeEmail(user.getEmail(), subject, text));
    }
}
