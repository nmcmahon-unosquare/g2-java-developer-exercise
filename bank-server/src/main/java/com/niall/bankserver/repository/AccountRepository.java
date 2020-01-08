package com.niall.bankserver.repository;

import com.niall.bankserver.entities.Account;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface AccountRepository extends CrudRepository<Account, Integer> {

    Optional<Account> findByAccountNumber(int accountNumber);

}
