package edu.hw5;

import edu.hw5.task4.SafePasswordChecker;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import java.util.stream.Stream;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class Task4Test {
    private static Stream<Arguments> passwordProvider() {
        return Stream.of(
            Arguments.of("password!", true),
            Arguments.of("password", false),
            Arguments.of("1234$", true),
            Arguments.of("noSpecialChars123", false),
            Arguments.of("!@#$%^&*", true),
            Arguments.of("normalText*", true),
            Arguments.of("justnumbers1234", false)
        );
    }

    @ParameterizedTest
    @MethodSource("passwordProvider")
    void testCheck(String password, boolean expected) {
        SafePasswordChecker checker = new SafePasswordChecker();
        assertEquals(expected, checker.check(password));
    }
}
