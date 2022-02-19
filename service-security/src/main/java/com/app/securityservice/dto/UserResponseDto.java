package com.app.securityservice.dto;

import java.time.Instant;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import com.app.securityservice.entity.Role;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserResponseDto {

    private String id;

    private String username;

    private String email;

    private List<Role> roles;

    private Double rating;

    private Instant createdAt;

}
