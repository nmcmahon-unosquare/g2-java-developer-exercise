package com.niall.bankserver.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public abstract class LoggingException extends RuntimeException {

    private Logger logger;

    public LoggingException(String message) {
        super(message);
        logger = LoggerFactory.getLogger(this.getClass());
        logger.error(message);
    }

    public LoggingException(String message, Class errorClass) {
        super(message);
        logger = LoggerFactory.getLogger(errorClass);
        logger.error(message);
    }
}
