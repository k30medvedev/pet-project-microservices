package com.app.auditservice.domain;

import com.app.auditservice.model.EntityAction;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import javax.validation.constraints.NotBlank;

@Data
@RequiredArgsConstructor
public class RecordRequestDto {

    @NotBlank
    private String entityId;

    @NotBlank
    private String body;

    @NotBlank
    private EntityAction action;

    @NotBlank
    private String entityType;

    @NotBlank
    private String date;

}
