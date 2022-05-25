package com.exadel.EmailSender.service;

import com.exadel.EmailSender.dto.EmailDto;
import com.exadel.EmailSender.entity.EmailEntity;
import com.exadel.EmailSender.repository.EmailRepository;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EmailService {

    @Value("${spring.mail.username}")
    private String fromEmail;
    private final static Logger log = LogManager.getLogger(EmailService.class);

    private final EmailRepository emailRepository;

    public EmailService(EmailRepository emailRepository) {
        this.emailRepository = emailRepository;
    }

    @CacheEvict(value = "makeEmail", allEntries = true)
    public SimpleMailMessage makeEmail(String email, String subject, String text) {
        log.info("Email made");
        SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
        simpleMailMessage.setFrom(fromEmail);
        simpleMailMessage.setTo(email);
        simpleMailMessage.setSubject(subject);
        simpleMailMessage.setText(text);
        return simpleMailMessage;
    }

    @CacheEvict(value = "saveEmail", allEntries = true)
    public void saveEmail(EmailDto emailDto) {
        log.info("Email saved");
        emailRepository.save(mapEmailDtoToEmailEntity(new EmailEntity(), emailDto));
    }

    @CacheEvict(value = "deleteEmail", allEntries = true)
    public void deleteEmail(Long id) {
        log.info("Email deleted");
        emailRepository.deleteById(id);
    }

    @CacheEvict(value = "getEmail", allEntries = true)
    public EmailDto getEmail(Long id) {
        log.info("Email get");
        return mapEmailEntityToEmailDto(emailRepository.getById(id));
    }

    @CacheEvict(value = "getAllEmails", allEntries = true)
    public List<EmailDto> getAllEmails() {
        log.info("All emails get");
        return emailRepository.findAll().stream()
                .map(this::mapEmailEntityToEmailDto).collect(java.util.stream.Collectors.toList());
    }

    @CacheEvict(value = "updateEmail", allEntries = true)
    public EmailDto updateEmail(Long id, EmailDto emailDto) {
        log.info("Email updated");
        Optional<EmailEntity> emailEntity = emailRepository.findById(id);
        if (emailEntity.isPresent()) {
            EmailEntity email = emailEntity.get();
            EmailEntity savedEmail = emailRepository.save(mapEmailDtoToEmailEntity(email, emailDto));
            return mapEmailEntityToEmailDto(savedEmail);
        }
        return null;
    }

    @CacheEvict(value = "mapEmailDtoToEmailEntity", allEntries = true)
    private EmailEntity mapEmailDtoToEmailEntity(EmailEntity emailEntity, EmailDto emailDto) {
        emailEntity.setUserId(emailDto.getUserId());
        emailEntity.setSubject(emailDto.getSubject());
        emailEntity.setText(emailDto.getText());
        return emailEntity;
    }

    @CacheEvict(value = "mapEmailEntityToEmailDto", allEntries = true)
    private EmailDto mapEmailEntityToEmailDto(EmailEntity emailEntity) {
        return new EmailDto(emailEntity.getUserId(), emailEntity.getSubject(), emailEntity.getText());
    }

}
