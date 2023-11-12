package edu.hw5.task7;

import java.util.regex.Pattern;

public class BinaryRegexMatcher {
    public BinaryRegexMatcher() {
    }

    public boolean checkFirstRule(String string) {
        Pattern pattern = Pattern.compile("^[01]{2}0[01]+");
        return pattern.matcher(string).find();
    }

    public boolean checkSecondRule(String string) {
        Pattern pattern = Pattern.compile("^0.*0$|^1.*1$|^[01]$");
        return pattern.matcher(string).find();
    }

    public boolean checkThirdRule(String string) {
        Pattern pattern = Pattern.compile("^[01]$|^[01]{2}$|^[01]{3}$");
        return pattern.matcher(string).find();
    }
}
