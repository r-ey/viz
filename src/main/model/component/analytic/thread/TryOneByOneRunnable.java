package model.component.analytic.thread;

import model.component.Grade;
import model.util.SimpleList;

import java.util.concurrent.Callable;

import static model.component.analytic.Analytics.EXAM_LIMIT;
import static model.component.analytic.Analytics.UPPER_LIMIT;
import static model.component.analytic.RegularAnalytics.CAP26_35;
import static model.component.analytic.RegularAnalytics.CAP36_100;

// class for threads to try all the possible grades one by one
public class TryOneByOneRunnable implements Callable<Float> {

    private final SimpleList<Grade> gradeList;
    private final int indexCap;
    private int grade;

    // REQUIRES : all the parameters aren't null
    // EFFECTS : construct a class for searching minimum grade to be run by thread
    public TryOneByOneRunnable(SimpleList<Grade> g, int i, int gr) {
        gradeList = g;
        indexCap = i;
        grade = gr;
    }

    // EFFECTS : searching lowest grade to satisfy target
    @Override
    public Float call() {
        return tryOneByOne();
    }

    // EFFECTS : searching lowest grade to satisfy target
    private float tryOneByOne() {
        if (indexCap == CAP26_35 || indexCap == CAP36_100) {
            grade += EXAM_LIMIT;
        }
        int size = gradeList.size();
        float result = 0;
        for (int i = 0; i < size; i++) {
            result += gradeList.get(i).getPercentage() * grade / UPPER_LIMIT;
        }
        return result;
    }
}
