package org.app.soft.messageservice.jms;


import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.RequiredArgsConstructor;
import org.app.soft.messageservice.domain.MailDataDto;
import org.app.soft.messageservice.service.EMailSender;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import javax.mail.MessagingException;

@Component
@RequiredArgsConstructor
public class RabbitMqConsumer {

    private final EMailSender senderMail;

    Logger logger = LoggerFactory.getLogger(RabbitMqConsumer.class);

    @RabbitListener(queues = "${solbeg.rabbitmq.RabbitListener.queue}")
    public void receive(MailDataDto mailData) throws MessagingException, JsonProcessingException {
        if (mailData != null) {

            senderMail.sendEmail(mailData);
            logger.debug("order : " + mailData + " was sent successfully");
        }
    }
}
