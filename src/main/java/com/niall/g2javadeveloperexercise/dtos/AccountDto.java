package com.niall.g2javadeveloperexercise.dtos;

import com.niall.g2javadeveloperexercise.enums.AccountType;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.List;

@Getter
@Setter
public class AccountDto {

    private int accountNumber;
    private String holderFirstName;
    private String holderLastName;
    private BigDecimal balance;
    private AccountType accountType;
    private List<TransactionDto> recentTransactions;

}
