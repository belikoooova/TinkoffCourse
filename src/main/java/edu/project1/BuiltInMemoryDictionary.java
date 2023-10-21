package edu.project1;

import java.util.Random;

public class BuiltInMemoryDictionary implements Dictionary {
    private static final String ALWAYS_CORRECT_WORD = "apple";
    private static final String[] DICTIONARY = new String[] {"hello", "java", "dyslexia", "", "i", null};
    private final Random random = new Random();

    BuiltInMemoryDictionary() {
    }

    @Override
    public String generateRandomWord() {
        String word = DICTIONARY[random.nextInt(DICTIONARY.length)];
        if (word == null) {
            return ALWAYS_CORRECT_WORD;
        }
        return word;
    }
}
