package edu.hw3.task3;

import java.util.ArrayList;
import java.util.HashMap;

public class FrequencyCounter {
    public <T> HashMap<T, Integer> freqDict(ArrayList<T> list) {
        HashMap<T, Integer> dictionary = new HashMap<>();
        for (var element : list) {
            if (dictionary.containsKey(element)) {
                dictionary.put(element, dictionary.get(element) + 1);
            }
            else {
                dictionary.put(element, 1);
            }
        }
        return dictionary;
    }
}
