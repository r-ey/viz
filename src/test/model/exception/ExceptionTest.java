package model.exception;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

// Unit tests for all exceptions in exception package
public class ExceptionTest {

    @Test
    public void testDateFormat() {
        assertEquals("200 is not in yyyy-mm-dd format", new DateFormatException("200").getMessage());
    }

    @Test
    public void testDateMonthYearZero() {
        assertEquals("There is no 0 year or 0 month or 0 date", new DateMonthYearZeroException().getMessage());
    }

    @Test
    public void testNoDateExist() {
        assertEquals("There is no date 31 in month of 11", new NoDateExistException(11, 31).getMessage());
    }

    @Test
    public void testNoMonthExist() {
        assertEquals("Month of 13 does not exist", new NoMonthExistException(13).getMessage());
    }

    @Test
    public void testNotInteger() {
        assertEquals("a is not an integer", new NotIntegerException("a").getMessage());
    }

    @Test
    public void testOptionsOutOfBounds() {
        assertEquals("3 is not larger than 0 and smaller than 2",
                new OptionsOutOfBoundsException(0, 2, 3).getMessage());
    }

    @Test
    public void testDifferentSize() {
        assertEquals("size is not the same", new DifferentSizeException().getMessage());
    }

    @Test
    public void testInvalidNumber() {
        assertEquals("number input is invalid", new InvalidNumberException().getMessage());
    }

    @Test
    public void testNotHundredPercent() {
        assertEquals("total is not a hundred percent", new NotHundredPercentException().getMessage());
    }
}
