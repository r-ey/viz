package model.exception;

// exception when format isn't yyyy-mm-dd format
public class DateFormatException extends Exception {

    private final String input;
    private static final String MESSAGE = " is not in yyyy-mm-dd format";

    // EFFECTS : creating a new exception
    public DateFormatException(String s) {
        super();
        input = s;
    }

    // Overriding method for getting message
    // EFFECTS : return exception as string
    public String getMessage() {
        return input + MESSAGE;
    }
}
