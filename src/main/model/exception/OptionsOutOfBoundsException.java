package model.exception;

// exception when option is out of bound
public class OptionsOutOfBoundsException extends Exception {

    private int minimum;
    private int maximum;
    private int input;

    // EFFECTS : creating a new exception
    public OptionsOutOfBoundsException(int min, int max, int i) {
        super();
        minimum = min;
        maximum = max;
        input = i;
    }

    // Overriding method for getting message
    // EFFECTS : return exception as string
    public String getMessage() {
        return input + " is not larger than " + minimum + " and smaller than " + maximum;
    }
}
