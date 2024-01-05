package ui.gui.page;

import model.component.Course;
import model.component.Grade;
import model.component.Profile;
import model.component.Term;
import model.exception.InvalidNumberException;
import model.util.SimpleList;
import persistence.LoadJson;
import ui.gui.component.Circle;
import ui.gui.component.Line;
import ui.gui.component.Rectangle;
import ui.gui.util.*;
import ui.gui.warning.InvalidNumberWarning;
import ui.gui.warning.NotNumberWarning;

import javax.swing.*;
import java.awt.*;
import java.time.LocalDateTime;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Random;
import java.util.Set;
import java.util.concurrent.ExecutionException;
import java.util.stream.Collectors;

// create the main page
public class MainPage extends JFrame {

    private final CustomFont customFont;
    private static JLayeredPane pane;

    private static int backgroundLayer;

    private static Profile profile;
    private static SimpleList<Term> termList;
    private static int termIndex;
    private static int courseIndex;
    private static boolean[] validSave;
    private static int flag;

    private JLabel textOneLeftBarSubHeader;
    private JLabel textOneCourseList;
    private JLabel textOneMain;

    private JLabel percent;
    private JLabel week;

    private JScrollPane scrollPaneLeft;
    private JScrollPane scrollPaneRight;

    private JTextField targetField;

    private Rectangle bar;

    private SimpleList<Circle> circles;
    private Map<String, JTextField> textField;

    // EFFECTS : creating the profile page
    public MainPage() {
        customFont = new CustomFont();
        profile = LoadJson.getProfile();
        termList = profile.getTermList();
        termIndex = 0;
        courseIndex = 0;
        textField = new LinkedHashMap<>();
        setPane();
        setBackground();
        setLeftBar();
        setMain();
    }

    public void setCourseIndex(int i) {
        courseIndex = i;
        refresh();
    }

    public void setTermIndex(int i) {
        termIndex = i;
        refresh();
    }

    // MODIFIES : this
    // EFFECTS : set up the frame and pane
    private void setPane() {
        pane = new JLayeredPane();
        pane.setLayout(null);
        pane.setSize(RelativeTo.relativeToHeight(0.9), RelativeTo.relativeToHeight(0.9));
        add(pane);

        setTitle("Dashboard");
        setSize(RelativeTo.relativeToWidth(0.9), RelativeTo.relativeToHeight(0.9));
        setResizable(false);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setUndecorated(false);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    // MODIFIES : this
    // EFFECTS : set up the background
    private void setBackground() {
        JLabel background = LoadImage.getImageMap().get("gradientBig");
        background.setBounds(0, 0, pane.getWidth(), pane.getHeight());
        pane.add(background);
        backgroundLayer = JLayeredPane.getLayer(background);
    }

    // MODIFIES : this
    // EFFECTS : set up the left bar
    private void setLeftBar() {
        setLeftBarRectangle();
        setLeftBarHeader();
        setLeftBarSubHeader();
        setCourseList();
        setCourseListCircle();
        setCourseListButtonAdd();
        setCourseListButtonDelete();
        setLeftBarButtonChangeTerm();
        setLeftBarButtonCourse();
        setLeftBarButtonAddTerm();
        setLeftBarButtonDeleteTerm();
    }

    // MODIFIES : this
    // EFFECTS : set up the main part
    private void setMain() {
        setMainHeaderLeft();
        setMainHeaderMiddle();
        setMainHeaderRight();

        setMainHeader();
        setMainHeaderMiddleTextField();

        setMainPieChart();

        setMainBar();
        setMainBarTarget();

        setMainComponents();

        setMainButtonSave();
        setMainButtonAnalytics();
        setMainButtonExit();
    }

    // MODIFIES : this
    // EFFECTS : set up the main part
    private void setMainHeaderLeft() {
        setMainHeaderLeftBook();
        setMainHeaderLeftText();
    }

    // MODIFIES : this
    // EFFECTS : set up the main part
    private void setMainHeaderLeftBook() {
        Rectangle rect = new Rectangle(RelativeTo.relativeToWidth(0.2055), RelativeTo.relativeToHeight(0.037),
                RelativeTo.relativeToWidth(0.03), RelativeTo.relativeToHeight(0.05),
                new Color(47, 62, 70, 128));
        rect.setSize(RelativeTo.relativeToWidth(1), RelativeTo.relativeToHeight(1));
        pane.setLayer(rect, backgroundLayer + 1);
        pane.add(rect);

        for (int i = 0; i < 3; i++) {
            Line line = new Line(RelativeTo.relativeToWidth(0.21),
                    RelativeTo.relativeToHeight(0.053 + (0.01 * i)),
                    RelativeTo.relativeToWidth(0.23), RelativeTo.relativeToHeight(0.053 + (0.01 * i)),
                    2, new Color(132, 169, 140));
            line.setSize(RelativeTo.relativeToWidth(1), RelativeTo.relativeToHeight(1));
            pane.setLayer(line, backgroundLayer + 2);
            pane.add(line);
        }
    }

    // MODIFIES : this
    // EFFECTS : set up the main part
    private void setMainHeaderLeftText() {
        if (textOneMain != null) {
            pane.remove(textOneMain);
        }
        String text = termList.get(termIndex).getCourseList().get(courseIndex).getName();
        textOneMain = new JLabel(text);
        textOneMain.setFont(customFont.getFont(40f));
        textOneMain.setForeground(new Color(202, 210, 197));
        textOneMain.setSize(RelativeTo.relativeToWidth(0.2), RelativeTo.relativeToHeight(0.1));
        textOneMain.setLocation(RelativeTo.relativeToWidth(0.26), RelativeTo.relativeToHeight(0));
        pane.setLayer(textOneMain, backgroundLayer + 1);
        pane.add(textOneMain);
    }

    // MODIFIES : this
    // EFFECTS : set up the main part
    private void setMainHeaderMiddle() {
        JLabel text = new JLabel("TARGET");
        text.setFont(customFont.getFont(16f));
        text.setForeground(new Color(202, 210, 197));
        text.setSize(RelativeTo.relativeToWidth(0.2), RelativeTo.relativeToHeight(0.1));
        text.setLocation(RelativeTo.relativeToWidth(0.51), RelativeTo.relativeToHeight(-0.03));
        pane.setLayer(text, backgroundLayer + 1);
        pane.add(text);

        JLabel textTwo = new JLabel("%");
        textTwo.setFont(customFont.getFont(36f));
        textTwo.setForeground(new Color(202, 210, 197));
        textTwo.setSize(RelativeTo.relativeToWidth(0.2), RelativeTo.relativeToHeight(0.1));
        textTwo.setLocation(RelativeTo.relativeToWidth(0.555), RelativeTo.relativeToHeight(0.008));
        pane.setLayer(textTwo, backgroundLayer + 1);
        pane.add(textTwo);
    }

    // MODIFIES : this
    // EFFECTS : set up the main part
    private void setMainHeaderMiddleTextField() {
        float courseTarget = profile.getTermList().get(termIndex).getCourseList().get(courseIndex).getTarget();
        String text = "";

        if (courseTarget != -1) {
            text = String.valueOf(courseTarget);
        }

        if (targetField != null) {
            targetField.setText(text);
        } else {
            targetField = new JTextField();
            targetField.setFont(customFont.getFont(32f));
            targetField.setText(text);
            targetField.setForeground(new Color(202, 210, 197));
            targetField.setBackground(new Color(82, 121, 111));
            targetField.setHorizontalAlignment(SwingConstants.CENTER);
            targetField.setSize(RelativeTo.relativeToWidth(0.05), RelativeTo.relativeToHeight(0.05));
            targetField.setLocation(RelativeTo.relativeToWidth(0.5), RelativeTo.relativeToHeight(0.04));
            pane.setLayer(targetField, backgroundLayer + 1);
            pane.add(targetField);
        }
    }

    // MODIFIES : this
    // EFFECTS : set up the main part
    private void setMainHeaderRight() {
        setMainHeaderRightText();
    }

    // MODIFIES : this
    // EFFECTS : set up the main part
    private void setMainHeaderRightText() {
        LocalDateTime date = LocalDateTime.now();
        int hour = date.getHour();
        String[] time = {"Morning", "Noon", "Afternoon", "Night"};
        int index;
        if (hour == 12) {
            index = 1;
        } else if (hour > 6 && hour < 12) {
            index = 0;
        } else if (hour > 12 && hour < 19) {
            index = 2;
        } else {
            index = 3;
        }
        String greet = "Good " + time[index] + ", " + profile.getName() + "!";
        JLabel text = new JLabel(greet);
        text.setFont(customFont.getFont(20f));
        text.setForeground(new Color(202, 210, 197));
        text.setSize(RelativeTo.relativeToWidth(0.2), RelativeTo.relativeToHeight(0.1));
        text.setLocation(RelativeTo.relativeToWidth(0.76), RelativeTo.relativeToHeight(-0.005));
        pane.setLayer(text, backgroundLayer + 1);
        pane.add(text);
        setMainHeaderRightShape(index);
    }

    // MODIFIES : this
    // EFFECTS : set up the main part
    private void setMainHeaderRightShape(int i) {
        int[] start = {100, 0, 90, 0};
        int[] extent = {60, 0, -180, 360};
        Circle circle = new Circle(RelativeTo.relativeToWidth(0.71), RelativeTo.relativeToHeight(0.03),
                0, 360, RelativeTo.relativeToHeight(0.05), RelativeTo.relativeToHeight(0.05),
                new Color(255, 235, 108));
        Circle shades = new Circle(RelativeTo.relativeToWidth(0.71), RelativeTo.relativeToHeight(0.03),
                start[i], extent[i], RelativeTo.relativeToHeight(0.05), RelativeTo.relativeToHeight(0.05),
                new Color(75, 92, 104));
        circle.setSize(RelativeTo.relativeToWidth(1), RelativeTo.relativeToHeight(1));
        shades.setSize(RelativeTo.relativeToWidth(1), RelativeTo.relativeToHeight(1));
        pane.setLayer(circle, backgroundLayer + 1);
        pane.setLayer(shades, backgroundLayer + 2);
        pane.add(circle);
        pane.add(shades);
    }

    // MODIFIES : this
    // EFFECTS : set up the main part
    private void setMainHeader() {
        JLabel text = new JLabel("Grading Scheme");
        text.setFont(customFont.getFont(35f));
        text.setForeground(new Color(202, 210, 197));
        text.setSize(RelativeTo.relativeToWidth(0.2), RelativeTo.relativeToHeight(0.1));
        text.setLocation(RelativeTo.relativeToWidth(0.46), RelativeTo.relativeToHeight(0.11));
        pane.setLayer(text, backgroundLayer + 1);
        pane.add(text);
    }

    // MODIFIES : this
    // EFFECTS : set up the main part
    private void setMainPieChart() {
        Circle circle = new Circle(RelativeTo.relativeToWidth(0.41), RelativeTo.relativeToHeight(0.235),
                0, 360, RelativeTo.relativeToHeight(0.4), RelativeTo.relativeToHeight(0.4),
                new Color(47, 62, 70));
        circle.setSize(RelativeTo.relativeToWidth(1), RelativeTo.relativeToHeight(1));
        pane.setLayer(circle, backgroundLayer + 1);
        pane.add(circle);
    }

    // MODIFIES : this
    // EFFECTS : set up the main part
    private void setMainPieChartComponents(SimpleList<Color> colors, SimpleList<Float> percentage) {
        if (circles != null) {
            for (int i = 0; i < circles.size(); i++) {
                pane.remove(circles.get(i));
            }
        }

        circles = new SimpleList<>();
        int size = colors.size();
        double[] start = new double[size];
        double[] extent = new double[size];
        calculateStartExtent(start, extent, percentage, size);
        for (int i = 0; i < size; i++) {
            Circle circle = new Circle(RelativeTo.relativeToWidth(0.41), RelativeTo.relativeToHeight(0.235),
                    start[i], extent[i], RelativeTo.relativeToHeight(0.4), RelativeTo.relativeToHeight(0.4),
                    colors.get(i));
            circle.setSize(RelativeTo.relativeToWidth(1), RelativeTo.relativeToHeight(1));
            pane.setLayer(circle, backgroundLayer + 2);
            pane.add(circle);
            circles.add(circle);
        }
    }

    // EFFECTS : calculates start and extent angle for each circle
    private void calculateStartExtent(double[] start, double[] extent, SimpleList<Float> percentage, int size) {
        for (int i = 0; i < size; i++) {
            extent[i] = 360 * percentage.get(i) / 100;
        }

        start[0] = 0;
        double startTemp = 0;
        for (int j = 1; j < size; j++) {
            start[j] = extent[j - 1] + startTemp;
            startTemp += extent[j - 1];
        }
    }

    // MODIFIES : this
    // EFFECTS : set up the main part
    private void setMainBar() {
        Course course = profile.getTermList().get(termIndex).getCourseList().get(courseIndex);
        try {
            course.currentCondition();
        } catch (ExecutionException | InterruptedException e) {
            e.printStackTrace();
        }
        float percentageNow = LoadJson.getTotalTemp() / course.getTarget();
        String percentage = String.format("%.2f", percentageNow * 100);
        int intWeek = profile.getTermList().get(termIndex).getWeek();
        setMainBarText(percentage, intWeek);
        setMainBarActual(percentageNow);
    }

    // MODIFIES : this
    // EFFECTS : set up the main part
    private void setMainBarActual(float p) {
        if (bar != null) {
            pane.remove(bar);
        }
        bar = new Rectangle(RelativeTo.relativeToWidth(0.41), RelativeTo.relativeToHeight(0.69),
                RelativeTo.relativeToWidth(0.253 * p), RelativeTo.relativeToHeight(0.02),
                new Color(47, 62, 70, 179));
        bar.setSize(RelativeTo.relativeToWidth(1), RelativeTo.relativeToHeight(1));
        pane.setLayer(bar, backgroundLayer + 2);
        pane.add(bar);
    }

    // MODIFIES : this
    // EFFECTS : set up the main part
    private void setMainBarTarget() {
        JLabel target = new JLabel("Target Realization");
        target.setFont(customFont.getFont(16f));
        target.setForeground(new Color(202, 210, 197));
        target.setSize(RelativeTo.relativeToWidth(0.09), RelativeTo.relativeToHeight(0.06));
        target.setLocation(RelativeTo.relativeToWidth(0.41), RelativeTo.relativeToHeight(0.63));
        pane.setLayer(target, backgroundLayer + 1);
        pane.add(target);
    }

    // MODIFIES : this
    // EFFECTS : set up the main part
    private void setMainBarText(String p, int w) {
        if (percent != null) {
            pane.remove(percent);
            pane.remove(week);
        }

        percent = new JLabel(p + "%");
        percent.setFont(customFont.getFont(16f));
        percent.setForeground(new Color(202, 210, 197));
        percent.setSize(RelativeTo.relativeToWidth(0.09), RelativeTo.relativeToHeight(0.06));
        percent.setLocation(RelativeTo.relativeToWidth(0.63), RelativeTo.relativeToHeight(0.63));
        pane.setLayer(percent, backgroundLayer + 1);
        pane.add(percent);

        week = new JLabel("Week " + w);
        week.setFont(customFont.getFont(16f));
        week.setForeground(new Color(202, 210, 197));
        week.setSize(RelativeTo.relativeToWidth(0.09), RelativeTo.relativeToHeight(0.06));
        week.setLocation(RelativeTo.relativeToWidth(0.41), RelativeTo.relativeToHeight(0.7));
        pane.setLayer(week, backgroundLayer + 1);
        pane.add(week);
    }

    // MODIFIES : this
    // EFFECTS : set up the main part
    @SuppressWarnings({"checkstyle:MethodLength", "checkstyle:SuppressWarnings"})
    private void setMainComponents() {
        SimpleList<String> nameList = profile.getTermList().get(termIndex)
                .getCourseList().get(courseIndex).getComponents().getNameList();
        SimpleList<SimpleList<Grade>> gradeList = profile.getTermList().get(termIndex)
                .getCourseList().get(courseIndex).getComponents().getGradeList();

        SimpleList<Integer> sortedIndex = sortBasedOnSize(gradeList);
        int size = sortedIndex.size();

        SimpleList<String> nameLeft = new SimpleList<>();
        SimpleList<String> nameRight = new SimpleList<>();
        SimpleList<SimpleList<Grade>> gradeLeft = new SimpleList<>();
        SimpleList<SimpleList<Grade>> gradeRight = new SimpleList<>();

        SimpleList<Color> colors = randomColor(size);
        SimpleList<Color> colorLeft = new SimpleList<>();
        SimpleList<Color> colorRight = new SimpleList<>();

        SimpleList<Float> percentage = new SimpleList<>();

        for (int i = 0; i < size; i++) {
            if ((i & 1) == 1) {
                nameLeft.add(nameList.get(i));
                gradeLeft.add(gradeList.get(i));
                colorLeft.add(colors.get(i));
            } else {
                nameRight.add(nameList.get(i));
                gradeRight.add(gradeList.get(i));
                colorRight.add(colors.get(i));
            }
            percentage.add(gradeList.get(i).get(0).getPercentage() * gradeList.get(i).size());
        }
        setMainComponentsLeftPanel(nameLeft, gradeLeft, colorLeft);
        setMainComponentsRightPanel(nameRight, gradeRight, colorRight);
        setMainPieChartComponents(colors, percentage);
    }

    // MODIFIES : this
    // EFFECTS : set up the main part
    private void setMainComponentsRightPanel(SimpleList<String> name, SimpleList<SimpleList<Grade>> grade,
                                             SimpleList<Color> colors) {
        if (scrollPaneRight != null) {
            pane.remove(scrollPaneRight);
        }
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setSize(new Dimension(RelativeTo.relativeToWidth(0.185),
                RelativeTo.relativeToHeight(0.47)));
        panel.setBackground(new Color(202, 210, 197, 140));

        setMainComponentsRight(panel, name, grade, colors);

        scrollPaneRight = new JScrollPane();
        scrollPaneRight.setViewportView(panel);
        scrollPaneRight.setBounds(RelativeTo.relativeToWidth(0.68), RelativeTo.relativeToHeight(0.18),
                panel.getWidth(), panel.getHeight());
        scrollPaneRight.setBorder(BorderFactory.createEmptyBorder());
        pane.setLayer(scrollPaneRight, backgroundLayer + 2);
        pane.add(scrollPaneRight);
    }

    // MODIFIES : this
    // EFFECTS : set up the main part
    private void setMainComponentsRight(JPanel panel, SimpleList<String> name, SimpleList<SimpleList<Grade>> grade,
                                        SimpleList<Color> colors) {
        int size = name.size();
        for (int i = 0; i < size; i++) {
            int gradeSize = grade.get(i).size();

            JLabel text = new JLabel("  " + name.get(i) + " | " + String.format("%.1f",
                    gradeSize * grade.get(i).get(0).getPercentage()) + "%  ");
            text.setFont(customFont.getFont(18f));
            text.setForeground(new Color(47, 62, 70));
            text.setSize(RelativeTo.relativeToWidth(0.2), RelativeTo.relativeToHeight(0.06));
            panel.add(text);

            for (int j = 1; j <= gradeSize; j++) {
                JLabel number = new JLabel("  " + j);
                number.setFont(customFont.getFont(18f));
                number.setForeground(new Color(47, 62, 70));
                number.setSize(RelativeTo.relativeToWidth(0.09), RelativeTo.relativeToHeight(0.06));
                panel.add(number);

                putTextField(panel, colors.get(i), grade.get(i).get(j - 1), name.get(i), j - 1);
            }
        }
    }

    // MODIFIES : this
    // EFFECTS : set up the main part
    private void setMainComponentsLeftPanel(SimpleList<String> name, SimpleList<SimpleList<Grade>> grade,
                                            SimpleList<Color> colors) {
        if (scrollPaneLeft != null) {
            pane.remove(scrollPaneLeft);
        }
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setSize(new Dimension(RelativeTo.relativeToWidth(0.185),
                RelativeTo.relativeToHeight(0.47)));
        panel.setBackground(new Color(202, 210, 197, 140));

        setMainComponentsLeft(panel, name, grade, colors);

        scrollPaneLeft = new JScrollPane();
        scrollPaneLeft.setViewportView(panel);
        scrollPaneLeft.setBounds(RelativeTo.relativeToWidth(0.204), RelativeTo.relativeToHeight(0.18),
                panel.getWidth(), panel.getHeight());
        scrollPaneLeft.setBorder(BorderFactory.createEmptyBorder());
        pane.setLayer(scrollPaneLeft, backgroundLayer + 2);
        pane.add(scrollPaneLeft);
    }

    // MODIFIES : this
    // EFFECTS : set up the main part
    private void setMainComponentsLeft(JPanel panel, SimpleList<String> name, SimpleList<SimpleList<Grade>> grade,
                                       SimpleList<Color> colors) {
        int size = name.size();
        for (int i = 0; i < size; i++) {
            int gradeSize = grade.get(i).size();

            JLabel text = new JLabel("  " + name.get(i) + " | " + String.format("%.1f",
                    gradeSize * grade.get(i).get(0).getPercentage()) + "%");
            text.setFont(customFont.getFont(18f));
            text.setForeground(new Color(47, 62, 70));
            text.setSize(RelativeTo.relativeToWidth(0.2), RelativeTo.relativeToHeight(0.06));
            panel.add(text);

            for (int j = 1; j <= gradeSize; j++) {
                JLabel number = new JLabel("  " + j);
                number.setFont(customFont.getFont(18f));
                number.setForeground(new Color(47, 62, 70));
                number.setSize(RelativeTo.relativeToWidth(0.09), RelativeTo.relativeToHeight(0.06));
                panel.add(number);

                putTextField(panel, colors.get(i), grade.get(i).get(j - 1), name.get(i), j - 1);
            }
        }
    }

    // MODIFIES : this
    // EFFECTS : set up the main part
    @SuppressWarnings({"checkstyle:MethodLength", "checkstyle:SuppressWarnings"})
    private void setMainButtonSave() {
        JLabel text = new JLabel("Save");
        text.setFont(customFont.getFont(16f));
        text.setForeground(new Color(53, 79, 82));
        text.setSize(RelativeTo.relativeToWidth(0.09), RelativeTo.relativeToHeight(0.06));
        text.setLocation(RelativeTo.relativeToWidth(0.262), RelativeTo.relativeToHeight(0.765));
        pane.setLayer(text, backgroundLayer + 1);
        pane.add(text);

        JButton button = new JButton();
        button.setBackground(new Color(202, 210, 197));
        button.setOpaque(true);
        button.setBorderPainted(false);
        button.setFocusPainted(false);
        button.addActionListener(e -> {
            flag = 0;
            validSave = new boolean[2];
            getGradesFromComponent();
            getTargetFromComponent();
            if (flag == 0) {
                setMainBar();
                LoadJson.saveToStorage();
                TextToSpeech.speak("Saved " + profile.getName() + "'s Academic Journey");
            }
        });
        button.setSize(RelativeTo.relativeToWidth(0.14), RelativeTo.relativeToHeight(0.04));
        button.setLocation(RelativeTo.relativeToWidth(0.204), RelativeTo.relativeToHeight(0.78));
        pane.setLayer(button, backgroundLayer + 1);
        pane.add(button);
    }

    // MODIFIES : this
    // EFFECTS : set up the main part
    @SuppressWarnings({"checkstyle:MethodLength", "checkstyle:SuppressWarnings"})
    private void setMainButtonAnalytics() {
        JLabel text = new JLabel("Analytics");
        text.setFont(customFont.getFont(16f));
        text.setForeground(new Color(53, 79, 82));
        text.setSize(RelativeTo.relativeToWidth(0.09), RelativeTo.relativeToHeight(0.06));
        text.setLocation(RelativeTo.relativeToWidth(0.515), RelativeTo.relativeToHeight(0.765));
        pane.setLayer(text, backgroundLayer + 1);
        pane.add(text);

        JButton button = new JButton();
        button.setBackground(new Color(202, 210, 197));
        button.setOpaque(true);
        button.setBorderPainted(false);
        button.setFocusPainted(false);
        button.addActionListener(e -> {
            flag = 0;
            validSave = new boolean[2];
            getGradesFromComponent();
            getTargetFromComponent();
            if (flag == 0) {
                new AnalyticsPage(profile, termIndex, courseIndex);
            }
        });
        button.setSize(RelativeTo.relativeToWidth(0.14), RelativeTo.relativeToHeight(0.04));
        button.setLocation(RelativeTo.relativeToWidth(0.465), RelativeTo.relativeToHeight(0.78));
        pane.setLayer(button, backgroundLayer + 1);
        pane.add(button);
    }

    // MODIFIES : this
    // EFFECTS : set up the main part
    private void setMainButtonExit() {
        JLabel text = new JLabel("Exit");
        text.setFont(customFont.getFont(16f));
        text.setForeground(new Color(53, 79, 82));
        text.setSize(RelativeTo.relativeToWidth(0.09), RelativeTo.relativeToHeight(0.06));
        text.setLocation(RelativeTo.relativeToWidth(0.788), RelativeTo.relativeToHeight(0.765));
        pane.setLayer(text, backgroundLayer + 1);
        pane.add(text);

        JButton button = new JButton();
        button.setBackground(new Color(202, 210, 197));
        button.setOpaque(true);
        button.setBorderPainted(false);
        button.setFocusPainted(false);
        button.addActionListener(e -> {
            LoadJson.printLog();
            System.exit(0);
        });
        button.setSize(RelativeTo.relativeToWidth(0.14), RelativeTo.relativeToHeight(0.04));
        button.setLocation(RelativeTo.relativeToWidth(0.726), RelativeTo.relativeToHeight(0.78));
        pane.setLayer(button, backgroundLayer + 1);
        pane.add(button);
    }

    // MODIFIES : this
    // EFFECTS : set up the left bar
    private void setLeftBarRectangle() {
        Rectangle rect = new Rectangle(RelativeTo.relativeToWidth(0), RelativeTo.relativeToHeight(0),
                RelativeTo.relativeToWidth(0.18), RelativeTo.relativeToHeight(0.9),
                new Color(53, 79, 82, 140));
        rect.setSize(RelativeTo.relativeToWidth(1), RelativeTo.relativeToHeight(1));
        pane.setLayer(rect, backgroundLayer + 1);
        pane.add(rect);
    }

    // MODIFIES : this
    // EFFECTS : set up the left bar
    private void setLeftBarHeader() {
        JLabel text = new JLabel("V I Z !");
        text.setFont(customFont.getFont(40f));
        text.setForeground(new Color(202, 210, 197));
        text.setSize(RelativeTo.relativeToWidth(0.2), RelativeTo.relativeToHeight(0.1));
        text.setLocation(RelativeTo.relativeToWidth(0.055), RelativeTo.relativeToHeight(0));
        pane.setLayer(text, backgroundLayer + 2);
        pane.add(text);

        Line top = new Line(RelativeTo.relativeToWidth(0), RelativeTo.relativeToHeight(0.12),
                RelativeTo.relativeToWidth(0.115), RelativeTo.relativeToHeight(0.12),
                3, new Color(82, 121, 111));

        Line bottom = new Line(RelativeTo.relativeToWidth(0.064), RelativeTo.relativeToHeight(0.13),
                RelativeTo.relativeToWidth(0.179), RelativeTo.relativeToHeight(0.13),
                3, new Color(82, 121, 111));

        top.setSize(RelativeTo.relativeToWidth(1), RelativeTo.relativeToHeight(1));
        bottom.setSize(RelativeTo.relativeToWidth(1), RelativeTo.relativeToHeight(1));
        pane.setLayer(top, backgroundLayer + 2);
        pane.setLayer(bottom, backgroundLayer + 2);
        pane.add(top);
        pane.add(bottom);
    }

    // MODIFIES : this
    // EFFECTS : set up the left bar
    private void setLeftBarSubHeader() {
        if (textOneLeftBarSubHeader != null) {
            pane.remove(textOneLeftBarSubHeader);
        }

        String text = termList.get(termIndex).getTermName();
        textOneLeftBarSubHeader = new JLabel(text);
        textOneLeftBarSubHeader.setFont(customFont.getFont(16f));
        textOneLeftBarSubHeader.setForeground(new Color(132, 169, 140));
        textOneLeftBarSubHeader.setSize(RelativeTo.relativeToWidth(0.2), RelativeTo.relativeToHeight(0.1));
        textOneLeftBarSubHeader.setLocation(RelativeTo.relativeToWidth(0.03), RelativeTo.relativeToHeight(0.12));
        pane.setLayer(textOneLeftBarSubHeader, backgroundLayer + 2);
        pane.add(textOneLeftBarSubHeader);

        JLabel textTwo = new JLabel("C O U R S E");
        textTwo.setFont(customFont.getFont(16f));
        textTwo.setForeground(new Color(202, 210, 197));
        textTwo.setSize(RelativeTo.relativeToWidth(0.2), RelativeTo.relativeToHeight(0.1));
        textTwo.setLocation(RelativeTo.relativeToWidth(0.03), RelativeTo.relativeToHeight(0.14));
        pane.setLayer(textTwo, backgroundLayer + 2);
        pane.add(textTwo);
    }

    // MODIFIES : this
    // EFFECTS : set up the left bar
    private void setCourseList() {
        if (textOneCourseList != null) {
            pane.remove(textOneCourseList);
        }

        String text = termList.get(termIndex).getCourseList().get(courseIndex).getName();
        textOneCourseList = new JLabel(text);
        textOneCourseList.setFont(customFont.getFont(16f));
        textOneCourseList.setForeground(new Color(202, 210, 197));
        textOneCourseList.setSize(RelativeTo.relativeToWidth(0.2), RelativeTo.relativeToHeight(0.1));
        textOneCourseList.setLocation(RelativeTo.relativeToWidth(0.09),RelativeTo.relativeToHeight(0.24));

        pane.setLayer(textOneCourseList, backgroundLayer + 2);
        pane.add(textOneCourseList);
    }

    // MODIFIES : this
    // EFFECTS : set up the left bar
    private void setCourseListCircle() {
        Circle circleOne = new Circle(RelativeTo.relativeToWidth(0.038),
                RelativeTo.relativeToHeight(0.28), 90, 180,
                RelativeTo.relativeToWidth(0.02), RelativeTo.relativeToWidth(0.02),
                new Color(132, 169, 140));
        circleOne.setSize(RelativeTo.relativeToWidth(1), RelativeTo.relativeToHeight(1));

        Circle circleTwo = new Circle(RelativeTo.relativeToWidth(0.043),
                RelativeTo.relativeToHeight(0.28), 90, -180,
                RelativeTo.relativeToWidth(0.02), RelativeTo.relativeToWidth(0.02),
                new Color(132, 169, 140));
        circleTwo.setSize(RelativeTo.relativeToWidth(1), RelativeTo.relativeToHeight(1));

        pane.setLayer(circleOne, backgroundLayer + 2);
        pane.setLayer(circleTwo, backgroundLayer + 2);
        pane.add(circleOne);
        pane.add(circleTwo);
    }

    // MODIFIES : this
    // EFFECTS : set up the left bar
    private void setCourseListButtonAdd() {
        JLabel text = new JLabel("Add Course");
        text.setFont(customFont.getFont(16f));
        text.setForeground(new Color(53, 79, 82));
        text.setSize(RelativeTo.relativeToWidth(0.09), RelativeTo.relativeToHeight(0.06));
        text.setLocation(RelativeTo.relativeToWidth(0.069), RelativeTo.relativeToHeight(0.415));
        pane.setLayer(text, backgroundLayer + 2);
        pane.add(text);

        JButton button = new JButton();
        button.setBackground(new Color(202, 210, 197));
        button.setOpaque(true);
        button.setBorderPainted(false);
        button.setFocusPainted(false);
        button.addActionListener(e -> new CoursePage(termList.get(termIndex)));
        button.setSize(RelativeTo.relativeToWidth(0.14), RelativeTo.relativeToHeight(0.04));
        button.setLocation(RelativeTo.relativeToWidth(0.022), RelativeTo.relativeToHeight(0.43));
        pane.setLayer(button, backgroundLayer + 2);
        pane.add(button);
    }

    // MODIFIES : this
    // EFFECTS : set up the left bar
    private void setCourseListButtonDelete() {
        JLabel text = new JLabel("Delete Course");
        text.setFont(customFont.getFont(16f));
        text.setForeground(new Color(202, 210, 197));
        text.setSize(RelativeTo.relativeToWidth(0.09), RelativeTo.relativeToHeight(0.06));
        text.setLocation(RelativeTo.relativeToWidth(0.065), RelativeTo.relativeToHeight(0.485));
        pane.setLayer(text, backgroundLayer + 2);
        pane.add(text);

        JButton button = new JButton();
        button.setBackground(new Color(82, 121, 111));
        button.setOpaque(true);
        button.setBorderPainted(false);
        button.setFocusPainted(false);
        button.addActionListener(e -> new DeleteCoursePage(termIndex));
        button.setSize(RelativeTo.relativeToWidth(0.14), RelativeTo.relativeToHeight(0.04));
        button.setLocation(RelativeTo.relativeToWidth(0.022), RelativeTo.relativeToHeight(0.50));
        pane.setLayer(button, backgroundLayer + 2);
        pane.add(button);
    }

    // MODIFIES : this
    // EFFECTS : set up the left bar
    private void setLeftBarButtonCourse() {
        JLabel text = new JLabel("Change Course");
        text.setFont(customFont.getFont(16f));
        text.setForeground(new Color(53, 79, 82));
        text.setSize(RelativeTo.relativeToWidth(0.09), RelativeTo.relativeToHeight(0.06));
        text.setLocation(RelativeTo.relativeToWidth(0.062), RelativeTo.relativeToHeight(0.555));
        pane.setLayer(text, backgroundLayer + 2);
        pane.add(text);

        JButton button = new JButton();
        button.setBackground(new Color(202, 210, 197));
        button.setOpaque(true);
        button.setBorderPainted(false);
        button.setFocusPainted(false);
        button.addActionListener(e -> new ChangeCoursePage(termIndex, this));
        button.setSize(RelativeTo.relativeToWidth(0.14), RelativeTo.relativeToHeight(0.04));
        button.setLocation(RelativeTo.relativeToWidth(0.022), RelativeTo.relativeToHeight(0.57));
        pane.setLayer(button, backgroundLayer + 2);
        pane.add(button);
    }

    // MODIFIES : this
    // EFFECTS : set up the left bar
    private void setLeftBarButtonChangeTerm() {
        JLabel text = new JLabel("Change Term");
        text.setFont(customFont.getFont(16f));
        text.setForeground(new Color(53, 79, 82));
        text.setSize(RelativeTo.relativeToWidth(0.09), RelativeTo.relativeToHeight(0.06));
        text.setLocation(RelativeTo.relativeToWidth(0.066), RelativeTo.relativeToHeight(0.765));
        pane.setLayer(text, backgroundLayer + 2);
        pane.add(text);

        JButton button = new JButton();
        button.setBackground(new Color(202, 210, 197));
        button.setOpaque(true);
        button.setBorderPainted(false);
        button.setFocusPainted(false);
        button.addActionListener(e -> new ChangeTermPage(this));
        button.setSize(RelativeTo.relativeToWidth(0.14), RelativeTo.relativeToHeight(0.04));
        button.setLocation(RelativeTo.relativeToWidth(0.022), RelativeTo.relativeToHeight(0.78));
        pane.setLayer(button, backgroundLayer + 2);
        pane.add(button);
    }

    // MODIFIES : this
    // EFFECTS : set up the left bar
    private void setLeftBarButtonAddTerm() {
        JLabel text = new JLabel("Add Term");
        text.setFont(customFont.getFont(16f));
        text.setForeground(new Color(53, 79, 82));
        text.setSize(RelativeTo.relativeToWidth(0.09), RelativeTo.relativeToHeight(0.06));
        text.setLocation(RelativeTo.relativeToWidth(0.072), RelativeTo.relativeToHeight(0.625));
        pane.setLayer(text, backgroundLayer + 2);
        pane.add(text);

        JButton button = new JButton();
        button.setBackground(new Color(202, 210, 197));
        button.setOpaque(true);
        button.setBorderPainted(false);
        button.setFocusPainted(false);
        button.addActionListener(e -> {
            courseIndex = 0;
            new TermPage(true);
        });
        button.setSize(RelativeTo.relativeToWidth(0.14), RelativeTo.relativeToHeight(0.04));
        button.setLocation(RelativeTo.relativeToWidth(0.022), RelativeTo.relativeToHeight(0.64));
        pane.setLayer(button, backgroundLayer + 2);
        pane.add(button);
    }

    // MODIFIES : this
    // EFFECTS : set up the left bar
    private void setLeftBarButtonDeleteTerm() {
        JLabel text = new JLabel("Delete Term");
        text.setFont(customFont.getFont(16f));
        text.setForeground(new Color(202, 210, 197));
        text.setSize(RelativeTo.relativeToWidth(0.09), RelativeTo.relativeToHeight(0.06));
        text.setLocation(RelativeTo.relativeToWidth(0.067), RelativeTo.relativeToHeight(0.695));
        pane.setLayer(text, backgroundLayer + 2);
        pane.add(text);

        JButton button = new JButton();
        button.setBackground(new Color(82, 121, 111));
        button.setOpaque(true);
        button.setBorderPainted(false);
        button.setFocusPainted(false);
        button.addActionListener(e -> new DeleteTermPage());
        button.setSize(RelativeTo.relativeToWidth(0.14), RelativeTo.relativeToHeight(0.04));
        button.setLocation(RelativeTo.relativeToWidth(0.022), RelativeTo.relativeToHeight(0.71));
        pane.setLayer(button, backgroundLayer + 2);
        pane.add(button);
    }

    // MODIFIES : this
    // EFFECTS : refresh the page
    private void refresh() {
        textField = new LinkedHashMap<>();
        setLeftBarSubHeader();
        setCourseList();
        setMainHeaderLeftText();
        setMainHeaderMiddleTextField();
        setMainComponents();
        setMainBar();
    }

    // code influenced by Sort a Map<K, V> by values
    // https://stackoverflow.com/questions/109383/sort-a-mapkey-value-by-values
    // EFFECTS : sort the list based on size
    private SimpleList<Integer> sortBasedOnSize(SimpleList<SimpleList<Grade>> gradeList) {
        SimpleList<Integer> index = new SimpleList<>();
        Map<Integer, Integer> indexSize = new LinkedHashMap<>();

        int size = gradeList.size();
        for (int i = 0; i < size; i++) {
            indexSize.put(i, gradeList.get(i).size());
        }

        Map<Integer, Integer> sortedSize = indexSize.entrySet().stream()
                .sorted(Map.Entry.comparingByValue())
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue, (e1, e2) -> e1, LinkedHashMap::new));

        Set<Integer> sorted = sortedSize.keySet();

        for (Integer i : sorted) {
            index.add(i);
        }

        return index;
    }

    // code influenced by how to generate light and pastel colors randomly in android
    // https://stackoverflow.com/questions/30703652/how-to-generate-light-and-pastel-colors-randomly-in-android
    // EFFECTS : generate random pastel colors
    private SimpleList<Color> randomColor(int size) {
        SimpleList<Color> colors = new SimpleList<>();
        int min = 0;
        int step = 255 / size;
        int max = step;
        int[] base = {202, 210, 197};
        for (int i = 1; i <= size; i++) {
            int[] rgb = {-1, -1, -1};
            for (int j = 0; j < 3; j++) {
                while (rgb[j] < min) {
                    rgb[j] = new Random().nextInt(max);
                }
                rgb[j] = ((rgb[j] + base[j]) / 2) % 256;
            }
            min = max;
            max = (i + 1) * step;
            colors.add(new Color(rgb[0], rgb[1], rgb[2]));
        }
        return colors;
    }

    // EFFECTS : get target from textfield
    @SuppressWarnings({"checkstyle:MethodLength", "checkstyle:SuppressWarnings"})
    private void getGradesFromComponent() {
        SimpleList<SimpleList<Grade>> gradeList = profile.getTermList().get(termIndex)
                .getCourseList().get(courseIndex).getComponents().getGradeList();

        SimpleList<String> nameList = profile.getTermList().get(termIndex).getCourseList().get(courseIndex)
                .getComponents().getNameList();

        Set<String> keys = textField.keySet();

        for (String str : keys) {
            int index = 0;
            for (int j = 0; j < nameList.size(); j++) {
                if (str.contains(nameList.get(j))) {
                    index = j;
                    break;
                }
            }
            int componentIndex = Integer.parseInt(str.substring(str.indexOf("(") + 1, str.indexOf(")")));
            if (!textField.get(str).getText().equals("Out of 100")) {
                checkNotNumberWarning(str);
                checkInvalidNumberWarning(str);
                if (isValidSave()) {
                    float toReal = Float.parseFloat(textField.get(str).getText()) / 100
                            * gradeList.get(index).get(componentIndex).getPercentage();
                    gradeList.get(index).get(componentIndex).setReal(toReal);
                    gradeList.get(index).get(componentIndex).setIsEmpty(false);
                }
            }
        }
    }

    // EFFECTS : check warning for grades
    private void checkNotNumberWarning() {
        try {
            Float.parseFloat(targetField.getText());
            validSave[0] = true;
        } catch (Exception e) {
            flag++;
            validSave[0] = false;
            new NotNumberWarning("Target");
        }
    }

    // EFFECTS : check warning for grades
    private void checkNotNumberWarning(String str) {
        try {
            Float.parseFloat(textField.get(str).getText());
            validSave[0] = true;
        } catch (Exception e) {
            flag++;
            validSave[0] = false;
            new NotNumberWarning("Grades");
        }
    }

    // EFFECTS : check warning for grades
    private void checkInvalidNumberWarning() {
        try {
            float target = Float.parseFloat(targetField.getText());
            invalidNumber(target);
            validSave[1] = true;
        } catch (Exception e) {
            flag++;
            validSave[1] = false;
            new InvalidNumberWarning("Target");
        }
    }


    // EFFECTS : check warning for grades
    private void checkInvalidNumberWarning(String str) {
        try {
            invalidNumber(Float.parseFloat(textField.get(str).getText()));
            validSave[1] = true;
        } catch (Exception e) {
            flag++;
            validSave[1] = false;
            new InvalidNumberWarning("Grades");
        }
    }

    // EFFECTS : get target from textfield
    private void getTargetFromComponent() {
        if (!targetField.getText().equals("")) {
            checkNotNumberWarning();
            checkInvalidNumberWarning();
            if (isValidSave()) {
                float target = Float.parseFloat(targetField.getText());
                profile.getTermList().get(termIndex).getCourseList().get(courseIndex).setTarget(target);
            }
        }
    }

    // EFFECTS : put the textfield on component
    private void putTextField(JPanel panel, Color color, Grade grade, String name, int j) {
        String key = name  + "(" + j + ")";
        JTextField field = new JTextField();
        if (!grade.getIsEmpty()) {
            float toPercent = grade.getReal() / grade.getPercentage() * 100;
            field.setText(String.format("%.2f", toPercent));
        } else {
            field.setText("Out of 100");
        }
        field.setFont(customFont.getFont(18f));
        field.setForeground(new Color(47, 62, 70));
        field.setBackground(color);
        field.setHorizontalAlignment(SwingConstants.CENTER);
        field.setBorder(BorderFactory.createEmptyBorder());
        field.setSize(RelativeTo.relativeToWidth(0.08), RelativeTo.relativeToHeight(0.02));
        panel.add(field);
        textField.put(key, field);
    }

    // EFFECTS : check if the input is between 0 and 100
    private void invalidNumber(float f) throws InvalidNumberException {
        if (f < 0 || f > 100) {
            throw new InvalidNumberException();
        }
    }

    // EFFECTS : check if it is valid to continue
    private boolean isValidSave() {
        return validSave[0] && validSave[1];
    }
}
