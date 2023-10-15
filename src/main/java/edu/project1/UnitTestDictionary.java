package edu.project1;

import org.jetbrains.annotations.NotNull;

public class UnitTestDictionary implements Dictionary {
    private final String[] dictionary = new String[] {"i", "hello"};
    private final int indexOfWord;

    public UnitTestDictionary(int indexOfWord) {
        this.indexOfWord = indexOfWord;
    }

    @Override
    public @NotNull String randomWord() {
        return dictionary[indexOfWord];
    }
}
