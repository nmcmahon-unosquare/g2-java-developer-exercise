package com.niall.bankclient.menu;

import com.niall.bankclient.menu.items.*;

public class Menus {

    public static Menu MAIN_MENU = new Menu("Welcome to G2 Banking app. Would you like to register a new account or log in to an existing one?", new RegisterNewAccountMenuItem(), new LoginToAccountMenuItem(), new ExitApplicationMenuItem());
    public static Menu CUSTOMER_MENU = new Menu("What would you like to do?", new PrintMiniStatementMenuItem(), new WithdrawMoneyMenuItem(), new DepositMoneyMenuItem(), new CloseAccountMenuItem(), new LogoutMenuItem(), new ExitApplicationMenuItem());
}
