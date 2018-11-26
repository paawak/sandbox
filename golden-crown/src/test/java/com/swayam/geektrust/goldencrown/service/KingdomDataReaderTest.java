package com.swayam.geektrust.goldencrown.service;

import static org.junit.Assert.assertEquals;

import java.util.AbstractMap.SimpleEntry;
import java.util.Map.Entry;

import org.junit.Test;

import com.swayam.geektrust.goldencrown.model.Kingdom;
import com.swayam.geektrust.goldencrown.model.KingdomImpl;
import com.swayam.geektrust.goldencrown.model.SoutherosKingdom;

public class KingdomDataReaderTest {

    @Test
    public void testParseDataLine_yes_only_emblem() {
        // given
        KingdomDataReader testClass = new KingdomDataReader();

        // when
        Entry<SoutherosKingdom, Kingdom> result = testClass.parseDataLine(" fire  =  someAnimal , ");

        // then
        assertEquals(new SimpleEntry<>(SoutherosKingdom.FIRE, new KingdomImpl("someAnimal", null)), result);
    }

}
