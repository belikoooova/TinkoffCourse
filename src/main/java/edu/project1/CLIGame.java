package edu.project1;

public class CLIGame implements Game {
    private final Dictionary dictionary = new BuiltInDictionary();
    private final Printer printer = new CLIPrinter();
    private final Inputer inputer = new CLIInputer();
    private boolean gameIsEnded = false;

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
