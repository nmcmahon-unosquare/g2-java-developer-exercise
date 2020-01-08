package com.niall.bankclient.menu;

import com.niall.bankclient.http.BankHttpClient;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.Scanner;

@Setter
@Getter
public abstract class MenuItem {

    private String description;
    protected Scanner scanner;
    protected BankHttpClient client;

    public MenuItem(String description) {
        this.description = description;
        this.scanner = new Scanner(System.in);
        this.client = BankHttpClient.getInstance();
    }

    public abstract void select();
}
