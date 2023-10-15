package edu.project1;

import org.apache.logging.log4j.Logger;
import org.jetbrains.annotations.NotNull;
import java.util.HashSet;
import java.util.Scanner;

public class Session {
    private final Logger logger;
    private final Scanner scanner;
    private final @NotNull String answer;
    private final int maxAttempts;
    private final GetterNextLetter getterNextLetter;
    private final HandlerLetter handlerLetter;
    private int attempts = 0;
    private boolean gameIsFinished = false;
    private final HashSet<Character> userAnswers = new HashSet<>();
    private String state;

    public Session(Logger logger, Scanner scanner, @NotNull String answer, GetterNextLetter getterNextLetter) {
        this.logger = logger;
        this.scanner = scanner;
        this.answer = answer.toLowerCase();
        this.maxAttempts = answer.length();
        this.getterNextLetter = getterNextLetter;
        changeState();
        this.handlerLetter = new HandlerLetter();
    }

    private void changeState() {
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < answer.length(); ++i) {
            char letter = answer.charAt(i);
            stringBuilder.append(userAnswers.contains(letter) ? letter : '*');
        }
        state = stringBuilder.toString();
    }

    public void run() {
        if (answer.length() <= 1) {
            logger.info(MessagesForUser.INCORRECT_LENGTH_OF_WORD);
            return;
        }
        while (!gameIsFinished) {
            char letter = getterNextLetter.getLetter();
            handlerLetter.handleLetter(letter);
        }
    }

    private class HandlerLetter {
        private void handleLetter(char letter) {
            if (userAnswers.contains(letter)) {
                handleLetterAlreadyHasBeenWritten();
                return;
            }
            handleGuessingLetter(letter);
            handleEnd();
        }

        private void handleLetterAlreadyHasBeenWritten() {
            logger.info(MessagesForUser.LETTER_ALREADY_HAS_BEEN_WRITTEN);
            changeState();
        }

        private void handleGuessingLetter(char letter) {
            userAnswers.add(letter);
            if (answer.contains(Character.toString(letter))) {
                handleGuessedLetter();
            } else {
                handleNotGuessedLetter();
            }
            logger.info(String.format(MessagesForUser.CURRENT_WORD.toString(), state));
        }

        private void handleGuessedLetter() {
            logger.info(MessagesForUser.LETTER_IS_GUESSED);
            changeState();
        }

        private void handleNotGuessedLetter() {
            ++attempts;
            logger.info(MessagesForUser.LETTER_IS_NOT_GUESSED);
            logger.info(String.format(MessagesForUser.MISTAKES_INFO.toString(), attempts, maxAttempts));
        }

        private void handleEnd() {
            if (attempts == maxAttempts) {
                handleLose();
                return;
            }
            if (state.equals(answer)) {
                handleWon();
                return;
            }
            handleGiveUp();
        }

        private void handleLose() {
            logger.info(MessagesForUser.LOSE);
            gameIsFinished = true;
        }

        private void handleWon() {
            logger.info(MessagesForUser.WIN);
            gameIsFinished = true;
        }

        private void handleGiveUp() {
            logger.info(MessagesForUser.GIVE_UP);
            String input = scanner.nextLine();
            if (input != null && input.equalsIgnoreCase("yes")) {
                gameIsFinished = true;
            }
        }
    }
}
