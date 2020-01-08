package com.niall.bankserver.enums;

import lombok.Getter;

@Getter
public enum TransactionType {
    DEPOSIT(BalanceModifierType.INCREASE),
    WITHDRAWAL(BalanceModifierType.DECREASE),
    DEBIT(BalanceModifierType.DECREASE),
    CHECK(BalanceModifierType.INCREASE);

    private BalanceModifierType balanceModifierType;

    TransactionType(BalanceModifierType balanceModifierType) {
        this.balanceModifierType = balanceModifierType;
    }


}
