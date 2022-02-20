package org.app.soft.messageservice.domain;


import lombok.Data;
import lombok.NoArgsConstructor;
import org.app.soft.messageservice.model.Status;
import org.app.soft.messageservice.service.Template;

import java.util.HashMap;

@Data
@NoArgsConstructor
public class MailData {

    private String email;

    private Template template;

    private String subject;

    private Status status;

    private HashMap<String, Object> mailData;

}
