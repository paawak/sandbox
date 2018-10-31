package com.swayam.spellchecker;

import java.util.Scanner;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SpellChecker {

    private static final Logger LOGGER = LoggerFactory.getLogger(SpellChecker.class);

    public static void main(String[] args) {
	Set<String> allWords = new DictionaryCrawler().crawl();
	LOGGER.trace("allWords: {}", allWords);

	WordAnalyser wordAnalyser = new WordAnalyser(allWords);

	LOGGER.info("Initialization complete, ready to accept input");
	try (Scanner scanner = new Scanner(System.in);) {
	    while (scanner.hasNextLine()) {
		String word = scanner.nextLine().toLowerCase();
		if (!DictionaryCrawler.WORD.matcher(word).matches()) {
		    LOGGER.warn("Wrong input: expecting only letters from A to Z in upper case or lower case");
		    continue;
		}
		System.out.println(wordAnalyser.doSpellCheck(word));
	    }
	}

    }

}
