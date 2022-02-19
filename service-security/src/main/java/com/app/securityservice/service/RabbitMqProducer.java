package com.app.securityservice.service;

import java.util.HashMap;

import com.app.securityservice.dto.MailDto;
import lombok.RequiredArgsConstructor;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RabbitMqProducer {

    Logger logger = LoggerFactory.getLogger(RabbitMqProducer.class);
    private final RabbitTemplate rabbitTemplate;
    // private final ValidationService validationService;

    public void sent(MailDto mailToSend) {
        HashMap<String, Object> contentMap = new HashMap<>();
        contentMap.put("userName", mailToSend.getUserName());
        mailToSend.setMailData(contentMap);
        rabbitTemplate.convertAndSend("topic.mails", "mails", mailToSend);
        logger.info("Logger logger = LoggerFactory.getLogger(Producer.class): " + mailToSend);
    }

    public void sent2(String mailToSend) {
        /*HashMap<String, Object> contentMap = new HashMap<>();
        contentMap.put("userName", mailToSend.getName());
        mailToSend.setMailData(contentMap);*/
        rabbitTemplate.convertAndSend("topic.mails", "mails", mailToSend);
        logger.info("Logger logger = LoggerFactory.getLogger(Producer.class): " + mailToSend);
    }
}
