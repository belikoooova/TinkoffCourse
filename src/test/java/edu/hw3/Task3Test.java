package edu.hw3;

import edu.hw3.task3.FrequencyCounter;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class Task3Test {
    @Test
    @DisplayName("1st sample test")
    void firstSampleTest() {
        // given
        ArrayList<String> list = new ArrayList<>(Arrays.asList("a", "bb", "a", "bb"));

        // when
        FrequencyCounter frequencyCounter = new FrequencyCounter();
        var result = frequencyCounter.freqDict(list);

        // then
        assertThat(result.get("a")).isEqualTo(list.stream().filter(s -> s.equals("a")).count());
        assertThat(result.get("bb")).isEqualTo(list.stream().filter(s -> s.equals("bb")).count());
    }

    @Test
    @DisplayName("2nd sample test")
    void secondSampleTest() {
        // given
        ArrayList<String> list = new ArrayList<>(Arrays.asList("this", "and", "that", "and"));

        // when
        FrequencyCounter frequencyCounter = new FrequencyCounter();
        var result = frequencyCounter.freqDict(list);

        // then
        assertThat(result.get("this")).isEqualTo(list.stream().filter(s -> s.equals("this")).count());
        assertThat(result.get("and")).isEqualTo(list.stream().filter(s -> s.equals("and")).count());
        assertThat(result.get("that")).isEqualTo(list.stream().filter(s -> s.equals("that")).count());
    }

    @Test
    @DisplayName("3rd sample test")
    void thirdSampleTest() {
        // given
        ArrayList<String> list = new ArrayList<>(Arrays.asList("код", "код", "код", "bug"));

        // when
        FrequencyCounter frequencyCounter = new FrequencyCounter();
        var result = frequencyCounter.freqDict(list);

        // then
        assertThat(result.get("код")).isEqualTo(list.stream().filter(s -> s.equals("код")).count());
        assertThat(result.get("bug")).isEqualTo(list.stream().filter(s -> s.equals("bug")).count());
    }

    @Test
    @DisplayName("4th sample test")
    void fourthSampleTest() {
        // given
        ArrayList<Integer> list = new ArrayList<>(Arrays.asList(1, 1, 2, 2));

        // when
        FrequencyCounter frequencyCounter = new FrequencyCounter();
        var result = frequencyCounter.freqDict(list);

        // then
        assertThat(result.get(1)).isEqualTo(list.stream().filter(s -> s.equals(1)).count());
        assertThat(result.get(2)).isEqualTo(list.stream().filter(s -> s.equals(2)).count());
    }
}
