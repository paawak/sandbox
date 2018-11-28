package com.swayam.geektrust.goldencrown.service.command;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import com.swayam.geektrust.goldencrown.model.Kingdom;

public class SingleMessageCommandTest {

    @Test
    public void testCanExecute_yes() {
        // given
        SingleMessageCommand testClass = new SingleMessageCommand(null);

        // when
        boolean result = testClass.canExecute("Input: Air, \"Let’s swing the sword together\"");

        // then
        assertTrue(result);
    }

    @Test
    public void testCanExecute_no() {
        // given
        SingleMessageCommand testClass = new SingleMessageCommand(null);

        // when
        boolean result = testClass.canExecute("abshgik;l;");

        // then
        assertFalse(result);
    }

    @Test
    public void testGetKingdomToSendMessage() {
        // given
        SingleMessageCommand testClass = new SingleMessageCommand(null);

        // when
        Kingdom result = testClass.getKingdomToSendMessage("Input: Air, \"Let’s swing the sword together\"");

        // then
        assertEquals(Kingdom.AIR, result);
    }

    @Test
    public void testGetMessageToSend() {
        // given
        SingleMessageCommand testClass = new SingleMessageCommand(null);

        // when
        String result = testClass.getMessageToSend("Input: Air, \"Let’s swing the sword together\"");

        // then
        assertEquals("Let’s swing the sword together", result);
    }

}
