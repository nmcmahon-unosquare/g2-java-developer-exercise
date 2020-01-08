package com.niall.bankserver.integrationtests;

import com.niall.bankserver.controllers.AccountController;
import com.niall.bankserver.controllers.requests.RegisterAccountRequest;
import com.niall.bankserver.dtos.AccountDto;
import com.niall.bankserver.enums.AccountType;
import com.niall.bankserver.services.AccountService;
import com.niall.bankserver.viewmodels.AccountRegisteredViewModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.test.context.TestPropertySource;

import java.util.Collections;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestPropertySource(locations = "classpath:test.properties")
public class AbstractIntegrationTest {

    @Autowired
    protected AccountController accountController;
    @Autowired
    private AccountService accountService;

    protected AccountRegisteredViewModel registerNewTestAccount() {
        RegisterAccountRequest registerAccountRequest = buildTestRegisterAccountRequest();
        return accountController.registerAccount(registerAccountRequest);
    }

    protected AccountDto registerAndAuthenticateNewAccount() {
        AccountRegisteredViewModel registeredViewModel = registerNewTestAccount();
        AccountDto account = accountService.getAccountByAccountNumber(registeredViewModel.getAccountNumber());
        Authentication authentication = new UsernamePasswordAuthenticationToken(account, "", Collections.singletonList(new SimpleGrantedAuthority("ROLE_" + account.getAccountType().name())));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        return account;
    }

    protected void unauthenticateCurrentAccount() {
        SecurityContextHolder.getContext().setAuthentication(null);
    }

    private RegisterAccountRequest buildTestRegisterAccountRequest() {
        RegisterAccountRequest registerAccountRequest = new RegisterAccountRequest();
        registerAccountRequest.setHolderFirstName("Test");
        registerAccountRequest.setHolderLastName("User");
        registerAccountRequest.setAccountType(AccountType.PERSONAL_ACCOUNT);
        registerAccountRequest.setHolderIdNumber("TEST1234");
        return registerAccountRequest;
    }
}
