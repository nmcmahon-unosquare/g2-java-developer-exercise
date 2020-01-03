package com.niall.g2javadeveloperexercise.services.impl;

import com.niall.g2javadeveloperexercise.controllers.requests.RegisterAccountRequest;
import com.niall.g2javadeveloperexercise.dtos.AccountDto;
import com.niall.g2javadeveloperexercise.dtos.AccountRegisteredDto;
import com.niall.g2javadeveloperexercise.dtos.TransactionDto;
import com.niall.g2javadeveloperexercise.entities.Account;
import com.niall.g2javadeveloperexercise.entities.Transaction;
import com.niall.g2javadeveloperexercise.exception.BusinessRulesException;
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

@Service
public class AccountServiceImpl extends DataService implements AccountService {

    @Autowired
    private AccountRepository accountRepository;
    @Autowired
    private TransactionRepository transactionRepository;

    @Override
    public AccountDto getAccountByAccountNumber(int accountNumber) {
        Account account = getByAccountNumber(accountNumber);
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

    @Override
    public void closeAccount(int accountNumber) {
        Account account = getByAccountNumber(accountNumber);
        accountRepository.delete(account);
    }

    @Override
    public void addToAccountBalance(int accountNumber, double amount) {
        Account account = getByAccountNumber(accountNumber);
        double newBalance = account.getBalance().doubleValue() + amount;
        if(newBalance < 0) {
            throw new BusinessRulesException(String.format("Cannot change account %s balance from £%.2f to £%.2f, resulting change would overdraw account", account.getAccountNumber(), account.getBalance().doubleValue(), newBalance));
        }
        account.setBalance(new BigDecimal(newBalance));
        accountRepository.save(account);
    }

    private Account registerNewAccount(Account newAccount) {
        try {
            return accountRepository.save(newAccount);
        } catch (DataIntegrityViolationException ex) {
            throw new RegistrationException("Could not register new account. Check holder first name, last name and ID number are present in request.", AccountService.class);
        }
    }

    private List<TransactionDto> getRecentTransactions(int accountNumber) {
        List<Transaction> recentTransactions = transactionRepository.getRecentTransactionsForAccount(accountNumber, 5);
        Type listOfTransactions = new TypeToken<List<TransactionDto>>() {}.getType();
        return mapper.map(recentTransactions, listOfTransactions);
    }

    private Account getByAccountNumber(int accountNumber) {
        return accountRepository.findByAccountNumber(accountNumber)
                .orElseThrow(() -> new DataNotPresentException(String.format("Account with account number %s not found", accountNumber), AccountServiceImpl.class));
    }
}
