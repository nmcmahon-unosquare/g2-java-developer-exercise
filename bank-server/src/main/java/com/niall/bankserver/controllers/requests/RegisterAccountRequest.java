package com.niall.bankserver.controllers.requests;

import com.niall.bankserver.enums.AccountType;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;

@Getter
@Setter
public class RegisterAccountRequest {

    @NotNull
    private String holderFirstName;
    @NotNull
    private String holderLastName;
    @NotNull
    private String holderIdNumber;

    private AccountType accountType = AccountType.PERSONAL_ACCOUNT;

}
