package com.swayam.geektrust.goldencrown;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.HashMap;
import java.util.Map;

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

    @Test
    public void testGetCharacterFrequency() {
        // given
        Map<Character, Integer> expected = new HashMap<>();
        expected.put('p', 3);
        expected.put('a', 3);
        expected.put('b', 1);
        expected.put('g', 1);
        expected.put('2', 1);
        expected.put('5', 1);
        expected.put('#', 1);
        IncomingMessageCheckerImpl testClass = new IncomingMessageCheckerImpl();

        // when
        Map<Character, Integer> result = testClass.getCharacterFrequency("pAaaPP#bG25");

        // then
        assertEquals(expected, result);
    }

}