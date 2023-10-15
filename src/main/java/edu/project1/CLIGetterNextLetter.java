package edu.project1;

import org.apache.logging.log4j.Logger;
import java.util.Scanner;

public class CLIGetterNextLetter implements GetterNextLetter {
    private final Scanner scanner;
    private final Logger logger;

    public CLIGetterNextLetter(Scanner scanner, Logger logger) {
        this.scanner = scanner;
        this.logger = logger;
    }

    @Override
    public char getLetter() {
        boolean isSuccess = false;
        char letter = '0';
        while (!isSuccess) {
            logger.info(MessagesForUser.GUESS_LETTER);
            String input = scanner.nextLine();
            if (!letterIsCorrectHandler(input)) {
                continue;
            }
            isSuccess = true;
            letter = Character.toLowerCase(input.charAt(0));
        }
        return letter;
    }

    private boolean letterIsCorrectHandler(String input) {
        if (input == null || input.length() > 1) {
            logger.info(MessagesForUser.NOT_CHAR_INPUT);
            return false;
        }
        char character = input.charAt(0);
        if (!Character.isLetter(character)) {
            logger.info(MessagesForUser.NOT_LETTER_INPUT);
            return false;
        }
        return true;
    }
}
