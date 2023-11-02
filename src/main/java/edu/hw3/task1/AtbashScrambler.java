package edu.hw3.task1;

import java.util.HashMap;

public class AtbashScrambler {
    private static HashMap<Character, Character> atbashDictionary = new HashMap<>();
    private static final int ALPHABET_SIZE = 26;

    public AtbashScrambler() {
        for (int i = 0; i < ALPHABET_SIZE; ++i) {
            atbashDictionary.put((char) ('A' + i), (char) ('A' + ALPHABET_SIZE - i - 1));
            atbashDictionary.put((char) ('a' + i), (char) ('a' + ALPHABET_SIZE - i - 1));
        }
    }

    public String encrypt(String stringToEncrypt) {
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < stringToEncrypt.length(); ++i) {
            if (atbashDictionary.containsKey(stringToEncrypt.charAt(i))) {
                stringBuilder.append(atbashDictionary.get(stringToEncrypt.charAt(i)));
            } else {
                stringBuilder.append(stringToEncrypt.charAt(i));
            }
        }
        return stringBuilder.toString();
    }
}
