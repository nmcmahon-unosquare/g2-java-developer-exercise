package com.niall.g2javadeveloperexercise.services;

import com.niall.g2javadeveloperexercise.controllers.requests.RegisterAccountRequest;
import com.niall.g2javadeveloperexercise.dtos.AccountDto;
import com.niall.g2javadeveloperexercise.dtos.AccountRegisteredDto;

public interface AccountService {

    AccountDto getAccountByAccountNumber(String accountNumber);

    AccountRegisteredDto createNewAccount(RegisterAccountRequest request);
}
