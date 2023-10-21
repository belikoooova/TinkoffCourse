package edu.project1;

import java.util.ArrayList;

public class UnitTestPrinter implements Printer {
    private final ArrayList<String> printedLines = new ArrayList<>();

    @Override
    public void printLine(HumanReadableMessage message) {
        printedLines.add(message.toString());
    }

    @Override
    public void printLine(String line) {
        printedLines.add(line);
    }

    public ArrayList<String> getPrintedLines() {
        return printedLines;
    }
}
