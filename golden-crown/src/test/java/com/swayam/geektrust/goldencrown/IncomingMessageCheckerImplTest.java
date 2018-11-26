package com.swayam.geektrust.goldencrown;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class IncomingMessageCheckerImplTest {

    @Test
    public void testMessageAccepted_yes_lower_case() {
        // given
        IncomingMessageCheckerImpl testClass = new IncomingMessageCheckerImpl();

        // when
        boolean result = testClass.messageAccepted(SoutherosKingdom.LAND, "a1d22n333a4444p");

        // then
        assertTrue(result);
    }

    @Test
    public void testMessageAccepted_yes_mixed_case() {
        // given
        IncomingMessageCheckerImpl testClass = new IncomingMessageCheckerImpl();

        // when
        boolean result = testClass.messageAccepted(SoutherosKingdom.LAND, "A1D22n333a4444p");

        // then
        assertTrue(result);
    }

    @Test
    public void testMessageAccepted_no() {
        // given
        IncomingMessageCheckerImpl testClass = new IncomingMessageCheckerImpl();

        // when
        boolean result = testClass.messageAccepted(SoutherosKingdom.LAND, "p1d22n333a4444p");

        // then
        assertFalse(result);
    }

}
