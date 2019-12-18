package com.niall.g2javadeveloperexercise.viewmodels;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import java.math.BigDecimal;
import java.util.Date;

@Getter
@Setter
public class TransactionViewModel {

    private String transactionId;
    private Date date;
    private BigDecimal amount;
    private String description;

}
