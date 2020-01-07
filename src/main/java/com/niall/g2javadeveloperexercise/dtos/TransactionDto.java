package com.niall.g2javadeveloperexercise.dtos;

import com.niall.g2javadeveloperexercise.enums.TransactionType;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.Date;

@Getter
@Setter
public class TransactionDto {

    private Integer transactionId;
    private Date date = new Date();
    private TransactionType type;
    private BigDecimal amount;
    private String description;

}
