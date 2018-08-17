package com.he.shoppingCart;

import java.math.BigDecimal;
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

    public void addItem(Product product, int amount) {
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

        String priceInfoTemplate = "%s %d %s";
        items.entrySet().stream().map((Entry<Product, Integer> productEntry) -> {
            return String.format(priceInfoTemplate, productEntry.getKey().getName(), productEntry.getValue(),
                    productEntry.getKey().getPrice().setScale(2, BigDecimal.ROUND_HALF_EVEN));
        }).forEach(System.out::println);

        BigDecimal totalAmount = items.entrySet().stream().map((Entry<Product, Integer> productEntry) -> {
            return productEntry.getKey().getPrice().multiply(new BigDecimal(productEntry.getValue()));
        }).reduce((BigDecimal left, BigDecimal right) -> {
            return left.add(right);
        }).get().setScale(2, BigDecimal.ROUND_HALF_EVEN);

        System.out.println("Total price: " + totalAmount);
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
