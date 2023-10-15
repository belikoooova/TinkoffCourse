package edu.project1;

public interface Printer {
    public void outputLine(Object object);
    public void outputLine(String line);
    public void outputLine(String line, int firstParameter, int secondParameter);
    public void outputLine(String line, String firstParameter);
}
