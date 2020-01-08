package com.niall.bankclient.http;

import com.niall.bankclient.exception.HttpRequestUnsuccessfulException;
import com.niall.bankclient.requests.CreateTransactionForAccountRequest;
import com.niall.bankclient.requests.CreateTransactionRequest;
import com.niall.bankclient.requests.RegisterAccountRequest;
import com.niall.bankclient.requests.SignInRequest;
import com.niall.bankclient.viewmodels.AccountRegisteredViewModel;
import com.niall.bankclient.viewmodels.AccountViewModel;
import com.niall.bankclient.viewmodels.AuthTokenViewModel;
import com.niall.bankclient.viewmodels.CreateTransactionViewModel;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

public class BankHttpClient {

    private RestTemplate restTemplate;
    private HttpHeaders headers;
    private static final String BANK_SERVER_ADDRESS = "http://localhost:8080";
    private static final String REGISTER_ACCOUNT_ENDPOINT = "/account/register";
    private static final String GET_ACCOUNT_DETAILS_ENDPOINT = "/account/getaccountdetails";
    private static final String CLOSE_ACCOUNT_ENDPOINT = "/account/close";
    private static final String SIGN_IN_ENDPOINT = "/authentication/signin";
    private static final String CREATE_TRANSACTION_ENDPOINT = "/transaction/create";
    private static final String CREATE_TRANSACTION_FOR_ACCOUNT_ENDPOINT = "/transaction/createforaccount";

    private static BankHttpClient INSTANCE;


    private BankHttpClient() {
        System.setProperty("logging.level.org.springframework", "OFF");
        System.setProperty("logging.level.root", "OFF");
        this.restTemplate = new RestTemplate();
        this.headers = new HttpHeaders();
        headers.add("Content-Type", "application/json");
        headers.add("Accept", "*/*");
    }

    public static BankHttpClient getInstance() {
        if(INSTANCE == null) {
            INSTANCE = new BankHttpClient();
        }
        return INSTANCE;
    }

    public AccountRegisteredViewModel registerAccount(String holderFirstName, String holderLastName, String holderIdNumber) {
        RegisterAccountRequest request = buildRegisterAccountRequest(holderFirstName, holderLastName, holderIdNumber);
        HttpEntity<RegisterAccountRequest> httpEntity = new HttpEntity<>(request, headers);
        return sendHttpRequest(REGISTER_ACCOUNT_ENDPOINT, httpEntity, AccountRegisteredViewModel.class);
    }

    public AccountViewModel getAccountDetails() {
        HttpEntity<String> httpEntity = new HttpEntity<>(null, headers);
        return sendHttpRequest(GET_ACCOUNT_DETAILS_ENDPOINT, httpEntity, AccountViewModel.class);
    }

    public CreateTransactionViewModel createTransaction(Double amount, String type, String description) {
        CreateTransactionRequest createTransactionRequest = new CreateTransactionRequest(amount, type, description);
        HttpEntity<CreateTransactionRequest> httpEntity = new HttpEntity<>(createTransactionRequest, headers);
        return sendHttpRequest(CREATE_TRANSACTION_ENDPOINT, httpEntity, CreateTransactionViewModel.class);
    }

    public CreateTransactionViewModel createTransactionForAccount(Double amount, String type, String description, Integer accountNumber) {
        CreateTransactionForAccountRequest createTransactionRequest = new CreateTransactionForAccountRequest(amount, type, accountNumber, description);
        HttpEntity<CreateTransactionForAccountRequest> httpEntity = new HttpEntity<>(createTransactionRequest, headers);
        return sendHttpRequest(CREATE_TRANSACTION_FOR_ACCOUNT_ENDPOINT, httpEntity, CreateTransactionViewModel.class);
    }

    public boolean closeAccount() {
        try {
            HttpEntity<String> httpEntity = new HttpEntity<>(null, headers);
            sendHttpRequest(CLOSE_ACCOUNT_ENDPOINT, httpEntity, String.class);
            return true;
        } catch (HttpRequestUnsuccessfulException ex) {
            return false;
        }
    }

    public void signin(Integer accountNumber, String pin) {
        SignInRequest signInRequest = new SignInRequest(accountNumber, pin);
        HttpEntity<SignInRequest> httpEntity = new HttpEntity<>(signInRequest, headers);
        AuthTokenViewModel tokenViewModel = sendHttpRequest(SIGN_IN_ENDPOINT, httpEntity, AuthTokenViewModel.class);
        headers.add("Authorization", "Bearer " + tokenViewModel.getToken());
    }

    private <ENTITY_TYPE, RESPONSE_TYPE> RESPONSE_TYPE sendHttpRequest(String endpoint, HttpEntity<ENTITY_TYPE> request, Class<RESPONSE_TYPE> responseBodyType) {
        try {
            ResponseEntity<RESPONSE_TYPE> response = restTemplate.exchange(BANK_SERVER_ADDRESS + endpoint, HttpMethod.POST, request, responseBodyType);
            if(response.getStatusCode().is2xxSuccessful()) {
                return response.getBody();
            }
            else {
                throw new HttpRequestUnsuccessfulException(response.getStatusCodeValue(), String.format("Request failed. Status code: %s\t Response: %s", response.getStatusCodeValue(), response.getBody()));
            }
        }catch (HttpClientErrorException ex) {
            throw new HttpRequestUnsuccessfulException(ex.getStatusCode().value(), String.format("Request failed. Status code: %s\t Response: %s", ex.getStatusCode().value(), ex.getMessage()));
        }

    }

    private RegisterAccountRequest buildRegisterAccountRequest(String holderFirstName, String holderLastName, String holderIdNumber) {
        RegisterAccountRequest request = new RegisterAccountRequest();
        request.setHolderFirstName(holderFirstName);
        request.setHolderLastName(holderLastName);
        request.setHolderIdNumber(holderIdNumber);
        return request;
    }


    public void removeAuthentication() {
        headers.remove("Authorization");
    }
}
