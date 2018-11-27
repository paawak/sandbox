package com.swayam.geektrust.goldencrown.service.command;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;

import org.junit.Test;

import com.swayam.geektrust.goldencrown.model.Kingdom;
import com.swayam.geektrust.goldencrown.model.KingdomData;
import com.swayam.geektrust.goldencrown.service.KingdomService;

public class FindAlliesOfRulerCommandTest {

    @Test
    public void testExecute_no_allies() {
	// given
	KingdomService kingdomService = mock(KingdomService.class);
	when(kingdomService.getAlliesOfRuler()).thenReturn(Collections.emptySet());

	FindAlliesOfRulerCommand testClass = new FindAlliesOfRulerCommand(kingdomService);

	// when
	String result = testClass.execute(null);

	// then
	assertEquals("None", result);
    }

    @Test
    public void testExecute_single_ally() {
	// given
	KingdomService kingdomService = mock(KingdomService.class);
	when(kingdomService.getAlliesOfRuler()).thenReturn(new HashSet<>(Arrays.asList(new KingdomData(Kingdom.AIR, "someAnimal", null))));

	FindAlliesOfRulerCommand testClass = new FindAlliesOfRulerCommand(kingdomService);

	// when
	String result = testClass.execute(null);

	// then
	assertEquals("AIR", result);
    }

    @Test
    public void testExecute_multiple_allies() {
	// given
	KingdomService kingdomService = mock(KingdomService.class);
	when(kingdomService.getAlliesOfRuler()).thenReturn(new HashSet<>(Arrays.asList(new KingdomData(Kingdom.AIR, "someAnimal", null), new KingdomData(Kingdom.LAND, "someAnimal", null),
		new KingdomData(Kingdom.WATER, "someAnimal", null), new KingdomData(Kingdom.ICE, "someAnimal", null))));

	FindAlliesOfRulerCommand testClass = new FindAlliesOfRulerCommand(kingdomService);

	// when
	String result = testClass.execute(null);

	// then
	assertEquals("AIR, ICE, LAND, WATER", result);
    }

}
