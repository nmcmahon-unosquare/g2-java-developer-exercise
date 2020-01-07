package com.niall.bankserver.services.impl;

import com.niall.bankserver.controllers.requests.RegisterAccountRequest;
import com.niall.bankserver.dtos.AccountDto;
import com.niall.bankserver.dtos.AccountRegisteredDto;
import com.niall.bankserver.dtos.TransactionDto;
import com.niall.bankserver.entities.Account;
import com.niall.bankserver.entities.Transaction;
import com.niall.bankserver.exception.BusinessRulesException;
import com.niall.bankserver.exception.DataNotPresentException;
import com.niall.bankserver.exception.RegistrationException;
import com.niall.bankserver.repository.AccountRepository;
import com.niall.bankserver.repository.TransactionRepository;
import com.niall.bankserver.services.AccountService;
import com.niall.bankserver.services.DataService;
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
    public AccountDto getAccountByAccountNumber(Integer accountNumber) {
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
    public void closeAccount(Integer accountNumber) {
        Account account = getByAccountNumber(accountNumber);
        accountRepository.delete(account);
    }

    @Override
    public void addToAccountBalance(Integer accountNumber, double amount) {
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

    private List<TransactionDto> getRecentTransactions(Integer accountNumber) {
        List<Transaction> recentTransactions = transactionRepository.getRecentTransactionsForAccount(accountNumber, 5);
        Type listOfTransactions = new TypeToken<List<TransactionDto>>() {}.getType();
        return mapper.map(recentTransactions, listOfTransactions);
    }

    private Account getByAccountNumber(Integer accountNumber) {
        if(accountNumber != null) {
            return accountRepository.findByAccountNumber(accountNumber)
                    .orElseThrow(() -> new DataNotPresentException(String.format("Account with account number %s not found", accountNumber), AccountServiceImpl.class));
        }
        throw new BusinessRulesException("Account number cannot be null.");
    }
}
