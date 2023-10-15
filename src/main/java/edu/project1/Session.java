package edu.project1;

import org.jetbrains.annotations.NotNull;
import java.util.HashSet;

public class Session {
    // private final Logger logger;
    private final Printer printer;
    // private final Scanner scanner;
    private final Inputer inputer;
    private final @NotNull String answer;
    private final int maxAttempts;
    private final GetterNextLetter getterNextLetter;
    private final HandlerLetter handlerLetter;
    private int attempts = 0;
    private boolean gameIsFinished = false;
    private final HashSet<Character> userAnswers = new HashSet<>();
    private String state;

    public Session(Printer printer, Inputer inputer, @NotNull String answer, GetterNextLetter getterNextLetter) {
        this.printer = printer;
        this.inputer = inputer;
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
            printer.outputLine(MessagesForUser.INCORRECT_LENGTH_OF_WORD);
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
                handleEnd();
                return;
            }
            handleGuessingLetter(letter);
            handleEnd();
        }

        private void handleLetterAlreadyHasBeenWritten() {
            printer.outputLine(MessagesForUser.LETTER_ALREADY_HAS_BEEN_WRITTEN);
            changeState();
            printer.outputLine(String.format(MessagesForUser.CURRENT_WORD.toString(), state));
        }

        private void handleGuessingLetter(char letter) {
            userAnswers.add(letter);
            if (answer.contains(Character.toString(letter))) {
                handleGuessedLetter();
            } else {
                handleNotGuessedLetter();
            }
            printer.outputLine(String.format(MessagesForUser.CURRENT_WORD.toString(), state));
        }

        private void handleGuessedLetter() {
            printer.outputLine(MessagesForUser.LETTER_IS_GUESSED);
            changeState();
        }

        private void handleNotGuessedLetter() {
            ++attempts;
            printer.outputLine(MessagesForUser.LETTER_IS_NOT_GUESSED);
            printer.outputLine(String.format(MessagesForUser.MISTAKES_INFO.toString(), attempts, maxAttempts));
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
            printer.outputLine(MessagesForUser.LOSE);
            gameIsFinished = true;
        }

        private void handleWon() {
            printer.outputLine(MessagesForUser.WIN);
            gameIsFinished = true;
        }

        private void handleGiveUp() {
            printer.outputLine(MessagesForUser.GIVE_UP);
            String input = inputer.getLine();
            if (input != null && input.equalsIgnoreCase("yes")) {
                gameIsFinished = true;
            }
        }
    }
}
