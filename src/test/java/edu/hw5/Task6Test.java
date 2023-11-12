package edu.hw5;

import edu.hw5.task6.SubsequenceFinder;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import java.util.stream.Stream;
import static org.junit.jupiter.api.Assertions.assertEquals;

class Task6Test {
    private static Stream<Arguments> sequenceProvider() {
        return Stream.of(
            Arguments.of("abc", "achfdbaabgabcaabg", true),
            Arguments.of("afh", "achfdbaabgabcaabg", false)
        );
    }

    @ParameterizedTest
    @MethodSource("sequenceProvider")
    void testCheck(String s, String t, boolean expected) {
        SubsequenceFinder finder = new SubsequenceFinder();
        assertEquals(expected, finder.isSubsequence(s, t));
    }
}

