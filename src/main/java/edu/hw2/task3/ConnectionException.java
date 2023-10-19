package edu.hw2.task3;

public class ConnectionException extends RuntimeException {
    public ConnectionException() {
    }

    public ConnectionException(Exception cause) {
        super(cause);
    }

    public ConnectionException(String message, Exception cause) {
        super(message, cause);
    }
}
