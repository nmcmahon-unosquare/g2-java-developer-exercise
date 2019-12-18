package com.niall.g2javadeveloperexercise.viewmodels;

import com.niall.g2javadeveloperexercise.entities.Transaction;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import java.math.BigDecimal;
import java.util.List;

@Getter
@Setter
public class AccountViewModel {

    private String accountNumber;
    private String holderFirstName;
    private String holderLastName;
    private BigDecimal balance;
    private List<TransactionViewModel> recentTransactions;

}
