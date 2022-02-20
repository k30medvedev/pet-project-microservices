package org.app.soft.messageservice.domain;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class MailSendDto {

    private String id;

    private String jsonObject;

    private TypeObj typeObj;

}

