package com.he.shoppingCart;

import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.stream.Collectors;

public class Cart {

    private final Inventory inventory;

    private final Map<Product, Integer> items = new HashMap<>();

    public Cart() {
        // have scope for DI
        inventory = Inventory.getInstance();
    }

    public void addItem(Product product, Integer amount) {
        if (amount <= 0) {
            throw new IllegalArgumentException("the quantity should be greater than 0");
        }

        items.put(product, amount);

    }

    public void removeItem(Product product) {
        if (!items.containsKey(product)) {
            throw new IllegalArgumentException("the product does not exist in the shopping cart");
        }

        items.remove(product);

    }

    public void generateInvoice() {

        DecimalFormat formatter = new DecimalFormat("#######.00");
        String priceInfoTemplate = "%s %d %s";
        items.entrySet().stream().sorted((Entry<Product, Integer> entry1, Entry<Product, Integer> entry2) -> {
            return entry1.getKey().getName().compareTo(entry2.getKey().getName());
        }).map((Entry<Product, Integer> productEntry) -> {
            return String.format(priceInfoTemplate, productEntry.getKey().getName(), productEntry.getValue(),
                    formatter.format(productEntry.getKey().getPrice()));
        }).forEach(System.out::println);

        double totalAmount = items.entrySet().stream().mapToDouble((Entry<Product, Integer> productEntry) -> {
            return productEntry.getKey().getPrice() * productEntry.getValue();
        }).sum();

        System.out.println("Total price: " + formatter.format(totalAmount));
    }

    public void checkOut() {
        if (items.isEmpty()) {
            return;
        }

        Map<Product, Integer> itemsBought = items.entrySet().stream().filter((Entry<Product, Integer> productEntry) -> {
            int availableQuantity = inventory.getAvailableAmount(productEntry.getKey());
            boolean stockAvailable = availableQuantity >= productEntry.getValue();
            if (!stockAvailable) {
                System.err.println("Stock is not availble for the product: " + productEntry.getKey().getName() + ", ignoring");
            }
            return stockAvailable;
        }).collect(Collectors.toMap((Entry<Product, Integer> productEntry) -> {
            return productEntry.getKey();
        }, (Entry<Product, Integer> productEntry) -> {
            return productEntry.getValue();
        }));

        inventory.removeItems(itemsBought);

        emptyCart();
    }

    public void emptyCart() {

        if (items.isEmpty()) {
            return;
        }

        items.clear();

    }

}
