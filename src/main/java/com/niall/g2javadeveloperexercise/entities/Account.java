package com.niall.g2javadeveloperexercise.entities;

import lombok.*;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.List;
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
    private String accountNumber;

    @Column(name = "holder_first_name", nullable = false)
    private String holderFirstName;

    @Column(name = "holder_last_name", nullable = false)
    private String holderLastName;

    @Column(name = "pin", nullable = false)
    private String pin = generateRandomPin();

    @Column(name = "holder_id_number", nullable = false)
    private String holderIdNumber;

    @Column(name = "current_balance")
    private BigDecimal balance = new BigDecimal(0);

    private String generateRandomPin() {
        Random rand = new Random();
        return String.format("%04d", rand.nextInt(10000));
    }

}
