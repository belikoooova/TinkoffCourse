package edu.hw2.task1;

public record Addition(Expr a, Expr b) implements Expr {
    @Override
    public double evaluate() {
        return a.evaluate() + b.evaluate();
    }

    public Addition(double a, double b) {
        this(new Constant(a), new Constant(b));
    }

    public Addition(Expr a, double b) {
        this(a, new Constant(b));
    }

    public Addition(double a, Expr b) {
        this(new Constant(a), b);
    }

    @Override
    public String toString() {
        return "(" + a.evaluate() + " + " + b.evaluate() + ")";
    }
}
