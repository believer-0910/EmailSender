package com.exadel.EmailSender.service;

import com.exadel.EmailSender.dto.EmailDto;
import com.exadel.EmailSender.entity.EmailEntity;
import com.exadel.EmailSender.repository.EmailRepository;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EmailService {

    @Value("${spring.mail.username}")
    private String fromEmail;
    private static final Logger log = LogManager.getLogger(EmailService.class);

    private final EmailRepository emailRepository;

    public EmailService(EmailRepository emailRepository) {
        this.emailRepository = emailRepository;
    }

    public SimpleMailMessage makeEmail(String email, String subject, String text) {
        log.info("Email made");
        SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
        simpleMailMessage.setFrom(fromEmail);
        simpleMailMessage.setTo(email);
        simpleMailMessage.setSubject(subject);
        simpleMailMessage.setText(text);
        return simpleMailMessage;
    }

    public void saveEmail(EmailDto emailDto) {
        log.info("Email saved");
        emailRepository.save(mapEmailDtoToEmailEntity(new EmailEntity(), emailDto));
    }

    public void deleteEmail(Long id) {
        log.info("Email deleted");
        emailRepository.deleteById(id);
    }

    public EmailDto getEmail(Long id) {
        log.info("Email get");
        return mapEmailEntityToEmailDto(emailRepository.getById(id));
    }

    public List<EmailDto> getAllEmails() {
        log.info("All emails get");
        return emailRepository.findAll().stream()
                .map(this::mapEmailEntityToEmailDto).collect(java.util.stream.Collectors.toList());
    }

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

    private EmailEntity mapEmailDtoToEmailEntity(EmailEntity emailEntity, EmailDto emailDto) {
        emailEntity.setUserId(emailDto.getUserId());
        emailEntity.setSubject(emailDto.getSubject());
        emailEntity.setText(emailDto.getText());
        return emailEntity;
    }

    private EmailDto mapEmailEntityToEmailDto(EmailEntity emailEntity) {
        return new EmailDto(emailEntity.getUserId(), emailEntity.getSubject(), emailEntity.getText());
    }

}
