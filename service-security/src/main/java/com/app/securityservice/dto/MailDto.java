package com.app.securityservice.dto;

import java.util.HashMap;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

@Data
@NoArgsConstructor
public class MailDto {

    @NotBlank
    private String email;

    private String subject;

    @NotBlank
    private String userName;

    @NotBlank
    private Template template;

    private HashMap<String, Object> mailData;

    public enum Template {
        EMAIL_APPROVE
    }
}
