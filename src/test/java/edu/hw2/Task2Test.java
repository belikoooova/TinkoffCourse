package edu.hw2;

import edu.hw2.task2.*;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import java.util.Comparator;
import java.util.stream.Collectors;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

class Task2Test {
    private static final double OLD_HEIGHT = 10;
    private static final double OLD_WIDTH = 5;
    private static final double NEW_HEIGHT = 20;
    private static final double NEW_WIDTH = 10;
    private static final double EXPECTED_SQUARE = 200;

    @Test
    @DisplayName("Square of rectangle")
    void squareOfRectangle() {
        // given
        Rectangle rectangle = new Rectangle(OLD_WIDTH, OLD_HEIGHT);

        // when
        double s = rectangle.setHeight(NEW_HEIGHT).setWidth(NEW_WIDTH).area();

        // then
        assertThat(s).isEqualTo(EXPECTED_SQUARE);
    }

    @Test
    @DisplayName("Square of square")
    void squareOfSquare() {
        // given
        Rectangle square = new Square(OLD_WIDTH);

        // when
        double s = square.setHeight(NEW_HEIGHT).setWidth(NEW_WIDTH).area();

        // then
        assertThat(s).isEqualTo(EXPECTED_SQUARE);
    }
}
