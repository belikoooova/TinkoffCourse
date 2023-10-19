package edu.hw2.task1;

public record Negate(Expr number) implements Expr {
    @Override
    public double evaluate() {
        return -1 * number.evaluate();
    }

    public Negate(double number) {
        this(new Constant(number));
    }

    @Override
    public String toString() {
        return Double.toString(evaluate());
    }
}
