package com.niall.bankserver.dtos;

import com.niall.bankserver.enums.AccountType;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.List;

@Getter
@Setter
public class AccountDto {

    private Integer accountNumber;
    private String holderFirstName;
    private String holderLastName;
    private BigDecimal balance;
    private AccountType accountType;
    private List<TransactionDto> recentTransactions;

}
