package edu.hw3;

import edu.hw3.task8.BackwardIterator;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import java.util.Iterator;
import java.util.List;
import java.util.TreeMap;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.in;

class Task8Test {
    private static final int ONE = 1;
    private static final int TWO = 2;
    private static final int THREE = 3;

    @Test
    @DisplayName("Sample test")
    void sampleTest() {
        // given
        List<Integer> list = List.of(ONE, TWO, THREE);
        Iterator<Integer> iterator = new BackwardIterator<>(list);
        int index = 2;

        // when
        while (iterator.hasNext()) {
            // then
            assertThat(iterator.next()).isEqualTo(list.get(index));
            --index;
        }
    }
}
