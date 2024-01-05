package model.component;

// Creating a Grade object to store the percentage of grade, the real grade, and empty or not
public class Grade {

    private float percentage;
    private float real;
    private boolean isEmpty;

    // REQUIRES : if empty, real equals to 0
    // EFFECTS : construct a grade with percentage, real value, and isEmpty attributes
    public Grade(float p, float r, boolean i) {
        percentage = p;
        real = r;
        isEmpty = i;
    }

    public float getPercentage() {
        return percentage;
    }

    public float getReal() {
        return real;
    }

    public boolean getIsEmpty() {
        return isEmpty;
    }

    public void setPercentage(float f) {
        percentage = f;
    }

    public void setReal(float f) {
        real = f;
    }

    public void setIsEmpty(boolean b) {
        isEmpty = b;
    }

    // Overriding method for printing Grade
    // EFFECTS : make string of a Grade object
    @Override
    public String toString() {
        String formatReal = String.format("%.2f", real);
        String formatPercentage = String.format("%.2f", percentage);
        return "Max Percentage : " + formatPercentage + ((isEmpty) ? ", Grade is Empty\n" : ", Real : "
                + formatReal + "\n");
    }
}
