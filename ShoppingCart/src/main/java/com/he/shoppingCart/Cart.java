package com.he.shoppingCart;

import java.util.HashMap;
import java.util.Map;

public class Cart {

    private final Inventory inventory;

    private final Map<Product, Integer> items = new HashMap<>();

    public Cart() {
        // have scope for DI
        inventory = Inventory.getInstance();
    }

    public void addItem(Product product, int amount) {
        if (amount <= 0) {
            throw new IllegalArgumentException("the quantity should be greater than 0");
        }
        int availableQuantity = inventory.getAvailableAmount(product);

        if (availableQuantity < amount) {
            throw new IllegalStateException("stock is not available for the given product");
        }

        // add to cart
        items.put(product, amount);

        // remove from inventory
        Map<Product, Integer> itemsToRemove = new HashMap<>();
        itemsToRemove.put(product, amount);
        inventory.removeItems(itemsToRemove);

    }

    public void removeItem(Product product) {
        if (!items.containsKey(product)) {
            throw new IllegalArgumentException("the product does not exist in the shopping cart");
        }

        int existingQuantity = items.get(product);
        // remove from cart
        items.put(product, existingQuantity - 1);

        // add to inventory
        Map<Product, Integer> itemsToAdd = new HashMap<>();
        itemsToAdd.put(product, existingQuantity - 1);
        inventory.addItems(itemsToAdd);

    }

    public void generateInvoice() {
        throw new UnsupportedOperationException();
    }

    public void checkOut() {
        throw new UnsupportedOperationException();
    }

    public void emptyCart() {
        throw new UnsupportedOperationException();
    }

}
