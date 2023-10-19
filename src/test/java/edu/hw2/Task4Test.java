package edu.hw2;

import edu.hw2.task4.*;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import java.util.Comparator;
import java.util.stream.Collectors;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

class Task4Test {
    @Test
    @DisplayName("Simple call")
    void simpleCall() {
        // given and when
        CallingInfo info = CallingInfo.callingInfo();

        // then
        assertThat(info.className()).hasToString("edu.hw2.Task4Test");
        assertThat(info.methodName()).hasToString("simpleCall");
    }
}
