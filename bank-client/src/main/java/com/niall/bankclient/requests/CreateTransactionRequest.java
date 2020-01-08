package com.niall.bankclient.requests;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class CreateTransactionRequest {

    Double amount;
    String type;

    String description;
}
