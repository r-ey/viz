package model.component;

import model.component.analytic.Analytics;
import model.component.analytic.GreedyAnalytics;
import model.component.analytic.RegularAnalytics;
import model.util.SimpleList;
import persistence.LoadJson;

import java.util.concurrent.ExecutionException;

// Constructing a course class to store its grading component, grades, name, week, and target
public class Course {

    private final String name;
    private float target;
    private final GradeComponents components;
    private float total;

    // REQUIRES : all the parameters aren't null
    // EFFECTS : make a course with its name, target score, grade components, and week
    public Course(String n, float t, GradeComponents c) {
        name = n;
        target = t;
        components = c;
    }

    public String getName() {
        return name;
    }

    public float getTarget() {
        return target;
    }

    public GradeComponents getComponents() {
        return components;
    }

    public void setTarget(float t) {
        target = t;
    }

    // REQUIRES : all the parameters aren't null
    // MODIFIES : this
    // EFFECTS : adding a grade component to components
    public void addGradeComponents(String n, SimpleList<Grade> g) {
        components.addComponent(n, g);
    }

    // EFFECTS : print current condition
    public String currentCondition() throws ExecutionException, InterruptedException {
        SimpleList<String> emptyName = new SimpleList<>();
        SimpleList<String> notEmptyName = new SimpleList<>();
        SimpleList<Grade> emptyGrade = new SimpleList<>();
        SimpleList<Grade> notEmptyGrade = new SimpleList<>();

        getEmptyAndNotEmptyAndTotal(emptyName, notEmptyName, emptyGrade, notEmptyGrade);
        String totalFormat = String.format("%.2f", total);
        String needFormat = String.format("%.2f", target - total);

        LoadJson.setTotalTemp(total);

        String current = "Not Empty : \n" + notEmptyName.stringTwoLists(notEmptyGrade) + "\nEmpty : \n"
                + emptyName.stringTwoLists(emptyGrade) + "Total : " + totalFormat + "/" + String.format("%.2f", target)
                + "\nNeed : " + needFormat + "\n";

        return current + getAnalytics(emptyGrade, emptyName);
    }

    // EFFECTS : print the analytics
    private String getAnalytics(SimpleList<Grade> emptyGrade, SimpleList<String> emptyName)
            throws ExecutionException, InterruptedException {
        String analytics;
        String analyticsResult;
        Analytics regularAnalytics = new RegularAnalytics(target, target - total, emptyGrade);
        Analytics greedyAnalytics = new GreedyAnalytics(target, target - total, emptyGrade);

        if (regularAnalytics.getPossible()) {
            analytics = "\nRegular Analytics : \n";
            emptyGrade = regularAnalytics.run();
        } else if (greedyAnalytics.getPossible()) {
            analytics = "\nGreedy Analytics : \n";
            emptyGrade = greedyAnalytics.run();
        } else {
            analytics = "\nThe target of : " + String.format("%.2f", target) + " is unachievable\n";
        }

        analyticsResult = emptyName.stringTwoLists(emptyGrade);
        revertAnalysis(emptyName);

        return analytics + analyticsResult;
    }

    // EFFECTS : revert the grade back after analysis
    private void revertAnalysis(SimpleList<String> emptyName) {
        int size = components.getNameList().size();
        int emptySize = emptyName.size();
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < emptySize; j++) {
                String componentName = components.getNameList().get(i);
                String empty = emptyName.get(j);
                if (empty.contains(componentName)) {
                    // \\D+ to remove non-integer
                    int index = Integer.parseInt(empty.replaceAll("\\D+","")) - 1;
                    float percentage = components.getGradeList().get(i).get(0).getPercentage();
                    components.getGradeList().get(i).set(index, new Grade(percentage, 0, true));
                }
            }
        }
        total = 0;
    }

    // EFFECTS : separating empty and not empty grade, and calculating total grades that have been accumulated
    private void getEmptyAndNotEmptyAndTotal(SimpleList<String> emptyName, SimpleList<String> notEmptyName,
                                     SimpleList<Grade> emptyGrade, SimpleList<Grade> notEmptyGrade) {
        int gradeListSize = components.getGradeList().size();
        for (int i = 0; i < gradeListSize; i++) {
            SimpleList<Grade> list = components.getGradeList().get(i);
            int size = list.size();
            for (int j = 0; j < size; j++) {
                if (list.get(j).getIsEmpty()) {
                    emptyName.add(components.getNameList().get(i) + (j + 1));
                    emptyGrade.add(list.get(j));
                } else {
                    notEmptyName.add(components.getNameList().get(i) + (j + 1));
                    notEmptyGrade.add(list.get(j));
                    total += list.get(j).getReal();
                }
            }
        }
    }

    // only for GUI part
    // EFFECTS : get the analytics name
    public String getAnalyticsName() throws ExecutionException, InterruptedException {
        SimpleList<String> emptyName = new SimpleList<>();
        SimpleList<String> notEmptyName = new SimpleList<>();
        SimpleList<Grade> emptyGrade = new SimpleList<>();
        SimpleList<Grade> notEmptyGrade = new SimpleList<>();
        total = 0;
        getEmptyAndNotEmptyAndTotal(emptyName, notEmptyName, emptyGrade, notEmptyGrade);
        return getAnalyticsName(emptyGrade);
    }

    // only for GUI part
    // EFFECTS : get the analytics name
    private String getAnalyticsName(SimpleList<Grade> emptyGrade) {
        String analytics;
        Analytics regularAnalytics = new RegularAnalytics(target, target - total, emptyGrade);
        Analytics greedyAnalytics = new GreedyAnalytics(target, target - total, emptyGrade);

        if (regularAnalytics.getPossible()) {
            analytics = "Regular Analytics : ";
        } else if (greedyAnalytics.getPossible()) {
            analytics = "Greedy Analytics : ";
        } else {
            analytics = "The target of : " + String.format("%.2f", target) + " is unachievable";
        }
        return analytics;
    }

    // only for GUI part
    // EFFECTS : get the analytics result
    public String getAnalyticsResult() throws ExecutionException, InterruptedException {
        SimpleList<String> emptyName = new SimpleList<>();
        SimpleList<String> notEmptyName = new SimpleList<>();
        SimpleList<Grade> emptyGrade = new SimpleList<>();
        SimpleList<Grade> notEmptyGrade = new SimpleList<>();
        total = 0;
        getEmptyAndNotEmptyAndTotal(emptyName, notEmptyName, emptyGrade, notEmptyGrade);
        return getAnalyticsResult(emptyGrade, emptyName);
    }

    // only for GUI part
    // EFFECTS : get the analytics result
    private String getAnalyticsResult(SimpleList<Grade> emptyGrade, SimpleList<String> emptyName)
            throws ExecutionException, InterruptedException {
        String analyticsResult;
        Analytics regularAnalytics = new RegularAnalytics(target, target - total, emptyGrade);
        Analytics greedyAnalytics = new GreedyAnalytics(target, target - total, emptyGrade);

        if (regularAnalytics.getPossible()) {
            emptyGrade = regularAnalytics.run();
        } else if (greedyAnalytics.getPossible()) {
            emptyGrade = greedyAnalytics.run();
        } else {
            return "";
        }

        analyticsResult = emptyName.stringTwoLists(emptyGrade);
        revertAnalysis(emptyName);

        return analyticsResult;
    }

    // Overriding method for printing Course
    // EFFECTS : make string of a Course object
    @Override
    public String toString() {
        return "Course : " + name + "\nTarget : " + String.format("%.2f", target) + "\n"
                + "\n" + components.toString();
    }
}
