package com.niall.bankserver.services;

import com.niall.bankserver.dtos.TransactionDto;

public interface TransactionService {

    TransactionDto createTransaction(TransactionDto transaction, Integer accountNumber);

}
