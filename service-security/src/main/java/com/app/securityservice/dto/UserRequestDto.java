package com.app.securityservice.dto;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserRequestDto {

    private String username;

    private String email;

    private Double rating;

    private String password;

    private List<String> roles;

    private Boolean active;

}
