package com.niall.g2javadeveloperexercise.dtos;

import com.niall.g2javadeveloperexercise.viewmodels.TransactionViewModel;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.List;

@Getter
@Setter
public class AccountDto {

    private String accountNumber;
    private String holderFirstName;
    private String holderLastName;
    private BigDecimal balance;
    private List<TransactionDto> recentTransactions;

}