package com.niall.g2javadeveloperexercise.controllers;

import com.niall.g2javadeveloperexercise.controllers.requests.SignInRequest;
import com.niall.g2javadeveloperexercise.dtos.AccountDto;
import com.niall.g2javadeveloperexercise.jwt.JwtTokenProvider;
import com.niall.g2javadeveloperexercise.services.AuthenticationService;
import com.niall.g2javadeveloperexercise.viewmodels.AuthTokenViewModel;
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
