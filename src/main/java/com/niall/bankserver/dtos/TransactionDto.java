package com.niall.bankserver.dtos;

import com.niall.bankserver.enums.TransactionType;
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
