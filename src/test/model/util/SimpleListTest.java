package model.util;

import model.component.Grade;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

// Unit tests for SimpleList class
public class SimpleListTest {

    private static final int MULTIPLE = 5;

    private static SimpleList<Integer> testIntegerList;
    private static SimpleList<Float> testFloatList;
    private static SimpleList<String> testStringList;
    private static SimpleList<Grade> testGradeList;

    @BeforeEach
    public void setUpInteger() {
        testIntegerList = new SimpleList<>();
    }

    // for testVarious
    public void setUpVarious() {
        testFloatList = new SimpleList<>();
        testStringList = new SimpleList<>();
        testGradeList = new SimpleList<>();
    }

    @Test
    public void testSimpleList() {
        assertNull(testIntegerList.getHead());
        assertNull(testIntegerList.getTail());
        assertEquals(0, testIntegerList.size());
    }

    @Test
    public void testIntegerAddOne() {
        testIntegerList.add(1);
        assertEquals(1, testIntegerList.get(0));
        assertEquals(1, testIntegerList.size());
    }

    @Test
    public void testIntegerAddMultiple() {
        for (int i = 0; i < MULTIPLE; i++) {
            testIntegerList.add(i);
            assertEquals(i, testIntegerList.get(i));
            assertEquals(i + 1, testIntegerList.size());
        }
    }

    @Test
    public void testIntegerDeleteOne() {
        testIntegerList.add(1);
        testIntegerList.delete(0);
        assertEquals(0, testIntegerList.size());
    }

    @Test
    public void testIntegerDeleteMultiple() {
        for (int i = 0; i < MULTIPLE; i++) {
            testIntegerList.add(i);
        }
        testIntegerList.delete(0);
        testIntegerList.delete(2);
        testIntegerList.delete(1);

        assertEquals(1, testIntegerList.get(0));
        assertEquals(4, testIntegerList.get(1));
        assertEquals(2, testIntegerList.size());
    }

    @Test
    public void testIntegerSetOne() {
        testIntegerList.add(1);
        testIntegerList.set(0, 2);
        assertEquals(2, testIntegerList.get(0));
    }

    @Test
    public void testIntegerSetMultiple() {
        for (int i = 0; i < MULTIPLE; i++) {
            testIntegerList.add(i);
        }
        testIntegerList.set(0, 10);
        testIntegerList.set(1, 20);
        testIntegerList.set(3, 30);
        assertEquals(10, testIntegerList.get(0));
        assertEquals(20, testIntegerList.get(1));
        assertEquals(30, testIntegerList.get(3));
    }

    @Test
    public void testIntegerPrintOne() {
        testIntegerList.add(1);
        assertEquals("1 ", testIntegerList.toString());
    }

    @Test
    public void testIntegerPrintMultiple() {
        for (int i = 0; i < MULTIPLE; i++) {
            testIntegerList.add(i);
        }
        assertEquals("0 1 2 3 4 ", testIntegerList.toString());
    }

    @Test
    public void testVariousAddOne() {
        setUpVarious();

        String grade = "Max Percentage : 2.50" + ", Real : 2.00\n";

        testIntegerList.add(1);
        testFloatList.add(2f);
        testStringList.add("a");
        testGradeList.add(new Grade(2.5f, 2.0f, false));

        assertEquals(1, testIntegerList.get(0));
        assertEquals(2f, testFloatList.get(0));
        assertEquals("a", testStringList.get(0));
        assertEquals(grade, testGradeList.get(0).toString());

        assertEquals(1, testIntegerList.size());
        assertEquals(1, testFloatList.size());
        assertEquals(1, testStringList.size());
        assertEquals(1, testGradeList.size());
    }

    @Test
    public void testVariousAddMultiple() {
        setUpVarious();

        String grade0 = "Max Percentage : 2.50" + ", Real : 2.00\n";
        String grade1 = "Max Percentage : 20.00" + ", Real : 15.00\n";
        String grade2 = "Max Percentage : 10.00, Grade is Empty\n";
        String grade3 = "Max Percentage : 5.00, Grade is Empty\n";
        String grade4 = "Max Percentage : 40.00" + ", Real : 37.50\n";

        for (int i = 0; i < MULTIPLE; i++) {
            testIntegerList.add(i);
            testFloatList.add(i * 2.5f);
            testStringList.add(String.valueOf('a' + i));

            assertEquals(i, testIntegerList.get(i));
            assertEquals(i + 1, testIntegerList.size());

            assertEquals(i * 2.5f, testFloatList.get(i));
            assertEquals(i + 1, testFloatList.size());

            assertEquals(String.valueOf('a' + i), testStringList.get(i));
            assertEquals(i + 1, testStringList.size());
        }

        testGradeList.add(new Grade(2.5f, 2.0f, false));
        testGradeList.add(new Grade(20f, 15f, false));
        testGradeList.add(new Grade(10f, 0f, true));
        testGradeList.add(new Grade(5f, 0f, true));
        testGradeList.add(new Grade(40f, 37.5f, false));

        assertEquals(grade0, testGradeList.get(0).toString());
        assertEquals(grade1, testGradeList.get(1).toString());
        assertEquals(grade2, testGradeList.get(2).toString());
        assertEquals(grade3, testGradeList.get(3).toString());
        assertEquals(grade4, testGradeList.get(4).toString());
        assertEquals(5, testGradeList.size());
    }

    @Test
    public void testVariousDeleteOne() {
        setUpVarious();

        testIntegerList.add(1);
        testFloatList.add(2f);
        testStringList.add("a");
        testGradeList.add(new Grade(2.5f, 2.0f, false));

        testIntegerList.delete(0);
        testFloatList.delete(0);
        testStringList.delete(0);
        testGradeList.delete(0);

        assertEquals(0, testIntegerList.size());
        assertEquals(0, testFloatList.size());
        assertEquals(0, testStringList.size());
        assertEquals(0, testGradeList.size());
    }

    @Test
    public void testVariousDeleteMultiple() {
        setUpVarious();

        String grade2 = "Max Percentage : 10.00, Grade is Empty\n";
        String grade3 = "Max Percentage : 5.00, Grade is Empty\n";

        for (int i = 0; i < MULTIPLE; i++) {
            testIntegerList.add(i);
            testFloatList.add(i * 2.5f);
            testStringList.add(String.valueOf('a' + i));
        }

        testGradeList.add(new Grade(2.5f, 2.0f, false));
        testGradeList.add(new Grade(20f, 15f, false));
        testGradeList.add(new Grade(10f, 0f, true));
        testGradeList.add(new Grade(5f, 0f, true));
        testGradeList.add(new Grade(40f, 37.5f, false));

        testIntegerList.delete(MULTIPLE - 1);
        testIntegerList.delete(0);
        testIntegerList.delete(1);

        testFloatList.delete(1);
        testFloatList.delete(2);

        testStringList.delete(0);
        testStringList.delete(0);
        testStringList.delete(0);
        testStringList.delete(0);
        testStringList.delete(0);

        testGradeList.delete(MULTIPLE - 1);
        testGradeList.delete(0);
        testGradeList.delete(0);

        assertEquals(1, testIntegerList.get(0));
        assertEquals(3, testIntegerList.get(1));

        assertEquals(0f, testFloatList.get(0));
        assertEquals(5f, testFloatList.get(1));
        assertEquals(10f, testFloatList.get(2));

        assertEquals(grade2, testGradeList.get(0).toString());
        assertEquals(grade3, testGradeList.get(1).toString());

        assertEquals(2, testIntegerList.size());
        assertEquals(3, testFloatList.size());
        assertEquals(0, testStringList.size());
        assertEquals(2, testGradeList.size());
    }

    @Test
    public void testVariousSetOne() {
        setUpVarious();

        String grade = "Max Percentage : 20.00" + ", Real : 15.00\n";

        testIntegerList.add(1);
        testFloatList.add(2f);
        testStringList.add("a");
        testGradeList.add(new Grade(2.5f, 2.0f, false));

        testIntegerList.set(0, 2);
        testFloatList.set(0, 5f);
        testStringList.set(0, "b");
        testGradeList.set(0, new Grade(20f, 15f, false));

        assertEquals(2, testIntegerList.get(0));
        assertEquals(5f, testFloatList.get(0));
        assertEquals("b", testStringList.get(0));
        assertEquals(grade, testGradeList.get(0).toString());
    }

    @Test
    public void testVariousSetMultiple() {
        setUpVarious();

        String grade0 = "Max Percentage : 20.00, Grade is Empty\n";
        String grade1 = "Max Percentage : 30.00, Grade is Empty\n";
        String grade4 = "Max Percentage : 60.00" + ", Real : 50.00\n";

        for (int i = 0; i < MULTIPLE; i++) {
            testIntegerList.add(i);
            testFloatList.add(i * 2.5f);
            testStringList.add(String.valueOf('a' + i));
        }

        testGradeList.add(new Grade(2.5f, 2.0f, false));
        testGradeList.add(new Grade(20f, 15f, false));
        testGradeList.add(new Grade(10f, 0f, true));
        testGradeList.add(new Grade(5f, 0f, true));
        testGradeList.add(new Grade(40f, 37.5f, false));

        testIntegerList.set(MULTIPLE - 1, 10);
        testIntegerList.set(0, 20);
        testIntegerList.set(1, 30);

        testFloatList.set(1, 40.5f);
        testFloatList.set(2, 45.5f);

        testStringList.set(0, "v");
        testStringList.set(1, "w");
        testStringList.set(2, "x");
        testStringList.set(3, "y");
        testStringList.set(4, "z");

        testGradeList.set(MULTIPLE - 1, new Grade(60f, 50f, false));
        testGradeList.set(0, new Grade(20f, 0, true));
        testGradeList.set(1, new Grade(30f, 0, true));

        assertEquals(20, testIntegerList.get(0));
        assertEquals(30, testIntegerList.get(1));
        assertEquals(10, testIntegerList.get(4));

        assertEquals(40.5f, testFloatList.get(1));
        assertEquals(45.5f, testFloatList.get(2));

        assertEquals("v", testStringList.get(0));
        assertEquals("w", testStringList.get(1));
        assertEquals("x", testStringList.get(2));
        assertEquals("y", testStringList.get(3));
        assertEquals("z", testStringList.get(4));

        assertEquals(grade0, testGradeList.get(0).toString());
        assertEquals(grade1, testGradeList.get(1).toString());
        assertEquals(grade4, testGradeList.get(4).toString());
    }

    @Test
    public void testVariousPrintOne() {
        setUpVarious();

        String gradeString = "Max Percentage : 2.50" + ", Real : 2.00\n ";
        String floatString = 2f + " ";

        testIntegerList.add(1);
        testFloatList.add(2f);
        testStringList.add("a");
        testGradeList.add(new Grade(2.5f, 2.0f, false));

        assertEquals("1 ", testIntegerList.toString());
        assertEquals(floatString, testFloatList.toString());
        assertEquals("a ", testStringList.toString());
        assertEquals(gradeString, testGradeList.toString());
    }

    @Test
    public void testVariousPrintMultiple() {
        setUpVarious();

        String floatString = 0f + " " + 2.5f + " " + 5f + " " + 7.5f + " " + 10f + " ";
        String gradeString = "Max Percentage : 2.50" + ", Real : 2.00\n" + " " +
                "Max Percentage : 20.00" + ", Real : 15.00\n" + " " +
                "Max Percentage : 10.00, Grade is Empty\n" + " " + "Max Percentage : 5.00, Grade is Empty\n" + " " +
                "Max Percentage : 40.00" + ", Real : 37.50\n" + " ";


        for (int i = 0; i < MULTIPLE; i++) {
            testIntegerList.add(i);
            testFloatList.add(i * 2.5f);
            testStringList.add(String.valueOf((char) ('a' + i)));
        }

        testGradeList.add(new Grade(2.5f, 2.0f, false));
        testGradeList.add(new Grade(20f, 15f, false));
        testGradeList.add(new Grade(10f, 0f, true));
        testGradeList.add(new Grade(5f, 0f, true));
        testGradeList.add(new Grade(40f, 37.5f, false));

        assertEquals("0 1 2 3 4 ", testIntegerList.toString());
        assertEquals(floatString, testFloatList.toString());
        assertEquals("a b c d e ", testStringList.toString());
        assertEquals(gradeString, testGradeList.toString());
    }

    @Test
    public void testVariousPrintTwoLists() {
        SimpleList<String> stringList = new SimpleList<>();
        for (int i = 0; i < MULTIPLE; i++) {
            testIntegerList.add(i);
            stringList.add(String.valueOf((char) ('a' + i)));
        }
        String str = "0\na\n1\nb\n2\nc\n3\nd\n4\ne\n";
        assertEquals(str, testIntegerList.stringTwoLists(stringList));
    }

    @Test
    public void testListInsideListPrint() {
        SimpleList<Integer> integerList1 = new SimpleList<>();
        SimpleList<Integer> integerList2 = new SimpleList<>();

        String str = "0 1 2 3 4  999 80  0 5 10 15 20  ";

        for (int i = 0; i < MULTIPLE; i++) {
            testIntegerList.add(i);
            integerList2.add(i * MULTIPLE);
        }
        integerList1.add(999);
        integerList1.add(80);

        SimpleList<SimpleList<Integer>> list = new SimpleList<>();
        list.add(testIntegerList);
        list.add(integerList1);
        list.add(integerList2);

        assertEquals(str, list.toString());
    }
}
