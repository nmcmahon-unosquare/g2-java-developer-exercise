package com.niall.bankclient.requests;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
@AllArgsConstructor
public class SignInRequest {
    private Integer accountNumber;
    private String accountPin;
}
