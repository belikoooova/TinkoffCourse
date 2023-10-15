package edu.project1;

import java.util.Random;
import org.jetbrains.annotations.NotNull;

public class BuiltInDictionary implements Dictionary {
    private static final @NotNull String ALWAYS_CORRECT_WORD = "apple";
    private final String[] dictionary = new String[] {"hello", "java", "dyslexia", "", "i", null};
    private final Random random = new Random();

    @Override
    public @NotNull String randomWord() {
        String word = dictionary[random.nextInt(dictionary.length)];
        if (word == null) {
            return ALWAYS_CORRECT_WORD;
        }
        return word;
    }
}
