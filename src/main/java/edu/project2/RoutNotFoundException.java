package edu.project2;

public class RoutNotFoundException extends IllegalArgumentException {
    public RoutNotFoundException(String s) {
        super(s);
    }
}
