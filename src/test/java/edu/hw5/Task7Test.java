package edu.hw5;

import edu.hw5.task7.BinaryRegexMatcher;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import java.util.stream.Stream;
import static org.junit.jupiter.api.Assertions.assertEquals;

class Task7Test {
    private static Stream<Arguments> firstSequenceProvider() {
        return Stream.of(
            Arguments.of("1", false), // Меньше 3 символов.
            Arguments.of("1010", false), // Третий символ - не 0.
            Arguments.of("1000", true)
        );
    }

    private static Stream<Arguments> secondSequenceProvider() {
        return Stream.of(
            Arguments.of("1", true),
            Arguments.of("1000", false),
            Arguments.of("1001", true)
        );
    }

    private static Stream<Arguments> thirdSequenceProvider() {
        return Stream.of(
            Arguments.of("1", true),
            Arguments.of("1000", false),
            Arguments.of("100", true),
            Arguments.of("", false),
            Arguments.of("10", true)
        );
    }

    @ParameterizedTest
    @MethodSource("firstSequenceProvider")
    void firstTestCheck(String s, boolean expected) {
        BinaryRegexMatcher matcher = new BinaryRegexMatcher();
        assertEquals(expected, matcher.checkFirstRule(s));
    }

    @ParameterizedTest
    @MethodSource("secondSequenceProvider")
    void secondTestCheck(String s, boolean expected) {
        BinaryRegexMatcher matcher = new BinaryRegexMatcher();
        assertEquals(expected, matcher.checkSecondRule(s));
    }

    @ParameterizedTest
    @MethodSource("thirdSequenceProvider")
    void thirdTestCheck(String s, boolean expected) {
        BinaryRegexMatcher matcher = new BinaryRegexMatcher();
        assertEquals(expected, matcher.checkThirdRule(s));
    }
}

