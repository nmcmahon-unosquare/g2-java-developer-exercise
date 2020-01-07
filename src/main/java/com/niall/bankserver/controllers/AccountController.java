package com.niall.bankserver.controllers;

import com.niall.bankserver.auth.AuthenticatedAccount;
import com.niall.bankserver.controllers.requests.RegisterAccountRequest;
import com.niall.bankserver.dtos.AccountDto;
import com.niall.bankserver.dtos.AccountRegisteredDto;
import com.niall.bankserver.services.AccountService;
import com.niall.bankserver.viewmodels.AccountRegisteredViewModel;
import com.niall.bankserver.viewmodels.AccountViewModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/account")
@Secured(value = "")
public class AccountController extends AbstractRestController {

    @Autowired
    private AuthenticatedAccount authenticatedAccount;
    @Autowired
    private AccountService accountService;

    @PostMapping("/getaccountdetails")
    public AccountViewModel getAccountDetails() {
        int accountNumber = authenticatedAccount.getAccountNumber();
        AccountDto accountDto = accountService.getAccountByAccountNumber(accountNumber);
        return mapper.map(accountDto, AccountViewModel.class);
    }

    @PostMapping("/register")
    public AccountRegisteredViewModel registerAccount(@Valid @RequestBody RegisterAccountRequest request) {
        AccountRegisteredDto newAccount = accountService.createNewAccount(request);
        return mapper.map(newAccount, AccountRegisteredViewModel.class);
    }

    @PostMapping("/close")
    public void closeAccount() {
        accountService.closeAccount(authenticatedAccount.getAccountNumber());
        authenticatedAccount.unauthenticate();
    }
}
