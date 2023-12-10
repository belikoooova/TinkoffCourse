package edu.hw10.task1;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

class Task1Test {
    private static final int MIN_CLASS_VALUE = 10;
    private static final int MAX_CLASS_VALUE = 50;
    private static final int MIN_RECORD_VALUE = 0;
    private static final int MAX_RECORD_VALUE = 100;

    @Test
    void testGenerateTestClass() throws IllegalAccessException {
        // given
        RandomObjectGenerator rog = new RandomObjectGenerator();

        // when
        TestClass testObject = rog.nextObject(TestClass.class);

        // then
        assertNotNull(testObject.getNotNullField());
        assertTrue(testObject.getMinField() >= MIN_CLASS_VALUE);
        assertTrue(testObject.getMaxField() <= MAX_CLASS_VALUE);
    }

    @Test
    void testGenerateTestRecord() {
        // given
        RandomObjectGenerator rog = new RandomObjectGenerator();

        // when
        TestRecord testRecord = rog.nextObject(TestRecord.class);

        // then
        assertNotNull(testRecord.name());
        assertTrue(testRecord.age() >= MIN_RECORD_VALUE && testRecord.age() <= MAX_RECORD_VALUE);
    }
}
