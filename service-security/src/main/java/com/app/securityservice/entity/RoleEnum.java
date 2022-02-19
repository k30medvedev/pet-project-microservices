package com.app.securityservice.entity;

import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.security.core.authority.SimpleGrantedAuthority;

public enum RoleEnum {

    ADMIN,
    DRIVER,
    OPERATOR;

}
