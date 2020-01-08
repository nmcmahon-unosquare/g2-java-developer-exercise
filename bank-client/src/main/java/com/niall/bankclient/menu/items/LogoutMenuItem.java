package com.niall.bankclient.menu.items;

import com.niall.bankclient.menu.MenuItem;
import com.niall.bankclient.menu.Menus;

public class LogoutMenuItem extends MenuItem {

    public LogoutMenuItem() {
        super("Log out of application.");
    }

    @Override
    public void select() {
        client.removeAuthentication();
        System.out.println("Successfully logged out.");
        Menus.MAIN_MENU.print();
    }
}
