package model.component;

import model.util.SimpleList;
import org.json.JSONArray;
import org.json.JSONObject;

// to store data to JSON
public class Storage {
    private final Profile profile;

    // EFFECTS : holder to JSON Object
    public Storage(Profile p) {
        profile = p;
    }

    public Profile getProfile() {
        return profile;
    }

    // EFFECTS : create JSON Object
    public JSONObject toJson() {
        JSONObject obj = new JSONObject();
        obj.put("profile name", profile.getName());
        termToJson(obj);
        return obj;
    }

    // EFFECTS : transform term to JSON Object
    private void termToJson(JSONObject obj) {
        JSONObject term = new JSONObject();
        SimpleList<Term> termList = profile.getTermList();
        int size = termList.size();
        for (int i = 0; i < size; i++) {
            term.put(termList.get(i).getTermName(), courseToJson(termList.get(i).getCourseList(),
                    termList.get(i).getWeek()));
        }
        obj.put("term list", term);
    }

    // EFFECTS : transform course to JSON Object
    private JSONObject courseToJson(SimpleList<Course> courseList, int week) {
        JSONObject course = new JSONObject();
        int size = courseList.size();
        for (int i = 0; i < size; i++) {
            if (i == 0) {
                course.put("week", week);
            }
            course.put(courseList.get(i).getName(), componentToJson(courseList.get(i).getComponents(),
                    courseList.get(i).getTarget()));
        }
        return course;
    }

    // EFFECTS : transform component to JSON Object
    private JSONObject componentToJson(GradeComponents components, float target) {
        JSONObject gradeComponents = new JSONObject();
        SimpleList<String> nameList = components.getNameList();
        SimpleList<SimpleList<Grade>> gradeList = components.getGradeList();
        gradeComponents.put("components", nameToJson(nameList, gradeList));
        gradeComponents.put("target", target);
        return gradeComponents;
    }

    // EFFECTS : transform course name to JSON Object
    private JSONObject nameToJson(SimpleList<String> nameList, SimpleList<SimpleList<Grade>> gradeList) {
        JSONObject name = new JSONObject();
        int size = nameList.size();
        for (int i = 0; i < size; i++) {
            name.put(nameList.get(i), gradeListToJson(gradeList.get(i)));
        }
        return name;
    }

    // EFFECTS : transform grade to JSON Array
    private JSONArray gradeListToJson(SimpleList<Grade> gradeList) {
        JSONArray grade = new JSONArray();
        int size = gradeList.size();
        for (int i = 0; i < size; i++) {
            grade.put(gradeList.get(i));
        }
        return grade;
    }
}
