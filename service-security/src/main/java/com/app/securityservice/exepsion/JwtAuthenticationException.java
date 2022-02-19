package com.app.securityservice.exepsion;

import javax.naming.AuthenticationException;

import lombok.Getter;

import org.springframework.http.HttpStatus;

@Getter
public class JwtAuthenticationException extends AuthenticationException {

    private HttpStatus httpStatus;

    public JwtAuthenticationException(String explanation, HttpStatus httpStatus) {
        super(explanation);
        this.httpStatus = httpStatus;
    }



}
