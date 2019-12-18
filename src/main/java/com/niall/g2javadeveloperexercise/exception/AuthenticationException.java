package com.niall.g2javadeveloperexercise.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class AuthenticationException extends LoggingException {

    public AuthenticationException(String message) {
        super(message);
    }

    public AuthenticationException(String message, Class errorClass) {
        super(message, errorClass);
    }
}
