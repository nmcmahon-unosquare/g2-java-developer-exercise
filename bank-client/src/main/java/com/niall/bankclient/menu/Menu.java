package com.niall.bankclient.menu;

import lombok.AllArgsConstructor;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

@AllArgsConstructor
public class Menu {

    private String preamble;
    private List<MenuItem> menuItems;

    public Menu(String preamble, MenuItem... items) {
        this.preamble = preamble;
        menuItems = Arrays.asList(items);
    }

    public void print() {
        System.out.println(preamble);
        int count = 1;
        for(MenuItem item : menuItems) {
            System.out.println(count++ + ". " + item.getDescription());
        }
        System.out.println(String.format("Please select an option from 1 to %s:", menuItems.size()));
        gatherResponse();
    }

    private void gatherResponse() {
        try {
            Scanner scanner = new Scanner(System.in);
            int selection = scanner.nextInt();
            scanner.nextLine();
            selectMenuItem(selection);
        } catch (Exception ex) {
            System.out.println("There was an error with your selection: " + ex.getMessage());
            this.print();
        }

    }

    private void selectMenuItem(int index) {
        menuItems.get(index-1).select();
    }
}
