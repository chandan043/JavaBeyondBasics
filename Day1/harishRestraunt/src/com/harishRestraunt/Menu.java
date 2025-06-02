package com.harishRestraunt;

import java.util.*;

// MenuItem data structure
class MenuItem {
    String category;
    double price;

    public MenuItem(String category, double price) {
        this.category = category;
        this.price = price;
    }
}

@FunctionalInterface
interface CategorizeMenuItem {
    String getCategory(MenuItem item);
}

@FunctionalInterface
interface ApplySpecialOffer {
    int discount(MenuItem item, Map<String, Integer> discountMap);
}

public class Menu {
    public static void main(String[] args) {
        Map<String, MenuItem> menuData = new HashMap<>();
        Map<String, Integer> discountData = new HashMap<>();

        menuData.put("Burger", new MenuItem("Main Course", 8.99));
        menuData.put("Fries", new MenuItem("Side", 3.49));
        menuData.put("Soda", new MenuItem("Drink", 1.99));

        discountData.put("Main Course", 10);
        discountData.put("Side", 5);
        discountData.put("Drink", 20);

        CategorizeMenuItem getCategory = item -> item.category;
        ApplySpecialOffer getDiscount = (item, discounts) -> discounts.getOrDefault(item.category, 0);

        String itemName = "Soda";
        MenuItem item = menuData.get(itemName);

        if (item != null) {
            System.out.println("Category: " + getCategory.getCategory(item));
            System.out.println("Discount: " + getDiscount.discount(item, discountData) + "%");
        } else {
            System.out.println("Item not found: " + itemName);
        }
    }
}
