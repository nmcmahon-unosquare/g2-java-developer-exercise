package com.niall.g2javadeveloperexercise.controllers.requests;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SignInRequest {
    private int accountNumber;
    private String accountPin;
}
