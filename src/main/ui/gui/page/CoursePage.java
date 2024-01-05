package ui.gui.page;

import model.component.Course;
import model.component.Grade;
import model.component.GradeComponents;
import model.component.Term;
import model.exception.DifferentSizeException;
import model.exception.InvalidNumberException;
import model.exception.NotHundredPercentException;
import model.util.SimpleList;
import ui.gui.component.Arrow;
import ui.gui.component.Rectangle;
import ui.gui.util.CustomFont;
import ui.gui.util.LoadImage;
import persistence.LoadJson;
import ui.gui.util.RelativeTo;
import ui.gui.warning.*;

import javax.swing.*;
import java.awt.*;

// create course page
public class CoursePage extends JFrame {

    private static final String PATH = "./data/img/3d-blob.gif";

    private final CustomFont customFont;
    private JLayeredPane pane;
    private JTextField fieldCourseName;
    private JTextField fieldCourseComponent;
    private JTextField fieldComponentSize;
    private JTextField fieldPercentageBreakdown;

    private int backgroundLayer;
    private final boolean addCourse;
    private boolean[] valid;
    private Term term;

    // EFFECTS : creating the course page
    public CoursePage() {
        customFont = new CustomFont();
        addCourse = false;
        setPane();
        setBackground();
        setHeader();
        setPicture();
        setTextField();
        setArrow();
    }

    // EFFECTS : creating the course page
    public CoursePage(Term t) {
        customFont = new CustomFont();
        addCourse = true;
        term = t;
        setPane();
        setBackground();
        setHeader();
        setPicture();
        setTextField();
        setArrow();
    }

    // MODIFIES : this
    // EFFECTS : set up the frame and pane
    private void setPane() {
        pane = new JLayeredPane();
        pane.setLayout(null);
        pane.setSize(RelativeTo.relativeToHeight(0.71), RelativeTo.relativeToHeight(0.77));
        add(pane);

        setTitle("Course");
        setSize(RelativeTo.relativeToWidth(0.71), RelativeTo.relativeToHeight(0.77));
        setResizable(false);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setUndecorated(false);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    // MODIFIES : this
    // EFFECTS : set up the background
    private void setBackground() {
        JLabel background = LoadImage.getImageMap().get("gradientSmall");
        background.setBounds(0, 0, pane.getWidth(), pane.getHeight());
        pane.add(background);
        backgroundLayer = JLayeredPane.getLayer(background);
    }

    // MODIFIES : this
    // EFFECTS : set up the header
    private void setHeader() {
        JLabel text = new JLabel("Set Up Course");
        text.setFont(customFont.getFont(120f));
        text.setForeground(new Color(202, 210, 197, 153));
        text.setSize(RelativeTo.relativeToWidth(0.56), RelativeTo.relativeToHeight(0.2));
        text.setLocation(RelativeTo.relativeToWidth(0.148), RelativeTo.relativeToHeight(-0.05));
        pane.setLayer(text, backgroundLayer + 1);
        pane.add(text);
    }

    // MODIFIES : this
    // EFFECTS : set up the picture
    private void setPicture() {
        Icon pict = new ImageIcon(PATH);
        JLabel image = new JLabel(pict);
        image.setBounds(RelativeTo.relativeToWidth(-0.26), RelativeTo.relativeToHeight(0.15),
                RelativeTo.relativeToWidth(1.2), RelativeTo.relativeToHeight(1.2));
        pane.setLayer(image, backgroundLayer + 1);
        pane.add(image);
    }

    // MODIFIES : this
    // EFFECTS : set up the textfield
    private void setTextField() {
        Rectangle rect = new Rectangle(RelativeTo.relativeToWidth(0.168), RelativeTo.relativeToHeight(0.22),
                RelativeTo.relativeToWidth(0.3735), RelativeTo.relativeToHeight(0.46),
                new Color(202, 210, 197, 51));
        rect.setSize(RelativeTo.relativeToWidth(0.8), RelativeTo.relativeToHeight(0.8));
        pane.setLayer(rect, backgroundLayer + 1);
        pane.add(rect);

        setName();
        setCourseComponent();
        setComponentSize();
        setPercentageBreakdown();
    }

    // MODIFIES : this
    // EFFECTS : create name textfield
    private void setName() {
        JLabel text = new JLabel("Name");
        text.setFont(customFont.getFont(32f));
        text.setForeground(new Color(47, 62, 70));
        text.setSize(RelativeTo.relativeToWidth(0.2), RelativeTo.relativeToHeight(0.1));
        text.setLocation(RelativeTo.relativeToWidth(0.2225), RelativeTo.relativeToHeight(0.211));
        pane.setLayer(text, backgroundLayer + 2);
        pane.add(text);

        fieldCourseName = new JTextField();
        fieldCourseName.setFont(customFont.getFont(12f));
        fieldCourseName.setForeground(new Color(47, 62, 70));
        fieldCourseName.setBackground(new Color(53, 79, 82, 127));
        fieldCourseName.setSize(RelativeTo.relativeToWidth(0.26), RelativeTo.relativeToHeight(0.03));
        fieldCourseName.setLocation(RelativeTo.relativeToWidth(0.2215), RelativeTo.relativeToHeight(0.3));
        pane.setLayer(fieldCourseName, backgroundLayer + 2);
        pane.add(fieldCourseName);
    }

    // MODIFIES : this
    // EFFECTS : create course component textfield
    private void setCourseComponent() {
        JLabel text = new JLabel("Course Component");
        text.setFont(customFont.getFont(32f));
        text.setForeground(new Color(47, 62, 70));
        text.setSize(RelativeTo.relativeToWidth(0.2), RelativeTo.relativeToHeight(0.1));
        text.setLocation(RelativeTo.relativeToWidth(0.2225), RelativeTo.relativeToHeight(0.311));
        pane.setLayer(text, backgroundLayer + 2);
        pane.add(text);

        fieldCourseComponent = new JTextField("Component1, Component2, ... (no integer on component's name)");
        fieldCourseComponent.setFont(customFont.getFont(12f));
        fieldCourseComponent.setForeground(new Color(47, 62, 70));
        fieldCourseComponent.setBackground(new Color(53, 79, 82, 127));
        fieldCourseComponent.setSize(RelativeTo.relativeToWidth(0.26), RelativeTo.relativeToHeight(0.03));
        fieldCourseComponent.setLocation(RelativeTo.relativeToWidth(0.2215), RelativeTo.relativeToHeight(0.4));
        pane.setLayer(fieldCourseComponent, backgroundLayer + 2);
        pane.add(fieldCourseComponent);
    }

    // MODIFIES : this
    // EFFECTS : create component size textfield
    private void setComponentSize() {
        JLabel text = new JLabel("Component Size");
        text.setFont(customFont.getFont(32f));
        text.setForeground(new Color(47, 62, 70));
        text.setSize(RelativeTo.relativeToWidth(0.2), RelativeTo.relativeToHeight(0.1));
        text.setLocation(RelativeTo.relativeToWidth(0.2225), RelativeTo.relativeToHeight(0.411));
        pane.setLayer(text, backgroundLayer + 2);
        pane.add(text);

        fieldComponentSize = new JTextField("11, 9, ... (only integer)");
        fieldComponentSize.setFont(customFont.getFont(12f));
        fieldComponentSize.setForeground(new Color(47, 62, 70));
        fieldComponentSize.setBackground(new Color(53, 79, 82, 127));
        fieldComponentSize.setSize(RelativeTo.relativeToWidth(0.26), RelativeTo.relativeToHeight(0.03));
        fieldComponentSize.setLocation(RelativeTo.relativeToWidth(0.2215), RelativeTo.relativeToHeight(0.5));
        pane.setLayer(fieldComponentSize, backgroundLayer + 2);
        pane.add(fieldComponentSize);
    }

    // MODIFIES : this
    // EFFECTS : create percentage breakdown textfield
    private void setPercentageBreakdown() {
        JLabel text = new JLabel("Percentage Breakdown");
        text.setFont(customFont.getFont(32f));
        text.setForeground(new Color(47, 62, 70));
        text.setSize(RelativeTo.relativeToWidth(0.2), RelativeTo.relativeToHeight(0.1));
        text.setLocation(RelativeTo.relativeToWidth(0.2225), RelativeTo.relativeToHeight(0.511));
        pane.setLayer(text, backgroundLayer + 2);
        pane.add(text);

        fieldPercentageBreakdown = new JTextField("20, 30, ... (out of 100 without %)");
        fieldPercentageBreakdown.setFont(customFont.getFont(12f));
        fieldPercentageBreakdown.setForeground(new Color(47, 62, 70));
        fieldPercentageBreakdown.setBackground(new Color(53, 79, 82, 127));
        fieldPercentageBreakdown.setSize(RelativeTo.relativeToWidth(0.26), RelativeTo.relativeToHeight(0.03));
        fieldPercentageBreakdown.setLocation(RelativeTo.relativeToWidth(0.2215), RelativeTo.relativeToHeight(0.6));
        pane.setLayer(fieldPercentageBreakdown, backgroundLayer + 2);
        pane.add(fieldPercentageBreakdown);
    }

    // MODIFIES : this
    // EFFECTS : set up the arrow
    private void setArrow() {
        Arrow arrow = new Arrow(RelativeTo.relativeToWidth(0.4515), RelativeTo.relativeToHeight(0.6165),
                RelativeTo.relativeToWidth(0.4715), RelativeTo.relativeToHeight(0.6165), 2,
                new Color(202, 210, 197));
        arrow.setSize(RelativeTo.relativeToWidth(0.8), RelativeTo.relativeToHeight(0.8));
        pane.setLayer(arrow, backgroundLayer + 3);
        pane.add(arrow);
        setArrowButton();
    }

    // MODIFIES : this
    // EFFECTS : set up the arrow
    private void setArrowButton() {
        JButton button = new JButton();
        button.setOpaque(false);
        button.setBorderPainted(false);
        button.setFocusPainted(false);
        button.setContentAreaFilled(false);
        button.setSize(RelativeTo.relativeToWidth(0.08), RelativeTo.relativeToHeight(0.035));
        button.setLocation(RelativeTo.relativeToWidth(0.3965), RelativeTo.relativeToHeight(0.598));
        button.addActionListener(e -> {
            valid = new boolean[6];
            checkFormatWarning();
            checkComponentSizeParseWarning();
            checkPercentageBreakdownParseWarning();
            checkInvalidNumberWarning();
            checkHundredPercentWarning();
        });
        pane.setLayer(button, backgroundLayer + 4);
        pane.add(button);
    }

    // EFFECTS : transform the data to course
    private Course toCourse(String name, String gc, String cs, String pb) {
        return new Course(name.toUpperCase(), -1f, toGradeComponent(gc, cs, pb));
    }

    // EFFECTS : transform the data to grade component
    private GradeComponents toGradeComponent(String gc, String cs, String pb) {
        String[] courseComponent = gc.split(", ");
        String[] courseSize = cs.split(", ");
        String[] percentageBreakdown = pb.split(", ");
        SimpleList<String> nameList = getNameList(courseComponent);
        SimpleList<SimpleList<Grade>> gradeList = getGradeList(courseSize, percentageBreakdown);
        return new GradeComponents(nameList, gradeList);
    }

    // EFFECTS : get the course component name
    private SimpleList<String> getNameList(String[] s) {
        SimpleList<String> nameList = new SimpleList<>();
        for (String str : s) {
            nameList.add(str);
        }
        return nameList;
    }

    // EFFECTS : get the grade list
    private SimpleList<SimpleList<Grade>> getGradeList(String[] cs, String[] pb) {
        SimpleList<SimpleList<Grade>> gradeList = new SimpleList<>();
        int size = cs.length;
        for (int i = 0; i < size; i++) {
            float percentage = Float.parseFloat(pb[i]);
            int componentSize = Integer.parseInt(cs[i]);
            SimpleList<Grade> grade = new SimpleList<>();
            for (int j = 0; j < componentSize; j++) {
                grade.add(new Grade(percentage / componentSize, 0, true));
            }
            gradeList.add(grade);
        }
        return gradeList;
    }

    // EFFECTS : check for warning
    private void checkFormatWarning() {
        String courseComponent = fieldCourseComponent.getText();
        String componentSize = fieldComponentSize.getText();
        String percentageBreakdown = fieldPercentageBreakdown.getText();
        int courseComponentCount = courseComponent.length()
                - courseComponent.replace(", ", "").length() - 1;
        int componentSizeCount = componentSize.length()
                - componentSize.replace(", ", "").length() - 1;
        int percentageBreakdownCount = percentageBreakdown.length()
                - percentageBreakdown.replace(", ", "").length() - 1;
        try {
            differentSize(componentSizeCount, courseComponentCount, percentageBreakdownCount);
            valid[0] = true;
        } catch (DifferentSizeException ds) {
            new DifferentSizeWarning();
        }
    }

    // EFFECTS : check for warning
    private void checkComponentSizeParseWarning() {
        try {
            String[] componentSize = fieldComponentSize.getText().split(", ");
            for (String s : componentSize) {
                Float.parseFloat(s);
            }
            valid[1] = true;
        } catch (Exception ex) {
            new NotNumberWarning("Component size");
        }
    }

    // EFFECTS : check for warning
    private void checkPercentageBreakdownParseWarning() {
        try {
            String[] percentageBreakdown = fieldPercentageBreakdown.getText().split(", ");
            for (String s : percentageBreakdown) {
                Float.parseFloat(s);
            }
            valid[2] = true;
        } catch (Exception ex) {
            new NotNumberWarning("Percentage breakdown");
        }
    }

    // EFFECTS : check for warning
    private void checkInvalidNumberWarning() {
        String[] componentSize = fieldComponentSize.getText().split(", ");
        String[] percentageBreakdown = fieldPercentageBreakdown.getText().split(", ");

        try {
            invalidNumber(componentSize);
            valid[3] = true;
        } catch (InvalidNumberException in) {
            new InvalidNumberWarning("Component size");
        }

        try {
            invalidNumber(percentageBreakdown);
            valid[4] = true;
        } catch (InvalidNumberException in) {
            new InvalidNumberWarning("Percentage breakdown");
        }

    }

    // EFFECTS : check for warning
    private void checkHundredPercentWarning() {
        String[] percentageBreakdown = fieldPercentageBreakdown.getText().split(", ");
        float total = 0;
        for (String s : percentageBreakdown) {
            total += Float.parseFloat(s);
        }

        try {
            hundredPercent(total);
            valid[5] = true;
            if (isValidToContinue()) {
                Course course = toCourse(fieldCourseName.getText(), fieldCourseComponent.getText(),
                        fieldComponentSize.getText(), fieldPercentageBreakdown.getText());
                if (addCourse) {
                    LoadJson.addCourse(course, term);
                } else {
                    LoadJson.setCourse(course);
                    new MainPage();
                }
                this.dispose();
            }
        } catch (NotHundredPercentException nh) {
            new NotHundredPercentWarning();
        }
    }

    // EFFECTS : check of the course component, component size, and percentage breakdown sizes
    private void differentSize(int a, int b, int c) throws DifferentSizeException {
        if (!(a == b && b == c)) {
            throw new DifferentSizeException();
        }
    }

    // EFFECTS : check if the input number valid
    private void invalidNumber(String[] s) throws InvalidNumberException {
        for (String str : s) {
            if (Float.parseFloat(str) < 0) {
                throw new InvalidNumberException();
            }
        }
    }

    // EFFECTS : check if total is equal to a hundred percent
    private void hundredPercent(float f) throws NotHundredPercentException {
        if (Math.abs(f - 100) > 0.1) {
            throw new NotHundredPercentException();
        }
    }

    // EFFECTS : check if it is valid to continue
    private boolean isValidToContinue() {
        for (boolean b : valid) {
            if (!b) {
                return false;
            }
        }
        return true;
    }
}
