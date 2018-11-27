package com.swayam.geektrust.goldencrown.dao;

import static org.junit.Assert.assertEquals;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import org.junit.Test;

import com.swayam.geektrust.goldencrown.model.Kingdom;

public class KingdomRepositoryImplTest {

    @Test
    public void testSaveSuccessfulMessage_1() {
	// given
	Set<Kingdom> expected = new HashSet<>(Arrays.asList(Kingdom.WATER));
	KingdomRepositoryImpl testClass = new KingdomRepositoryImpl(null);

	// when
	testClass.saveSuccessfulMessage(Kingdom.AIR, Kingdom.WATER);

	// then
	assertEquals(expected, testClass.getSuccessfulMessages(Kingdom.AIR));
    }

    @Test
    public void testSaveSuccessfulMessage_2() {
	// given
	Set<Kingdom> expected = new HashSet<>(Arrays.asList(Kingdom.WATER, Kingdom.LAND));
	KingdomRepositoryImpl testClass = new KingdomRepositoryImpl(null);

	// when
	testClass.saveSuccessfulMessage(Kingdom.AIR, Kingdom.WATER);
	testClass.saveSuccessfulMessage(Kingdom.AIR, Kingdom.LAND);

	// then
	assertEquals(expected, testClass.getSuccessfulMessages(Kingdom.AIR));
    }

    @Test
    public void testSaveSuccessfulMessage_3() {
	// given
	KingdomRepositoryImpl testClass = new KingdomRepositoryImpl(null);

	// when
	testClass.saveSuccessfulMessage(Kingdom.AIR, Kingdom.WATER);
	testClass.saveSuccessfulMessage(Kingdom.WATER, Kingdom.AIR);

	// then
	assertEquals(new HashSet<>(Arrays.asList(Kingdom.WATER)), testClass.getSuccessfulMessages(Kingdom.AIR));
	assertEquals(new HashSet<>(Arrays.asList(Kingdom.AIR)), testClass.getSuccessfulMessages(Kingdom.WATER));
    }

}
