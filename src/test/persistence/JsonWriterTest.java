package persistence;

import model.component.Storage;
import model.component.Profile;
import model.component.Term;
import model.component.Course;
import model.component.GradeComponents;
import model.component.Grade;

import model.util.SimpleList;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static java.util.Arrays.sort;
import static org.junit.jupiter.api.Assertions.*;

// Code influenced by the JsonSerializationDemo
// https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo/tree/master/src
// Unit tests for JsonReader
public class JsonWriterTest {
    private Profile testProfile;

    @Test
    void testWriterInvalidFile() {
        try {
            JsonWriter writer = new JsonWriter("./data/my\0illegal:fileName.json");
            writer.open();
            fail("IOException was expected");
        } catch (IOException e) {
            // pass
        }
    }

    @Test
    void testWriterEmptyWorkroom() {
        try {
            setUp();
            JsonWriter writer = new JsonWriter("./data/testWriterEmpty.json");
            writer.open();
            writer.write(new Storage(testProfile));
            writer.close();

            JsonReader reader = new JsonReader("./data/testWriterEmpty.json");
            Storage testStorage = reader.read();

            // lost the order
            char[] expected = testProfile.toString().toCharArray();
            sort(expected);
            char[] actual = testStorage.getProfile().toString().toCharArray();
            sort(actual);

            assertArrayEquals(expected, actual);
        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }

    @Test
    void testWriterGeneralWorkroom() {
        try {
            setUp();
            JsonWriter writer = new JsonWriter("./data/testWriterGeneral.json");
            writer.open();
            writer.write(new Storage(testProfile));
            writer.close();

            JsonReader reader = new JsonReader("./data/testWriterGeneral.json");
            Storage testStorage = reader.read();

            // lost the order
            char[] expected = testProfile.toString().toCharArray();
            sort(expected);
            char[] actual = testStorage.getProfile().toString().toCharArray();
            sort(actual);

            assertArrayEquals(expected, actual);
        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }

    private void setUp() {
        SimpleList<Term> termList;
        termList = new SimpleList<>();
        termList.add(setUpTerm("Fall 23"));
        termList.add(setUpTerm("Spring 24"));
        testProfile = new Profile(termList, "Rey");
    }

    private Term setUpTerm(String s) {
        SimpleList<Course> courseList = new SimpleList<>();
        courseList.add(setUpCourse());
        courseList.add(setUpCourse2());
        return new Term(courseList, s, 7);
    }

    private Course setUpCourse() {
        return new Course("CPSC 210", 90, setUpGradeComponents());
    }

    private GradeComponents setUpGradeComponents() {
        return new GradeComponents(setUpNameList(), setUpGradeList());
    }

    private SimpleList<String> setUpNameList() {
        SimpleList<String> name = new SimpleList<>();
        name.add("Midterm");
        name.add("Final");
        return name;
    }

    private SimpleList<SimpleList<Grade>> setUpGradeList() {
        SimpleList<SimpleList<Grade>> gradeList = new SimpleList<>();
        SimpleList<Grade> midtermGrade = new SimpleList<>();
        SimpleList<Grade> finalGrade = new SimpleList<>();
        midtermGrade.add(new Grade(50, 45, false));
        finalGrade.add(new Grade(50, 0, true));
        gradeList.add(midtermGrade);
        gradeList.add(finalGrade);
        return gradeList;
    }


    private Course setUpCourse2() {
        return new Course("MATH 220", 80, setUpGradeComponents2());
    }

    private GradeComponents setUpGradeComponents2() {
        return new GradeComponents(setUpNameList2(), setUpGradeList2());
    }

    private SimpleList<String> setUpNameList2() {
        SimpleList<String> name = new SimpleList<>();
        name.add("HomeWork");
        name.add("Assignment");
        name.add("Midterm");
        name.add("Final");
        return name;
    }

    private SimpleList<SimpleList<Grade>> setUpGradeList2() {
        SimpleList<SimpleList<Grade>> gradeList = new SimpleList<>();

        SimpleList<Grade> homeworkGrade = new SimpleList<>();
        SimpleList<Grade> assignmentGrade = new SimpleList<>();
        SimpleList<Grade> midtermGrade = new SimpleList<>();
        SimpleList<Grade> finalGrade = new SimpleList<>();

        homeworkGrade.add(new Grade(4, 3, false));
        homeworkGrade.add(new Grade(3, 1, false));
        homeworkGrade.add(new Grade(3, 0, true));

        assignmentGrade.add(new Grade(2.5f, 2f, false));
        assignmentGrade.add(new Grade(2.5f, 1.6f, false));
        assignmentGrade.add(new Grade(2.5f, 1.8f, false));
        assignmentGrade.add(new Grade(2.5f, 0, true));

        midtermGrade.add(new Grade(15, 12.5f, false));
        midtermGrade.add(new Grade(15, 13f, false));

        finalGrade.add(new Grade(50, 0, true));

        gradeList.add(homeworkGrade);
        gradeList.add(assignmentGrade);
        gradeList.add(midtermGrade);
        gradeList.add(finalGrade);
        return gradeList;
    }
}
