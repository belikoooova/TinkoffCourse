package edu.hw5.task8;

import java.util.regex.Pattern;

public class BinaryRegexMatcher {
    private static final Pattern FIRST = Pattern.compile("^([01]{2})*[01]$");
    private static final Pattern SECOND = Pattern.compile("^0([01]{2})*$|^1[01]([01]{2})*$");
    private static final Pattern THIRD = Pattern.compile("^1*((01*){3})*$");
    private static final Pattern FIFTH = Pattern.compile("^1$|^(1[01])*$");
    private static final Pattern SEVENTH = Pattern.compile("^1?(0*010+)*1?$");

    public boolean checkFirstRule(String string) {
        return FIRST.matcher(string).find();
    }

    public boolean checkSecondRule(String string) {
        return SECOND.matcher(string).find();
    }

    public boolean checkThirdRule(String string) {
        return THIRD.matcher(string).find();
    }

    public boolean checkFifthRule(String string) {
        return FIFTH.matcher(string).find();
    }

    public boolean checkSeventhRule(String string) {
        return SEVENTH.matcher(string).find();
    }
}
