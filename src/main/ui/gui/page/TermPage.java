package ui.gui.page;

import model.exception.DateFormatException;
import model.exception.DateMonthYearZeroException;
import model.exception.NoDateExistException;
import model.exception.NoMonthExistException;
import ui.gui.component.Arrow;
import ui.gui.component.Rectangle;
import ui.gui.util.CustomFont;
import ui.gui.util.LoadImage;
import persistence.LoadJson;
import ui.gui.util.RelativeTo;
import ui.gui.warning.DateFormatWarning;
import ui.gui.warning.DateMonthYearZeroWarning;
import ui.gui.warning.NoDateExistWarning;
import ui.gui.warning.NoMonthExistWarning;

import javax.swing.*;
import java.awt.*;

// create the term page
public class TermPage extends JFrame {

    private final CustomFont customFont;
    private static JLayeredPane pane;
    private JTextField fieldName;
    private JTextField fieldWeek;

    private static int backgroundLayer;
    private final boolean addTerm;

    // EFFECTS : creating the term page
    public TermPage() {
        customFont = new CustomFont();
        addTerm = false;
        setPane();
        setBackground();
        setHeader();
        setPicture();
        setTextFieldName();
        setTextFieldWeek();
        setCredits();
        setArrow();
    }

    // EFFECTS : creating the term page
    public TermPage(boolean add) {
        customFont = new CustomFont();
        addTerm = add;
        setPane();
        setBackground();
        setHeader();
        setPicture();
        setTextFieldName();
        setTextFieldWeek();
        setCredits();
        setArrow();
    }

    // MODIFIES : this
    // EFFECTS : set up the frame and pane
    private void setPane() {
        pane = new JLayeredPane();
        pane.setLayout(null);
        pane.setSize(RelativeTo.relativeToHeight(0.71), RelativeTo.relativeToHeight(0.77));
        add(pane);

        setTitle("Term");
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
        JLabel text = new JLabel("Set Up Term");
        text.setFont(customFont.getFont(120f));
        text.setForeground(new Color(202, 210, 197, 153));
        text.setSize(RelativeTo.relativeToWidth(0.56), RelativeTo.relativeToHeight(0.2));
        text.setLocation(RelativeTo.relativeToWidth(0.166), RelativeTo.relativeToHeight(-0.05));
        pane.setLayer(text, backgroundLayer + 1);
        pane.add(text);
    }

    // MODIFIES : this
    // EFFECTS : set up the picture
    private void setPicture() {
        JLabel pict = LoadImage.getImageMap().get("apple");
        pict.setBounds(RelativeTo.relativeToWidth(0), RelativeTo.relativeToHeight(-0.0005),
                RelativeTo.relativeToWidth(0.7), RelativeTo.relativeToHeight(0.7));
        pane.setLayer(pict, backgroundLayer + 1);
        pane.add(pict);
    }

    // MODIFIES : this
    // EFFECTS : set up the textfield
    private void setTextFieldName() {
        Rectangle rect = new Rectangle(RelativeTo.relativeToWidth(-0.1), RelativeTo.relativeToHeight(0.55),
                RelativeTo.relativeToWidth(0.8), RelativeTo.relativeToHeight(0.12),
                new Color(202, 210, 197, 51));
        rect.setSize(RelativeTo.relativeToWidth(0.75), RelativeTo.relativeToHeight(0.8));
        pane.setLayer(rect, backgroundLayer + 1);
        pane.add(rect);

        JLabel text = new JLabel("Name");
        text.setFont(customFont.getFont(26f));
        text.setForeground(new Color(47, 62, 70));
        text.setSize(RelativeTo.relativeToWidth(0.2), RelativeTo.relativeToHeight(0.1));
        text.setLocation(RelativeTo.relativeToWidth(0.19), RelativeTo.relativeToHeight(0.535));
        pane.setLayer(text, backgroundLayer + 2);
        pane.add(text);

        fieldName = new JTextField();
        fieldName.setFont(customFont.getFont(12f));
        fieldName.setForeground(new Color(47, 62, 70));
        fieldName.setBackground(new Color(53, 79, 82, 127));
        fieldName.setSize(RelativeTo.relativeToWidth(0.26), RelativeTo.relativeToHeight(0.03));
        fieldName.setLocation(RelativeTo.relativeToWidth(0.08), RelativeTo.relativeToHeight(0.62));
        pane.setLayer(fieldName, backgroundLayer + 2);
        pane.add(fieldName);
    }

    // MODIFIES : this
    // EFFECTS : set up the textfield
    private void setTextFieldWeek() {
        JLabel text = new JLabel("Week Start");
        text.setFont(customFont.getFont(26f));
        text.setForeground(new Color(47, 62, 70));
        text.setSize(RelativeTo.relativeToWidth(0.2), RelativeTo.relativeToHeight(0.1));
        text.setLocation(RelativeTo.relativeToWidth(0.465), RelativeTo.relativeToHeight(0.535));
        pane.setLayer(text, backgroundLayer + 2);
        pane.add(text);

        fieldWeek = new JTextField("YYYY-MM-DD format");
        fieldWeek.setFont(customFont.getFont(12f));
        fieldWeek.setForeground(new Color(47, 62, 70));
        fieldWeek.setBackground(new Color(53, 79, 82, 127));
        fieldWeek.setSize(RelativeTo.relativeToWidth(0.26), RelativeTo.relativeToHeight(0.03));
        fieldWeek.setLocation(RelativeTo.relativeToWidth(0.38), RelativeTo.relativeToHeight(0.62));
        pane.setLayer(fieldWeek, backgroundLayer + 2);
        pane.add(fieldWeek);
    }

    // MODIFIES : this
    // EFFECTS : set up the credits
    private void setCredits() {
        JLabel text = new JLabel("Image AI Generated | Fotor");
        text.setFont(customFont.getFont(20f));
        text.setForeground(new Color(47, 62, 70));
        text.setSize(RelativeTo.relativeToWidth(0.27), RelativeTo.relativeToHeight(0.14));
        text.setLocation(RelativeTo.relativeToWidth(0.54), RelativeTo.relativeToHeight(0.62));
        pane.setLayer(text, backgroundLayer + 1);
        pane.add(text);
    }

    // MODIFIES : this
    // EFFECTS : set up the arrow
    private void setArrow() {
        Arrow arrow = new Arrow(RelativeTo.relativeToWidth(0.61), RelativeTo.relativeToHeight(0.6365),
                RelativeTo.relativeToWidth(0.63), RelativeTo.relativeToHeight(0.6365), 2,
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
        button.setLocation(RelativeTo.relativeToWidth(0.565), RelativeTo.relativeToHeight(0.618));
        button.addActionListener(e -> checkWarning());
        pane.setLayer(button, backgroundLayer + 4);
        pane.add(button);
    }

    // EFFECTS : check all the possible problem in input
    private void checkDateInput(String s) throws DateFormatException,
            NoDateExistException, NoMonthExistException, DateMonthYearZeroException {
        checkDateFormat(s);
        checkDateMonth(s);
    }

    // EFFECTS : check if the string is in yyyy-mm-dd format or not
    private void checkDateFormat(String s) throws DateFormatException {
        char[] string = s.toCharArray();

        if (string.length != 10 || string[4] != '-' || string[7] != '-' || string[0] - '0' < 0 || string[0] - '0' > 9
                || string[1] - '0' < 0 || string[1] - '0' > 9 || string[2] - '0' < 0 || string[2] - '0' > 9
                || string[3] - '0' < 0 || string[3] - '0' > 9 || string[5] - '0' < 0 || string[5] - '0' > 9
                || string[6] - '0' < 0 || string[6] - '0' > 9 || string[8] - '0' < 0 || string[8] - '0' > 9
                || string[9] - '0' < 0 || string[9] - '0' > 9) {
            throw new DateFormatException(s);
        }
    }

    // EFFECTS : check if the string month and date is correct or not
    private void checkDateMonth(String s) throws NoDateExistException,
            NoMonthExistException, DateMonthYearZeroException {
        char[] string = s.toCharArray();
        int year = getYear(string);
        int month = getMonth(string);
        int date = getDate(string);
        int[] maxDate = {31, (isLeapYear(year)) ? 29 : 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};

        if (year == 0 || month == 0 || date == 0) {
            throw new DateMonthYearZeroException();
        }
        if (month > 12) {
            throw new NoMonthExistException(month);
        }
        if (date > maxDate[month - 1]) {
            throw new NoDateExistException(month, date);
        }
    }

    // EFFECTS : get date from string
    private int getDate(char[] c) {
        return (c[8] - '0') * 10 + (c[9] - '0');
    }

    // EFFECTS : get month from string
    private int getMonth(char[] c) {
        return (c[5] - '0') * 10 + (c[6] - '0');
    }

    // EFFECTS : get year from string
    private int getYear(char[] c) {
        return (c[0] - '0') * 1000 + (c[1] - '0') * 100 + (c[2] - '0') * 10 + (c[3] - '0');
    }

    // EFFECTS : check if the year is leap year or not
    private boolean isLeapYear(int year) {
        return year % 4 == 0;
    }

    // EFFECTS : check for warning
    private void checkWarning() {
        try {
            checkDateInput(fieldWeek.getText());
            String name = firstCharCaps(fieldName.getText());
            if (addTerm) {
                new CoursePage(LoadJson.addTerm(name, fieldWeek.getText()));
            } else {
                LoadJson.setTermName(name);
                LoadJson.setTermWeek(fieldWeek.getText());
                new CoursePage();
            }
            this.dispose();
        } catch (DateFormatException df) {
            new DateFormatWarning();
        } catch (NoDateExistException nd) {
            new NoDateExistWarning();
        } catch (NoMonthExistException nm) {
            new NoMonthExistWarning();
        } catch (DateMonthYearZeroException dm) {
            new DateMonthYearZeroWarning();
        }
    }

    // EFFECTS : make first character a capital letter
    private String firstCharCaps(String s) {
        return s.substring(0, 1).toUpperCase() + s.substring(1).toLowerCase();
    }

}
