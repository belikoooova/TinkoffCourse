package edu.project1;

public class UnitTestDictionary implements Dictionary {
    private final String[] dictionary = new String[] {"i", "hello"};
    private final int indexOfWord;

    public UnitTestDictionary(int indexOfWord) {
        this.indexOfWord = indexOfWord;
    }

    @Override
    public String generateRandomWord() {
        return dictionary[indexOfWord];
    }
}
