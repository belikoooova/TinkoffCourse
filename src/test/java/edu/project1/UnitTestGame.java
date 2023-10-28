package edu.project1;

import java.util.ArrayList;

public class UnitTestGame implements Game {
    private final Dictionary dictionary;
    private final Printer printer = new UnitTestPrinter();
    private final Reader reader;

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
        String answer = dictionary.generateRandomWord();
        ;
        if (shouldNotStartGaming(answer)) {
            if (!shouldContinueGaming()) {
                return;
            }
        }
        do {
            Session session = new Session(printer, reader, answer,
                new NextLetterProvider(reader, printer)
            );
            session.run();
        }
        while (shouldContinueGaming());
    }

    private boolean shouldNotStartGaming(String answer) {
        if (answer.length() <= 1) {
            printer.printLine(HumanReadableMessage.INCORRECT_LENGTH_OF_WORD);
            return true;
        }
        return false;
    }

    private boolean shouldContinueGaming() {
        printer.printLine(HumanReadableMessage.EXIT);
        String input = reader.getLine();
        return input != null && !input.equalsIgnoreCase("yes");
    }
}
