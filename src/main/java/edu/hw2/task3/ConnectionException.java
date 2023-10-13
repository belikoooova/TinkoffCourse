package edu.hw2.task3;

public class ConnectionException extends RuntimeException {
    private final Exception cause;

    public ConnectionException() {
        cause = null;
    }

    public ConnectionException(Exception cause) {
        this.cause = cause;
    }

    public Exception getCause() {
        return cause;
    }
}
