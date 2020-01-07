package com.niall.g2javadeveloperexercise.viewmodels;

import com.niall.g2javadeveloperexercise.enums.TransactionType;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class CreateTransactionViewModel {

    private Integer transactionId;
    private Date date;
    private Double amount;
    private TransactionType type;
    private String description;

}
