package com.niall.bankserver.services.impl;

import com.niall.bankserver.dtos.TransactionDto;
import com.niall.bankserver.entities.Account;
import com.niall.bankserver.entities.Transaction;
import com.niall.bankserver.enums.BalanceModifierType;
import com.niall.bankserver.exception.BusinessRulesException;
import com.niall.bankserver.repository.TransactionRepository;
import com.niall.bankserver.services.AccountService;
import com.niall.bankserver.services.DataService;
import com.niall.bankserver.services.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class TransactionServiceImpl extends DataService implements TransactionService {

    @Autowired
    private TransactionRepository transactionRepository;
    @Autowired
    private AccountService accountService;

    @Override
    public TransactionDto createTransaction(TransactionDto transactionDto, Integer accountNumber) {
        Account account = mapper.map(accountService.getAccountByAccountNumber(accountNumber), Account.class);
        Transaction newTransaction = buildNewTransaction(transactionDto, account);
        changeAccountBalanceBasedOnTransactionType(transactionDto, accountNumber);
        Transaction savedTransaction = transactionRepository.save(newTransaction);
        return mapper.map(savedTransaction, TransactionDto.class);
    }

    private void changeAccountBalanceBasedOnTransactionType(TransactionDto transactionDto, Integer accountNumber) {
        if(transactionDto.getType() != null && transactionDto.getAmount() != null) {
            changeAccountBalance(transactionDto, accountNumber);
        }
        else {
            throw new BusinessRulesException("Cannot create transaction. Transaction Type and amount are required fields.");
        }
    }

    private void changeAccountBalance(TransactionDto transactionDto, Integer accountNumber) {
        if(transactionDto.getType().getBalanceModifierType() == BalanceModifierType.INCREASE) {
            accountService.addToAccountBalance(accountNumber, transactionDto.getAmount().doubleValue());
        }
        else if(transactionDto.getType().getBalanceModifierType() == BalanceModifierType.DECREASE) {
            accountService.addToAccountBalance(accountNumber, transactionDto.getAmount().doubleValue() * -1);
        }
        else {
            throw new BusinessRulesException(String.format("Could not create transaction. Transactions of type %s are not yet supported", transactionDto.getType().name()));
        }
    }

    private Transaction buildNewTransaction(TransactionDto transactionDto, Account account) {
        Transaction newTransaction = mapper.map(transactionDto, Transaction.class);
        newTransaction.setAccount(account);
        if(newTransaction.getDescription() == null) {
            generateDescriptionForTransaction(newTransaction);
        }
        return newTransaction;
    }

    private void generateDescriptionForTransaction(Transaction newTransaction) {
        String type = newTransaction.getType();
        BigDecimal amount = newTransaction.getAmount();
        if(type != null && amount != null) {
            newTransaction.setDescription(String.format("%s £%.2f", type, amount.doubleValue()));
        }
    }

}
