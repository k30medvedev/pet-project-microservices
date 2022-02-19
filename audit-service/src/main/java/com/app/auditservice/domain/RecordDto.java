package com.app.auditservice.domain;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
public class RecordDto {

    private String id;

    private String entityId;

    private String body;

    private LocalDateTime date;

    private String entityAction;

    private String entityType;


}
