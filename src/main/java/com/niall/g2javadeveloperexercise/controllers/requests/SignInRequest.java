package com.niall.g2javadeveloperexercise.controllers.requests;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;

@Getter
@Setter
public class SignInRequest {
    @NotNull
    private Integer accountNumber;
    @NotNull
    private String accountPin;
}
