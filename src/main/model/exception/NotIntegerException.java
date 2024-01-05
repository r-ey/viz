package model.exception;

// exception when option is not an integer
public class NotIntegerException extends Exception {

    private final String input;

    // EFFECTS : creating a new exception
    public NotIntegerException(String s) {
        super();
        input = s;
    }

    // Overriding method for getting message
    // EFFECTS : return exception as string
    public String getMessage() {
        return input + " is not an integer";
    }
}
