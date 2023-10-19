package edu.hw2;

import edu.hw2.task1.*;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import java.util.Comparator;
import java.util.stream.Collectors;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

class Task1Test {
    private static final double NEG_VALUE = -2.5;
    private static final double POS_VALUE = 5;
    private static final double TWO = 2;
    private static final double FOUR = 4;
    private static final double ONE = 1;
    private static final double RESULT_SAMPLE = 37;

    @Test
    @DisplayName("Creating constant")
    void createConstant() {
        // given
        double number = NEG_VALUE;

        // when
        Constant constNumber = new Constant(number);

        // then
        assertThat(constNumber.evaluate()).isEqualTo(NEG_VALUE);
        assertThat(constNumber.toString()).isEqualTo("-2.5");
    }

    @Test
    @DisplayName("Negativing constant")
    void negativeConstant() {
        // given
        Constant constNumber = new Constant(NEG_VALUE);

        // when
        Negate negateNumber = new Negate(constNumber);

        // then
        assertThat(negateNumber.evaluate()).isEqualTo(NEG_VALUE * -1);
    }

    @Test
    @DisplayName("Negativing number")
    void negativeNumber() {
        // given
        double number = NEG_VALUE;

        // when
        Negate negateNumber = new Negate(number);

        // then
        assertThat(negateNumber.evaluate()).isEqualTo(NEG_VALUE * -1);
    }

    @Test
    @DisplayName("Print addition")
    void printAddition() {
        // given
        var firstComponent = new Constant(NEG_VALUE);
        var secondComponent = new Constant(POS_VALUE);

        // when
        Addition result = new Addition(firstComponent, secondComponent);

        // then
        assertThat(result.toString()).hasToString("(-2.5 + 5.0)");
    }

    @Test
    @DisplayName("Addition constants")
    void addConstants() {
        // given
        var firstComponent = new Constant(NEG_VALUE);
        var secondComponent = new Constant(POS_VALUE);

        // when
        Addition result = new Addition(firstComponent, secondComponent);

        // then
        assertThat(result.evaluate()).isEqualTo(NEG_VALUE + POS_VALUE);
    }

    @Test
    @DisplayName("Addition numbers")
    void addNumbers() {
        // given
        double firstComponent = NEG_VALUE;
        double secondComponent = POS_VALUE;

        // when
        Addition result = new Addition(firstComponent, secondComponent);

        // then
        assertThat(result.evaluate()).isEqualTo(NEG_VALUE + POS_VALUE);
    }

    @Test
    @DisplayName("Print multiplication")
    void printMultiplication() {
        // given
        var firstComponent = new Constant(NEG_VALUE);
        var secondComponent = new Constant(POS_VALUE);

        // when
        Multiplication result = new Multiplication(firstComponent, secondComponent);

        // then
        assertThat(result.toString()).hasToString("(-2.5 * 5.0)");
    }

    @Test
    @DisplayName("Multiplication constants")
    void multConstants() {
        // given
        var firstComponent = new Constant(NEG_VALUE);
        var secondComponent = new Constant(POS_VALUE);

        // when
        Multiplication result = new Multiplication(firstComponent, secondComponent);

        // then
        assertThat(result.evaluate()).isEqualTo(NEG_VALUE * POS_VALUE);
    }

    @Test
    @DisplayName("Multiplication numbers")
    void multNumbers() {
        // given
        double firstComponent = NEG_VALUE;
        double secondComponent = POS_VALUE;

        // when
        Multiplication result = new Multiplication(firstComponent, secondComponent);

        // then
        assertThat(result.evaluate()).isEqualTo(NEG_VALUE * POS_VALUE);
    }

    @Test
    @DisplayName("Print exponent")
    void printExponent() {
        // given
        var base = new Constant(NEG_VALUE);
        var exponent = new Constant(POS_VALUE);

        // when
        Exponent result = new Exponent(base, exponent);

        // then
        assertThat(result.toString()).hasToString("-2.5 ^ 5.0");
    }

    @Test
    @DisplayName("Exponent constant")
    void expConstant() {
        // given
        var base = new Constant(NEG_VALUE);
        var exponent = new Constant(POS_VALUE);

        // when
        Exponent result = new Exponent(base, exponent);

        // then
        assertThat(result.evaluate()).isEqualTo(Math.pow(NEG_VALUE, POS_VALUE));
    }

    @Test
    @DisplayName("Exponent number")
    void expNumber() {
        // given
        double base = NEG_VALUE;
        double exponent = POS_VALUE;

        // when
        Exponent result = new Exponent(base, exponent);

        // then
        assertThat(result.evaluate()).isEqualTo(Math.pow(NEG_VALUE, POS_VALUE));
    }

    @Test
    @DisplayName("Sample test")
    void sample() {
        var two = new Constant(TWO);
        var four = new Constant(FOUR);
        var negOne = new Negate(new Constant(ONE));
        var sumTwoFour = new Addition(two, four);
        var mult = new Multiplication(sumTwoFour, negOne);
        var exp = new Exponent(mult, TWO);
        var res = new Addition(exp, new Constant(ONE));

        // then
        assertThat(res.evaluate()).isEqualTo(RESULT_SAMPLE);
    }
}
