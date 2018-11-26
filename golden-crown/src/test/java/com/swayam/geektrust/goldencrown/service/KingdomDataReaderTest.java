package com.swayam.geektrust.goldencrown.service;

import static org.junit.Assert.assertEquals;

import java.util.AbstractMap.SimpleEntry;
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
    public void testParseDataLine_no_more_than_2_data_elements() {
        // given
        KingdomDataReader testClass = new KingdomDataReader();

        // when, then
        thrown.expect(UnsupportedOperationException.class);
        thrown.expectMessage("This <Kingdom Data> format is not yet supported");
        testClass.parseDataLine("  fire  =  aaa,bbb,ccc ");

    }

}
