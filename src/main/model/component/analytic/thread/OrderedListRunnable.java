package model.component.analytic.thread;

import model.component.Grade;
import model.util.SimpleList;

import static model.component.analytic.Analytics.*;

// thread class for classifying list based on their percentage
public class OrderedListRunnable implements Runnable {

    private final SimpleList<Grade> gradeList;
    private final SimpleList<Grade> orderedGradeList;
    private final int capIndex;

    // REQUIRES : all the parameters aren't null
    // EFFECTS : construct a class for breaking list to be run by thread
    public OrderedListRunnable(int c, SimpleList<Grade> g, SimpleList<Grade> og) {
        capIndex = c;
        gradeList = g;
        orderedGradeList = og;
    }

    // EFFECTS : get nameList and gradeList based on lower percentage grade
    @Override
    public void run() {
        getOrderedList(capIndex, orderedGradeList);
    }

    // REQUIRES : index based on cap
    // MODIFIES : this
    // EFFECTS : get nameList and gradeList based on lower percentage grade
    private void getOrderedList(int capIndex, SimpleList<Grade> orderedGradeList) {
        int size = gradeList.size();
        for (int i = 0; i < size; i++) {
            float percentage = gradeList.get(i).getPercentage();
            Grade gradeAdded = gradeList.get(i);
            if ((percentage >= CAP_LIMIT_BOTTOM[capIndex]) && (percentage <= CAP_LIMIT_TOP[capIndex])) {
                orderedGradeList.add(gradeAdded);
            }
        }
    }
}
