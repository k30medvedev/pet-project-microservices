package com.app.orderservice.jms;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.messaging.MessagingException;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class RabbitMqConsumer {


//    @RabbitListener(queues = "${solbeg.rabbitmq.RabbitListener.queue}")
//    public void receive(MailDtoRequest mailData) throws MessagingException {
//        if (mailData != null) {
//            senderMail.sendEmail(mailData);
//            logger.debug("order : " + mailData + " was sent successfully");
//        }
//    }
}
