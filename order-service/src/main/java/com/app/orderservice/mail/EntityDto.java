package com.app.orderservice.mail;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@Data
@RequiredArgsConstructor
public class EntityDto {

    private String entityId;

    private String body;

    private EntityAction action;

    private String entityType;

    private String date;

}

