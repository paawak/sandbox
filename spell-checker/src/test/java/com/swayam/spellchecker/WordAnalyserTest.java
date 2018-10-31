package com.swayam.spellchecker;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import com.swayam.spellchecker.WordAnalyser;

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

}
