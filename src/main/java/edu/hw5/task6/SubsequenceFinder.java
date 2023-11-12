package edu.hw5.task6;

import java.util.regex.Pattern;

public class SubsequenceFinder {
    public SubsequenceFinder() {
    }

    public boolean isSubsequence(String s, String t) {
        Pattern pattern = Pattern.compile(String.join(".*", s.split("")));
        return pattern.matcher(t).find();
    }
}
