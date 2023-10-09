package edu.hw1;

public class Task7 {
    private static final int ONE = 1;
    private static final int TWO = 2;

    private Task7() {
    }

    public static int rotateRight(int n, int shift) {
        int size = (int) (Math.log10(n) / Math.log10(TWO)) + ONE;
        int newShift = shift % size;
        int left = n >> newShift;
        int right = n << (size - newShift);
        return (left | right) % (int) Math.pow(TWO, size);
    }

    public static int rotateLeft(int n, int shift) {
        int size = (int) (Math.log10(n) / Math.log10(TWO)) + ONE;
        int newShift = shift % size;
        int left = n << newShift;
        int right = n >> (size - newShift);
        return (left | right) % (int) Math.pow(TWO, size);
    }
}
