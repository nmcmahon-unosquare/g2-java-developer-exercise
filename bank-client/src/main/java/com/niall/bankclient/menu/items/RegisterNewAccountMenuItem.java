package com.niall.bankclient.menu.items;

import com.niall.bankclient.exception.HttpRequestUnsuccessfulException;
import com.niall.bankclient.http.BankHttpClient;
import com.niall.bankclient.menu.Menu;
import com.niall.bankclient.menu.MenuItem;
import com.niall.bankclient.menu.Menus;
import com.niall.bankclient.viewmodels.AccountRegisteredViewModel;

import java.util.Scanner;

public class RegisterNewAccountMenuItem extends MenuItem {

    public RegisterNewAccountMenuItem() {
        super("Register a new account.");
    }

    @Override
    public void select() {
        System.out.println("Please enter the following account details:");
        System.out.println("Account holder first name: ");
        String firstName = scanner.nextLine();
        System.out.println("Account holder last name: ");
        String lastName = scanner.nextLine();
        System.out.println("Account holder ID number: ");
        String idNumber = scanner.nextLine();
        try {
            AccountRegisteredViewModel accountRegisteredViewModel = client.registerAccount(firstName, lastName, idNumber);
            System.out.println(String.format("Account registered successfully. New account number: %s pin: %s", accountRegisteredViewModel.getAccountNumber(), accountRegisteredViewModel.getPin()));
            client.signin(accountRegisteredViewModel.getAccountNumber(), accountRegisteredViewModel.getPin());
            Menus.CUSTOMER_MENU.print();
        } catch (HttpRequestUnsuccessfulException ex) {
            System.out.println("ERROR: Account registration failed: " + ex.getMessage());
            Menus.MAIN_MENU.print();
        }


    }

}
