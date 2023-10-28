package edu.project1;

public class NextLetterProvider {
    private final Reader reader;
    private final Printer printer;

    public NextLetterProvider(Reader reader, Printer printer) {
        this.reader = reader;
        this.printer = printer;
    }

    public char getLetter() {
        String userInput;
        do {
            userInput = getUserInput();
        } while (!isCorrectLetter(userInput));
        return Character.toLowerCase(userInput.charAt(0));
    }

    private String getUserInput() {
        printer.printLine(HumanReadableMessage.GUESS_LETTER);
        return reader.getLine();
    }

    private boolean isCorrectLetter(String input) {
        if (input == null || input.length() != 1) {
            printer.printLine(HumanReadableMessage.NOT_CHAR_INPUT);
            return false;
        }
        char character = input.charAt(0);
        if (!Character.isLetter(character)) {
            printer.printLine(HumanReadableMessage.NOT_LETTER_INPUT);
            return false;
        }
        return true;
    }
}
