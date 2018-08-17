package com.he.shoppingCart.inventory;

import java.util.HashMap;
import java.util.Map;

import com.he.shoppingCart.Product;

public class Inventory {

    private static final Inventory INSTANCE = new Inventory();

    private final Map<Product, Integer> masterRoster = new HashMap<>();

    /** Visible for testing */
    Inventory() {

    }

    public static Inventory getInstance() {
        return INSTANCE;
    }

    public void addItems(Map<Product, Integer> items) {
        items.forEach((Product product, Integer quantity) -> {
            if (quantity < 0) {
                throw new IllegalStateException("cannot add negative quantity for the product with name: " + product.getName());
            }
            Integer existingQuantity = masterRoster.get(product);

            int newQuantity;

            if ((existingQuantity == null) || (existingQuantity == 0)) {
                newQuantity = quantity;
            } else {
                newQuantity = quantity + existingQuantity;
            }
            masterRoster.put(product, newQuantity);
        });
    }

    public void removeItems(Map<Product, Integer> items) {
        items.forEach((Product product, Integer quantity) -> {
            if (quantity < 0) {
                throw new IllegalStateException("cannot add negative quantity for the product with name: " + product.getName());
            }
            Integer existingQuantity = masterRoster.get(product);

            if ((existingQuantity == null) || (existingQuantity == 0) || (existingQuantity < quantity)) {
                throw new IllegalStateException("Stock does not exist for the product with name: " + product.getName() + ", cannot remove");
            } else if (existingQuantity == quantity) {
                masterRoster.remove(product);
            } else {
                masterRoster.put(product, existingQuantity - quantity);
            }

        });
    }

    public int getAvailableAmount(Product product) {
        Integer quantity = masterRoster.get(product);
        if (quantity == null) {
            return 0;
        }
        return quantity;
    }

}
