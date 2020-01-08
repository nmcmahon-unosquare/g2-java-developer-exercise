package com.niall.bankclient.menu.items;

import com.niall.bankclient.exception.HttpRequestUnsuccessfulException;
import com.niall.bankclient.menu.MenuItem;
import com.niall.bankclient.menu.Menus;

public class WithdrawMoneyMenuItem extends MenuItem {

    public WithdrawMoneyMenuItem() {
        super("Withdraw money.");
    }

    @Override
    public void select() {
        System.out.println("How much money would you like to withdraw?");
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
            client.createTransaction(amount, "WITHDRAWAL", description);
            System.out.println(String.format("Successfully withdrew £%.2f from your account", amount));
        }catch (HttpRequestUnsuccessfulException ex) {
            System.out.println("There was an error withdrawing money from your account: " + ex.getMessage());
        }
        Menus.CUSTOMER_MENU.print();
    }
}
