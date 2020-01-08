package com.niall.bankserver.services.impl;

import com.niall.bankserver.dtos.AccountDto;
import com.niall.bankserver.entities.Account;
import com.niall.bankserver.exception.AuthenticationException;
import com.niall.bankserver.repository.AccountRepository;
import com.niall.bankserver.services.AuthenticationService;
import com.niall.bankserver.services.DataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AuthenticationServiceImpl extends DataService implements AuthenticationService {

    @Autowired
    private AccountRepository accountRepository;

    @Override
    public AccountDto signIn(Integer accountNumber, String accountPin) {
        Optional<Account> account = accountRepository.findByAccountNumber(accountNumber);
        if(account.isPresent() && account.get().getPin().equals(accountPin)) {
            return mapper.map(account.get(), AccountDto.class);
        }
        throw new AuthenticationException("Could not sign in. Account details incorrect");
    }
}
