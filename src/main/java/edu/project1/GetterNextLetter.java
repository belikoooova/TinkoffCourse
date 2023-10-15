package edu.project1;

public class GetterNextLetter {
    private final Inputer inputer;
    private final Printer printer;

    public GetterNextLetter(Inputer inputer, Printer printer) {
        this.inputer = inputer;
        this.printer = printer;
    }

    public char getLetter() {
        boolean isSuccess = false;
        char letter = '0';
        while (!isSuccess) {
            printer.outputLine(MessagesForUser.GUESS_LETTER);
            String input = inputer.getLine();
            if (!letterIsCorrectHandler(input)) {
                continue;
            }
            isSuccess = true;
            letter = Character.toLowerCase(input.charAt(0));
        }
        return letter;
    }

    private boolean letterIsCorrectHandler(String input) {
        if (input == null || input.length() != 1) {
            printer.outputLine(MessagesForUser.NOT_CHAR_INPUT);
            return false;
        }
        char character = input.charAt(0);
        if (!Character.isLetter(character)) {
            printer.outputLine(MessagesForUser.NOT_LETTER_INPUT);
            return false;
        }
        return true;
    }
}
