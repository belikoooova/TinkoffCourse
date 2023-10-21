package edu.project1;

public class CLIGame implements Game {
    private final Dictionary dictionary = new BuiltInMemoryDictionary();
    private final Printer printer = new CLIPrinter();
    private final Reader reader = new CLIReader();

    @Override
    public void run() {
        printer.printLine(HumanReadableMessage.HELLO);
        String answer;
        do {
            answer = dictionary.generateRandomWord();
        } while (shouldNotStartGaming(answer));

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
