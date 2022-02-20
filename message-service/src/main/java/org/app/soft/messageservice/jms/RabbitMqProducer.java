package org.app.soft.messageservice.jms;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.app.soft.messageservice.domain.MailSendDto;
import org.app.soft.messageservice.domain.TypeObj;
import org.app.soft.messageservice.model.Mail;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RabbitMqProducer {

    Logger logger = LoggerFactory.getLogger(RabbitMqProducer.class);

    private final ObjectMapper mapper;
    private final RabbitTemplate rabbitTemplate;

    public void send(Mail mailRequest) throws JsonProcessingException {

        MailSendDto mailDtoSend = new MailSendDto();
        mailDtoSend.setId(mailRequest.getId());

        String jsonObject = mapper.writeValueAsString(mailRequest);

        mailDtoSend.setJsonObject(jsonObject);
        mailDtoSend.setTypeObj(TypeObj.MAIL);
        rabbitTemplate.convertAndSend("topic.mails", "archive.audit-service", mailDtoSend);

        logger.debug("Logger logger = LoggerFactory.getLogger(Producer.class): " + mailDtoSend);

    }

}
