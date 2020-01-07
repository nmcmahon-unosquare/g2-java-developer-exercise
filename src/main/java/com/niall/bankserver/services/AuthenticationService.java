package com.niall.bankserver.services;

import com.niall.bankserver.dtos.AccountDto;

public interface AuthenticationService {

    AccountDto signIn(Integer accountNumber, String accountPin);

}
