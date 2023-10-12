package edu.hw2;

import edu.hw2.task1.Expr;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import java.util.Comparator;
import java.util.stream.Collectors;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class Task1Test {
    public static final double NEG_VALUE = -2.5;
    public static final double POS_VALUE = 5;
    public static final double TWO = 2;
    public static final double FOUR = 4;
    public static final double ONE = 1;
    public static final double RESULT_SAMPLE = 37;

    @Test
    @DisplayName("Creating constant")
    void createConstant() {
        // given
        double number = NEG_VALUE;

        // when
        Expr.Constant constNumber = new Expr.Constant(number);

        // then
        assertThat(constNumber.evaluate()).isEqualTo(NEG_VALUE);
    }

    @Test
    @DisplayName("Negativing constant")
    void negativeConstant() {
        // given
        Expr.Constant constNumber = new Expr.Constant(NEG_VALUE);

        // when
        Expr.Negate negateNumber = new Expr.Negate(constNumber);

        // then
        assertThat(negateNumber.evaluate()).isEqualTo(NEG_VALUE * -1);
    }

    @Test
    @DisplayName("Negativing number")
    void negativeNumber() {
        // given
        double number = NEG_VALUE;

        // when
        Expr.Negate negateNumber = new Expr.Negate(number);

        // then
        assertThat(negateNumber.evaluate()).isEqualTo(NEG_VALUE * -1);
    }

    @Test
    @DisplayName("Print addition")
    void printAddition() {
        // given
        var firstComponent = new Expr.Constant(NEG_VALUE);
        var secondComponent = new Expr.Constant(POS_VALUE);

        // when
        Expr.Addition result = new Expr.Addition(firstComponent, secondComponent);

        // then
        assertThat(result.toString()).hasToString("(-2.5 + 5.0)");
    }

    @Test
    @DisplayName("Addition constants")
    void addConstants() {
        // given
        var firstComponent = new Expr.Constant(NEG_VALUE);
        var secondComponent = new Expr.Constant(POS_VALUE);

        // when
        Expr.Addition result = new Expr.Addition(firstComponent, secondComponent);

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
        Expr.Addition result = new Expr.Addition(firstComponent, secondComponent);

        // then
        assertThat(result.evaluate()).isEqualTo(NEG_VALUE + POS_VALUE);
    }

    @Test
    @DisplayName("Print multiplication")
    void printMultiplication() {
        // given
        var firstComponent = new Expr.Constant(NEG_VALUE);
        var secondComponent = new Expr.Constant(POS_VALUE);

        // when
        Expr.Multiplication result = new Expr.Multiplication(firstComponent, secondComponent);

        // then
        assertThat(result.toString()).hasToString("(-2.5 * 5.0)");
    }

    @Test
    @DisplayName("Multiplication constants")
    void multConstants() {
        // given
        var firstComponent = new Expr.Constant(NEG_VALUE);
        var secondComponent = new Expr.Constant(POS_VALUE);

        // when
        Expr.Multiplication result = new Expr.Multiplication(firstComponent, secondComponent);

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
        Expr.Multiplication result = new Expr.Multiplication(firstComponent, secondComponent);

        // then
        assertThat(result.evaluate()).isEqualTo(NEG_VALUE * POS_VALUE);
    }

    @Test
    @DisplayName("Print exponent")
    void printExponent() {
        // given
        var base = new Expr.Constant(NEG_VALUE);
        var exponent = new Expr.Constant(POS_VALUE);

        // when
        Expr.Exponent result = new Expr.Exponent(base, exponent);

        // then
        assertThat(result.toString()).hasToString("-2.5 ^ 5.0");
    }

    @Test
    @DisplayName("Exponent constant")
    void expConstant() {
        // given
        var base = new Expr.Constant(NEG_VALUE);
        var exponent = new Expr.Constant(POS_VALUE);

        // when
        Expr.Exponent result = new Expr.Exponent(base, exponent);

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
        Expr.Exponent result = new Expr.Exponent(base, exponent);

        // then
        assertThat(result.evaluate()).isEqualTo(Math.pow(NEG_VALUE, POS_VALUE));
    }

    @Test
    @DisplayName("Sample test")
    void sample() {
        var two = new Expr.Constant(TWO);
        var four = new Expr.Constant(FOUR);
        var negOne = new Expr.Negate(new Expr.Constant(ONE));
        var sumTwoFour = new Expr.Addition(two, four);
        var mult = new Expr.Multiplication(sumTwoFour, negOne);
        var exp = new Expr.Exponent(mult, TWO);
        var res = new Expr.Addition(exp, new Expr.Constant(ONE));

        // then
        assertThat(res.evaluate()).isEqualTo(RESULT_SAMPLE);
    }
}
