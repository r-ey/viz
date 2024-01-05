package model.component;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

// Unit tests for Grade class
public class GradeTest {

    private Grade testGrade;

    @BeforeEach
    public void setUp() {
        testGrade = new Grade(50f, 45f, false);
    }

    @Test
    public void testGrade() {
        assertEquals(50f, testGrade.getPercentage());
        assertEquals(45f, testGrade.getReal());
        assertFalse(testGrade.getIsEmpty());
    }

    @Test
    public void testSetter() {
        testGrade.setPercentage(60f);
        testGrade.setReal(0f);
        testGrade.setIsEmpty(true);

        assertEquals(60f, testGrade.getPercentage());
        assertEquals(0f, testGrade.getReal());
        assertTrue(testGrade.getIsEmpty());
    }

    @Test
    public void testPrintEmpty() {
        testGrade.setPercentage(60f);
        testGrade.setReal(0f);
        testGrade.setIsEmpty(true);

        assertEquals("Max Percentage : 60.00, Grade is Empty\n", testGrade.toString());
    }

    @Test
    public void testPrintNotEmpty() {
        assertEquals("Max Percentage : 50.00" + ", Real : 45.00\n", testGrade.toString());
    }
}
