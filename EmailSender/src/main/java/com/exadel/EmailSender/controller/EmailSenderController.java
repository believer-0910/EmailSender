package com.exadel.EmailSender.controller;

import  com.exadel.EmailSender.dto.EmailDto;
import com.exadel.EmailSender.service.EmailSender;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/email/send")
public class EmailSenderController {

    private final EmailSender emailSender;
    private final static Logger log = LogManager.getLogger(EmailSenderController.class);

    public EmailSenderController(EmailSender emailSender) {
        this.emailSender = emailSender;
    }

    @PostMapping("/toAllUsers")
    public void sendEmailToAllUsers(@RequestBody EmailDto emailDto) {
        log.info("Send email to all users");
        emailSender.sendEmailToAllUsers(emailDto.getSubject(), emailDto.getSubject());
    }

    @PostMapping("/toUser")
    public void sendEmailToUser(@RequestBody EmailDto emailDto) {
        log.info("Send email to user with id: " + emailDto.getUserId());
        emailSender.sendEmailToUser(emailDto.getUserId(), emailDto.getSubject(), emailDto.getSubject());
    }
}
