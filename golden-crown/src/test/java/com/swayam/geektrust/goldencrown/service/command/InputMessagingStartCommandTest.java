package com.swayam.geektrust.goldencrown.service.command;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class InputMessagingStartCommandTest {

    @Test
    public void testCanExecute() {
        // given
        InputMessagingStartCommand testClass = new InputMessagingStartCommand(null);

        // when
        boolean result = testClass.canExecute("input  Messages to   Kingdoms   from King ccccD:");

        // then
        assertTrue(result);
    }

    @Test
    public void testGetKingName() {
        // given
        InputMessagingStartCommand testClass = new InputMessagingStartCommand(null);

        // when
        String result = testClass.getKingName("input  Messages to   Kingdoms   from King ccccD:");

        // then
        assertEquals("ccccD", result);
    }

}
