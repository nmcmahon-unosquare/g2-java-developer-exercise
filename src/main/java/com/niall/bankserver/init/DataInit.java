package com.niall.bankserver.init;

import com.niall.bankserver.entities.Account;
import com.niall.bankserver.entities.Transaction;
import com.niall.bankserver.enums.AccountType;
import com.niall.bankserver.repository.AccountRepository;
import com.niall.bankserver.repository.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

@Component
public class DataInit implements ApplicationRunner {


    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private TransactionRepository transactionRepository;

    private static final DateFormat df = new SimpleDateFormat("yyyy-MM-dd");

    @Override
    public void run(ApplicationArguments applicationArguments) throws Exception {
        populateAccountTable();
    }

    private void populateTransactions(Account account) {
        for(int i = 10; i < 21 ; i++) {
            Transaction transaction = Transaction.builder()
                    .account(account)
                    .date(new Date())
                    .amount(new BigDecimal(i))
                    .type("WITHDRAWAL")
                    .description(String.format("Bought something for £%d", i))
                    .build();
            transactionRepository.save(transaction);
        }

    }

    private void populateAccountTable() {
        long count = accountRepository.count();
        if(count == 0) {
            Account account = Account.builder()
                    .holderFirstName("Niall")
                    .holderLastName("McMahon")
                    .pin("1234")
                    .holderIdNumber("AB19234")
                    .accountType(AccountType.BUSINESS_ACCOUNT)
                    .balance(new BigDecimal(0))
                    .build();
            accountRepository.save(account);
            populateTransactions(account);
        }
    }
}
