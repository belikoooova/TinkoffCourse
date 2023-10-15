package edu.project1;

import java.util.ArrayList;

public class UnitTestPrinter implements Printer {
    private final ArrayList<String> printedLines = new ArrayList<>();
    @Override
    public void outputLine(Object object) {
        printedLines.add(object.toString());
    }

    @Override
    public void outputLine(String line) {
        printedLines.add(line);
    }

    @Override
    public void outputLine(String line, int firstParameter, int secondParameter) {
        printedLines.add(String.format(line, firstParameter, secondParameter));
    }

    @Override
    public void outputLine(String line, String firstParameter) {
        printedLines.add(String.format(line, firstParameter));
    }

    public ArrayList<String> getPrintedLines() {
        return printedLines;
    }
}
