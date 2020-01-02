package com.niall.g2javadeveloperexercise.dtos;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.Date;

@Getter
@Setter
public class TransactionDto {

    private int transactionId;
    private Date date;
    private BigDecimal amount;
    private String description;

}
