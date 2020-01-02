package com.niall.g2javadeveloperexercise.viewmodels;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.List;

@Getter
@Setter
public class AccountViewModel {

    private int accountNumber;
    private String holderFirstName;
    private String holderLastName;
    private BigDecimal balance;
    private List<TransactionViewModel> recentTransactions;

}
