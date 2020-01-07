package com.niall.g2javadeveloperexercise.integrationtests;

import com.niall.g2javadeveloperexercise.controllers.AccountController;
import com.niall.g2javadeveloperexercise.controllers.AuthenticationController;
import com.niall.g2javadeveloperexercise.controllers.requests.SignInRequest;
import com.niall.g2javadeveloperexercise.exception.AuthenticationException;
import com.niall.g2javadeveloperexercise.jwt.JwtTokenProvider;
import com.niall.g2javadeveloperexercise.viewmodels.AccountRegisteredViewModel;
import com.niall.g2javadeveloperexercise.viewmodels.AuthTokenViewModel;
import io.jsonwebtoken.lang.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
public class AuthenticationControllerIntegrationTest extends AbstractIntegrationTest{

    @Autowired
    private AuthenticationController authenticationController;
    @Autowired
    private AccountController accountController;
    @Autowired
    private JwtTokenProvider tokenProvider;


    @Test
    public void testSignInWithValidCredentialsReturnsValidToken() {
        AccountRegisteredViewModel account = registerNewTestAccount();
        SignInRequest request = new SignInRequest();
        request.setAccountNumber(account.getAccountNumber());
        request.setAccountPin(account.getPin());
        AuthTokenViewModel tokenResponse =  authenticationController.signin(request);
        Assert.notNull(tokenResponse.getToken());
        Assert.isTrue(tokenProvider.validateToken(tokenResponse.getToken()));
    }

    @Test(expected = AuthenticationException.class)
    public void testSignInWithInvalidCredentialsThrowsException() {
        SignInRequest request = new SignInRequest();
        request.setAccountNumber(-1);
        request.setAccountPin("NonNumericPin");
        authenticationController.signin(request);
    }
}
