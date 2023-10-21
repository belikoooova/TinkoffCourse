package edu.project1;

import java.util.ArrayList;

public class UnitTestGame implements Game {
    private final Dictionary dictionary;
    private final Printer printer = new UnitTestPrinter();
    private final Reader reader;
    private boolean gameIsEnded = false;

    public UnitTestGame(int indexOfWord, String[] inputLines) {
        this.dictionary = new UnitTestDictionary(indexOfWord);
        this.reader = new UnitTestReader(inputLines);
    }

    public ArrayList<String> getReceivedString() {
        UnitTestPrinter unitTestPrinter = (UnitTestPrinter) printer;
        return unitTestPrinter.getPrintedLines();
    }

    @Override
    public void run() {
        printer.printLine(HumanReadableMessage.HELLO);
        while (!gameIsEnded) {
            Session session = new Session(printer, reader, dictionary.generateRandomWord(),
                new NextLetterProvider(reader, printer)
            );
            session.run();
            handleEnd();
        }
    }

    private void handleEnd() {
        printer.printLine(HumanReadableMessage.EXIT);
        String input = reader.getLine();
        if (input != null && input.equalsIgnoreCase("yes")) {
            gameIsEnded = true;
        }
    }
}
