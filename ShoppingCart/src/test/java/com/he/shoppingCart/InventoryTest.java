package com.he.shoppingCart;

import static org.junit.Assert.assertEquals;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import com.he.shoppingCart.Product;

public class InventoryTest {

    @Rule
    public ExpectedException thrown = ExpectedException.none();

    @Test
    public void testAddItems_happy_path() {
        // given
        Map<Product, Integer> items = new HashMap<>();
        items.put(new Product("Basmati Rice", new BigDecimal("102.89")), 100);
        items.put(new Product("Pitted Dates", new BigDecimal("56.78")), 200);
        items.put(new Product("Mango Alphanso", new BigDecimal("500.00")), 300);
        items.put(new Product("Tooth Paste Colgate", new BigDecimal("230.08")), 400);

        Inventory testClass = Inventory.getInstance();

        // when
        testClass.addItems(items);

        // then
        assertEquals(100, testClass.getAvailableAmount(new Product("Basmati Rice", new BigDecimal("102.89"))));
        assertEquals(200, testClass.getAvailableAmount(new Product("Pitted Dates", new BigDecimal("56.78"))));
        assertEquals(300, testClass.getAvailableAmount(new Product("Mango Alphanso", new BigDecimal("500.00"))));
        assertEquals(400, testClass.getAvailableAmount(new Product("Tooth Paste Colgate", new BigDecimal("230.08"))));
        assertEquals(0, testClass.getAvailableAmount(new Product("Ashirvad Atta", new BigDecimal("678.09"))));

        // cleanup
        testClass.clearMasterRoster();
    }

    @Test
    public void testAddItems_happy_path_existing() {
        // given
        Map<Product, Integer> items1 = new HashMap<>();
        items1.put(new Product("Basmati Rice", new BigDecimal("102.89")), 100);
        items1.put(new Product("Pitted Dates", new BigDecimal("56.78")), 200);
        items1.put(new Product("Mango Alphanso", new BigDecimal("500.00")), 300);
        items1.put(new Product("Tooth Paste Colgate", new BigDecimal("230.08")), 400);

        Map<Product, Integer> items2 = new HashMap<>();
        items2.put(new Product("Basmati Rice", new BigDecimal("102.89")), 100);
        items2.put(new Product("Pitted Dates", new BigDecimal("56.78")), 200);

        Inventory testClass = Inventory.getInstance();

        // when
        testClass.addItems(items1);
        testClass.addItems(items2);

        // then
        assertEquals(200, testClass.getAvailableAmount(new Product("Basmati Rice", new BigDecimal("102.89"))));
        assertEquals(400, testClass.getAvailableAmount(new Product("Pitted Dates", new BigDecimal("56.78"))));
        assertEquals(300, testClass.getAvailableAmount(new Product("Mango Alphanso", new BigDecimal("500.00"))));
        assertEquals(400, testClass.getAvailableAmount(new Product("Tooth Paste Colgate", new BigDecimal("230.08"))));

        // cleanup
        testClass.clearMasterRoster();
    }

    @Test
    public void testAddItems_negative_quantity() {
        // given
        Map<Product, Integer> items = new HashMap<>();
        items.put(new Product("Basmati Rice", new BigDecimal("102.89")), 100);
        items.put(new Product("Pitted Dates", new BigDecimal("56.78")), 200);
        items.put(new Product("Mango Alphanso", new BigDecimal("500.00")), 300);
        items.put(new Product("Tooth Paste Colgate", new BigDecimal("230.08")), -2);

        Inventory testClass = Inventory.getInstance();

        thrown.expect(IllegalStateException.class);
        thrown.expectMessage("cannot add negative quantity for the product with name: Tooth Paste Colgate");

        // when, then
        try {
            testClass.addItems(items);
        } finally {
            // cleanup
            testClass.clearMasterRoster();
        }
    }

}
