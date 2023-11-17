package edu.hw5.task4;

import java.util.regex.Pattern;
import lombok.NoArgsConstructor;

@NoArgsConstructor
public class SafePasswordChecker {
    private static final Pattern PATTERN = Pattern.compile("[~!@$%^&|*]+");

    public boolean check(String password) {
        return PATTERN.matcher(password).find();
    }
}
