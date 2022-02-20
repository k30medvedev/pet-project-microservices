package org.app.soft.messageservice.domain;


import lombok.Data;
import lombok.NoArgsConstructor;
import org.app.soft.messageservice.service.Template;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import java.util.HashMap;

@Data
@NoArgsConstructor
public class MailDataDto {

    //email to
    @NotBlank(message = "Email must not be empty")
    @Email
    private String email;

    @NotBlank(message = "Template must not be empty")
    private Template template;

    private String subject;

    private HashMap<String, Object> mailData;

}
