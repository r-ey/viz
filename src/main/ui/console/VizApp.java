package ui.console;

import model.component.*;
import model.exception.*;
import model.util.SimpleList;
import model.util.Week;
import persistence.JsonReader;
import persistence.JsonWriter;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

// driver for the application
public class VizApp {

    private static final String PATH = "./data/storage.json";
    private Profile profile;
    private SimpleList<Term> termList;
    private int weeks;
    private int gradeSize;

    // EFFECTS : creating the whole application
    public VizApp() {
        termList = new SimpleList<>();
        run();
    }

    // EFFECTS : running all methods
    public void run() {
        if (newUser()) {
            welcomePrompt();
        } else {
            loadExistingFile();
        }
        loop();
    }

    // EFFECTS : looping the menu until end
    private void loop() {
        while (true) {
            int menu = menu();
            if (menu == 1) {
                System.out.println(profile);
            } else if (menu == 2) {
                if (displayTerms() == 1) {
                    displayAllTerms();
                } else {
                    displayOneTerm(selectTerm());
                }
            } else if (menu == 3) {
                addTerm();
            } else if (menu == 4) {
                deleteTerms(selectTerm());
            } else if (menu == 5) {
                addCourse(termList.get(selectTerm()).getCourseList());
            } else if (menu == 6) {
                saveToStorage();
            } else {
                System.exit(0);
            }
        }
    }

    // EFFECTS : ask if new user
    private boolean newUser() {
        Scanner scan = new Scanner(System.in);
        System.out.println("Welcome to Viz!\n");
        System.out.print("Are you a new user [y/n] : ");
        return scan.nextLine().equals("y");
    }

    // EFFECTS : load the existing json file
    private void loadExistingFile() {
        JsonReader reader = new JsonReader(PATH);
        try {
            profile = reader.read().getProfile();
            termList = profile.getTermList();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        System.out.println("Loaded Success, Welcome " + profile.getName());
    }

    // EFFECTS : save the entire state of the application
    private void saveToStorage() {
        JsonWriter writer = new JsonWriter(PATH);
        try {
            writer.open();
            writer.write(new Storage(profile));
            writer.close();
            System.out.println("Saved " + profile.getName() + "'s Academic Journey");
        } catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
        }
    }

    // EFFECTS : printing the welcome prompt
    private void welcomePrompt() {
        Scanner scan = new Scanner(System.in);
        System.out.print("Enter your name : ");
        String name = scan.nextLine();
        addTerm();
        profile = new Profile(termList, name);
    }

    // EFFECTS : get and check start date from user input
    private String getDateWeek() {
        Scanner scan = new Scanner(System.in);
        String date = null;
        while (date == null) {
            try {
                System.out.print("Starting Date of school (yyyy-mm-dd) : ");
                date = scan.next();
                checkDateInput(date);
            } catch (DateFormatException df) {
                System.out.println(df.getMessage());
                date = null;
            } catch (NoDateExistException nd) {
                System.out.println(nd.getMessage());
                date = null;
            } catch (DateMonthYearZeroException dz) {
                System.out.println(dz.getMessage());
                date = null;
            } catch (NoMonthExistException nm) {
                System.out.println(nm.getMessage());
                date = null;
            }
        }
        return date;
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

    // REQUIRES : start is in format of yyyy-mm-dd
    // MODIFIES : this
    // EFFECTS : get week from start date until now
    private void getWeek(String start, String end) {
        Week week = new Week();
        week.searchWeek(start, end);
        weeks = week.getWeek();
    }

    // EFFECTS : check and return the options
    private int getOptionsInput(int max) {
        Scanner scan = new Scanner(System.in);
        String option = null;
        while (option == null) {
            try {
                option = scan.nextLine();
                checkOptions(option, max);
            } catch (OptionsOutOfBoundsException o) {
                System.out.println(o.getMessage());
                option = null;
            } catch (NotIntegerException n) {
                System.out.println(n.getMessage());
                option = null;
            }
        }
        return Integer.parseInt(option);
    }

    // EFFECTS : check if option is between 0-max or not
    private void checkOptions(String s, int max) throws OptionsOutOfBoundsException, NotIntegerException {
        char[] string = s.toCharArray();

        if (s.length() < 1 || string[0] - '0' < 0 || string[0] - '0' > 9) {
            throw new NotIntegerException(s);
        }
        if (string[0] - '0' > max) {
            throw new OptionsOutOfBoundsException(0, max, Integer.parseInt(s));
        }
    }

    // REQUIRES : 0 < input < 8
    // EFFECTS : printing the menu and get menu options
    private int menu() {
        System.out.println("\n----------------MENU----------------");
        System.out.println("1. Display profile");
        System.out.println("2. Display terms");
        System.out.println("3. Add terms");
        System.out.println("4. Delete terms");
        System.out.println("5. Add courses");
        System.out.println("6. Save");
        System.out.println("7. Exit");
        System.out.print("Options : ");
        return getOptionsInput(8);
    }

    // REQUIRES : 0 < input < 3
    // EFFECTS : get options to display all terms or just one term
    private int displayTerms() {
        System.out.println("\n----------------DISPLAY TERMS----------------");
        System.out.println("1. Display all term(s)");
        System.out.println("2. Display one term");
        System.out.print("Options : ");
        return getOptionsInput(3);
    }

    // REQUIRES : 0 < input < termList.size()
    // EFFECTS : get the selected term index
    private int selectTerm() {
        int size = termList.size();
        System.out.println("\n----------------SELECT TERM----------------");
        for (int i = 0; i < size; i++) {
            System.out.println((i + 1) + ". " + termList.get(i).getTermName());
        }
        System.out.print("Options : ");
        return getOptionsInput(size) - 1;
    }

    // REQUIRES : 0 < i < termList.size()
    // EFFECTS : print the selected term
    private void displayOneTerm(int i)  {
        System.out.println("\n----------------" + termList.get(i).getTermName() + "----------------");
        System.out.println(termList.get(i));
    }

    // EFFECTS : print all the terms
    private void displayAllTerms() {
        int size = termList.size();
        for (int i = 0; i < size; i++) {
            System.out.println("\n----------------" + termList.get(i).getTermName() + "----------------");
            System.out.println(termList.get(i));
            System.out.println("--------------------------------");
        }
    }

    // MODIFIES : this
    // EFFECTS : adding a term to termList
    private void addTerm() {
        Scanner scan = new Scanner(System.in);
        String name;

        System.out.println("\n----------------ADD TERM----------------");
        System.out.print("Term name : ");
        name = scan.nextLine();

        String startDate = getDateWeek();
        LocalDate now = LocalDate.now();
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        String format = now.format(dtf);
        getWeek(startDate, format);

        SimpleList<Course> courseList = new SimpleList<>();
        addCourse(courseList);
        termList.add(new Term(courseList, name, weeks));
    }

    // REQUIRES : 0 < i < termList.size() and termList.size() > 0
    // MODIFIES : this
    // EFFECTS : delete the selected term
    private void deleteTerms(int i) {
        String name = termList.get(i).getTermName();
        termList.delete(i);
        System.out.println("\n----------------" + name + " has been deleted successfully----------------");
    }

    // MODIFIES : this
    // EFFECTS : adding a course to courseList
    private void addCourse(SimpleList<Course> courseList) {
        Scanner scan = new Scanner(System.in);
        Scanner scan2 = new Scanner(System.in);
        float target;
        String name;
        System.out.println("\n----------------ADD COURSE----------------");
        System.out.print("Course name : ");
        name = scan.nextLine();

        System.out.print("Grade target for " + name + " : ");
        target = scan2.nextFloat();
        setUpCourse(name, target, courseList);
    }

    // REQUIRES : answer equals to "y" or "n"
    // EFFECTS : return grade component name
    private SimpleList<String> addGradeName() {
        SimpleList<String> nameList = new SimpleList<>();
        String answer = "y";
        System.out.print("\n----------------ADD GRADE COMPONENT NAME----------------");
        while (answer.equals("y")) {
            Scanner scan = new Scanner(System.in);
            System.out.print("\nAdd grade components [y/n] ? ");
            answer = scan.nextLine();
            if (answer.equals("y")) {
                Scanner scan2 = new Scanner(System.in);
                System.out.print("\nGrade component name : ");
                String name = scan2.nextLine();
                nameList.add(name);
                System.out.println("\n----------------" + name + " has been added successfully----------------");
            }
        }
        gradeSize = nameList.size();
        return nameList;
    }

    // REQUIRES : 0 < percentage < 100, size > 0, answer is either "y" or "n", and 0 <= grade <= 100
    // EFFECTS : return grade component list
    private SimpleList<Grade> addGradeList(String name) {
        Scanner scan = new Scanner(System.in);
        Scanner scan2 = new Scanner(System.in);
        SimpleList<Grade> gradeList = new SimpleList<>();
        System.out.println("\n----------------Add grade for " + name + "----------------");
        System.out.print("Total percentage for " + name + " is : ");
        float percentage = scan.nextFloat();
        System.out.print("\nHow many " + name + " are there : ");
        int size = scan2.nextInt();
        for (int i = 0; i < size; i++) {
            Scanner scan3 = new Scanner(System.in);
            System.out.print("Is " + name + " " + (i + 1) + " empty [y/n] ? ");
            String answer = scan3.next();
            if (answer.equals("y")) {
                gradeList.add(new Grade(percentage / size, 0f, true));
            } else {
                Scanner scan4 = new Scanner(System.in);
                System.out.print("Grade of " + name + " " + (i + 1) + " is : ");
                float grade = scan4.nextFloat();
                gradeList.add(new Grade(percentage / size, grade / 100 * percentage / size, false));
            }
        }
        return gradeList;
    }

    // REQUIRES : n and t aren't null
    // MODIFIES : this
    // EFFECTS : setting up course grade component
    private void setUpCourse(String n, float t, SimpleList<Course> courseList) {
        SimpleList<String> gradeName = addGradeName();
        SimpleList<SimpleList<Grade>> gradeList = new SimpleList<>();

        for (int i = 0; i < gradeSize; i++) {
            gradeList.add(addGradeList(gradeName.get(i)));
        }

        GradeComponents components = new GradeComponents(gradeName, gradeList);
        courseList.add(new Course(n, t, components));
    }
}
