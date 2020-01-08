package com.niall.bankclient.menu.items;

import com.niall.bankclient.exception.HttpRequestUnsuccessfulException;
import com.niall.bankclient.menu.MenuItem;
import com.niall.bankclient.menu.Menus;

public class DepositMoneyMenuItem extends MenuItem {

    public DepositMoneyMenuItem() {
        super("Deposit money.");
    }

    @Override
    public void select() {
        System.out.println("How much money would you like to deposit?");
        Double amount = scanner.nextDouble();
        scanner.nextLine();
        System.out.println("Would you like to add a description to this transaction? (Y/N)");
        String descResponse = scanner.nextLine();
        String description = null;
        if(descResponse.equalsIgnoreCase("y")) {
            System.out.println("Please enter description:");
            description = scanner.nextLine();
        }
        try {
            client.createTransaction(amount, "DEPOSIT", description);
            System.out.println(String.format("Successfully deposited £%.2f into your account", amount));
        }catch (HttpRequestUnsuccessfulException ex) {
            System.out.println("There was an error depositing money into your account: " + ex.getMessage());
        }
        Menus.CUSTOMER_MENU.print();
    }
}
