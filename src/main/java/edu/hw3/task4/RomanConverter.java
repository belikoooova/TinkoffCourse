package edu.hw3.task4;

import java.util.NavigableMap;
import java.util.TreeMap;

public class RomanConverter {
    private RomanConverter() {
    }

    private static NavigableMap<Integer, String> arabianToRomanDict;
    private static final int MAX_ROMAN_NUMBER = 3999;

    public static String convertToRoman(int number) {
        if (number > MAX_ROMAN_NUMBER) {
            throw new IllegalArgumentException("Incorrect value of number.");
        }
        fillDictionary();
        StringBuilder stringBuilder = new StringBuilder();
        int copyNumber = number;
        while (copyNumber > 0) {
            for (var key : arabianToRomanDict.navigableKeySet()) {
                if (copyNumber >= key) {
                    copyNumber -= key;
                    stringBuilder.append(arabianToRomanDict.get(key));
                    break;
                }
            }
        }
        return stringBuilder.toString();
    }

    @SuppressWarnings("MagicNumber")
    private static void fillDictionary() {
        arabianToRomanDict = new TreeMap<>();
        arabianToRomanDict.put(1, "I");
        arabianToRomanDict.put(4, "IV");
        arabianToRomanDict.put(5, "V");
        arabianToRomanDict.put(9, "IX");
        arabianToRomanDict.put(10, "X");
        arabianToRomanDict.put(40, "XL");
        arabianToRomanDict.put(50, "L");
        arabianToRomanDict.put(90, "XC");
        arabianToRomanDict.put(100, "C");
        arabianToRomanDict.put(400, "CD");
        arabianToRomanDict.put(500, "D");
        arabianToRomanDict.put(900, "CM");
        arabianToRomanDict.put(1000, "M");
        arabianToRomanDict = arabianToRomanDict.reversed();
    }
}
