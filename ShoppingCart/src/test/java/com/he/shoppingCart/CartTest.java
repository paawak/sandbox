package com.he.shoppingCart;

import static org.junit.Assert.assertEquals;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

public class CartTest {

    @Rule
    public ExpectedException thrown = ExpectedException.none();

    @Test
    public void testAddItem_happy() {

        // given
        Cart testClass = new Cart();

        // when
        testClass.addItem(new Product("Pitted Dates", 56.78), 4);

        // then
        assertEquals(4, testClass.getQuantityInCart(new Product("Pitted Dates", 56.78)));
    }

    @Test
    public void testAddItem_negative() {

        // given
        Cart testClass = new Cart();

        thrown.expect(IllegalArgumentException.class);
        thrown.expectMessage("the quantity should be greater than 0");

        // when, then
        testClass.addItem(new Product("Pitted Dates", 56.78), -4);
    }

    @Test
    public void testRemoveItem_happy() {

        // given
        Cart testClass = new Cart();

        testClass.addItem(new Product("Pitted Dates", 56.78), 4);

        // when
        testClass.removeItem(new Product("Pitted Dates", 56.78));

        // then
        assertEquals(0, testClass.getQuantityInCart(new Product("Pitted Dates", 56.78)));
    }

    @Test
    public void testRemoveItem_negative() {

        // given
        Cart testClass = new Cart();

        thrown.expect(IllegalArgumentException.class);
        thrown.expectMessage("the product does not exist in the shopping cart");

        // when, then
        testClass.removeItem(new Product("Pitted Dates", 56.78));
    }

    @Test
    public void testGenerateInvoice() {

        // given
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        PrintStream printStream = new PrintStream(bos);

        PrintStream originalPrintStream = System.out;

        System.setOut(printStream);

        List<String> expectedLines = new ArrayList<>();
        expectedLines.add("Basmati Rice 2 102.89");
        expectedLines.add("Mango Alphanso 5 500.00");
        expectedLines.add("Pitted Dates 4 56.78");
        expectedLines.add("Tooth Paste Colgate 8 230.08");
        expectedLines.add("Total price: 4773.54");

        Cart testClass = new Cart();

        testClass.addItem(new Product("Pitted Dates", 56.78), 4);
        testClass.addItem(new Product("Basmati Rice", 102.89), 2);
        testClass.addItem(new Product("Mango Alphanso", 500.00), 5);
        testClass.addItem(new Product("Tooth Paste Colgate", 230.08), 8);

        // when
        testClass.generateInvoice();

        // then
        printStream.flush();

        List<String> actualLines = Arrays.asList(new String(bos.toByteArray()).split("\n"));

        assertEquals(expectedLines, actualLines);

        // cleanup
        System.setOut(originalPrintStream);

    }

    @Test
    public void testCheckOut() {

        // given

        Map<Product, Integer> items = new HashMap<>();
        items.put(new Product("Basmati Rice", 102.89), 100);
        items.put(new Product("Pitted Dates", 56.78), 200);
        items.put(new Product("Mango Alphanso", 500.00), 800);
        items.put(new Product("Tooth Paste Colgate", 230.08), 400);

        Inventory.getInstance().addItems(items);

        Cart testClass = new Cart();

        testClass.addItem(new Product("Pitted Dates", 56.78), 100);
        testClass.addItem(new Product("Tooth Paste Colgate", 230.08), 800);
        testClass.addItem(new Product("Basmati Rice", 102.89), 200);
        testClass.addItem(new Product("Mango Alphanso", 500.00), 500);
        testClass.addItem(new Product("Litchi Bunch", 200.00), 2);
        testClass.removeItem(new Product("Tooth Paste Colgate", 230.08));

        // when
        testClass.checkOut();

        // then
        assertEquals(100, Inventory.getInstance().getAvailableAmount(new Product("Pitted Dates", 56.78)));
        assertEquals(400, Inventory.getInstance().getAvailableAmount(new Product("Tooth Paste Colgate", 230.08)));
        assertEquals(100, Inventory.getInstance().getAvailableAmount(new Product("Basmati Rice", 102.89)));
        assertEquals(300, Inventory.getInstance().getAvailableAmount(new Product("Mango Alphanso", 500.00)));

        assertEquals(0, testClass.getQuantityInCart(new Product("Pitted Dates", 56.78)));
        assertEquals(0, testClass.getQuantityInCart(new Product("Tooth Paste Colgate", 230.08)));
        assertEquals(0, testClass.getQuantityInCart(new Product("Basmati Rice", 102.89)));
        assertEquals(0, testClass.getQuantityInCart(new Product("Mango Alphanso", 500.00)));
        assertEquals(0, testClass.getQuantityInCart(new Product("Litchi Bunch", 200.00)));

        // cleanup
        Inventory.getInstance().clearMasterRoster();

    }

}
