package com.niall.bankclient.menu.items;

import com.niall.bankclient.exception.HttpRequestUnsuccessfulException;
import com.niall.bankclient.http.BankHttpClient;
import com.niall.bankclient.menu.MenuItem;
import com.niall.bankclient.menu.Menus;
import com.niall.bankclient.viewmodels.AccountRegisteredViewModel;

import java.util.Scanner;

public class LoginToAccountMenuItem extends MenuItem {

    public LoginToAccountMenuItem() {
        super("Log in to account");
    }

    @Override
    public void select() {
        System.out.println("Please enter the following account details:");
        System.out.println("Account number: ");
        int accountNumber = scanner.nextInt();
        scanner.nextLine();
        System.out.println("Account pin: ");
        String pin = scanner.nextLine();
        try {
            client.signin(accountNumber, pin);
            System.out.println("Account signed in successfully.");
            Menus.CUSTOMER_MENU.print();
        } catch (HttpRequestUnsuccessfulException ex) {
            if(ex.getStatusCode() == 401) {
                System.out.println("Could not log into account. Account details incorrect");
            }
            else {
                System.out.println("ERROR: Account log in failed: " + ex.getMessage());
            }
            Menus.MAIN_MENU.print();
        }
    }
}
