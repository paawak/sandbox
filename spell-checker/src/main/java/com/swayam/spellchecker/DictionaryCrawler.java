package com.swayam.spellchecker;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
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

	Set<String> allWords = new HashSet<>();

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

	return Collections.unmodifiableSet(allWords);

    }

    private void parseLine(Set<String> allWords, String line) {
	String[] words = line.split("[\\s,\\.\"\\-\\*]");
	Arrays.stream(words).filter((String word) -> {
	    return WORD.matcher(word).matches();
	}).map(String::toLowerCase).forEach(allWords::add);
    }

}
