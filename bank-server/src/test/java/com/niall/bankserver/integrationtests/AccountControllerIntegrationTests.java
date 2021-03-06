package com.niall.bankserver.integrationtests;

import com.niall.bankserver.controllers.requests.RegisterAccountRequest;
import com.niall.bankserver.dtos.AccountDto;
import com.niall.bankserver.enums.AccountType;
import com.niall.bankserver.exception.AuthenticationException;
import com.niall.bankserver.exception.DataNotPresentException;
import com.niall.bankserver.exception.RegistrationException;
import com.niall.bankserver.viewmodels.AccountRegisteredViewModel;
import com.niall.bankserver.viewmodels.AccountViewModel;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
public class AccountControllerIntegrationTests extends AbstractIntegrationTest {

    @Test
    public void testRegisterNewAccountWithValidDetailsProvidesValidResponse() {
        RegisterAccountRequest request = buildValidRegisterRequest();
        AccountRegisteredViewModel accountRegisteredViewModel = accountController.registerAccount(request);
        Assert.assertTrue(accountRegisteredViewModel.getAccountNumber() > 0);
        Assert.assertTrue(accountRegisteredViewModel.getPin().matches("[0-9]{4}"));
    }

    @Test (expected = RegistrationException.class)
    public void testRegisterNewAccountWithInvalidDetailsProvidesThrowsException() {
        RegisterAccountRequest request = new RegisterAccountRequest();
        request.setAccountType(AccountType.PERSONAL_ACCOUNT);
        request.setHolderFirstName("Valid First Name");
        request.setHolderLastName("Valid Last Name");
        request.setHolderIdNumber(null);
        accountController.registerAccount(request);
    }

    @Test
    public void testGetAccountDetailsReturnsCorrectAccountDetailsForNewAccount() {
        AccountDto account = registerAndAuthenticateNewAccount();
        AccountViewModel accountViewModel = accountController.getAccountDetails();
        Assert.assertEquals(account.getAccountNumber(), accountViewModel.getAccountNumber());
        Assert.assertEquals(account.getHolderFirstName(), accountViewModel.getHolderFirstName());
        Assert.assertEquals(account.getHolderLastName(), accountViewModel.getHolderLastName());
    }

    @Test (expected = AuthenticationException.class)
    public void testGetAccountDetailsThrowsExceptionWhenNoAccountIsAuthenticated() {
        unauthenticateCurrentAccount();
        accountController.getAccountDetails();
    }

    @Test (expected = DataNotPresentException.class)
    public void testCloseAccountSuccessfullyClosesAccount() {
        AccountDto accountDto = registerAndAuthenticateNewAccount();
        AccountViewModel accountViewModel = accountController.getAccountDetails();
        Assert.assertEquals(accountDto.getAccountNumber(), accountViewModel.getAccountNumber());

        // Attempt to get account details after closing. Should throw exception
        accountController.closeAccount();
        accountController.getAccountDetails();
    }

    @Test (expected = AuthenticationException.class)
    public void testCloseAccountThrowsExceptionWhenNoAccountIsAuthenticated() {
        registerNewTestAccount();
        accountController.closeAccount();
    }

    private RegisterAccountRequest buildValidRegisterRequest() {
        RegisterAccountRequest request = new RegisterAccountRequest();
        request.setAccountType(AccountType.PERSONAL_ACCOUNT);
        request.setHolderFirstName("Valid First Name");
        request.setHolderLastName("Valid Last Name");
        request.setHolderIdNumber("VALIDIDNUMBER");
        return request;
    }
}
