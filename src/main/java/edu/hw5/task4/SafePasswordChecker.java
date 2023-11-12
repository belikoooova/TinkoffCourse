package edu.hw5.task4;

import java.util.regex.Pattern;

public class SafePasswordChecker {
    public SafePasswordChecker() {
    }

    public boolean check(String password) {
        Pattern pattern = Pattern.compile("[~!@$%^&|*]+");
        return pattern.matcher(password).find();
    }
}
