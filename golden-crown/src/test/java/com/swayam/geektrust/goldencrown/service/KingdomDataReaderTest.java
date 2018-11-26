package com.swayam.geektrust.goldencrown.service;

import static org.junit.Assert.assertEquals;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.io.IOException;
import java.io.Reader;
import java.io.StringReader;
import java.util.AbstractMap.SimpleEntry;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import com.swayam.geektrust.goldencrown.model.Kingdom;
import com.swayam.geektrust.goldencrown.model.KingdomImpl;
import com.swayam.geektrust.goldencrown.model.SoutherosKingdom;

public class KingdomDataReaderTest {

    @Rule
    public ExpectedException thrown = ExpectedException.none();

    @Test
    public void testParseDataLine_yes_only_emblem_1() {
        // given
        KingdomDataReader testClass = new KingdomDataReader();

        // when
        Entry<SoutherosKingdom, Kingdom> result = testClass.parseDataLine(" fire  =  someAnimal , ");

        // then
        assertEquals(new SimpleEntry<>(SoutherosKingdom.FIRE, new KingdomImpl("someAnimal", null)), result);
    }

    @Test
    public void testParseDataLine_yes_only_emblem_2() {
        // given
        KingdomDataReader testClass = new KingdomDataReader();

        // when
        Entry<SoutherosKingdom, Kingdom> result = testClass.parseDataLine(" fire  =  someAnimal  ");

        // then
        assertEquals(new SimpleEntry<>(SoutherosKingdom.FIRE, new KingdomImpl("someAnimal", null)), result);
    }

    @Test
    public void testParseDataLine_yes_emblem_and_king() {
        // given
        KingdomDataReader testClass = new KingdomDataReader();

        // when
        Entry<SoutherosKingdom, Kingdom> result = testClass.parseDataLine(" fire  =  someAnimal , myKing ");

        // then
        assertEquals(new SimpleEntry<>(SoutherosKingdom.FIRE, new KingdomImpl("someAnimal", "myKing")), result);
    }

    @Test
    public void testParseDataLine_no_empty_line() {
        // given
        KingdomDataReader testClass = new KingdomDataReader();

        // when, then
        thrown.expect(IllegalArgumentException.class);
        thrown.expectMessage("Wrong input format: expecting the line to be of the format: <Kingdom Name>=<Kingdom Data>");
        testClass.parseDataLine("  ");

    }

    @Test
    public void testParseDataLine_no_empty_emblem() {
        // given
        KingdomDataReader testClass = new KingdomDataReader();

        // when, then
        thrown.expect(IllegalArgumentException.class);
        thrown.expectMessage("The <Animal Emblem> cannot be empty");
        testClass.parseDataLine("  fire  =   ");

    }

    @Test
    public void testParseDataLine_no_invalid_kingdom() {
        // given
        KingdomDataReader testClass = new KingdomDataReader();

        // when, then
        thrown.expect(IllegalArgumentException.class);
        thrown.expectMessage("No enum constant " + SoutherosKingdom.class.getName() + ".FIRE23");
        testClass.parseDataLine("  fire23  =   ");

    }

    @Test
    public void testParseDataLine_no_more_than_2_data_elements() {
        // given
        KingdomDataReader testClass = new KingdomDataReader();

        // when, then
        thrown.expect(UnsupportedOperationException.class);
        thrown.expectMessage("This <Kingdom Data> format is not yet supported");
        testClass.parseDataLine("  fire  =  aaa,bbb,ccc ");

    }

    @Test
    public void testReadAvailableKingdoms_yes() {
        // given
        String inputLines = " fire  =  someAnimal1 , myKing \n" + " ice  =  someAnimal2 ,  \n";

        Map<SoutherosKingdom, Kingdom> expected = new HashMap<>();
        expected.put(SoutherosKingdom.FIRE, new KingdomImpl("someAnimal1", "myKing"));
        expected.put(SoutherosKingdom.ICE, new KingdomImpl("someAnimal2", null));

        KingdomDataReader testClass = new KingdomDataReader();

        // when
        Map<SoutherosKingdom, Kingdom> result = testClass.readAvailableKingdoms(new StringReader(inputLines));

        // then
        assertEquals(expected, result);
    }

    @Test
    public void testReadAvailableKingdoms_no() throws IOException {
        // given
        Reader mockReader = mock(Reader.class);

        when(mockReader.read(any(char[].class), any(Integer.class), any(Integer.class))).thenThrow(IOException.class);

        Map<SoutherosKingdom, Kingdom> expected = new HashMap<>();
        expected.put(SoutherosKingdom.FIRE, new KingdomImpl("someAnimal1", "myKing"));
        expected.put(SoutherosKingdom.ICE, new KingdomImpl("someAnimal2", null));

        KingdomDataReader testClass = new KingdomDataReader();

        // when
        Map<SoutherosKingdom, Kingdom> result = testClass.readAvailableKingdoms(mockReader);

        // then
        assertEquals(expected, result);
    }

}
