package com.niall.bankclient.requests;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RegisterAccountRequest {

    private String holderFirstName;
    private String holderLastName;
    private String holderIdNumber;
    private String accountType = "PERSONAL_ACCOUNT";

}
