package model.exception;

public class InvalidNumberException extends Exception {

    private static final String MESSAGE = "number input is invalid";

    public InvalidNumberException() {
        super();
    }

    // Overriding method for getting message
    // EFFECTS : return exception as string
    public String getMessage() {
        return MESSAGE;
    }
}
