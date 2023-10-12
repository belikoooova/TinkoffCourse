package edu.hw2.task1;

public sealed interface Expr {
    double evaluate();

    public record Constant(double value) implements Expr {
        @Override
        public double evaluate() {
            return value();
        }

        @Override
        public String toString() {
            return Double.toString(evaluate());
        }
    }

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

    public record Multiplication(Expr a, Expr b) implements Expr {
        @Override
        public double evaluate() {
            return a.evaluate() * b.evaluate();
        }

        public Multiplication(double a, double b) {
            this(new Constant(a), new Constant(b));
        }

        public Multiplication(Expr a, double b) {
            this(a, new Constant(b));
        }

        public Multiplication(double a, Expr b) {
            this(new Constant(a), b);
        }

        @Override
        public String toString() {
            return "(" + a.evaluate() + " * " + b.evaluate() + ")";
        }
    }
}
