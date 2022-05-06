package com.exadel.EmailSender.controller;

import com.exadel.EmailSender.dto.EmailDto;
import com.exadel.EmailSender.service.EmailSender;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/email/send")
public class EmailSenderController {

    private final EmailSender emailSender;

    public EmailSenderController(EmailSender emailSender) {
        this.emailSender = emailSender;
    }

    @PostMapping("/toAllUsers")
    public void sendEmailToAllUsers(@RequestBody EmailDto emailDto) {
        emailSender.sendEmailToAllUsers(emailDto.getSubject(), emailDto.getSubject());
    }

    @PostMapping("/toUser")
    public void sendEmailToUser(@RequestBody EmailDto emailDto) {
        emailSender.sendEmailToUser(emailDto.getUserId(), emailDto.getSubject(), emailDto.getSubject());
    }
}
