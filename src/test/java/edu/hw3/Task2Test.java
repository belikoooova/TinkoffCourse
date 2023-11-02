package edu.hw3;

import edu.hw3.task2.Task2;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

class Task2Test {
    @Test
    @DisplayName("1st sample test")
    void firstSampleTest() {
        // given
        String stringToClusterize = "()()()";

        // when
        var result = Task2.clusterize(stringToClusterize);

        // then
        assertThat(result).isEqualTo(new ArrayList<String>(Arrays.asList("()", "()", "()")));
    }

    @Test
    @DisplayName("2nd sample test")
    void secondSampleTest() {
        // given
        String stringToClusterize = "((()))";

        // when
        var result = Task2.clusterize(stringToClusterize);

        // then
        assertThat(result).isEqualTo(new ArrayList<String>(List.of("((()))")));
    }

    @Test
    @DisplayName("3rd sample test")
    void thirdSampleTest() {
        // given
        String stringToClusterize = "((()))(())()()(()())";

        // when
        var result = Task2.clusterize(stringToClusterize);

        // then
        assertThat(result).isEqualTo(new ArrayList<String>(Arrays.asList("((()))", "(())", "()", "()", "(()())")));
    }

    @Test
    @DisplayName("4th sample test")
    void fourthSampleTest() {
        // given
        String stringToClusterize = "((())())(()(()()))";

        // when
        var result = Task2.clusterize(stringToClusterize);

        // then
        assertThat(result).isEqualTo(new ArrayList<String>(Arrays.asList("((())())", "(()(()()))")));
    }

    @Test
    @DisplayName("Incorrect argument test")
    void incorrectArgumentTest() {
        // given
        String stringToClusterize = "((())())(()(()()";

        // when and then
        assertThrows(IllegalArgumentException.class, () -> Task2.clusterize(stringToClusterize));
    }
}
