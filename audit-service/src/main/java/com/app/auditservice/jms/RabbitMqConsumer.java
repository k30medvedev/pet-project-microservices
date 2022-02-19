package com.app.auditservice.jms;

import com.app.auditservice.domain.RecordRequestDto;
import com.app.auditservice.service.RecordService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Slf4j
public class RabbitMqConsumer {

    private final RecordService service;

    @RabbitListener(queues = "entities.archive.audit-service")
    public void receive(RecordRequestDto receiveData) {
        if (receiveData != null) {
            service.addRecordInDb(receiveData);
            log.debug("queueData : " + receiveData + " saved successfully");
        }

    }
}

