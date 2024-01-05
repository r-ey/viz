package model.exception;

// exception when there is 0 year or 0 month or 0 date
public class DateMonthYearZeroException extends Exception {

    private static final String MESSAGE = "There is no 0 year or 0 month or 0 date";

    public DateMonthYearZeroException() {
        super();
    }

    // Overriding method for getting message
    // EFFECTS : return exception as string
    public String getMessage() {
        return MESSAGE;
    }
}
