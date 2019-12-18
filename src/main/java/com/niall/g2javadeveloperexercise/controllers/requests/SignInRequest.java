package com.niall.g2javadeveloperexercise.controllers.requests;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SignInRequest {
    private String accountNumber;
    private String accountPin;
}
