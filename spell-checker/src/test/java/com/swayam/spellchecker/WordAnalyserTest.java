package com.swayam.spellchecker;

import static org.junit.Assert.assertEquals;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import org.junit.Test;

public class WordAnalyserTest {

    @Test
    public void testDeleteChar() {
	// given
	WordAnalyser testClass = new WordAnalyser(null);

	// when
	String result = testClass.deleteChar("abcd", 2);

	// then
	assertEquals("abd", result);
    }

    @Test
    public void testReplaceChar() {
	// given
	WordAnalyser testClass = new WordAnalyser(null);

	// when
	String result = testClass.replaceChar("abcd", 2, 'z');

	// then
	assertEquals("abzd", result);
    }

    @Test
    public void testInsertChar_1() {
	// given
	WordAnalyser testClass = new WordAnalyser(null);

	// when
	String result = testClass.insertChar("abcd", 2, 'z');

	// then
	assertEquals("abzcd", result);
    }

    @Test
    public void testInsertChar_2() {
	// given
	WordAnalyser testClass = new WordAnalyser(null);

	// when
	String result = testClass.insertChar("abcd", 0, 'z');

	// then
	assertEquals("zabcd", result);
    }

    @Test
    public void testInsertChar_3() {
	// given
	WordAnalyser testClass = new WordAnalyser(null);

	// when
	String result = testClass.insertChar("abcd", 4, 'z');

	// then
	assertEquals("abcdz", result);
    }

    @Test
    public void testTranspose_1() {
	// given
	WordAnalyser testClass = new WordAnalyser(null);

	// when
	String result = testClass.transpose("abcd", 0);

	// then
	assertEquals("bacd", result);
    }

    @Test
    public void testTranspose_2() {
	// given
	WordAnalyser testClass = new WordAnalyser(null);

	// when
	String result = testClass.transpose("abcd", 2);

	// then
	assertEquals("abdc", result);
    }

    @Test(expected = ArrayIndexOutOfBoundsException.class)
    public void testTranspose_3() {
	// given
	WordAnalyser testClass = new WordAnalyser(null);

	// when, then
	testClass.transpose("abcd", 3);

    }

    @Test
    public void testDoSpellCheck_single_replacement_1() {
	// given
	Set<String> master = new HashSet<>();
	master.add("spelling");
	WordAnalyser testClass = new WordAnalyser(master);

	// when
	String result = testClass.doSpellCheck("speling");

	// then
	assertEquals("spelling", result);
    }

    @Test
    public void testDoSpellCheck_single_replacement_2() {
	// given
	Set<String> master = new HashSet<>();
	master.add("bicycle");
	WordAnalyser testClass = new WordAnalyser(master);

	// when
	String result = testClass.doSpellCheck("bycycle");

	// then
	assertEquals("bicycle", result);
    }

    @Test
    public void testDoSpellCheck_single_replacement_3() {
	// given
	Set<String> master = new HashSet<>();
	master.add("poetry");
	WordAnalyser testClass = new WordAnalyser(master);

	// when
	String result = testClass.doSpellCheck("peotry");

	// then
	assertEquals("poetry", result);
    }

    @Test
    public void testDoSpellCheck_single_replacement_4() {
	// given
	Set<String> master = new HashSet<>();
	master.add("arranged");
	WordAnalyser testClass = new WordAnalyser(master);

	// when
	String result = testClass.doSpellCheck("arrainged");

	// then
	assertEquals("arranged", result);
    }

    @Test
    public void testDoSpellCheck_double_replacement_1() {
	// given
	Set<String> master = new HashSet<>();
	master.add("corrected");
	WordAnalyser testClass = new WordAnalyser(master);

	// when
	String result = testClass.doSpellCheck("korrectud");

	// then
	assertEquals("corrected", result);
    }

    @Test
    public void testDoSpellCheck_double_replacement_2() {
	// given
	Set<String> master = new HashSet<>();
	master.add("inconvenient");
	WordAnalyser testClass = new WordAnalyser(master);

	// when
	String result = testClass.doSpellCheck("inconvient");

	// then
	assertEquals("inconvenient", result);
    }

    @Test
    public void testDoSpellCheck_double_replacement_3() {
	// given
	Set<String> master = new HashSet<>();
	master.add("poetry");
	WordAnalyser testClass = new WordAnalyser(master);

	// when
	String result = testClass.doSpellCheck("peotryy");

	// then
	assertEquals("poetry", result);
    }

    @Test
    public void testDoSpellCheck_correct_input() {
	// given
	Set<String> master = new HashSet<>();
	master.add("word");
	WordAnalyser testClass = new WordAnalyser(master);

	// when
	String result = testClass.doSpellCheck("word");

	// then
	assertEquals("word", result);
    }

    @Test
    public void testDoSpellCheck_word_not_found() {
	// given
	WordAnalyser testClass = new WordAnalyser(Collections.emptySet());

	// when
	String result = testClass.doSpellCheck("quintessential");

	// then
	assertEquals("quintessential", result);
    }

}
