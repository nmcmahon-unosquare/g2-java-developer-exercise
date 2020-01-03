package com.niall.g2javadeveloperexercise.services;

import com.niall.g2javadeveloperexercise.dtos.TransactionDto;

public interface TransactionService {

    TransactionDto createTransaction(TransactionDto transaction, int accountNumber);

}
