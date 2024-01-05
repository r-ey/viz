package persistence;

import model.component.*;
import model.util.SimpleList;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Set;
import java.util.stream.Stream;

// Represents a reader that reads workroom from JSON data stored in file
public class JsonReader {
    private final String source;

    // EFFECTS: constructs reader to read from source file
    public JsonReader(String s) {
        source = s;
    }

    // EFFECTS: reads storage from file and returns it;
    // throws IOException if an error occurs reading data from file
    public Storage read() throws IOException {
        String jsonData = readFile(source);
        return parseStorage(new JSONObject(jsonData));
    }

    // EFFECTS: reads source file as string and returns it
    private String readFile(String source) throws IOException {
        StringBuilder contentBuilder = new StringBuilder();

        try (Stream<String> stream = Files.lines(Paths.get(source), StandardCharsets.UTF_8)) {
            stream.forEach(contentBuilder::append);
        }

        return contentBuilder.toString();
    }

    // EFFECTS: parses storage from JSON object and returns it
    private Storage parseStorage(JSONObject jsonObject) {
        return new Storage(toProfile(jsonObject));
    }

    // EFFECTS : create profile from JSON
    private Profile toProfile(JSONObject obj) {
        return new Profile(getTermList(obj), getProfileName(obj));
    }

    // EFFECTS : get profile name from JSON
    private String getProfileName(JSONObject obj) {
        return (String)obj.get("profile name");
    }

    // EFFECTS : get list of terms from JSON
    private SimpleList<Term> getTermList(JSONObject obj) {
        SimpleList<Term> termList = new SimpleList<>();
        JSONObject term = obj.getJSONObject("term list");
        Set<String> termName = term.keySet();

        for (String s : termName) {
            termList.add(new Term(getCourseList(term.getJSONObject(s)), s, getWeek(term.getJSONObject(s))));
        }
        return termList;
    }

    // EFFECTS : getting week from JSON
    private int getWeek(JSONObject obj) {
        return (Integer)obj.get("week");
    }

    // EFFECTS : getting list of courses from JSON string
    private SimpleList<Course> getCourseList(JSONObject obj) {
        SimpleList<Course> courseList = new SimpleList<>();
        Set<String> courseName = obj.keySet();

        for (String s : courseName) {
            if (!s.equals("week")) {
                courseList.add(new Course(s, getTarget(obj.getJSONObject(s)),
                        getGradeComponents(obj.getJSONObject(s))));
            }
        }
        return courseList;
    }

    // EFFECTS : get target from JSON
    private float getTarget(JSONObject obj) {
        return Float.parseFloat(String.valueOf(obj.get("target")));
    }

    // EFFECTS : get grade components from JSON
    private GradeComponents getGradeComponents(JSONObject obj) {
        JSONObject gradeComponents = obj.getJSONObject("components");
        SimpleList<String> nameList = new SimpleList<>();
        SimpleList<SimpleList<Grade>> gradeList = new SimpleList<>();
        Set<String> nameSet = gradeComponents.keySet();
        for (String s : nameSet) {
            nameList.add(s);
            gradeList.add(getGradeList(gradeComponents.getJSONArray(s)));
        }
        return new GradeComponents(nameList, gradeList);
    }

    // EFFECTS : get list of grades from JSON
    private SimpleList<Grade> getGradeList(JSONArray obj) {
        SimpleList<Grade> gradeList = new SimpleList<>();
        int size = obj.length();
        for (int i = 0; i < size; i++) {
            String gradeString = obj.get(i).toString();
            String percentageString = gradeString.substring(gradeString.indexOf("Percentage : ") + 13,
                    gradeString.indexOf(", "));
            String realString = gradeString.substring(gradeString.indexOf("Real : ") + 7, gradeString.indexOf("\n"));

            float percentage = Float.parseFloat(percentageString);
            float real;
            boolean isEmpty = false;

            if (gradeString.contains("Real")) {
                real = Float.parseFloat(realString);
            } else {
                isEmpty = true;
                real = 0;
            }

            gradeList.add(new Grade(percentage, real, isEmpty));
        }
        return gradeList;
    }
}
