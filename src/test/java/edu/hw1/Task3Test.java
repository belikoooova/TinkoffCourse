package edu.hw1;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@SuppressWarnings("MagicNumber")
public class Task3Test {
    @Test
    @DisplayName("Пустой внешний массив")
    void emptyExternal() {
        // given
        int[] a1 = new int[] {1, 2, 3};
        int[] a2 = new int[0];

        // when
        boolean result = Task3.isNested(a1, a2);

        // then
        assertThat(result).isFalse();
    }

    @Test
    @DisplayName("Пустой внутренний массив")
    void emptyInternal() {
        // given
        int[] a1 = new int[0];
        int[] a2 = new int[] {1, 2, 3};

        // when
        boolean result = Task3.isNested(a1, a2);

        // then
        assertThat(result).isTrue();
    }

    @Test
    @DisplayName("Во внешнем массиве 1 элемент")
    void oneElementExternal() {
        // given
        int[] a1 = new int[] {1, 2, 3};
        int[] a2 = new int[] {4};

        // when
        boolean result = Task3.isNested(a1, a2);

        // then
        assertThat(result).isFalse();
    }

    @Test
    @DisplayName("Включается")
    void isTrue() {
        // given
        int[] a1 = new int[] {1, 2, 3};
        int[] a2 = new int[] {-7, 6, 2};

        // when
        boolean result = Task3.isNested(a1, a2);

        // then
        assertThat(result).isTrue();
    }

    @Test
    @DisplayName("Не включается")
    void isFalse() {
        // given
        int[] a1 = new int[] {1, 2, 3};
        int[] a2 = new int[] {7, 4};

        // when
        boolean result = Task3.isNested(a1, a2);

        // then
        assertThat(result).isFalse();
    }
}
