package edu.hw3;

import edu.hw3.task7.NullableTreeSetComparator;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import java.util.TreeMap;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

class Task7Test {
    @Test
    @DisplayName("Sample test")
    void sampleTest() {
        // given
        TreeMap<String, String> tree = new TreeMap<>(new NullableTreeSetComparator());

        // when
        tree.put(null, "test");

        // then
        assertThat(tree.get(null)).isEqualTo("test");
    }
}
