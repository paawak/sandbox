package com.swayam.spellchecker;

import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class WordAnalyser {

    private static final Logger LOGGER = LoggerFactory.getLogger(WordAnalyser.class);

    private final Set<String> masterWordList;

    private final Map<String, String> singleLetterTransforms;
    private final Map<String, String> twoLetterTransforms;

    public WordAnalyser(Set<String> masterWordList) {
        this.masterWordList = masterWordList;
        singleLetterTransforms = new ConcurrentHashMap<>(15_000_000);
        twoLetterTransforms = new ConcurrentHashMap<>(15_000_000);
    }

    public void initialize() {

        LOGGER.info("START initializing single letter transforms");

        masterWordList.parallelStream().forEach((String word) -> {
            applyTransforms(singleLetterTransforms, word);
        });

        LOGGER.info("END single letter transforms, with a total count of {}", singleLetterTransforms.size());

        LOGGER.info("START initializing two-letter transforms");

        singleLetterTransforms.keySet().stream().forEach((String word) -> {
            applyTransforms(twoLetterTransforms, word);
        });

        LOGGER.info("END two-letter transforms, with a total count of {}", twoLetterTransforms.size());
    }

    private void applyTransforms(Map<String, String> allPossibleWordCombinations, String word) {
        applyDeletion(allPossibleWordCombinations, word);
        applyReplacement(allPossibleWordCombinations, word);
        applyTranspose(allPossibleWordCombinations, word);
        applyInsertion(allPossibleWordCombinations, word);
    }

    public String doSpellCheck(String word) {

        String newWord;

        if (masterWordList.contains(word)) {
            LOGGER.info("word with correct spelling");
            newWord = word;
        } else if (singleLetterTransforms.containsKey(word)) {
            LOGGER.info("spell check applied for single letter transform");
            newWord = singleLetterTransforms.get(word);
        } else if (twoLetterTransforms.containsKey(word)) {
            LOGGER.info("spell check applied for two-letter transform");
            newWord = singleLetterTransforms.get(twoLetterTransforms.get(word));
        } else {
            LOGGER.info("NO MATCH FOUND!!");
            newWord = word;
        }

        return "'" + word + "' -> '" + newWord + "'";
    }

    String deleteChar(String word, int index) {
        return new StringBuilder(word).deleteCharAt(index).toString();
    }

    String insertChar(String word, int index, char newChar) {
        return new StringBuilder(word).insert(index, newChar).toString();
    }

    String replaceChar(String word, int index, char replacement) {
        return new StringBuilder(word).deleteCharAt(index).insert(index, replacement).toString();
    }

    String transpose(String word, int index) {
        char[] chars = word.toCharArray();
        char temp = chars[index];
        chars[index] = chars[index + 1];
        chars[index + 1] = temp;

        return new String(chars);
    }

    private void applyDeletion(Map<String, String> allPossibleWordCombinations, String word) {
        for (int i = 0; i < word.length(); i++) {
            allPossibleWordCombinations.put(deleteChar(word, i), word);
        }
    }

    private void applyReplacement(Map<String, String> allPossibleWordCombinations, String word) {
        for (char c = 'a'; c <= 'z'; c++) {
            for (int i = 0; i < word.length(); i++) {
                String newWord = replaceChar(word, i, c);
                LOGGER.trace("{} -> {}", word, newWord);
                allPossibleWordCombinations.put(newWord, word);
            }
        }
    }

    private void applyInsertion(Map<String, String> allPossibleWordCombinations, String word) {
        for (char c = 'a'; c <= 'z'; c++) {
            for (int i = 0; i <= word.length(); i++) {
                String newWord = insertChar(word, i, c);
                LOGGER.trace("{} -> {}", word, newWord);
                allPossibleWordCombinations.put(newWord, word);
            }
        }
    }

    private void applyTranspose(Map<String, String> allPossibleWordCombinations, String word) {
        for (int i = 0; i < word.length() - 1; i++) {
            allPossibleWordCombinations.put(transpose(word, i), word);
        }
    }

}
