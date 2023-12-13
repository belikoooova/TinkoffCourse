package edu.hw11;

import edu.hw11.task1.ByteBuddyClassCreator;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

class Task1Test {
    @Test
    void testCreateClass() {
        // given
        ByteBuddyClassCreator creator = new ByteBuddyClassCreator();

        // when and then
        assertEquals("Hello, ByteBuddy!", creator.createUnloadedTypeAndCallToString());
    }
}
