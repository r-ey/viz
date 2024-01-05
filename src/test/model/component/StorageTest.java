package model.component;

import model.util.SimpleList;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

// Unit tests for Storage Class
public class StorageTest {

    private Storage testStorage;
    private SimpleList<Term> termList;

    @BeforeEach
    public void setUp() {
        termList = new SimpleList<>();
        termList.add(setUpTerm());
        testStorage = new Storage(new Profile(termList, "Rey"));
    }

    @Test
    public void testStorage() {
        Profile testProfile = new Profile(termList, "Rey");
        assertEquals(testProfile.toString(), testStorage.getProfile().toString());
    }

    private Term setUpTerm() {
        SimpleList<Course> courseList = new SimpleList<>();
        courseList.add(setUpCourse());
        return new Term(courseList, "Fall 23", 7);
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
}
