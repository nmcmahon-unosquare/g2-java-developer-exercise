package com.niall.g2javadeveloperexercise.services.impl;

import com.niall.g2javadeveloperexercise.dtos.AccountDto;
import com.niall.g2javadeveloperexercise.entities.Account;
import com.niall.g2javadeveloperexercise.exception.AuthenticationException;
import com.niall.g2javadeveloperexercise.repository.AccountRepository;
import com.niall.g2javadeveloperexercise.services.AuthenticationService;
import com.niall.g2javadeveloperexercise.services.DataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AuthenticationServiceImpl extends DataService implements AuthenticationService {

    @Autowired
    private AccountRepository accountRepository;

    @Override
    public AccountDto signIn(int accountNumber, String accountPin) {
        Optional<Account> account = accountRepository.findByAccountNumber(accountNumber);
        if(account.isPresent() && account.get().getPin().equals(accountPin)) {
            return mapper.map(account.get(), AccountDto.class);
        }
        throw new AuthenticationException("Could not sign in. Account details incorrect");
    }
}
