package com.niall.bankserver.integrationtests;

import com.niall.bankserver.controllers.TransactionController;
import com.niall.bankserver.controllers.requests.CreateTransactionForAccountRequest;
import com.niall.bankserver.controllers.requests.CreateTransactionRequest;
import com.niall.bankserver.enums.TransactionType;
import com.niall.bankserver.exception.BusinessRulesException;
import com.niall.bankserver.viewmodels.AccountRegisteredViewModel;
import com.niall.bankserver.viewmodels.AccountViewModel;
import com.niall.bankserver.viewmodels.CreateTransactionViewModel;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
public class TransactionControllerIntegrationTest extends AbstractIntegrationTest {

    @Autowired
    private TransactionController transactionController;

    @Test
    public void testCreateTransactionCreatesNewTransactionWhenInputIsValid() {
        registerAndAuthenticateNewAccount();
        CreateTransactionRequest request = new CreateTransactionRequest();
        request.setAmount(5.50);
        request.setType(TransactionType.DEPOSIT);
        CreateTransactionViewModel createdTransaction = transactionController.createTransaction(request);

        AccountViewModel accountDto = accountController.getAccountDetails();
        Assert.assertEquals(5.50, accountDto.getBalance().doubleValue(), 0.01);
        Assert.assertEquals(1, accountDto.getRecentTransactions().size());

        Assert.assertEquals(createdTransaction.getAmount(), request.getAmount(), 0.01);
        Assert.assertEquals(createdTransaction.getType(), request.getType());
        Assert.assertEquals(createdTransaction.getDescription(), "DEPOSIT £5.50");
    }

    @Test (expected = BusinessRulesException.class)
    public void testCreateTransactionThrowsExceptionWhenUserTriesToOverdrawAccount() {
        registerAndAuthenticateNewAccount();
        CreateTransactionRequest request = new CreateTransactionRequest();
        request.setAmount(5.50);
        request.setType(TransactionType.WITHDRAWAL);
        transactionController.createTransaction(request);
    }

    @Test (expected = BusinessRulesException.class)
    public void testCreateTransactionThrowsExceptionWhenTransactionTypeIsMissing() {
        registerAndAuthenticateNewAccount();
        CreateTransactionRequest request = new CreateTransactionRequest();
        request.setAmount(5.50);
        transactionController.createTransaction(request);
    }

    @Test (expected = BusinessRulesException.class)
    public void testCreateTransactionThrowsExceptionWhenAmountIsMissing() {
        registerAndAuthenticateNewAccount();
        CreateTransactionRequest request = new CreateTransactionRequest();
        request.setType(TransactionType.DEPOSIT);
        transactionController.createTransaction(request);
    }

    @Test
    public void testCreateTransactionForAccountCreatesNewTransactionWhenInputIsValid() {
        registerAndAuthenticateNewAccount();
        AccountRegisteredViewModel accountBeingChanged = registerNewTestAccount();
        CreateTransactionForAccountRequest request = new CreateTransactionForAccountRequest();
        request.setAmount(5.50);
        request.setType(TransactionType.DEPOSIT);
        request.setAccountNumber(accountBeingChanged.getAccountNumber());
        CreateTransactionViewModel createdTransaction = transactionController.createTransactionForAccount(request);

        Assert.assertEquals(createdTransaction.getAmount(), request.getAmount(), 0.01);
        Assert.assertEquals(createdTransaction.getType(), request.getType());
        Assert.assertEquals(createdTransaction.getDescription(), "DEPOSIT £5.50");
    }

    @Test (expected = BusinessRulesException.class)
    public void testCreateTransactionForAccountThrowsExceptionWhenAccountNumberIsNotProvided() {
        registerAndAuthenticateNewAccount();
        CreateTransactionForAccountRequest request = new CreateTransactionForAccountRequest();
        request.setAmount(5.50);
        request.setType(TransactionType.DEPOSIT);
        request.setAccountNumber(null);
        transactionController.createTransactionForAccount(request);
    }
}
