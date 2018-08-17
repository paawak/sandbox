package com.he.shoppingCart;

import java.util.Map;

public class Inventory {

    private static final Inventory INSTANCE = new Inventory();

    private Inventory() {

    }

    public static Inventory getInstance() {
        return INSTANCE;
    }

    public void addItems(Map<Product, Integer> items) {
        throw new UnsupportedOperationException();
    }

    public void removeItems(Map<Product, Integer> items) {
        throw new UnsupportedOperationException();
    }

    public Integer getAvailableAmount(Product product) {
        throw new UnsupportedOperationException();
    }

}
