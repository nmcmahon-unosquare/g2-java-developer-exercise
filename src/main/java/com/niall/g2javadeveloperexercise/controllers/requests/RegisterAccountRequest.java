package com.niall.g2javadeveloperexercise.controllers.requests;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RegisterAccountRequest {

    private String holderFirstName;
    private String holderLastName;
    private String holderIdNumber;

}
