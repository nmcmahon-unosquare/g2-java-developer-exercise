package com.niall.bankclient.viewmodels;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.List;

@Getter
@Setter
public class AccountViewModel {

    private Integer accountNumber;
    private String holderFirstName;
    private String holderLastName;
    private BigDecimal balance;
    private List<TransactionViewModel> recentTransactions;

    @Override
    public String toString() {
        String recentTransactionsString = "";
        for(TransactionViewModel transaction: recentTransactions) {
            recentTransactionsString += String.format("Transaction ID: %s\tTransaction Date: %s\tTransaction type: %s\tTransaction amount: %s\tTransaction description: %s", transaction.getTransactionId(), transaction.getDate(), transaction.getType(), transaction.getAmount(), transaction.getDescription());
            recentTransactionsString += "\n";
        }
        return String.format("Account Number: %s\nAccount holder first name: %s\nAccount holder last name: %s\nAccount balance: %s\nRecent transactions:\n%s", accountNumber, holderFirstName, holderLastName, balance, recentTransactionsString);
    }
}
