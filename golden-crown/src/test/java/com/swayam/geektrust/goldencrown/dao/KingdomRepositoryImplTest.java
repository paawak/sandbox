package com.swayam.geektrust.goldencrown.dao;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.mock;

import java.io.IOException;
import java.io.Reader;
import java.io.StringReader;
import java.io.UncheckedIOException;
import java.util.AbstractMap.SimpleEntry;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import com.swayam.geektrust.goldencrown.model.Kingdom;
import com.swayam.geektrust.goldencrown.model.KingdomData;

public class KingdomRepositoryImplTest {

    @Rule
    public ExpectedException thrown = ExpectedException.none();

    @Test
    public void testParseDataLine_yes_only_emblem_1() {
	// given
	KingdomRepositoryImpl testClass = new KingdomRepositoryImpl();

	// when
	Entry<Kingdom, KingdomData> result = testClass.parseDataLine(" fire  =  someAnimal , ");

	// then
	assertEquals(new SimpleEntry<>(Kingdom.FIRE, new KingdomData(Kingdom.FIRE, "someAnimal", null)), result);
    }

    @Test
    public void testParseDataLine_yes_only_emblem_2() {
	// given
	KingdomRepositoryImpl testClass = new KingdomRepositoryImpl();

	// when
	Entry<Kingdom, KingdomData> result = testClass.parseDataLine(" fire  =  someAnimal  ");

	// then
	assertEquals(new SimpleEntry<>(Kingdom.FIRE, new KingdomData(Kingdom.FIRE, "someAnimal", null)), result);
    }

    @Test
    public void testParseDataLine_yes_emblem_and_king() {
	// given
	KingdomRepositoryImpl testClass = new KingdomRepositoryImpl();

	// when
	Entry<Kingdom, KingdomData> result = testClass.parseDataLine(" fire  =  someAnimal , myKing ");

	// then
	assertEquals(new SimpleEntry<>(Kingdom.FIRE, new KingdomData(Kingdom.FIRE, "someAnimal", "myKing")), result);
    }

    @Test
    public void testParseDataLine_no_empty_line() {
	// given
	KingdomRepositoryImpl testClass = new KingdomRepositoryImpl();

	// when, then
	thrown.expect(IllegalArgumentException.class);
	thrown.expectMessage("Wrong input format: expecting the line to be of the format: <Kingdom Name>=<Kingdom Data>");
	testClass.parseDataLine("  ");

    }

    @Test
    public void testParseDataLine_no_empty_emblem() {
	// given
	KingdomRepositoryImpl testClass = new KingdomRepositoryImpl();

	// when, then
	thrown.expect(IllegalArgumentException.class);
	thrown.expectMessage("The <Animal Emblem> cannot be empty");
	testClass.parseDataLine("  fire  =   ");

    }

    @Test
    public void testParseDataLine_no_invalid_kingdom() {
	// given
	KingdomRepositoryImpl testClass = new KingdomRepositoryImpl();

	// when, then
	thrown.expect(IllegalArgumentException.class);
	thrown.expectMessage("No enum constant " + Kingdom.class.getName() + ".FIRE23");
	testClass.parseDataLine("  fire23  =   ");

    }

    @Test
    public void testParseDataLine_no_more_than_2_data_elements() {
	// given
	KingdomRepositoryImpl testClass = new KingdomRepositoryImpl();

	// when, then
	thrown.expect(UnsupportedOperationException.class);
	thrown.expectMessage("This <Kingdom Data> format is not yet supported");
	testClass.parseDataLine("  fire  =  aaa,bbb,ccc ");

    }

    @Test
    public void testReadFileForAvailableKingdoms_yes() {
	// given
	String inputLines = " fire  =  someAnimal1 , myKing \n" + " ice  =  someAnimal2 ,  \n";

	Map<Kingdom, KingdomData> expected = new HashMap<>();
	expected.put(Kingdom.FIRE, new KingdomData(Kingdom.FIRE, "someAnimal1", "myKing"));
	expected.put(Kingdom.ICE, new KingdomData(Kingdom.ICE, "someAnimal2", null));

	KingdomRepositoryImpl testClass = new KingdomRepositoryImpl();

	// when
	Map<Kingdom, KingdomData> result = testClass.readFileForAvailableKingdoms(new StringReader(inputLines));

	// then
	assertEquals(expected, result);
    }

    @Test
    public void testReadFileForAvailableKingdoms_no() throws IOException {
	// given
	Reader mockReader = mock(Reader.class);

	doThrow(IOException.class).when(mockReader).read(any(char[].class), any(Integer.class), any(Integer.class));

	KingdomRepositoryImpl testClass = new KingdomRepositoryImpl();

	// when, then
	thrown.expect(UncheckedIOException.class);
	testClass.readFileForAvailableKingdoms(mockReader);

    }

    @Test
    public void testSaveSuccessfulMessage_1() {
	// given
	Set<Kingdom> expected = new HashSet<>(Arrays.asList(Kingdom.WATER));
	KingdomRepositoryImpl testClass = new KingdomRepositoryImpl();

	// when
	testClass.saveSuccessfulMessage(Kingdom.AIR, Kingdom.WATER);

	// then
	assertEquals(expected, testClass.getSuccessfulMessages(Kingdom.AIR));
    }

    @Test
    public void testSaveSuccessfulMessage_2() {
	// given
	Set<Kingdom> expected = new HashSet<>(Arrays.asList(Kingdom.WATER, Kingdom.LAND));
	KingdomRepositoryImpl testClass = new KingdomRepositoryImpl();

	// when
	testClass.saveSuccessfulMessage(Kingdom.AIR, Kingdom.WATER);
	testClass.saveSuccessfulMessage(Kingdom.AIR, Kingdom.LAND);

	// then
	assertEquals(expected, testClass.getSuccessfulMessages(Kingdom.AIR));
    }

    @Test
    public void testSaveSuccessfulMessage_3() {
	// given
	KingdomRepositoryImpl testClass = new KingdomRepositoryImpl();

	// when
	testClass.saveSuccessfulMessage(Kingdom.AIR, Kingdom.WATER);
	testClass.saveSuccessfulMessage(Kingdom.WATER, Kingdom.AIR);

	// then
	assertEquals(new HashSet<>(Arrays.asList(Kingdom.WATER)), testClass.getSuccessfulMessages(Kingdom.AIR));
	assertEquals(new HashSet<>(Arrays.asList(Kingdom.AIR)), testClass.getSuccessfulMessages(Kingdom.WATER));
    }

}
