package edu.project1;

import org.apache.logging.log4j.LogManager;
import java.util.Scanner;

public class CLIGame implements Game {

    private final Dictionary dictionary;
    private final org.apache.logging.log4j.Logger logger = LogManager.getLogger();
    private final Scanner scanner = new Scanner(System.in);
    private boolean gameIsEnded = false;

    public CLIGame(Dictionary dictionary) {
        this.dictionary = dictionary;
    }

    @Override
    public void run() {
        logger.info(MessagesForUser.HELLO);
        while (!gameIsEnded) {
            Session session = new Session(logger, scanner, dictionary.randomWord(),
                new CLIGetterNextLetter(scanner, logger));
            session.run();
            handleEnd();
        }
    }

    private void handleEnd() {
        logger.info(MessagesForUser.EXIT);
        String input = scanner.nextLine();
        if (input != null && input.equalsIgnoreCase("yes")) {
            gameIsEnded = true;
        }
    }
}
