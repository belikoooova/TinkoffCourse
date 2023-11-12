package edu.hw5.task5;

import java.util.regex.Pattern;

public class ValidNumberChecker {
    public ValidNumberChecker() {
    }

    public boolean check(String number) {
        Pattern pattern = Pattern.compile("^[АВЕКМНОРСТУХ]\\d{3}[АВЕКМНОРСТУХ]{2}\\d{2}\\d?$");
        return pattern.matcher(number).find();
    }
}
