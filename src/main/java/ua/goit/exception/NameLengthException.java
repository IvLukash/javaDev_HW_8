package ua.goit.exception;

public class NameLengthException extends RuntimeException {
    public NameLengthException(String message) {
        super(message);
    }
}
