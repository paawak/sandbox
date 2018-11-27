package com.swayam.geektrust.goldencrown.service.command;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import com.swayam.geektrust.goldencrown.service.KingdomService;

public class AlliesOfKingFinderCommandTest {

    @Rule
    public ExpectedException thrown = ExpectedException.none();

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
