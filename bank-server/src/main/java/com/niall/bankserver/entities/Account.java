package com.niall.bankserver.entities;

import com.niall.bankserver.enums.AccountType;
import lombok.*;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Random;

@Entity
@Table(name = "ACCOUNT")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Account {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "account_number", nullable = false)
    private int accountNumber;

    @Column(name = "holder_first_name", nullable = false)
    private String holderFirstName;

    @Column(name = "holder_last_name", nullable = false)
    private String holderLastName;

    @Column(name = "pin", nullable = false)
    @Builder.Default
    private String pin = generateRandomPin();

    @Column(name = "holder_id_number", nullable = false)
    private String holderIdNumber;

    @Column(name = "current_balance")
    @Builder.Default
    private BigDecimal balance = new BigDecimal(0);

    @Column(name = "account_type", nullable = false)
    private AccountType accountType;

    private static String generateRandomPin() {
        Random rand = new Random();
        return String.format("%04d", rand.nextInt(10000));
    }

}
