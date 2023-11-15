package edu.hw5.task5;

import java.util.regex.Pattern;
import lombok.NoArgsConstructor;

@NoArgsConstructor
public class ValidNumberChecker {
    private static final Pattern PATTERN = Pattern.compile("^[АВЕКМНОРСТУХ]\\d{3}[АВЕКМНОРСТУХ]{2}\\d{2}\\d?$");

    public boolean check(String number) {
        return PATTERN.matcher(number).find();
    }
}
