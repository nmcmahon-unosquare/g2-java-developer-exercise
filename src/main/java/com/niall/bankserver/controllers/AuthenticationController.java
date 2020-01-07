package com.niall.bankserver.controllers;

import com.niall.bankserver.controllers.requests.SignInRequest;
import com.niall.bankserver.dtos.AccountDto;
import com.niall.bankserver.jwt.JwtTokenProvider;
import com.niall.bankserver.services.AuthenticationService;
import com.niall.bankserver.viewmodels.AuthTokenViewModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/authentication")
public class AuthenticationController extends AbstractRestController {

    @Autowired
    private AuthenticationService authenticationService;
    @Autowired
    private JwtTokenProvider jwtTokenProvider;

    @PostMapping("/signin")
    public AuthTokenViewModel signin(@Valid @RequestBody SignInRequest request) {
        AccountDto account = authenticationService.signIn(request.getAccountNumber(), request.getAccountPin());
        String token = jwtTokenProvider.createBearerToken("" + account.getAccountNumber());
        return new AuthTokenViewModel(token);
    }
}
