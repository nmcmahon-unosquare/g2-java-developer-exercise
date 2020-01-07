package com.niall.bankserver.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NO_CONTENT)
public class DataNotPresentException extends LoggingException {

    public DataNotPresentException(String message) {
        super(message);
    }

    public DataNotPresentException(String message, Class errorClass) {
        super(message, errorClass);
    }
}
