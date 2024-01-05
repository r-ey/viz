package model.component;

import model.util.SimpleList;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

// Unit tests for GradeComponents class
public class GradeComponentsTest {

    // Constant for each of grade components
    private static final int HW_PERCENTAGE = 5;
    private static final int A_PERCENTAGE = 3;
    private static final int MID_PERCENTAGE = 18;
    private static final int FIN_PERCENTAGE = 30;

    // other constant
    private static final int MULTIPLE = 3;
    private static final int SIZE = 4;

    // Fields for testing GradeComponents
    private SimpleList<String> nameList;
    private SimpleList<SimpleList<Grade>> gradeList;

    private SimpleList<Grade> homeworkGradeList;
    private SimpleList<Grade> assignmentGradeList;
    private SimpleList<Grade> midtermGradeList;
    private SimpleList<Grade> finalGradeList;

    private GradeComponents testGradeComponents;

    @BeforeEach
    public void setUp() {
        homeworkGradeList = new SimpleList<>();
        assignmentGradeList = new SimpleList<>();
        midtermGradeList = new SimpleList<>();
        finalGradeList = new SimpleList<>();

        setUpHomeworkGradeList();
        setUpAssignmentGradeList();
        setUpMidtermGradeList();
        setUpFinalGradeList();

        nameList = new SimpleList<>();
        gradeList = new SimpleList<>();

        setUpNameList();
        setUpGradeList();

        testGradeComponents = new GradeComponents(nameList, gradeList);
    }

    @Test
    public void testGradeComponents() {
        assertEquals(SIZE, testGradeComponents.getNameList().size());
        assertEquals(testGradeComponents.getNameList().size(), testGradeComponents.getGradeList().size());
        assertEquals(nameList, testGradeComponents.getNameList());
        assertEquals(gradeList, testGradeComponents.getGradeList());
    }

    @Test
    public void testAddOne() {
        String nameProject = "Project";
        Grade proj = new Grade(15f, 12f, false);
        SimpleList<Grade> projectGradeList = new SimpleList<>();
        projectGradeList.add(proj);

        testGradeComponents.addComponent(nameProject, projectGradeList);
        assertEquals(SIZE + 1, testGradeComponents.getGradeList().size());
        assertEquals(testGradeComponents.getGradeList().size(), testGradeComponents.getNameList().size());
        assertEquals(nameProject, testGradeComponents.getNameList().get(SIZE));
        assertEquals(projectGradeList, testGradeComponents.getGradeList().get(SIZE));
    }

    @Test
    public void testAddMultiple() {
        String nameProject = "Project";
        String nameLab = "Lab";
        String nameWebWorks = "WebWorks";

        SimpleList<Grade> projectGradeList = new SimpleList<>();
        SimpleList<Grade> labGradeList = new SimpleList<>();
        SimpleList<Grade> webWorksGradeList = new SimpleList<>();

        setUpProjectLabWebWorksGrade(projectGradeList, labGradeList, webWorksGradeList);

        testGradeComponents.addComponent(nameProject, projectGradeList);
        testGradeComponents.addComponent(nameLab, labGradeList);
        testGradeComponents.addComponent(nameWebWorks, webWorksGradeList);

        assertEquals(SIZE + MULTIPLE, testGradeComponents.getGradeList().size());
        assertEquals(testGradeComponents.getGradeList().size(), testGradeComponents.getNameList().size());
        assertEquals(nameProject, testGradeComponents.getNameList().get(SIZE));
        assertEquals(projectGradeList, testGradeComponents.getGradeList().get(SIZE));
        assertEquals(nameLab, testGradeComponents.getNameList().get(SIZE + 1));
        assertEquals(labGradeList, testGradeComponents.getGradeList().get(SIZE + 1));
        assertEquals(nameWebWorks, testGradeComponents.getNameList().get(SIZE + 2));
        assertEquals(webWorksGradeList, testGradeComponents.getGradeList().get(SIZE + 2));
    }

    @Test
    public void testEditComponentOne() {
        String nameProject = "Project";
        Grade proj = new Grade(15f, 12f, false);
        SimpleList<Grade> projectGradeList = new SimpleList<>();
        projectGradeList.add(proj);

        testGradeComponents.editComponent(0, nameProject, projectGradeList);
        assertEquals(nameProject, testGradeComponents.getNameList().get(0));
        assertEquals(projectGradeList, testGradeComponents.getGradeList().get(0));
    }

    @Test
    public void testEditComponentMultiple() {
        String nameProject = "Project";
        String nameLab = "Lab";
        String nameWebWorks = "WebWorks";

        SimpleList<Grade> projectGradeList = new SimpleList<>();
        SimpleList<Grade> labGradeList = new SimpleList<>();
        SimpleList<Grade> webWorksGradeList = new SimpleList<>();

        setUpProjectLabWebWorksGrade(projectGradeList, labGradeList, webWorksGradeList);

        testGradeComponents.editComponent(0, nameProject, projectGradeList);
        testGradeComponents.editComponent(1, nameLab, labGradeList);
        testGradeComponents.editComponent(2, nameWebWorks, webWorksGradeList);

        assertEquals(nameProject, testGradeComponents.getNameList().get(0));
        assertEquals(nameLab, testGradeComponents.getNameList().get(1));
        assertEquals(nameWebWorks, testGradeComponents.getNameList().get(2));

        assertEquals(projectGradeList, testGradeComponents.getGradeList().get(0));
        assertEquals(labGradeList, testGradeComponents.getGradeList().get(1));
        assertEquals(webWorksGradeList, testGradeComponents.getGradeList().get(2));
    }

    @Test
    public void testDeleteComponentOne() {
        testGradeComponents.deleteComponent(0);

        assertEquals(SIZE - 1, testGradeComponents.getNameList().size());
        assertEquals(testGradeComponents.getNameList().size(), testGradeComponents.getGradeList().size());

        assertEquals("Assignment", testGradeComponents.getNameList().get(0));
        assertEquals("Midterm", testGradeComponents.getNameList().get(1));
        assertEquals("Final", testGradeComponents.getNameList().get(2));

        assertEquals(assignmentGradeList, testGradeComponents.getGradeList().get(0));
        assertEquals(midtermGradeList, testGradeComponents.getGradeList().get(1));
        assertEquals(finalGradeList, testGradeComponents.getGradeList().get(2));
    }

    @Test
    public void testDeleteComponentMultiple() {
        testGradeComponents.deleteComponent(1);
        testGradeComponents.deleteComponent(1);
        testGradeComponents.deleteComponent(1);

        assertEquals(1, testGradeComponents.getNameList().size());
        assertEquals(testGradeComponents.getNameList().size(), testGradeComponents.getGradeList().size());

        assertEquals("Homework", testGradeComponents.getNameList().get(0));
        assertEquals(homeworkGradeList, testGradeComponents.getGradeList().get(0));
    }

    @Test
    public void testPrintComponent() {
        String str = "Homework\n" +
                "Max Percentage : 5.00, Real : 3.00\n Max Percentage : 5.00, Real : 3.50\n Max Percentage : 5.00, " +
                "Real : 4.00\n Max Percentage : 5.00, Grade is Empty\n Max Percentage : 5.00, Grade is Empty\n \n" +
                "Assignment\n" +
                "Max Percentage : 3.00, Real : 3.00" + "\n Max Percentage : 3.00, Real : 2.00\n " +
                "Max Percentage : 3.00, Grade is Empty\n \n" +
                "Midterm\n" + "Max Percentage : 18.00, " + "Real : 15.80\n " +
                "Max Percentage : 18.00, Grade is Empty\n \n" +
                "Final\n" + "Max Percentage : 30.00, Grade is Empty\n \n";
        assertEquals(str, testGradeComponents.toString());
    }

    private void setUpHomeworkGradeList() {
        // Grades for homework, it accounts for 25% of total grade
        // per homework is 5%
        Grade hw1 = new Grade(HW_PERCENTAGE, 3f, false);
        Grade hw2 = new Grade(HW_PERCENTAGE, 3.5f, false);
        Grade hw3 = new Grade(HW_PERCENTAGE, 4f, false);
        Grade hw4 = new Grade(HW_PERCENTAGE, 0, true);
        Grade hw5 = new Grade(HW_PERCENTAGE, 0, true);

        homeworkGradeList.add(hw1);
        homeworkGradeList.add(hw2);
        homeworkGradeList.add(hw3);
        homeworkGradeList.add(hw4);
        homeworkGradeList.add(hw5);
    }

    private void setUpAssignmentGradeList() {
        // Grades for Assignment, it accounts for 9% of total grade
        // per assignment is 3%
        Grade a1 = new Grade(A_PERCENTAGE, 3f, false);
        Grade a2 = new Grade(A_PERCENTAGE, 2f, false);
        Grade a3 = new Grade(A_PERCENTAGE, 0, true);

        assignmentGradeList.add(a1);
        assignmentGradeList.add(a2);
        assignmentGradeList.add(a3);
    }

    private void setUpMidtermGradeList() {
        // Grades for Midterm, it accounts for 36% of total grade
        // per midterm is 18%
        Grade mid1 = new Grade(MID_PERCENTAGE, 15.8f, false);
        Grade mid2 = new Grade(MID_PERCENTAGE, 0, true);

        midtermGradeList.add(mid1);
        midtermGradeList.add(mid2);
    }

    private void setUpFinalGradeList() {
        // Grades for Final, it accounts for 30% of total grade
        Grade fin = new Grade(FIN_PERCENTAGE, 0, true);
        finalGradeList.add(fin);
    }

    private void setUpNameList() {
        nameList.add("Homework");
        nameList.add("Assignment");
        nameList.add("Midterm");
        nameList.add("Final");
    }

    private void setUpGradeList() {
        gradeList.add(homeworkGradeList);
        gradeList.add(assignmentGradeList);
        gradeList.add(midtermGradeList);
        gradeList.add(finalGradeList);
    }

    private void setUpProjectLabWebWorksGrade(SimpleList<Grade> projectGradeList, SimpleList<Grade> labGradeList,
                                         SimpleList<Grade> webWorksGradeList) {
        Grade proj = new Grade(15f, 12f, false);
        Grade lab1 = new Grade(4f, 2f, false);
        Grade lab2 = new Grade(4f, 1.5f, false);
        Grade lab3 = new Grade(4f, 4f, false);
        Grade ww1 = new Grade(3f, 2f, false);
        Grade ww2 = new Grade(3f, 0, true);

        projectGradeList.add(proj);
        labGradeList.add(lab1);
        labGradeList.add(lab2);
        labGradeList.add(lab3);
        webWorksGradeList.add(ww1);
        webWorksGradeList.add(ww2);
    }
}
