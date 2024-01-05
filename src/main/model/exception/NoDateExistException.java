package model.exception;

// exception when date and month does not match
public class NoDateExistException extends Exception {

    private final int month;
    private final int date;

    // EFFECTS : creating a new exception
    public NoDateExistException(int m, int d) {
        super();
        month = m;
        date = d;
    }

    // Overriding method for getting message
    // EFFECTS : return exception as string
    public String getMessage() {
        return "There is no date " + date + " in month of " + month;
    }
}
