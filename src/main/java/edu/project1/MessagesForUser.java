package edu.project1;

public enum MessagesForUser {
    INCORRECT_LENGTH_OF_WORD("Sorry, length of answer word is incorrect. The game cannot be started"),
    NOT_CHAR_INPUT("You must input just one symbol"),
    NOT_LETTER_INPUT("You must input the letter"),
    GUESS_LETTER("Guess a next letter:"),
    LETTER_ALREADY_HAS_BEEN_WRITTEN("You have already written this letter"),
    LETTER_IS_GUESSED("Hit!"),
    LETTER_IS_NOT_GUESSED("Wrong!"),
    WIN("You won!"),
    LOSE("You lose"),
    CURRENT_WORD("The word: %s"),
    MISTAKES_INFO("Mistakes %d of %d"),
    HELLO("Hello! This is hangman game"),
    GIVE_UP("Do you want to give up? Write yes if you want, else a new game will be started"),
    EXIT("Do you want to exit? Write yes if you want, else a new game will be started");

    private final String message;

    MessagesForUser(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return this.message;
    }
}
