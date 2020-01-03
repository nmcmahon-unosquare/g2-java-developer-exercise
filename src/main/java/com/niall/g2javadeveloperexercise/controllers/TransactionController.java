package com.niall.g2javadeveloperexercise.controllers;

import com.niall.g2javadeveloperexercise.auth.AuthenticatedAccount;
import com.niall.g2javadeveloperexercise.controllers.requests.CreateTransactionForAccountRequest;
import com.niall.g2javadeveloperexercise.controllers.requests.CreateTransactionRequest;
import com.niall.g2javadeveloperexercise.dtos.TransactionDto;
import com.niall.g2javadeveloperexercise.services.TransactionService;
import com.niall.g2javadeveloperexercise.viewmodels.CreateTransactionViewModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/transaction")
public class TransactionController extends AbstractRestController {

    @Autowired
    private AuthenticatedAccount authenticatedAccount;
    @Autowired
    private TransactionService transactionService;

    @PostMapping("/create")
    public CreateTransactionViewModel createTransaction(@Valid @RequestBody CreateTransactionRequest request) {
        TransactionDto newTransaction = mapper.map(request, TransactionDto.class);
        TransactionDto createdTransaction = transactionService.createTransaction(newTransaction, authenticatedAccount.getAccountNumber());
        return mapper.map(createdTransaction, CreateTransactionViewModel.class);
    }

    @PostMapping("/createforaccount")
    public CreateTransactionViewModel createTransactionForAccount(@Valid @RequestBody CreateTransactionForAccountRequest request) {
        TransactionDto newTransaction = mapper.map(request, TransactionDto.class);
        TransactionDto createdTransaction = transactionService.createTransaction(newTransaction, request.getAccountNumber());
        return mapper.map(createdTransaction, CreateTransactionViewModel.class);
    }

}
