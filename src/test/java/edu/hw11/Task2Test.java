package edu.hw11;

import edu.hw11.task2.ByteBuddyMethodEditor;
import lombok.SneakyThrows;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

class Task2Test {
    private static final int A = 5;
    private static final int B = 6;

    @SneakyThrows
    @Test
    void testEditMethod() {
        // given
        Class<?> newType = ByteBuddyMethodEditor.getMultiplierClassInsteadOfSum();

        // when
        Object instance = newType.newInstance();
        int res = (int) newType.getMethod("sum", int.class, int.class).invoke(instance, A, B);

        // then
        assertEquals(A * B, res);
    }
}
