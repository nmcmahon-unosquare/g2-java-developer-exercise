package com.niall.g2javadeveloperexercise.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.CONFLICT)
public class BusinessRulesException extends LoggingException {

    public BusinessRulesException(String message) {
        super(message);
    }

    public BusinessRulesException(String message, Class errorClass) {
        super(message, errorClass);
    }

}
