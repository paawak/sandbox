package com.swayam.geektrust.goldencrown.service.command;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class RulerFinderCommandTest {

    @Test
    public void testCanExecute_mixed_case() {
        // given
        RulerFinderCommand testClass = new RulerFinderCommand(null);

        // when
        boolean result = testClass.canExecute("Who is the ruler of Southeros?");

        // then
        assertTrue(result);
    }

    @Test
    public void testCanExecute_lower_case() {
        // given
        RulerFinderCommand testClass = new RulerFinderCommand(null);

        // when
        boolean result = testClass.canExecute("who is the ruler of southeros?");

        // then
        assertTrue(result);
    }

    @Test
    public void testCanExecute_upper_case() {
        // given
        RulerFinderCommand testClass = new RulerFinderCommand(null);

        // when
        boolean result = testClass.canExecute("WHO IS THE RULER OF SOUTHEROS?");

        // then
        assertTrue(result);
    }

    @Test
    public void testCanExecute_many_spaces() {
        // given
        RulerFinderCommand testClass = new RulerFinderCommand(null);

        // when
        boolean result = testClass.canExecute("Who     is    the    ruler    of    Southeros   ?");

        // then
        assertTrue(result);
    }

}
