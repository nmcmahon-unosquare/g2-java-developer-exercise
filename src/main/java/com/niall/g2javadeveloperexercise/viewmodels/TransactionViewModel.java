package com.niall.g2javadeveloperexercise.viewmodels;

import com.niall.g2javadeveloperexercise.enums.TransactionType;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.Date;

@Getter
@Setter
public class TransactionViewModel {

    private Integer transactionId;
    private Date date;
    private TransactionType type;
    private BigDecimal amount;
    private String description;

}
