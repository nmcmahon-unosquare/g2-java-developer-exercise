package com.niall.g2javadeveloperexercise.entities;

import lombok.*;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;

@Entity
@Table(name = "TRANSACTION")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Transaction {

    @Id
    @GeneratedValue
    @Column(name = "transaction_id", nullable = false)
    private int transactionId;

    @Column(name = "date", nullable = false)
    private Date date;

    @Column(name = "amount", nullable = false)
    private BigDecimal amount;

    @Column(name = "description")
    private String description;

    @ManyToOne
    @JoinColumn(name = "account_number", nullable = false)
    private Account account;

}
