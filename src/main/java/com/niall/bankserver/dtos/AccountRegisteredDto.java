package com.niall.bankserver.dtos;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AccountRegisteredDto {
    private Integer accountNumber;
    private String pin;
}
