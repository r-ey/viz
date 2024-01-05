package model.component;

import model.util.SimpleList;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

// Unit tests for Profile class
public class ProfileTest {

    private Profile testProfile;

    @BeforeEach
    public void setUp() {
        SimpleList<Term> termList = new SimpleList<>();
        termList.add(setUpTerm());
        testProfile = new Profile(termList, "Rey");
    }

    @Test
    public void testProfileName() {
        assertEquals("Rey", testProfile.getName());
    }

    @Test
    public void testPrintOnce() {
        String profile = "Name : Rey\n--------------------------------\n" + "Term : Fall 23\nWeek : 7"
                + "\n--------------------------------\nCourse : CPSC 210\nTarget : 90.00"
                + "\n\nMidterm\nMax Percentage : 50.00, Real : 45.00\n \nFinal\nMax Percentage : 50.00, Grade is Empty"
                + "\n \nNot Empty : \n"
                + "Midterm1\nMax Percentage : 50.00, Real : 45.00\n\n\nEmpty : \nFinal1\nMax Percentage : 50.00, "
                + "Grade is Empty\n\nTotal : " + "45.00/90.00\nNeed : 45.00\n\n"
                + "Greedy Analytics : \nFinal1\nMax Percentage : 50.00, Real : 45.00\n\n";
        assertEquals(profile, testProfile.toString());
    }

    @Test
    public void testPrintMultiple() {
        Profile testProfile2 = new Profile(testProfile.getTermList(), "me");
        SimpleList<Profile> profileList = new SimpleList<>();
        profileList.add(testProfile);
        profileList.add(testProfile2);
        String profile = "Name : Rey\n--------------------------------\n" + "Term : Fall 23\nWeek : 7"
                + "\n--------------------------------\nCourse : CPSC 210\nTarget : 90.00"
                + "\n\nMidterm\nMax Percentage : 50.00, Real : 45.00\n \nFinal\nMax Percentage : 50.00, "
                + "Grade is Empty\n \nNot Empty : \n"
                + "Midterm1\nMax Percentage : 50.00, Real : 45.00\n\n\nEmpty : \nFinal1\nMax Percentage : 50.00, "
                + "Grade is Empty\n\nTotal : "
                + "45.00/90.00\nNeed : 45.00\n\nGreedy Analytics : \nFinal1\nMax Percentage : 50.00, Real : 45.00\n\n"
                + " Name : me\n--------------------------------\n" + "Term : Fall 23\nWeek : 7"
                + "\n--------------------------------\nCourse : CPSC 210\nTarget : 90.00"
                + "\n\nMidterm\nMax Percentage : 50.00, Real : 45.00\n \nFinal\nMax Percentage : 50.00, "
                + "Grade is Empty\n \nNot Empty : \n"
                + "Midterm1\nMax Percentage : 50.00, Real : 45.00\n\n\nEmpty : \nFinal1\nMax Percentage : 50.00, "
                + "Grade is Empty\n\nTotal : "
                + "45.00/90.00\nNeed : 45.00\n\nGreedy Analytics : \nFinal1\nMax Percentage : 50.00, Real : 45.00\n\n ";
        assertEquals(profile, profileList.toString());
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
