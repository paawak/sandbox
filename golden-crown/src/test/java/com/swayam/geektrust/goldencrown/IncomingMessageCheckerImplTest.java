package com.swayam.geektrust.goldencrown;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.HashMap;
import java.util.Map;

import org.junit.Test;

import com.swayam.geektrust.goldencrown.model.KingdomImpl;

public class IncomingMessageCheckerImplTest {

    @Test
    public void testMessageAccepted_yes_lower_case() {
        // given
        IncomingMessageCheckerImpl testClass = new IncomingMessageCheckerImpl();

        // when
        boolean result = testClass.messageAccepted(new KingdomImpl("PANDA"), "a1d22n333a4444p");

        // then
        assertTrue(result);
    }

    @Test
    public void testMessageAccepted_yes_mixed_case() {
        // given
        IncomingMessageCheckerImpl testClass = new IncomingMessageCheckerImpl();

        // when
        boolean result = testClass.messageAccepted(new KingdomImpl("PANDA"), "A1D22n333a4444pPP");

        // then
        assertTrue(result);
    }

    @Test
    public void testMessageAccepted_yes_1() {
        // given
        IncomingMessageCheckerImpl testClass = new IncomingMessageCheckerImpl();

        // when
        boolean result = testClass.messageAccepted(new KingdomImpl("owl"), "Let’s swing the sword together");

        // then
        assertTrue(result);
    }

    @Test
    public void testMessageAccepted_yes_2() {
        // given
        IncomingMessageCheckerImpl testClass = new IncomingMessageCheckerImpl();

        // when
        boolean result = testClass.messageAccepted(new KingdomImpl("mammoTH"), "Ahoy! Fight for me with men and money");

        // then
        assertTrue(result);
    }

    @Test
    public void testMessageAccepted_yes_3() {
        // given
        IncomingMessageCheckerImpl testClass = new IncomingMessageCheckerImpl();

        // when
        boolean result = testClass.messageAccepted(new KingdomImpl("Dragon"), "Drag on Martin!");

        // then
        assertTrue(result);
    }

    @Test
    public void testMessageAccepted_no_1() {
        // given
        IncomingMessageCheckerImpl testClass = new IncomingMessageCheckerImpl();

        // when
        boolean result = testClass.messageAccepted(new KingdomImpl("PANDA"), "p1d22n333a4444p");

        // then
        assertFalse(result);
    }

    @Test
    public void testMessageAccepted_no_2() {
        // given
        IncomingMessageCheckerImpl testClass = new IncomingMessageCheckerImpl();

        // when
        boolean result = testClass.messageAccepted(new KingdomImpl("Dragon"), "peterpan345");

        // then
        assertFalse(result);
    }

    @Test
    public void testMessageAccepted_no_3() {
        // given
        IncomingMessageCheckerImpl testClass = new IncomingMessageCheckerImpl();

        // when
        boolean result = testClass.messageAccepted(new KingdomImpl("Octopus"), "Summer is coming");

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
