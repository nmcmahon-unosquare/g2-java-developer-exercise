package com.niall.bankclient.viewmodels;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.Date;

@Getter
@Setter
public class TransactionViewModel {

    private Integer transactionId;
    private Date date;
    private String type;
    private BigDecimal amount;
    private String description;

}
