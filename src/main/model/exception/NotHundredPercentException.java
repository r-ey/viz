package model.exception;

public class NotHundredPercentException extends Exception {

    private static final String MESSAGE = "total is not a hundred percent";

    public NotHundredPercentException() {
        super();
    }

    // Overriding method for getting message
    // EFFECTS : return exception as string
    public String getMessage() {
        return MESSAGE;
    }
}
