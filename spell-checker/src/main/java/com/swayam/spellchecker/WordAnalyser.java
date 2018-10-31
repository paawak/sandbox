package com.swayam.spellchecker;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class WordAnalyser {

    private static final Logger LOGGER = LoggerFactory.getLogger(WordAnalyser.class);

    private final Collection<String> masterWordList;

    public WordAnalyser(Collection<String> masterWordList) {
        this.masterWordList = masterWordList;
    }

    public String doSpellCheck(String word) {

        String newWord;

        if (masterWordList.contains(word)) {
            LOGGER.info("word with correct spelling");
            newWord = word;
        } else {

            LOGGER.info("processing, hold tight, might take some time...");

            List<WordPair> singleLetterMatches = masterWordList.stream().flatMap((String masterWord) -> {
                return applySingleLetterTransforms(masterWord).stream();
            }).filter((WordPair wordPair) -> {
                return wordPair.transformedWord.equals(word);
            }).collect(Collectors.toList());

            if (!singleLetterMatches.isEmpty()) {
                LOGGER.info("spell check applied for single letter transforms");
                newWord = singleLetterMatches.get(0).originalWord;
            } else {

                List<WordPair> twoLetterMatches = Arrays.asList(word).stream().flatMap((String masterWord) -> {

                    List<WordPair> trf = apply2LetterTransforms(masterWord);
                    LOGGER.debug("2 letter transforms for {} : {}", word, trf);
                    return trf.stream();
                }).filter((WordPair wordPair) -> {
                    return wordPair.transformedWord.equals(word);
                }).collect(Collectors.toList());

                if (!twoLetterMatches.isEmpty()) {
                    LOGGER.info("spell check applied for 2 letter transforms");
                    newWord = twoLetterMatches.get(0).originalWord;
                } else {
                    LOGGER.info("NO MATCH FOUND!!");
                    newWord = word;
                }
            }
        }

        return "'" + word + "' -> '" + newWord + "'";
    }

    private List<WordPair> applySingleLetterTransforms(String word) {
        List<WordPair> transforms = new ArrayList<>();

        transforms.addAll(applyDeletion(word));
        transforms.addAll(applyReplacement(word));
        transforms.addAll(applyTranspose(word));
        transforms.addAll(applyInsertion(word));

        return transforms;
    }

    private List<WordPair> apply2LetterTransforms(String word) {

        List<WordPair> singleLetterTransforms = applySingleLetterTransforms(word);

        return singleLetterTransforms.stream().flatMap((WordPair singleTransformedWord) -> {
            List<WordPair> twoLetterTransforms = applySingleLetterTransforms(singleTransformedWord.transformedWord);
            return twoLetterTransforms.stream().map((WordPair twoLetterTransformedWord) -> {
                return new WordPair(singleTransformedWord.originalWord, twoLetterTransformedWord.transformedWord);
            });
        }).collect(Collectors.toList());
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

    private List<WordPair> applyDeletion(String word) {
        List<WordPair> wordPairs = new ArrayList<>();
        for (int i = 0; i < word.length(); i++) {
            wordPairs.add(new WordPair(word, deleteChar(word, i)));
        }
        return wordPairs;
    }

    private List<WordPair> applyReplacement(String word) {
        List<WordPair> wordPairs = new ArrayList<>();
        for (char c = 'a'; c <= 'z'; c++) {
            for (int i = 0; i < word.length(); i++) {
                String newWord = replaceChar(word, i, c);
                wordPairs.add(new WordPair(word, newWord));
            }
        }
        return wordPairs;
    }

    private List<WordPair> applyInsertion(String word) {
        List<WordPair> wordPairs = new ArrayList<>();
        for (char c = 'a'; c <= 'z'; c++) {
            for (int i = 0; i <= word.length(); i++) {
                String newWord = insertChar(word, i, c);
                wordPairs.add(new WordPair(word, newWord));
            }
        }
        return wordPairs;
    }

    private List<WordPair> applyTranspose(String word) {
        List<WordPair> wordPairs = new ArrayList<>();
        for (int i = 0; i < word.length() - 1; i++) {
            wordPairs.add(new WordPair(word, transpose(word, i)));
        }
        return wordPairs;
    }

    private static class WordPair {
        final String originalWord;
        final String transformedWord;

        public WordPair(String originalWord, String transformedWord) {
            this.originalWord = originalWord;
            this.transformedWord = transformedWord;
        }

        @Override
        public String toString() {
            StringBuilder builder = new StringBuilder();
            builder.append("WordPair [originalWord=");
            builder.append(originalWord);
            builder.append(", transformedWord=");
            builder.append(transformedWord);
            builder.append("]");
            return builder.toString();
        }

    }

}
