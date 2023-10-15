package edu.project1;

import java.util.ArrayList;

public class UnitTestGame implements Game {
    private final Dictionary dictionary;
    private final Printer printer = new UnitTestPrinter();
    private final Inputer inputer;
    private boolean gameIsEnded = false;

    public UnitTestGame(int indexOfWord, String[] inputLines) {
        this.dictionary = new UnitTestDictionary(indexOfWord);
        this.inputer = new UnitTestInputer(inputLines);
    }

    public ArrayList<String> getReceivedString() {
        UnitTestPrinter unitTestPrinter = (UnitTestPrinter) printer;
        return unitTestPrinter.getPrintedLines();
    }

    @Override
    public void run() {
        printer.outputLine(MessagesForUser.HELLO);
        while (!gameIsEnded) {
            Session session = new Session(printer, inputer, dictionary.randomWord(),
                new GetterNextLetter(inputer, printer)
            );
            session.run();
            handleEnd();
        }
    }

    private void handleEnd() {
        printer.outputLine(MessagesForUser.EXIT);
        String input = inputer.getLine();
        if (input != null && input.equalsIgnoreCase("yes")) {
            gameIsEnded = true;
        }
    }
}
