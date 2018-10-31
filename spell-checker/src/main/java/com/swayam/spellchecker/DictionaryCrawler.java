package com.swayam.spellchecker;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DictionaryCrawler {

    private static final Logger LOGGER = LoggerFactory.getLogger(DictionaryCrawler.class);

    private static final String DICTIONARY_URL = "https://raw.githubusercontent.com/smerrill/scala-norvig-spell-check/master/big.txt";

    static final Pattern WORD = Pattern.compile("[A-Za-z]{2,}");

    public Collection<String> crawl() {

        LOGGER.debug("-------------------------START crawl of {}", DICTIONARY_URL);

        URL url;
        try {
            url = new URL(DICTIONARY_URL);
        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        }

        Map<String, Integer> allWords = new HashMap<>();

        try (BufferedReader bf = new BufferedReader(new InputStreamReader(url.openStream(), Charset.forName("utf-8")));) {
            while (true) {
                String line = bf.readLine();
                if (line == null) {
                    break;
                }
                parseLine(allWords, line);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        LOGGER.debug("-------------------------END crawl");

        Map<Integer, List<String>> frequencyMap = new HashMap<>();

        for (String word : allWords.keySet()) {

            int frequency = allWords.get(word);

            if (frequencyMap.containsKey(frequency)) {
                frequencyMap.get(frequency).add(word);
            } else {
                List<String> words = new ArrayList<>();
                words.add(word);
                frequencyMap.put(frequency, words);
            }
        }

        // sort descending order by the frequency
        List<Integer> sortedFrequency = new ArrayList<Integer>(frequencyMap.keySet());

        Collections.sort(sortedFrequency, (Integer i, Integer j) -> {
            return j - i;
        });

        LOGGER.trace("sortedFrequency: {}", sortedFrequency);

        List<String> sortedWords = new ArrayList<>();
        for (int frequency : sortedFrequency) {
            sortedWords.addAll(frequencyMap.get(frequency));
        }

        return Collections.unmodifiableList(sortedWords);

    }

    private void parseLine(Map<String, Integer> allWords, String line) {
        String[] words = line.split("[\\s,\\.\"\\-\\*]");
        Arrays.stream(words).filter((String word) -> {
            return WORD.matcher(word).matches();
        }).map(String::toLowerCase).forEach((String word) -> {

            if (allWords.containsKey(word)) {
                allWords.put(word, allWords.get(word) + 1);
            } else {
                allWords.put(word, 1);
            }

        });
    }

}
