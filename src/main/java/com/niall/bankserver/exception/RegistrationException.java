package com.niall.bankserver.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class RegistrationException extends LoggingException {

    public RegistrationException(String message) {
        super(message);
    }

    public RegistrationException(String message, Class errorClass) {
        super(message, errorClass);
    }

}
