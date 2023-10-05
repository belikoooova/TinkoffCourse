package edu.hw1;

public class Task7 {
    private Task7() {
    }

    @SuppressWarnings("MagicNumber")
    public static int rotateRight(int n, int shift) {
        int size = (int) (Math.log10(n) / Math.log10(2)) + 1;
        shift %= size;
        int left = n >> shift;
        int right = n << (size - shift);
        return (left | right) % (int) Math.pow(2, size);
    }

    @SuppressWarnings("MagicNumber")
    public static int rotateLeft(int n, int shift) {
        int size = (int) (Math.log10(n) / Math.log10(2)) + 1;
        shift %= size;
        int left = n << shift;
        int right = n >> (size - shift);
        return (left | right) % (int) Math.pow(2, size);
    }
}
