package com.exadel.EmailSender.listener;

import com.exadel.EmailSender.dto.EmailDto;
import com.exadel.EmailSender.dto.rabbitMqMessage.MessageFromRabbitMQ;
import com.exadel.EmailSender.service.EmailSender;
import com.exadel.EmailSender.service.config.MQConfig;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class MessageListener {

    private final EmailSender emailSender;

    public MessageListener(EmailSender emailSender) {
        this.emailSender = emailSender;
    }

    @RabbitListener(queues = MQConfig.QUEUE)
    public void listener(MessageFromRabbitMQ messageFromRabbitMQ) {
        EmailDto emailDto = messageFromRabbitMQ.getEmailDto();
        if (messageFromRabbitMQ.getToUser()) {
            emailSender.sendEmailToUser(emailDto);
        } else {
            emailSender.sendEmailToAllUsers(emailDto);
        }
    }
}
