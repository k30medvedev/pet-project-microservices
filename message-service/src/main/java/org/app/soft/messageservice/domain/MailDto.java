package org.app.soft.messageservice.domain;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.app.soft.messageservice.model.Status;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

@Data
@RequiredArgsConstructor
public class MailDto {

    @NotBlank(message = "Id must not be empty")
    private String id;

    @NotBlank(message = "Email must not be empty")
    @Email
    private String mailFrom;

    @NotBlank(message = "Email must not be empty")
    @Email
    private String mailTo;

    private String body;

    @NotBlank(message = "Status must not be empty")
    private Status status;

    private String error;


}
