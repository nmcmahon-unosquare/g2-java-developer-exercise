package com.niall.bankclient.menu.items;

import com.niall.bankclient.menu.MenuItem;
import com.niall.bankclient.menu.Menus;
import com.niall.bankclient.viewmodels.AccountViewModel;

public class PrintMiniStatementMenuItem extends MenuItem {

    public PrintMiniStatementMenuItem() {
        super("Print a mini account statement");
    }

    @Override
    public void select() {
        try {
            AccountViewModel accountDetails = client.getAccountDetails();
            System.out.println(accountDetails);
        } catch (Exception ex) {
            System.out.println("There was an issue getting your account details: " + ex.getMessage());
        }
        Menus.CUSTOMER_MENU.print();
    }
}
