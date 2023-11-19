package edu.hw5;

import edu.hw5.task8.BinaryRegexMatcher;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import java.util.stream.Stream;
import static org.junit.jupiter.api.Assertions.assertEquals;

class Task8Test {
    // Нечетная длина.
    private static Stream<Arguments> firstSequenceProvider() {
        return Stream.of(
            Arguments.of("101010", false),
            Arguments.of("", false),
            Arguments.of("100", true),
            Arguments.of("1", true)
        );
    }

    // Hачинается с 0 и имеет нечетную длину, или начинается с 1 и имеет четную длину.
    private static Stream<Arguments> secondSequenceProvider() {
        return Stream.of(
            Arguments.of("0", true),
            Arguments.of("00110", true),
            Arguments.of("0011", false),
            Arguments.of("1", false),
            Arguments.of("10", true),
            Arguments.of("10110", false),
            Arguments.of("100110", true)
        );
    }

    // Kоличество 0 кратно 3.
    private static Stream<Arguments> thirdSequenceProvider() {
        return Stream.of(
            Arguments.of("1", true),
            Arguments.of("1000", true),
            Arguments.of("010", false),
            Arguments.of("", true),
            Arguments.of("0100100110", true)
        );
    }

    // Kаждый нечетный символ равен 1.
    private static Stream<Arguments> fifthSequenceProvider() {
        return Stream.of(
            Arguments.of("1", true),
            Arguments.of("1011", true),
            Arguments.of("010", false),
            Arguments.of("", true)
        );
    }

    // Hет последовательных 1.
    private static Stream<Arguments> seventhSequenceProvider() {
        return Stream.of(
            Arguments.of("1", true),
            Arguments.of("1011", false),
            Arguments.of("0100010", true),
            Arguments.of("", true)
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

    @ParameterizedTest
    @MethodSource("fifthSequenceProvider")
    void fifthTestCheck(String s, boolean expected) {
        BinaryRegexMatcher matcher = new BinaryRegexMatcher();
        assertEquals(expected, matcher.checkFifthRule(s));
    }

    @ParameterizedTest
    @MethodSource("seventhSequenceProvider")
    void seventhTestCheck(String s, boolean expected) {
        BinaryRegexMatcher matcher = new BinaryRegexMatcher();
        assertEquals(expected, matcher.checkSeventhRule(s));
    }
}

