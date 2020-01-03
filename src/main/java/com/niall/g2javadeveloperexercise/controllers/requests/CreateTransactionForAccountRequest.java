package com.niall.g2javadeveloperexercise.controllers.requests;

import com.niall.g2javadeveloperexercise.enums.TransactionType;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotNull;

@Getter
@Setter
public class CreateTransactionForAccountRequest {

    @NotNull(message = "Transaction amount must be populated.")
    @DecimalMin(value = "0.0", message = "Transaction amount cannot be negative.")
    private Double amount;

    @NotNull(message = "Transaction type must be populated")
    private TransactionType type;

    @NotNull
    private int accountNumber;

    private String description;
}
