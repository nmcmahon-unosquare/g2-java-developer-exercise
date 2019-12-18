package com.niall.g2javadeveloperexercise.services.impl;

import com.niall.g2javadeveloperexercise.controllers.requests.RegisterAccountRequest;
import com.niall.g2javadeveloperexercise.dtos.AccountDto;
import com.niall.g2javadeveloperexercise.dtos.AccountRegisteredDto;
import com.niall.g2javadeveloperexercise.dtos.TransactionDto;
import com.niall.g2javadeveloperexercise.entities.Account;
import com.niall.g2javadeveloperexercise.entities.Transaction;
import com.niall.g2javadeveloperexercise.exception.DataNotPresentException;
import com.niall.g2javadeveloperexercise.exception.RegistrationException;
import com.niall.g2javadeveloperexercise.repository.AccountRepository;
import com.niall.g2javadeveloperexercise.repository.TransactionRepository;
import com.niall.g2javadeveloperexercise.services.AccountService;
import com.niall.g2javadeveloperexercise.services.DataService;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.lang.reflect.Type;
import java.math.BigDecimal;
import java.util.List;
import java.util.Random;

@Service
public class AccountServiceImpl extends DataService implements AccountService {

    @Autowired
    private AccountRepository accountRepository;
    @Autowired
    private TransactionRepository transactionRepository;

    @Override
    public AccountDto getAccountByAccountNumber(String accountNumber) {
        Account account = accountRepository.findByAccountNumber(accountNumber)
                .orElseThrow(() -> new DataNotPresentException(String.format("Account with account number %s not found", accountNumber), AccountServiceImpl.class));;
        List<TransactionDto> recentTransactions = getRecentTransactions(accountNumber);
        AccountDto accountDto = mapper.map(account, AccountDto.class);
        accountDto.setRecentTransactions(recentTransactions);
        return accountDto;
    }

    @Override
    public AccountRegisteredDto createNewAccount(RegisterAccountRequest request) {
        Account newAccount = mapper.map(request, Account.class);
        Account createdAccount = registerNewAccount(newAccount);
        return mapper.map(createdAccount, AccountRegisteredDto.class);

    }

    private Account registerNewAccount(Account newAccount) {
        try {
            return accountRepository.save(newAccount);
        } catch (DataIntegrityViolationException ex) {
            throw new RegistrationException("Could not register new account. Check holder first name, last name and ID number are present in request.", AccountService.class);
        }
    }

    private List<TransactionDto> getRecentTransactions(String accountNumber) {
        List<Transaction> recentTransactions = transactionRepository.getRecentTransactionsForAccount(accountNumber, 5);
        Type listOfTransactions = new TypeToken<List<TransactionDto>>() {}.getType();
        return mapper.map(recentTransactions, listOfTransactions);
    }
}
