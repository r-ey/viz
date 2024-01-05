package model.component;

import model.util.SimpleList;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.concurrent.ExecutionException;

import static org.junit.jupiter.api.Assertions.*;

// Unit tests for Course
// This test also includes Analytics, GreedyAnalytics, RegularAnalytics, OrderedListRunnable, and TryOneByOneRunnable
// All of them are essentially part of course class (the analytics part)
// Class that ends with Runnable are for multithreading
public class CourseTest {

    // constant for course 1
    private static final String COURSE_ONE_NAME = "CPSC 210";
    private static final float COURSE_ONE_TARGET = 80f;
    private static final int[] COURSE_ONE_GRADE_SIZE = {5, 5, 1, 2, 1};
    private static final String[] COURSE_ONE_GRADE_NAME = {"LECTURE TICKETS", "LABS", "PROJECT", "MIDTERM", "FINAL"};
    private static final float[] COURSE_ONE_GRADE_WORTH = {3f, 7f, 15f, 35f, 40f};
    private SimpleList<Grade> lectureGradeOne;
    private SimpleList<Grade> labGradeOne;
    private SimpleList<Grade> projectGradeOne;
    private SimpleList<Grade> midtermGradeOne;
    private SimpleList<Grade> finalGradeOne;


    // constant for course 2
    private static final String COURSE_TWO_NAME = "MATH 220";
    private static final float COURSE_TWO_TARGET = 91f;
    private static final int[] COURSE_TWO_GRADE_SIZE = {10, 10, 1, 1};
    private static final String[] COURSE_TWO_GRADE_NAME = {"QUIZZES", "HOMEWORK", "MIDTERM", "FINAL"};
    private static final float[] COURSE_TWO_GRADE_WORTH = {5f, 20f, 25f, 50f};

    private Course testCourseOne;
    private Course testCourseTwo;

    @BeforeEach
    public void setUpOne() {
        testCourseOne = new Course(COURSE_ONE_NAME, COURSE_ONE_TARGET, setUpGradeComponentOne());
    }

    @Test
    public void testCourse() {
        assertEquals(COURSE_ONE_NAME, testCourseOne.getName());
        assertEquals(COURSE_ONE_TARGET, testCourseOne.getTarget());
        assertEquals(COURSE_ONE_GRADE_NAME[0], testCourseOne.getComponents().getNameList().get(0));
        assertEquals(COURSE_ONE_GRADE_NAME[1], testCourseOne.getComponents().getNameList().get(1));
        assertEquals(COURSE_ONE_GRADE_NAME[2], testCourseOne.getComponents().getNameList().get(2));
        assertEquals(COURSE_ONE_GRADE_NAME[3], testCourseOne.getComponents().getNameList().get(3));
        assertEquals(COURSE_ONE_GRADE_NAME[4], testCourseOne.getComponents().getNameList().get(4));
        assertEquals(lectureGradeOne, testCourseOne.getComponents().getGradeList().get(0));
        assertEquals(labGradeOne, testCourseOne.getComponents().getGradeList().get(1));
        assertEquals(projectGradeOne, testCourseOne.getComponents().getGradeList().get(2));
        assertEquals(midtermGradeOne, testCourseOne.getComponents().getGradeList().get(3));
        assertEquals(finalGradeOne, testCourseOne.getComponents().getGradeList().get(4));
    }

    @Test
    public void testAddGradeComponentOnce() {
        String name = "ASSIGNMENT";
        SimpleList<Grade> assignmentGradeList = new SimpleList<>();
        float percentage = 5f;
        assignmentGradeList.add(new Grade(percentage, 4f, false));
        assignmentGradeList.add(new Grade(percentage, 2.5f, false));
        assignmentGradeList.add(new Grade(percentage, 0f, true));
        testCourseOne.addGradeComponents(name, assignmentGradeList);
        assertEquals(name, testCourseOne.getComponents().getNameList().get(5));
        assertEquals(assignmentGradeList, testCourseOne.getComponents().getGradeList().get(5));
    }

    @Test
    public void testAddGradeComponentMultiple() {
        String nameOne = "ASSIGNMENT";
        SimpleList<Grade> assignmentGradeList = new SimpleList<>();
        float percentageOne = 5f;
        assignmentGradeList.add(new Grade(percentageOne, 4f, false));
        assignmentGradeList.add(new Grade(percentageOne, 2.5f, false));
        assignmentGradeList.add(new Grade(percentageOne, 0f, true));

        String nameTwo = "WEBWORKS";
        SimpleList<Grade> webWorksGradeList = new SimpleList<>();
        float percentageTwo = 3f;
        webWorksGradeList.add(new Grade(percentageTwo, 1f, false));
        webWorksGradeList.add(new Grade(percentageTwo, 3f, false));
        webWorksGradeList.add(new Grade(percentageTwo, 0f, true));
        webWorksGradeList.add(new Grade(percentageTwo, 0f, true));

        testCourseOne.addGradeComponents(nameOne, assignmentGradeList);
        assertEquals(nameOne, testCourseOne.getComponents().getNameList().get(5));
        assertEquals(assignmentGradeList, testCourseOne.getComponents().getGradeList().get(5));

        testCourseOne.addGradeComponents(nameTwo, webWorksGradeList);
        assertEquals(nameTwo, testCourseOne.getComponents().getNameList().get(6));
        assertEquals(webWorksGradeList, testCourseOne.getComponents().getGradeList().get(6));
    }

    @Test
    public void testPrintOnce() {
        String output = "Course : " + COURSE_ONE_NAME + "\nTarget : " + String.format("%.2f", COURSE_ONE_TARGET)
                + "\n\n" + COURSE_ONE_GRADE_NAME[0] + "\n" + "Max Percentage : 0.60, Real : 0.54\n"
                + " Max Percentage : 0.60, Real : 0.54\n Max Percentage : 0.60, Real : 0.54\n Max Percentage : 0.60, "
                + "Real : 0.54\n Max Percentage : 0.60, Real : 0.54\n \n" + COURSE_ONE_GRADE_NAME[1] + "\n"
                + "Max Percentage : 1.40, Real : 1.19\n Max Percentage : 1.40, Real : 1.19\n "
                + "Max Percentage : 1.40, Real : 1.19\n Max Percentage : 1.40, Real : 1.19\n "
                + "Max Percentage : 1.40, Real : 1.19\n \n" + COURSE_ONE_GRADE_NAME[2]
                + "\nMax Percentage : 15.00, Grade is Empty\n \n"
                + COURSE_ONE_GRADE_NAME[3] + "\nMax Percentage : 17.50, Real : 15.75\n "
                + "Max Percentage : 17.50, Grade is Empty\n \n"
                + COURSE_ONE_GRADE_NAME[4] + "\nMax Percentage : 40.00, Grade is Empty\n \n";
        assertEquals(output, testCourseOne.toString());
    }

    @Test
    public void testPrintMultiple() {
        setUpTwo();
        SimpleList<Course> courseList = new SimpleList<>();
        courseList.add(testCourseOne);
        courseList.add(testCourseTwo);
        String outputOne = "Course : " + COURSE_ONE_NAME + "\nTarget : " + String.format("%.2f", COURSE_ONE_TARGET)
                + "\n\n" + COURSE_ONE_GRADE_NAME[0] + "\n" + "Max Percentage : 0.60, Real : 0.54\n"
                + " Max Percentage : 0.60, Real : 0.54\n Max Percentage : 0.60, Real : 0.54\n Max Percentage : 0.60, "
                + "Real : 0.54\n Max Percentage : 0.60, Real : 0.54\n \n" + COURSE_ONE_GRADE_NAME[1] + "\n"
                + "Max Percentage : 1.40, Real : 1.19\n Max Percentage : 1.40, Real : 1.19\n "
                + "Max Percentage : 1.40, Real : 1.19\n Max Percentage : 1.40, Real : 1.19\n "
                + "Max Percentage : 1.40, Real : 1.19\n \n" + COURSE_ONE_GRADE_NAME[2]
                + "\nMax Percentage : 15.00, Grade is Empty\n \n"
                + COURSE_ONE_GRADE_NAME[3] + "\nMax Percentage : 17.50, Real : 15.75\n "
                + "Max Percentage : 17.50, Grade is Empty\n \n"
                + COURSE_ONE_GRADE_NAME[4] + "\nMax Percentage : 40.00, Grade is Empty\n \n";
        String outputTwo = "Course : " + COURSE_TWO_NAME + "\nTarget : " + String.format("%.2f", COURSE_TWO_TARGET)
                + "\n\n" + COURSE_TWO_GRADE_NAME[0] + "\n" + "Max Percentage : 0.50, Real : 0.40"
                + "\n Max Percentage : 0.50, Real : 0.40\n Max Percentage : 0.50, Real : 0.40"
                + "\n Max Percentage : 0.50, Real : 0.40\n Max Percentage : 0.50, Real : 0.40"
                + "\n Max Percentage : 0.50, Real : 0.40\n Max Percentage : 0.50, Real : 0.40"
                + "\n Max Percentage : 0.50, Real : 0.40\n Max Percentage : 0.50, Real : 0.40"
                + "\n Max Percentage : 0.50, Real : 0.40\n \n" + COURSE_TWO_GRADE_NAME[1] + "\n"
                + "Max Percentage : 2.00, Real : 1.20\n Max Percentage : 2.00, Real : 1.20\n"
                + " Max Percentage : 2.00, Real : 1.20\n Max Percentage : 2.00, Real : 1.20\n"
                + " Max Percentage : 2.00, Real : 1.20\n Max Percentage : 2.00, Real : 1.20\n"
                + " Max Percentage : 2.00, Real : 1.20\n Max Percentage : 2.00, Real : 1.20\n"
                + " Max Percentage : 2.00, Real : 1.20\n Max Percentage : 2.00, Real : 1.20\n \n"
                + COURSE_TWO_GRADE_NAME[2] + "\nMax Percentage : 25.00, Grade is Empty\n \n" + COURSE_TWO_GRADE_NAME[3]
                + "\nMax Percentage : 50.00, Grade is Empty\n \n";
        assertEquals(outputOne, courseList.get(0).toString());
        assertEquals(outputTwo, courseList.get(1).toString());
    }

    @Test
    public void testCurrentConditionRegularAnalytics() throws ExecutionException, InterruptedException {
        SimpleList<Grade> lectureTicketGrade = new SimpleList<>();
        lectureTicketGrade.add(new Grade(0.6f, 0.54f, false));
        lectureTicketGrade.add(new Grade(0.6f, 0.54f, false));
        lectureTicketGrade.add(new Grade(0.6f, 0.54f, false));
        lectureTicketGrade.add(new Grade(0.6f, 0.54f, false));
        lectureTicketGrade.add(new Grade(0.6f, 0f, true));
        testCourseOne.getComponents().editComponent(0, "LECTURE TICKETS", lectureTicketGrade);
        String regularAnalyticsString = "Not Empty : \nLECTURE TICKETS1\nMax Percentage : 0.60, Real : 0.54\n\n"
                + "LECTURE TICKETS2\nMax Percentage : 0.60, Real : 0.54\n\n"
                + "LECTURE TICKETS3\nMax Percentage : 0.60, Real : 0.54\n\n"
                + "LECTURE TICKETS4\nMax Percentage : 0.60, Real : 0.54\n\n"
                + "LABS1\nMax Percentage : 1.40, Real : 1.19\n\n"
                + "LABS2\nMax Percentage : 1.40, Real : 1.19\n\n"
                + "LABS3\nMax Percentage : 1.40, Real : 1.19\n\n"
                + "LABS4\nMax Percentage : 1.40, Real : 1.19\n\n"
                + "LABS5\nMax Percentage : 1.40, Real : 1.19\n\n"
                + "MIDTERM1\nMax Percentage : 17.50, Real : 15.75\n\n\n"
                + "Empty : \nLECTURE TICKETS5\nMax Percentage : 0.60, Grade is Empty\n\nPROJECT1\n"
                + "Max Percentage : 15.00, Grade is Empty\n\n"
                + "MIDTERM2\nMax Percentage : 17.50, Grade is Empty\n\nFINAL1\n"
                + "Max Percentage : 40.00, Grade is Empty\n\n"
                + "Total : 23.86/80.00\nNeed : 56.14\n\nRegular Analytics : \nLECTURE TICKETS5\n"
                + "Max Percentage : 0.60, Real : 0.60\n\nPROJECT1\n"
                + "Max Percentage : 15.00, Real : 14.25\n\nMIDTERM2\nMax Percentage : 17.50, Real : 16.63\n\n"
                + "FINAL1\nMax Percentage : 40.00, Real : 24.80\n\n";
        assertEquals(regularAnalyticsString, testCourseOne.currentCondition());
    }

    @Test
    public void testCurrentConditionRegularAndGreedyMaxAnalytics() throws ExecutionException, InterruptedException {
        setUpTwo();
        SimpleList<Course> courseList = new SimpleList<>();
        courseList.add(testCourseOne);
        courseList.add(testCourseTwo);
        testCourseOne.setTarget(89.2f);
        String regularAnalyticsString = "Not Empty : \nLECTURE TICKETS1\nMax Percentage : 0.60, Real : 0.54\n\n"
                + "LECTURE TICKETS2\nMax Percentage : 0.60, Real : 0.54\n\n"
                + "LECTURE TICKETS3\nMax Percentage : 0.60, Real : 0.54\n\n"
                + "LECTURE TICKETS4\nMax Percentage : 0.60, Real : 0.54\n\n"
                + "LECTURE TICKETS5\nMax Percentage : 0.60, Real : 0.54\n\n"
                + "LABS1\nMax Percentage : 1.40, Real : 1.19\n\n"
                + "LABS2\nMax Percentage : 1.40, Real : 1.19\n\n"
                + "LABS3\nMax Percentage : 1.40, Real : 1.19\n\n"
                + "LABS4\nMax Percentage : 1.40, Real : 1.19\n\n"
                + "LABS5\nMax Percentage : 1.40, Real : 1.19\n\n"
                + "MIDTERM1\nMax Percentage : 17.50, Real : 15.75\n\n\n"
                + "Empty : \nPROJECT1\nMax Percentage : 15.00, Grade is Empty\n\n"
                + "MIDTERM2\nMax Percentage : 17.50, Grade is Empty\n\nFINAL1\n"
                + "Max Percentage : 40.00, Grade is Empty\n\n"
                + "Total : 24.40/89.20\nNeed : 64.80\n\nRegular Analytics : \nPROJECT1\n"
                + "Max Percentage : 15.00, Real : 14.25\n\nMIDTERM2\nMax Percentage : 17.50, Real : 16.63\n\n"
                + "FINAL1\nMax Percentage : 40.00, Real : 34.00\n\n";
        String greedyAnalyticsString = "Not Empty : \nQUIZZES1\nMax Percentage : 0.50, Real : 0.40\n\n"
                + "QUIZZES2\nMax Percentage : 0.50, Real : 0.40\n\n"
                + "QUIZZES3\nMax Percentage : 0.50, Real : 0.40\n\n"
                + "QUIZZES4\nMax Percentage : 0.50, Real : 0.40\n\n"
                + "QUIZZES5\nMax Percentage : 0.50, Real : 0.40\n\n"
                + "QUIZZES6\nMax Percentage : 0.50, Real : 0.40\n\n"
                + "QUIZZES7\nMax Percentage : 0.50, Real : 0.40\n\n"
                + "QUIZZES8\nMax Percentage : 0.50, Real : 0.40\n\n"
                + "QUIZZES9\nMax Percentage : 0.50, Real : 0.40\n\n"
                + "QUIZZES10\nMax Percentage : 0.50, Real : 0.40\n\n"
                + "HOMEWORK1\nMax Percentage : 2.00, Real : 1.20\n\n"
                + "HOMEWORK2\nMax Percentage : 2.00, Real : 1.20\n\n"
                + "HOMEWORK3\nMax Percentage : 2.00, Real : 1.20\n\n"
                + "HOMEWORK4\nMax Percentage : 2.00, Real : 1.20\n\n"
                + "HOMEWORK5\nMax Percentage : 2.00, Real : 1.20\n\n"
                + "HOMEWORK6\nMax Percentage : 2.00, Real : 1.20\n\n"
                + "HOMEWORK7\nMax Percentage : 2.00, Real : 1.20\n\n"
                + "HOMEWORK8\nMax Percentage : 2.00, Real : 1.20\n\n"
                + "HOMEWORK9\nMax Percentage : 2.00, Real : 1.20\n\n"
                + "HOMEWORK10\nMax Percentage : 2.00, Real : 1.20\n\n\n"
                + "Empty : \nMIDTERM1\nMax Percentage : 25.00, Grade is Empty\n\n"
                + "FINAL1\nMax Percentage : 50.00, Grade is Empty\n\n"
                + "Total : 16.00/91.00\nNeed : 75.00\n\nGreedy Analytics : \nMIDTERM1\n"
                + "Max Percentage : 25.00, Real : 25.00\n\nFINAL1\nMax Percentage : 50.00, Real : 50.00\n\n";
        assertEquals(regularAnalyticsString, courseList.get(0).currentCondition());
        assertEquals(greedyAnalyticsString, courseList.get(1).currentCondition());
    }

    @Test
    public void testCurrentConditionTwoGreedyAnalytics() throws ExecutionException, InterruptedException {
        setUpTwo();
        SimpleList<Course> courseList = new SimpleList<>();
        courseList.add(testCourseOne);
        courseList.add(testCourseTwo);
        testCourseOne.setTarget(91.5f);
        testCourseTwo.setTarget(85f);
        String greedyAnalyticsStringOne = "Not Empty : \nLECTURE TICKETS1\nMax Percentage : 0.60, Real : 0.54\n\n"
                + "LECTURE TICKETS2\nMax Percentage : 0.60, Real : 0.54\n\n"
                + "LECTURE TICKETS3\nMax Percentage : 0.60, Real : 0.54\n\n"
                + "LECTURE TICKETS4\nMax Percentage : 0.60, Real : 0.54\n\n"
                + "LECTURE TICKETS5\nMax Percentage : 0.60, Real : 0.54\n\n"
                + "LABS1\nMax Percentage : 1.40, Real : 1.19\n\n"
                + "LABS2\nMax Percentage : 1.40, Real : 1.19\n\n"
                + "LABS3\nMax Percentage : 1.40, Real : 1.19\n\n"
                + "LABS4\nMax Percentage : 1.40, Real : 1.19\n\n"
                + "LABS5\nMax Percentage : 1.40, Real : 1.19\n\n"
                + "MIDTERM1\nMax Percentage : 17.50, Real : 15.75\n\n\n"
                + "Empty : \nPROJECT1\nMax Percentage : 15.00, Grade is Empty\n\n"
                + "MIDTERM2\nMax Percentage : 17.50, Grade is Empty\n\n"
                + "FINAL1\nMax Percentage : 40.00, Grade is Empty\n\n"
                + "Total : 24.40/91.50\nNeed : 67.10\n\nGreedy Analytics : \nPROJECT1\n"
                + "Max Percentage : 15.00, Real : 15.00\n\nMIDTERM2\nMax Percentage : 17.50, Real : 17.50\n\n"
                + "FINAL1\nMax Percentage : 40.00, Real : 34.80\n\n";
        String greedyAnalyticsStringTwo = "Not Empty : \nQUIZZES1\nMax Percentage : 0.50, Real : 0.40\n\n"
                + "QUIZZES2\nMax Percentage : 0.50, Real : 0.40\n\n"
                + "QUIZZES3\nMax Percentage : 0.50, Real : 0.40\n\n"
                + "QUIZZES4\nMax Percentage : 0.50, Real : 0.40\n\n"
                + "QUIZZES5\nMax Percentage : 0.50, Real : 0.40\n\n"
                + "QUIZZES6\nMax Percentage : 0.50, Real : 0.40\n\n"
                + "QUIZZES7\nMax Percentage : 0.50, Real : 0.40\n\n"
                + "QUIZZES8\nMax Percentage : 0.50, Real : 0.40\n\n"
                + "QUIZZES9\nMax Percentage : 0.50, Real : 0.40\n\n"
                + "QUIZZES10\nMax Percentage : 0.50, Real : 0.40\n\n"
                + "HOMEWORK1\nMax Percentage : 2.00, Real : 1.20\n\n"
                + "HOMEWORK2\nMax Percentage : 2.00, Real : 1.20\n\n"
                + "HOMEWORK3\nMax Percentage : 2.00, Real : 1.20\n\n"
                + "HOMEWORK4\nMax Percentage : 2.00, Real : 1.20\n\n"
                + "HOMEWORK5\nMax Percentage : 2.00, Real : 1.20\n\n"
                + "HOMEWORK6\nMax Percentage : 2.00, Real : 1.20\n\n"
                + "HOMEWORK7\nMax Percentage : 2.00, Real : 1.20\n\n"
                + "HOMEWORK8\nMax Percentage : 2.00, Real : 1.20\n\n"
                + "HOMEWORK9\nMax Percentage : 2.00, Real : 1.20\n\n"
                + "HOMEWORK10\nMax Percentage : 2.00, Real : 1.20\n\n\n"
                + "Empty : \nMIDTERM1\nMax Percentage : 25.00, Grade is Empty\n\n"
                + "FINAL1\nMax Percentage : 50.00, Grade is Empty\n\n"
                + "Total : 16.00/85.00\nNeed : 69.00\n\nGreedy Analytics : \nMIDTERM1\n"
                + "Max Percentage : 25.00, Real : 25.00\n\nFINAL1\nMax Percentage : 50.00, Real : 44.00\n\n";
        assertEquals(greedyAnalyticsStringOne, courseList.get(0).currentCondition());
        assertEquals(greedyAnalyticsStringTwo, courseList.get(1).currentCondition());
    }

    @Test
    public void testCurrentConditionRegularAnalyticsAndNotPossible() throws ExecutionException, InterruptedException {
        setUpTwo();
        SimpleList<Course> courseList = new SimpleList<>();
        courseList.add(testCourseOne);
        courseList.add(testCourseTwo);
        testCourseOne.setTarget(99f);
        testCourseTwo.setTarget(80f);
        String notPossible = "Not Empty : \nLECTURE TICKETS1\nMax Percentage : 0.60, Real : 0.54\n\n"
                + "LECTURE TICKETS2\nMax Percentage : 0.60, Real : 0.54\n\n"
                + "LECTURE TICKETS3\nMax Percentage : 0.60, Real : 0.54\n\n"
                + "LECTURE TICKETS4\nMax Percentage : 0.60, Real : 0.54\n\n"
                + "LECTURE TICKETS5\nMax Percentage : 0.60, Real : 0.54\n\n"
                + "LABS1\nMax Percentage : 1.40, Real : 1.19\n\n"
                + "LABS2\nMax Percentage : 1.40, Real : 1.19\n\n"
                + "LABS3\nMax Percentage : 1.40, Real : 1.19\n\n"
                + "LABS4\nMax Percentage : 1.40, Real : 1.19\n\n"
                + "LABS5\nMax Percentage : 1.40, Real : 1.19\n\n"
                + "MIDTERM1\nMax Percentage : 17.50, Real : 15.75\n\n\n"
                + "Empty : \nPROJECT1\nMax Percentage : 15.00, Grade is Empty\n\n"
                + "MIDTERM2\nMax Percentage : 17.50, Grade is Empty\n\n"
                + "FINAL1\nMax Percentage : 40.00, Grade is Empty\n\n"
                + "Total : 24.40/99.00\nNeed : 74.60\n\nThe target of : 99.00 is unachievable\n"
                + "PROJECT1\nMax Percentage : 15.00, Grade is Empty\n\n"
                + "MIDTERM2\nMax Percentage : 17.50, Grade is Empty\n\n"
                + "FINAL1\nMax Percentage : 40.00, Grade is Empty\n\n";
        String regularAnalyticsString = "Not Empty : \nQUIZZES1\nMax Percentage : 0.50, Real : 0.40\n\n"
                + "QUIZZES2\nMax Percentage : 0.50, Real : 0.40\n\n"
                + "QUIZZES3\nMax Percentage : 0.50, Real : 0.40\n\n"
                + "QUIZZES4\nMax Percentage : 0.50, Real : 0.40\n\n"
                + "QUIZZES5\nMax Percentage : 0.50, Real : 0.40\n\n"
                + "QUIZZES6\nMax Percentage : 0.50, Real : 0.40\n\n"
                + "QUIZZES7\nMax Percentage : 0.50, Real : 0.40\n\n"
                + "QUIZZES8\nMax Percentage : 0.50, Real : 0.40\n\n"
                + "QUIZZES9\nMax Percentage : 0.50, Real : 0.40\n\n"
                + "QUIZZES10\nMax Percentage : 0.50, Real : 0.40\n\n"
                + "HOMEWORK1\nMax Percentage : 2.00, Real : 1.20\n\n"
                + "HOMEWORK2\nMax Percentage : 2.00, Real : 1.20\n\n"
                + "HOMEWORK3\nMax Percentage : 2.00, Real : 1.20\n\n"
                + "HOMEWORK4\nMax Percentage : 2.00, Real : 1.20\n\n"
                + "HOMEWORK5\nMax Percentage : 2.00, Real : 1.20\n\n"
                + "HOMEWORK6\nMax Percentage : 2.00, Real : 1.20\n\n"
                + "HOMEWORK7\nMax Percentage : 2.00, Real : 1.20\n\n"
                + "HOMEWORK8\nMax Percentage : 2.00, Real : 1.20\n\n"
                + "HOMEWORK9\nMax Percentage : 2.00, Real : 1.20\n\n"
                + "HOMEWORK10\nMax Percentage : 2.00, Real : 1.20\n\n\n"
                + "Empty : \nMIDTERM1\nMax Percentage : 25.00, Grade is Empty\n\n"
                + "FINAL1\nMax Percentage : 50.00, Grade is Empty\n\n"
                + "Total : 16.00/80.00\nNeed : 64.00\n\nRegular Analytics : \nMIDTERM1\n"
                + "Max Percentage : 25.00, Real : 23.75\n\nFINAL1\nMax Percentage : 50.00, Real : 40.50\n\n";
        assertEquals(notPossible, courseList.get(0).currentCondition());
        assertEquals(regularAnalyticsString, courseList.get(1).currentCondition());
    }

    @Test
    public void testCurrentConditionGreedyAnalyticsAndRegularAnalytics()
            throws ExecutionException, InterruptedException {
        setUpTwo();
        SimpleList<Course> courseList = new SimpleList<>();
        courseList.add(testCourseOne);
        courseList.add(testCourseTwo);
        testCourseOne.setTarget(90f);
        testCourseTwo.setTarget(78f);

        SimpleList<Grade> courseOneMidterm = new SimpleList<>();
        courseOneMidterm.add(new Grade(35f, 0f, true));

        SimpleList<Grade> courseTwoMidterm = new SimpleList<>();
        courseTwoMidterm.add(new Grade(30f, 0f, true));
        SimpleList<Grade> courseTwoFinal = new SimpleList<>();
        courseTwoFinal.add(new Grade(30f, 0f, true));
        SimpleList<Grade> courseTwoWebWorks = new SimpleList<>();
        courseTwoWebWorks.add(new Grade(15f, 0f, true));

        testCourseOne.getComponents().editComponent(3, "MIDTERM", courseOneMidterm);

        testCourseTwo.getComponents().editComponent(2, "MIDTERM", courseTwoMidterm);
        testCourseTwo.getComponents().editComponent(3, "FINAL", courseTwoFinal);
        testCourseTwo.addGradeComponents("WEBWORKS", courseTwoWebWorks);

        String greedyAnalyticsString = "Not Empty : \nLECTURE TICKETS1\nMax Percentage : 0.60, Real : 0.54\n\n"
                + "LECTURE TICKETS2\nMax Percentage : 0.60, Real : 0.54\n\n"
                + "LECTURE TICKETS3\nMax Percentage : 0.60, Real : 0.54\n\n"
                + "LECTURE TICKETS4\nMax Percentage : 0.60, Real : 0.54\n\n"
                + "LECTURE TICKETS5\nMax Percentage : 0.60, Real : 0.54\n\n"
                + "LABS1\nMax Percentage : 1.40, Real : 1.19\n\n"
                + "LABS2\nMax Percentage : 1.40, Real : 1.19\n\n"
                + "LABS3\nMax Percentage : 1.40, Real : 1.19\n\n"
                + "LABS4\nMax Percentage : 1.40, Real : 1.19\n\n"
                + "LABS5\nMax Percentage : 1.40, Real : 1.19\n\n\n"
                + "Empty : \nPROJECT1\nMax Percentage : 15.00, Grade is Empty\n\n"
                + "MIDTERM1\nMax Percentage : 35.00, Grade is Empty\n\n"
                + "FINAL1\nMax Percentage : 40.00, Grade is Empty\n\n"
                + "Total : 8.65/90.00\nNeed : 81.35\n\nGreedy Analytics : \nPROJECT1\n"
                + "Max Percentage : 15.00, Real : 15.00\n\nMIDTERM1\nMax Percentage : 35.00, Real : 35.00\n\n"
                + "FINAL1\nMax Percentage : 40.00, Real : 31.60\n\n";
        String regularAnalyticsString = "Not Empty : \nQUIZZES1\nMax Percentage : 0.50, Real : 0.40\n\n"
                + "QUIZZES2\nMax Percentage : 0.50, Real : 0.40\n\n"
                + "QUIZZES3\nMax Percentage : 0.50, Real : 0.40\n\n"
                + "QUIZZES4\nMax Percentage : 0.50, Real : 0.40\n\n"
                + "QUIZZES5\nMax Percentage : 0.50, Real : 0.40\n\n"
                + "QUIZZES6\nMax Percentage : 0.50, Real : 0.40\n\n"
                + "QUIZZES7\nMax Percentage : 0.50, Real : 0.40\n\n"
                + "QUIZZES8\nMax Percentage : 0.50, Real : 0.40\n\n"
                + "QUIZZES9\nMax Percentage : 0.50, Real : 0.40\n\n"
                + "QUIZZES10\nMax Percentage : 0.50, Real : 0.40\n\n"
                + "HOMEWORK1\nMax Percentage : 2.00, Real : 1.20\n\n"
                + "HOMEWORK2\nMax Percentage : 2.00, Real : 1.20\n\n"
                + "HOMEWORK3\nMax Percentage : 2.00, Real : 1.20\n\n"
                + "HOMEWORK4\nMax Percentage : 2.00, Real : 1.20\n\n"
                + "HOMEWORK5\nMax Percentage : 2.00, Real : 1.20\n\n"
                + "HOMEWORK6\nMax Percentage : 2.00, Real : 1.20\n\n"
                + "HOMEWORK7\nMax Percentage : 2.00, Real : 1.20\n\n"
                + "HOMEWORK8\nMax Percentage : 2.00, Real : 1.20\n\n"
                + "HOMEWORK9\nMax Percentage : 2.00, Real : 1.20\n\n"
                + "HOMEWORK10\nMax Percentage : 2.00, Real : 1.20\n\n\n"
                + "Empty : \nMIDTERM1\nMax Percentage : 30.00, Grade is Empty\n\n"
                + "FINAL1\nMax Percentage : 30.00, Grade is Empty\n\n"
                + "WEBWORKS1\nMax Percentage : 15.00, Grade is Empty\n\n"
                + "Total : 16.00/78.00\nNeed : 62.00\n\nRegular Analytics : \nMIDTERM1\n"
                + "Max Percentage : 30.00, Real : 24.00\n\nFINAL1\nMax Percentage : 30.00, Real : 24.00\n\n"
                + "WEBWORKS1\nMax Percentage : 15.00, Real : 14.25\n\n";
        assertEquals(greedyAnalyticsString, courseList.get(0).currentCondition());
        assertEquals(regularAnalyticsString, courseList.get(1).currentCondition());
    }

    @Test
    public void testGetAnalyticsNameRegular() {
        String analyticsName = "Regular Analytics : ";
        try {
            assertEquals(analyticsName, testCourseOne.getAnalyticsName());
        } catch (ExecutionException | InterruptedException e) {
            fail("Shouldn't have thrown exception");
        }
    }

    @Test
    public void testGetAnalyticsResultRegular() {
        String analyticsResult = "PROJECT1\n" + "Max Percentage : 15.00, Real : 14.25\n\n"
                + "MIDTERM2\nMax Percentage : 17.50, Real : 16.63\n\n"
                + "FINAL1\nMax Percentage : 40.00, Real : 24.80\n\n";
        try {
            assertEquals(analyticsResult, testCourseOne.getAnalyticsResult());
        } catch (ExecutionException | InterruptedException e) {
            fail("Shouldn't have thrown exception");
        }
    }

    @Test
    public void testGetAnalyticsNameGreedy() {
        testCourseOne.setTarget(91.5f);
        String analyticsName = "Greedy Analytics : ";
        try {
            assertEquals(analyticsName, testCourseOne.getAnalyticsName());
        } catch (ExecutionException | InterruptedException e) {
            fail("Shouldn't have thrown exception");
        }
    }

    @Test
    public void testGetAnalyticsResultGreedy() {
        testCourseOne.setTarget(91.5f);
        String analyticsResult = "PROJECT1\n" + "Max Percentage : 15.00, Real : 15.00\n\n"
                + "MIDTERM2\nMax Percentage : 17.50, Real : 17.50\n\n"
                + "FINAL1\nMax Percentage : 40.00, Real : 34.80\n\n";
        try {
            assertEquals(analyticsResult, testCourseOne.getAnalyticsResult());
        } catch (ExecutionException | InterruptedException e) {
            fail("Shouldn't have thrown exception");
        }
    }

    @Test
    public void testGetAnalyticsNameNotPossible() {
        testCourseOne.setTarget(99f);
        String analyticsName = "The target of : 99.00 is unachievable";
        try {
            assertEquals(analyticsName, testCourseOne.getAnalyticsName());
        } catch (ExecutionException | InterruptedException e) {
            fail("Shouldn't have thrown exception");
        }
    }

    @Test
    public void testGetAnalyticsResultNotPossible() {
        testCourseOne.setTarget(99f);
        String analyticsResult = "";
        try {
            assertEquals(analyticsResult, testCourseOne.getAnalyticsResult());
        } catch (ExecutionException | InterruptedException e) {
            fail("Shouldn't have thrown exception");
        }
    }

    private void setUpTwo() {
        testCourseTwo = new Course(COURSE_TWO_NAME, COURSE_TWO_TARGET, setUpGradeComponentTwo());
    }

    private GradeComponents setUpGradeComponentOne() {
        return new GradeComponents(setUpGradeName(COURSE_ONE_GRADE_NAME), setUpGradeListOne());
    }

    private GradeComponents setUpGradeComponentTwo() {
        return new GradeComponents(setUpGradeName(COURSE_TWO_GRADE_NAME), setUpGradeListTwo());
    }

    private SimpleList<String> setUpGradeName(String[] s) {
        SimpleList<String> gradeName = new SimpleList<>();
        for (String str : s) {
            gradeName.add(str);
        }
        return gradeName;
    }

    private SimpleList<SimpleList<Grade>> setUpGradeListOne() {
        SimpleList<SimpleList<Grade>> gradeList = new SimpleList<>();
        lectureGradeOne = new SimpleList<>();
        labGradeOne = new SimpleList<>();
        projectGradeOne = new SimpleList<>();
        midtermGradeOne = new SimpleList<>();
        finalGradeOne = new SimpleList<>();

        for (int i = 0; i < COURSE_ONE_GRADE_SIZE[0]; i++) {
            float percentage = COURSE_ONE_GRADE_WORTH[0]/COURSE_ONE_GRADE_SIZE[0];
            lectureGradeOne.add(new Grade(percentage, (float) 0.9 * percentage, false));
        }

        for (int i = 0; i < COURSE_ONE_GRADE_SIZE[1]; i++) {
            float percentage = COURSE_ONE_GRADE_WORTH[1]/COURSE_ONE_GRADE_SIZE[1];
            labGradeOne.add(new Grade(percentage, (float) 0.85 * percentage, false));
        }

        projectGradeOne.add(new Grade(COURSE_ONE_GRADE_WORTH[2]/COURSE_ONE_GRADE_SIZE[2], 0f, true));
        midtermGradeOne.add(new Grade(COURSE_ONE_GRADE_WORTH[3]/COURSE_ONE_GRADE_SIZE[3],
                (float) 0.9 * COURSE_ONE_GRADE_WORTH[3]/COURSE_ONE_GRADE_SIZE[3], false));
        midtermGradeOne.add(new Grade(COURSE_ONE_GRADE_WORTH[3]/COURSE_ONE_GRADE_SIZE[3], 0f, true));
        finalGradeOne.add(new Grade(COURSE_ONE_GRADE_WORTH[4]/COURSE_ONE_GRADE_SIZE[4], 0f, true));
        gradeList.add(lectureGradeOne);
        gradeList.add(labGradeOne);
        gradeList.add(projectGradeOne);
        gradeList.add(midtermGradeOne);
        gradeList.add(finalGradeOne);

        return gradeList;
    }

    private SimpleList<SimpleList<Grade>> setUpGradeListTwo() {
        SimpleList<SimpleList<Grade>> gradeList = new SimpleList<>();
        SimpleList<Grade> quizzesGradeTwo = new SimpleList<>();
        SimpleList<Grade>homeworkGradeTwo = new SimpleList<>();
        SimpleList<Grade>midtermGradeTwo = new SimpleList<>();
        SimpleList<Grade>finalGradeTwo = new SimpleList<>();

        for (int i = 0; i < COURSE_TWO_GRADE_SIZE[0]; i++) {
            float percentage = COURSE_TWO_GRADE_WORTH[0]/COURSE_TWO_GRADE_SIZE[0];
            quizzesGradeTwo.add(new Grade(percentage, (float) 0.8 * percentage, false));
        }

        for (int i = 0; i < COURSE_TWO_GRADE_SIZE[1]; i++) {
            float percentage = COURSE_TWO_GRADE_WORTH[1]/COURSE_TWO_GRADE_SIZE[1];
            homeworkGradeTwo.add(new Grade(percentage, (float) 0.6 * percentage, false));
        }

        midtermGradeTwo.add(new Grade(COURSE_TWO_GRADE_WORTH[2]/COURSE_TWO_GRADE_SIZE[2], 0f, true));
        finalGradeTwo.add(new Grade(COURSE_TWO_GRADE_WORTH[3]/COURSE_TWO_GRADE_SIZE[3], 0f, true));

        gradeList.add(quizzesGradeTwo);
        gradeList.add(homeworkGradeTwo);
        gradeList.add(midtermGradeTwo);
        gradeList.add(finalGradeTwo);

        return gradeList;
    }
}
