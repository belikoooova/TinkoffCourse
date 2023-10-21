package edu.project1;

public class CLIGame implements Game {
    private final Dictionary dictionary = new BuiltInMemoryDictionary();
    private final Printer printer = new CLIPrinter();
    private final Reader reader = new CLIReader();

    @Override
    public void run() {
        printer.printLine(HumanReadableMessage.HELLO);
        do {
            Session session = new Session(printer, reader, dictionary.generateRandomWord(),
                new NextLetterProvider(reader, printer)
            );
            session.run();
        }
        while (shouldContinueGaming());
    }

    private boolean shouldContinueGaming() {
        printer.printLine(HumanReadableMessage.EXIT);
        String input = reader.getLine();
        return input != null && !input.equalsIgnoreCase("yes");
    }
}
