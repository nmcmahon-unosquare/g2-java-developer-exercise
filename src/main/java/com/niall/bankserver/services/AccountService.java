package com.niall.bankserver.services;

import com.niall.bankserver.controllers.requests.RegisterAccountRequest;
import com.niall.bankserver.dtos.AccountDto;
import com.niall.bankserver.dtos.AccountRegisteredDto;

public interface AccountService {

    AccountDto getAccountByAccountNumber(Integer accountNumber);

    AccountRegisteredDto createNewAccount(RegisterAccountRequest request);

    void closeAccount(Integer accountNumber);

    void addToAccountBalance(Integer accountNumber, double amount);
}
