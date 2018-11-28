package com.swayam.geektrust.goldencrown.service.command;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.HashSet;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import com.swayam.geektrust.goldencrown.model.Kingdom;
import com.swayam.geektrust.goldencrown.model.KingdomData;
import com.swayam.geektrust.goldencrown.service.KingdomService;

public class AlliesOfKingFinderCommandTest {

    @Rule
    public ExpectedException thrown = ExpectedException.none();

    @Test
    public void testCanExecute_yes() {
        // given
        AlliesOfKingFinderCommand testClass = new AlliesOfKingFinderCommand(null);

        // when
        boolean result = testClass.canExecute("Allies of King Shan?");

        // then
        assertTrue(result);
    }

    @Test
    public void testGetNameOfKing() {
        // given
        AlliesOfKingFinderCommand testClass = new AlliesOfKingFinderCommand(null);

        // when
        String result = testClass.getNameOfKing("Allies of   King   ABCDEFG  ?");

        // then
        assertEquals("abcdefg", result);
    }

    @Test
    public void testGetKingdom_found_match() {
        // given
        KingdomService kingdomService = mock(KingdomService.class);

        when(kingdomService.getKingdomsInSoutheros()).thenReturn(
                new HashSet<>(Arrays.asList(new KingdomData(Kingdom.AIR, "someAnimal", "ShaN"), new KingdomData(Kingdom.LAND, "someAnimal", "ShaN34"),
                        new KingdomData(Kingdom.WATER, "someAnimal", "67shan"), new KingdomData(Kingdom.ICE, "someAnimal", null))));

        AlliesOfKingFinderCommand testClass = new AlliesOfKingFinderCommand(kingdomService);

        // when
        Kingdom result = testClass.getKingdom("shan");

        // then
        assertEquals(Kingdom.AIR, result);
    }

    @Test
    public void testGetKingdom_no_match() {
        // given
        KingdomService kingdomService = mock(KingdomService.class);

        AlliesOfKingFinderCommand testClass = new AlliesOfKingFinderCommand(kingdomService);

        // when, then
        thrown.expect(IllegalArgumentException.class);
        thrown.expectMessage("No matching Kingdom found for the King named shan");
        testClass.getKingdom("shan");

    }

}
