package com.niall.bankclient.requests;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class CreateTransactionForAccountRequest {

    private Double amount;
    private String type;
    private Integer accountNumber;
    private String description;
}
