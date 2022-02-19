package com.app.orderservice.jms;

import com.app.orderservice.domain.OrderResponseDto;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.app.orderservice.mail.EntityAction;
import com.app.orderservice.mail.EntityDto;
import com.app.orderservice.model.Order;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
@Slf4j
public class RabbitMqProducer {

    private final RabbitTemplate rabbitTemplate;
    private final ObjectMapper mapper;

    public void sendOrder(Order order) {
        rabbitTemplate.convertAndSend("topic.mails", "orders.send.finder-service", order);
        log.info("RabbitMqProducer => send order to finder-service: " + order);
    }

    public void sendEntity(OrderResponseDto order, EntityAction entityAction) throws JsonProcessingException {

        EntityDto entityDtoToSend = new EntityDto();
        String json = mapper.writeValueAsString(order);
        LocalDateTime localDateTime = LocalDateTime.now();
        entityDtoToSend.setEntityId(order.getId());
        entityDtoToSend.setEntityType(String.valueOf(order.getClass()));
        entityDtoToSend.setBody(json);
        entityDtoToSend.setAction(entityAction);
        entityDtoToSend.setDate(localDateTime.toString());

        rabbitTemplate.convertAndSend("topic.mails", "entities.archive.audit-service", entityDtoToSend);
        log.info("RabbitMqProducer => send to finder-service" + entityDtoToSend);
    }

}
