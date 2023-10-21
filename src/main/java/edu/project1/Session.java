package edu.project1;

import java.util.HashSet;
import org.jetbrains.annotations.NotNull;

public class Session {
    private final Printer printer;
    private final Reader reader;
    private final @NotNull String answer;
    private final int maxAttempts;
    private final NextLetterProvider nextLetterProvider;
    private final LetterHandler letterHandler;
    private int attempts = 0;
    private final HashSet<Character> userAnswers = new HashSet<>();

    public Session(Printer printer, Reader reader, @NotNull String answer, NextLetterProvider nextLetterProvider) {
        this.printer = printer;
        this.reader = reader;
        this.answer = answer.toLowerCase();
        this.maxAttempts = answer.length();
        this.nextLetterProvider = nextLetterProvider;
        this.letterHandler = new LetterHandler();
    }

    public void run() {
        do {
            char letter = nextLetterProvider.getLetter();
            letterHandler.handle(letter);
        } while (shouldContinueGaming());
    }

    private boolean shouldContinueGaming() {
        if (attempts == maxAttempts) {
            lose();
            return false;
        } else if (letterHandler.getState().equals(answer)) {
            win();
            return false;
        } else {
            return giveUp();
        }
    }

    private void lose() {
        printer.printLine(HumanReadableMessage.LOSE);
    }

    private void win() {
        printer.printLine(HumanReadableMessage.WIN);
    }

    private boolean giveUp() {
        printer.printLine(HumanReadableMessage.GIVE_UP);
        String input = reader.getLine();
        return input != null && !input.equalsIgnoreCase("yes");
    }

    private class LetterHandler {
        private String state;

        public String getState() {
            return state;
        }

        LetterHandler() {
            addGuessedLetters();
        }

        private void addGuessedLetters() {
            StringBuilder stringBuilder = new StringBuilder();
            for (int i = 0; i < answer.length(); ++i) {
                char letter = answer.charAt(i);
                stringBuilder.append(userAnswers.contains(letter) ? letter : '*');
            }
            state = stringBuilder.toString();
        }

        private void handle(char letter) {
            if (userAnswers.contains(letter)) {
                letterAlreadyHasBeenWritten();
                return;
            }
            guessingLetter(letter);
        }

        private void letterAlreadyHasBeenWritten() {
            printer.printLine(HumanReadableMessage.LETTER_ALREADY_HAS_BEEN_WRITTEN);
            printer.printLine(String.format(HumanReadableMessage.CURRENT_WORD.toString(), state));
        }

        private void guessingLetter(char letter) {
            userAnswers.add(letter);
            if (answer.contains(Character.toString(letter))) {
                letterWasGuessed();
            } else {
                letterWasNotGuessed();
            }
            printer.printLine(String.format(HumanReadableMessage.CURRENT_WORD.toString(), state));
        }

        private void letterWasGuessed() {
            printer.printLine(HumanReadableMessage.LETTER_IS_GUESSED);
            addGuessedLetters();
        }

        private void letterWasNotGuessed() {
            ++attempts;
            printer.printLine(HumanReadableMessage.LETTER_IS_NOT_GUESSED);
            printer.printLine(String.format(HumanReadableMessage.MISTAKES_INFO.toString(), attempts, maxAttempts));
        }
    }
}
