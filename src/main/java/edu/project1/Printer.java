package edu.project1;

public interface Printer {
    void outputLine(Object object);

    void outputLine(String line);

    void outputLine(String line, int firstParameter, int secondParameter);

    void outputLine(String line, String firstParameter);
}
