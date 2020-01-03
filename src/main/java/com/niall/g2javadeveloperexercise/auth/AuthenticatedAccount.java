package com.niall.g2javadeveloperexercise.auth;

import com.niall.g2javadeveloperexercise.dtos.AccountDto;
import com.niall.g2javadeveloperexercise.exception.AuthenticationException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

@Component
public class AuthenticatedAccount {

    public AccountDto getAccount() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if(authentication != null) {
            return (AccountDto) authentication.getPrincipal();
        }
        throw new AuthenticationException("Could not get authenticated account");
    }

    public void unauthenticate() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if(authentication != null) {
            authentication.setAuthenticated(false);
        }
    }

    public int getAccountNumber() {
        return getAccount().getAccountNumber();
    }
}
