package edu.hw2.task1;

public record Exponent(Expr base, double exponent) implements Expr {
    @Override
    public double evaluate() {
        return Math.pow(base.evaluate(), exponent);
    }

    public Exponent(double base, double exponent) {
        this(new Constant(base), exponent);
    }

    public Exponent(Expr base, Expr exponent) {
        this(base, exponent.evaluate());
    }

    @Override
    public String toString() {
        return base.evaluate() + " ^ " + exponent;
    }
}
