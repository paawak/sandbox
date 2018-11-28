package com.swayam.geektrust.goldencrown.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import com.swayam.geektrust.goldencrown.dao.KingdomRepository;
import com.swayam.geektrust.goldencrown.model.Kingdom;
import com.swayam.geektrust.goldencrown.model.KingdomData;

public class KingdomServiceImplTest {

    @Rule
    public ExpectedException thrown = ExpectedException.none();

    @Test
    public void testGetRuler_empty() {

        // given
        KingdomRepository kingdomRepository = mock(KingdomRepository.class);
        when(kingdomRepository.getSuccessfulMessages()).thenReturn(Collections.emptyMap());

        KingdomServiceImpl testClass = new KingdomServiceImpl(kingdomRepository, null);

        // when
        Optional<KingdomData> result = testClass.getRuler();

        // then
        assertFalse(result.isPresent());

    }

    @Test
    public void testGetRuler_yes() {

        // given
        KingdomRepository kingdomRepository = mock(KingdomRepository.class);

        Map<Kingdom, Set<Kingdom>> successfulMessages = new HashMap<>();
        successfulMessages.put(Kingdom.AIR, new HashSet<>(Arrays.asList(Kingdom.LAND, Kingdom.WATER, Kingdom.FIRE)));
        successfulMessages.put(Kingdom.FIRE, new HashSet<>(Arrays.asList(Kingdom.LAND, Kingdom.WATER)));

        when(kingdomRepository.getSuccessfulMessages()).thenReturn(successfulMessages);
        when(kingdomRepository.getKingdomData(Kingdom.AIR)).thenReturn(new KingdomData(Kingdom.AIR, "nnnn", null));

        KingdomServiceImpl testClass = new KingdomServiceImpl(kingdomRepository, null);

        // when
        Optional<KingdomData> result = testClass.getRuler();

        // then
        assertEquals(new KingdomData(Kingdom.AIR, "nnnn", null), result.get());

    }

    @Test
    public void testGetRuler_no() {

        // given
        KingdomRepository kingdomRepository = mock(KingdomRepository.class);

        Map<Kingdom, Set<Kingdom>> successfulMessages = new HashMap<>();
        successfulMessages.put(Kingdom.AIR, new HashSet<>(Arrays.asList(Kingdom.LAND, Kingdom.WATER)));
        successfulMessages.put(Kingdom.FIRE, new HashSet<>(Arrays.asList(Kingdom.LAND, Kingdom.WATER)));

        when(kingdomRepository.getSuccessfulMessages()).thenReturn(successfulMessages);

        KingdomServiceImpl testClass = new KingdomServiceImpl(kingdomRepository, null);

        // when
        Optional<KingdomData> result = testClass.getRuler();

        // then
        assertFalse(result.isPresent());

    }

    @Test
    public void testGetRuler_exception() {

        // given
        KingdomRepository kingdomRepository = mock(KingdomRepository.class);

        Map<Kingdom, Set<Kingdom>> successfulMessages = new HashMap<>();
        successfulMessages.put(Kingdom.AIR, new HashSet<>(Arrays.asList(Kingdom.LAND, Kingdom.WATER, Kingdom.FIRE)));
        successfulMessages.put(Kingdom.FIRE, new HashSet<>(Arrays.asList(Kingdom.LAND, Kingdom.WATER, Kingdom.AIR)));

        when(kingdomRepository.getSuccessfulMessages()).thenReturn(successfulMessages);

        KingdomServiceImpl testClass = new KingdomServiceImpl(kingdomRepository, null);

        // when, then
        thrown.expect(IllegalArgumentException.class);
        thrown.expectMessage("More than 1 Rulers found!");
        testClass.getRuler();

    }

}
