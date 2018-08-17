package com.he.shoppingCart;

public class Cart {

    private final Inventory inventory;

    public Cart() {
        // have scope for DI
        inventory = Inventory.getInstance();
    }

    public void addItem(Product product, Integer amount) {
        throw new UnsupportedOperationException();
    }

    public void removeItem(Product product) {
        throw new UnsupportedOperationException();
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
