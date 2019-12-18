package com.niall.g2javadeveloperexercise.repository;

import com.niall.g2javadeveloperexercise.entities.Account;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface AccountRepository extends CrudRepository<Account, String> {

    Optional<Account> findByAccountNumber(String accountNumber);

}
