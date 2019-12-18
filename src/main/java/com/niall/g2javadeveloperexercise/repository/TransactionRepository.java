package com.niall.g2javadeveloperexercise.repository;

import com.niall.g2javadeveloperexercise.entities.Transaction;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TransactionRepository extends CrudRepository<Transaction, String> {

    @Query(value = "SELECT * FROM TRANSACTION WHERE account_number = ?1 ORDER BY date DESC LIMIT ?2", nativeQuery = true)
    List<Transaction> getRecentTransactionsForAccount(String accountNumber, int numberOfTransactions);

}
