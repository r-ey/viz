package model.exception;

// exception when month is more than 12
public class NoMonthExistException extends Exception {

    private final int month;

    // EFFECTS : creating a new exception
    public NoMonthExistException(int m) {
        super();
        month = m;
    }

    // Overriding method for getting message
    // EFFECTS : return exception as string
    public String getMessage() {
        return "Month of " + month + " does not exist";
    }
}
