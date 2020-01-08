package com.niall.bankclient.menu.items;

import com.niall.bankclient.exception.HttpRequestUnsuccessfulException;
import com.niall.bankclient.menu.MenuItem;
import com.niall.bankclient.menu.Menus;

public class CloseAccountMenuItem extends MenuItem {

    public CloseAccountMenuItem() {
        super("Close your account");
    }

    @Override
    public void select() {
        try {
            client.closeAccount();
            client.removeAuthentication();
            System.out.println("Successfully closed your account.");
            Menus.MAIN_MENU.print();
        } catch (HttpRequestUnsuccessfulException ex) {
            System.out.println("There was an error closing your account: " + ex.getMessage());
            Menus.CUSTOMER_MENU.print();
        }
    }
}
