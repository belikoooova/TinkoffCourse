package edu.hw7.task3;

public class DuplicatedPersonIdException extends RuntimeException {
    public DuplicatedPersonIdException(int id) {
        super("Person with ID %d already exist.".formatted(id));
    }
}
