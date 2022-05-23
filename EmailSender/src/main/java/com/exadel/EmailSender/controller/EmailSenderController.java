package com.exadel.EmailSender.controller;

import com.exadel.EmailSender.dto.EmailDto;
import com.exadel.EmailSender.service.EmailCRUD;
import com.exadel.EmailSender.service.EmailSender;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/email/send")
public class EmailSenderController {

    private final EmailSender emailSender;
    private final EmailCRUD emailCRUD;
    private final static Logger log = LogManager.getLogger(EmailSenderController.class);

    public EmailSenderController(EmailSender emailSender, EmailCRUD emailCRUD) {
        this.emailSender = emailSender;
        this.emailCRUD = emailCRUD;
    }

    @PostMapping("/toAllUsers")
    public void sendEmailToAllUsers(@RequestBody EmailDto emailDto) {
        log.info("Send email to all users");
        emailSender.sendEmailToAllUsers(emailDto);
    }

    @PostMapping("/toUser")
    public void sendEmailToUser(@RequestBody EmailDto emailDto) {
        log.info("Send email to user with id: " + emailDto.getUserId());
        emailSender.sendEmailToUser(emailDto);
    }

    @GetMapping("/getAll")
    public ResponseEntity<List<EmailDto>> getAll() {
        log.info("get all emails");
        return ResponseEntity.ok(emailCRUD.getAllEmails());
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<EmailDto> get(@PathVariable("id") Long id) {
        log.info("get email by id: " + id);
        return ResponseEntity.ok(emailCRUD.getEmail(id));
    }

    @DeleteMapping("/delete/{id}")
    public void delete(@PathVariable("id") Long id) {
        log.info("delete email by id: " + id);
        emailCRUD.deleteEmail(id);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<EmailDto> update(@PathVariable("id") Long id, @RequestBody EmailDto emailDto) {
        log.info("update email: " + emailDto);
        return ResponseEntity.ok(emailCRUD.updateEmail(id, emailDto));
    }
}
