package model.exception;

public class DifferentSizeException extends Exception {

    private static final String MESSAGE = "size is not the same";

    public DifferentSizeException() {
        super();
    }

    // Overriding method for getting message
    // EFFECTS : return exception as string
    public String getMessage() {
        return MESSAGE;
    }
}
