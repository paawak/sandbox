package com.he.shoppingCart;

import static org.junit.Assert.assertEquals;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.Test;

public class CartTest {

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

        testClass.addItem(new Product("Pitted Dates", new BigDecimal("56.78")), 4);
        testClass.addItem(new Product("Basmati Rice", new BigDecimal("102.89")), 2);
        testClass.addItem(new Product("Mango Alphanso", new BigDecimal("500.00")), 5);
        testClass.addItem(new Product("Tooth Paste Colgate", new BigDecimal("230.08")), 8);

        // when
        testClass.generateInvoice();

        // then
        printStream.flush();

        List<String> actualLines = Arrays.asList(new String(bos.toByteArray()).split("\n"));

        assertEquals(expectedLines, actualLines);

        // cleanup
        System.setOut(originalPrintStream);
        Inventory.getInstance().clearMasterRoster();

    }

}
