package persistence;

import model.component.*;
import model.log.Event;
import model.log.EventLog;
import model.util.SimpleList;
import model.util.Week;
import ui.gui.util.TextToSpeech;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

// class for reading and writing JSON
// also for temp storage
public final class LoadJson {

    private static final String PATH = "./data/storage.json";
    private static Profile profile;

    private static String profileName;
    private static String termName;
    private static Course course;
    private static String startWeek;

    private static float totalTemp;

    // EFFECTS : loading the profile from storage
    public static void loadExistingFile() {
        JsonReader reader = new JsonReader(PATH);
        try {
            profile = reader.read().getProfile();
            EventLog.getInstance().logEvent(new Event("Load profile of " + profile.getName() + "\n"));
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println(e.getMessage());
        }
        TextToSpeech.speak("Loaded Success, welcome " + profile.getName());
    }

    // EFFECTS : saving the profile to storage
    public static void saveToStorage() {
        JsonWriter writer = new JsonWriter(PATH);
        try {
            writer.open();
            writer.write(new Storage(profile));
            writer.close();
            EventLog.getInstance().logEvent(new Event("Saved profile of " + profile.getName() + "\n"));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static Profile getProfile() {
        return profile;
    }

    public static float getTotalTemp() {
        return totalTemp;
    }

    public static void setProfileName(String s) {
        profileName = s;
    }

    public static void setTermName(String s) {
        termName = s;
    }

    public static void setTermWeek(String s) {
        startWeek = s;
    }

    public static void setTotalTemp(float p) {
        totalTemp = p;
    }

    public static void setCourse(Course c) {
        course = c;
        setUpProfile();
        saveToStorage();
    }

    // MODIFIES : this
    // EFFECTS : set up profile for saving data
    private static void setUpProfile() {
        LocalDate now = LocalDate.now();
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        String format = now.format(dtf);
        Week week = new Week();
        week.searchWeek(startWeek, format);
        SimpleList<Term> termList = new SimpleList<>();
        SimpleList<Course> courseList = new SimpleList<>();
        courseList.add(course);
        termList.add(new Term(courseList, termName, week.getWeek()));
        profile = new Profile(termList, profileName);
    }

    // MODIFIES : this
    // EFFECTS : add term to profile
    public static Term addTerm(String name, String start) {
        LocalDate now = LocalDate.now();
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        String format = now.format(dtf);
        Week week = new Week();
        week.searchWeek(start, format);
        SimpleList<Term> termList = profile.getTermList();
        Term term = new Term(new SimpleList<>(), name, week.getWeek());
        termList.add(term);
        EventLog.getInstance().logEvent(new Event(profile.getName() + " add " + name + " term\n"));
        return term;
    }

    // MODIFIES : this
    // EFFECTS : add course to profile
    public static void addCourse(Course c, Term t) {
        SimpleList<Term> termList = profile.getTermList();
        int size = termList.size();
        for (int i = 0; i < size; i++) {
            if (termList.get(i) == t) {
                termList.get(i).getCourseList().add(c);
                break;
            }
        }
        EventLog.getInstance().logEvent(new Event(profile.getName() + " add " + c.getName() + " to "
                + t.getTermName() + " term\n"));
        saveToStorage();
    }

    // MODIFIES : this
    // EFFECTS : delete course from profile
    public static void deleteCourse(int t, int c) {
        SimpleList<Term> termList = profile.getTermList();
        EventLog.getInstance().logEvent(new Event(profile.getName() + " delete "
                + termList.get(t).getCourseList().get(c).getName() + " in "
                + termList.get(t).getTermName() + " term\n"));
        termList.get(t).getCourseList().delete(c);
        saveToStorage();
    }

    // MODIFIES : this
    // EFFECTS : delete term from profile
    public static void deleteTerm(int t) {
        SimpleList<Term> termList = profile.getTermList();
        EventLog.getInstance().logEvent(new Event(profile.getName() + " delete "
                + termList.get(t).getTermName() + " term\n"));
        termList.delete(t);
        saveToStorage();
    }

    // EFFECTS : print all the logs
    public static void printLog() {
        for (Event e : EventLog.getInstance()) {
            System.out.println(e);
        }
    }
}
