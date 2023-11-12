package edu.hw5;

import edu.hw5.task5.ValidNumberChecker;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import java.util.stream.Stream;
import static org.junit.jupiter.api.Assertions.assertEquals;

class Task5Test {
    private static Stream<Arguments> numberProvider() {
        return Stream.of(
            Arguments.of("А123ВЕ777", true),
            Arguments.of("А123ВЕ77", true),
            Arguments.of("О777ОО177", true),
            Arguments.of("123АВЕ777", false),
            Arguments.of("А123ВГ77", false),
            Arguments.of("А123ВЕ7777", false)
        );
    }

    @ParameterizedTest
    @MethodSource("numberProvider")
    void testCheck(String number, boolean expected) {
        ValidNumberChecker checker = new ValidNumberChecker();
        assertEquals(expected, checker.check(number));
    }
}
