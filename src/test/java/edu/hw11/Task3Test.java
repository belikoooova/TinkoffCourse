package edu.hw11;

import edu.hw11.task3.ByteBuddyFibCounterCreator;
import edu.hw11.task3.FibCounter;
import lombok.SneakyThrows;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

class Task3Test {
    @SneakyThrows
    @Test
    void testFibCount() {
        // given
        var type = ByteBuddyFibCounterCreator.createClass();
        var fibCounter = new FibCounter();

        // when
        Object byteBuddyFibCounter = type.newInstance();

        // then
        assertEquals(fibCounter.fib(5), type.getMethod("fib", int.class).invoke(byteBuddyFibCounter, 5));
    }
}
