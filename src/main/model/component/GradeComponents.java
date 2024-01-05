package model.component;

import model.util.SimpleList;

// Creating a grade breakdown to store the component's name and component's grade
public class GradeComponents {

    private final SimpleList<String> nameList;
    private final SimpleList<SimpleList<Grade>> gradeList;

    // REQUIRES : n.size() == g.size(), and both of them aren't null
    // EFFECTS : construct components for a course
    public GradeComponents(SimpleList<String> n, SimpleList<SimpleList<Grade>> g) {
        nameList = n;
        gradeList = g;
    }

    public SimpleList<String> getNameList() {
        return nameList;
    }

    public SimpleList<SimpleList<Grade>> getGradeList() {
        return gradeList;
    }

    // REQUIRES : n and g aren't null
    // MODIFIES : this
    // EFFECTS : adding new component to the grading components
    public void addComponent(String n, SimpleList<Grade> g) {
        nameList.add(n);
        gradeList.add(g);
    }

    // REQUIRES : i < nameList.size() and i >= 0
    // MODIFIES : this
    // EFFECTS : component at index i will be replaced with new component
    public void editComponent(int i, String n, SimpleList<Grade> g) {
        nameList.set(i, n);
        gradeList.set(i, g);
    }

    // REQUIRES : i < nameList.size() and i >= 0
    // MODIFIES : this
    // EFFECTS : component at index i will be deleted
    public void deleteComponent(int i) {
        nameList.delete(i);
        gradeList.delete(i);
    }

    // Overriding method for printing gradeComponents
    // REQUIRES : nameList.size() == gradeList.size() and both of them aren't null
    // EFFECTS : make string of a GradeComponents object
    @Override
    public String toString() {
        return nameList.stringTwoLists(gradeList);
    }
}
