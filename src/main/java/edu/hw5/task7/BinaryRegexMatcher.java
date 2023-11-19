package edu.hw5.task7;

import java.util.regex.Pattern;

public class BinaryRegexMatcher {
    private static final Pattern FIRST = Pattern.compile("^[01]{2}0[01]+");
    private static final Pattern SECOND = Pattern.compile("^0.*0$|^1.*1$|^[01]$");
    private static final Pattern THIRD = Pattern.compile("^[01]$|^[01]{2}$|^[01]{3}$");

    public boolean checkFirstRule(String string) {
        return FIRST.matcher(string).find();
    }

    public boolean checkSecondRule(String string) {
        return SECOND.matcher(string).find();
    }

    public boolean checkThirdRule(String string) {
        return THIRD.matcher(string).find();
    }
}
