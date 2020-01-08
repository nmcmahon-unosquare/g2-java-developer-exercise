package com.niall.bankclient.menu.items;

import com.niall.bankclient.menu.MenuItem;

public class ExitApplicationMenuItem extends MenuItem {

    public ExitApplicationMenuItem() {
        super("Exit the application");
    }

    @Override
    public void select() {
        System.out.println("Thank you for using the G2 banking app.");
        System.exit(0);
    }
}
