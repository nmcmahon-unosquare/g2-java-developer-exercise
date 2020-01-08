package com.niall.bankclient.exception;


import lombok.Getter;

@Getter
public class HttpRequestUnsuccessfulException extends RuntimeException {
    private int statusCode;

    public HttpRequestUnsuccessfulException(int statusCode, String message) {
        super(message);
        this.statusCode = statusCode;
    }
}
