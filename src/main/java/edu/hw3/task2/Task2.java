package edu.hw3.task2;

import java.util.ArrayList;

public class Task2 {
    private Task2() {
    }

    public static ArrayList<String> clusterize(String stringToClusterize) {
        ArrayList<String> clusterizedStrings = new ArrayList<>();
        int prevInd = -1;
        int countOfBrackets = 0;
        for (int i = 0; i < stringToClusterize.length(); ++i) {
            if (stringToClusterize.charAt(i) == '(') {
                ++countOfBrackets;
            } else {
                --countOfBrackets;
            }
            if (countOfBrackets == 0) {
                clusterizedStrings.add(stringToClusterize.substring(prevInd + 1, i + 1));
                prevInd = i;
            }
        }
        return clusterizedStrings;
    }
}
