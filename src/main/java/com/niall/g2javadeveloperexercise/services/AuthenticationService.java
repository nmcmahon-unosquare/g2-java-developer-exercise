package com.niall.g2javadeveloperexercise.services;

import com.niall.g2javadeveloperexercise.dtos.AccountDto;

public interface AuthenticationService {

    AccountDto signIn(Integer accountNumber, String accountPin);

}
