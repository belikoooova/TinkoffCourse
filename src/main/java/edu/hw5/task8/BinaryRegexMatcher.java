package edu.hw5.task8;

import java.util.regex.Pattern;

public class BinaryRegexMatcher {
    public BinaryRegexMatcher() {
    }

    public boolean checkFirstRule(String string) {
        Pattern pattern = Pattern.compile("^([01]{2})*[01]$");
        return pattern.matcher(string).find();
    }

    public boolean checkSecondRule(String string) {
        Pattern pattern = Pattern.compile("^0([01]{2})*$|^1[01]([01]{2})*$");
        return pattern.matcher(string).find();
    }

    public boolean checkThirdRule(String string) {
        Pattern pattern = Pattern.compile("^1*((01*){3})*$");
        return pattern.matcher(string).find();
    }

    public boolean checkFifthRule(String string) {
        Pattern pattern = Pattern.compile("^1$|^(1[01])*$");
        return pattern.matcher(string).find();
    }

    public boolean checkSeventhRule(String string) {
        Pattern pattern = Pattern.compile("^1?(0*010+)*1?$");
        return pattern.matcher(string).find();
    }
}
