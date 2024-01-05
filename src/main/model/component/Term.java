package model.component;

import model.util.SimpleList;

// constructing term class to store course information
public class Term {

    private final SimpleList<Course> courseList;
    private final String termName;
    private final int week;

    // REQUIRES : c and t aren't null
    // EFFECTS : make a term with list of courses
    public Term(SimpleList<Course> c, String t, int w) {
        courseList = c;
        termName = t;
        week = w;
    }

    public int getWeek() {
        return week;
    }

    public String getTermName() {
        return termName;
    }

    public SimpleList<Course> getCourseList() {
        return courseList;
    }

    // Overriding method for printing Course
    // EFFECTS : make string of a Term object
    @Override
    public String toString() {
        String course = "";
        for (int i = 0; i < courseList.size(); i++) {
            course = course.concat(courseList.get(i).toString());
            try {
                course = course.concat(courseList.get(i).currentCondition());
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
        return "Term : " + termName + "\nWeek : " + week + "\n--------------------------------\n" + course;
    }
}
