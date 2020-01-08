package com.niall.bankclient.viewmodels;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class CreateTransactionViewModel {

    private Integer transactionId;
    private Date date;
    private Double amount;
    private String type;
    private String description;

}
