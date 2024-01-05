package model.component.analytic;

import model.component.Grade;
import model.util.SimpleList;

import java.util.concurrent.ExecutionException;

// create an analytics based on percentage priority without cap
public class GreedyAnalytics extends Analytics {

    // REQUIRES : all not null
    // EFFECTS : construct greedy analytics
    public GreedyAnalytics(float t, float n, SimpleList<Grade> g) {
        super();
        target = t;
        gradeList = g;
        needed = n;
        max = maxAchievable();
        possible = isPossible();
    }

    // MODIFIES ; this
    // EFFECTS : run the analytics and return the list
    @Override
    public SimpleList<Grade> run() throws ExecutionException, InterruptedException {
        if (Math.abs(needed - max) < 0.1) {
            resultGrade.add(UPPER_LIMIT);
            resultGrade.add(UPPER_LIMIT);
            resultGrade.add(UPPER_LIMIT);
            resultGrade.add(UPPER_LIMIT);
        } else {
            float total = 0;
            int firstCapIncrement = 0;
            int secondCapIncrement = 0;
            int thirdCapIncrement = 0;
            int fourthCapIncrement = 0;
            result(total, firstCapIncrement, secondCapIncrement, thirdCapIncrement, fourthCapIncrement);
        }
        setResultGrade(resultGrade);
        pool.shutdown();

        return gradeList;
    }

    // EFFECTS : get the maximum grade with restriction
    @Override
    protected float maxAchievable() {
        int size = gradeList.size();
        float maximum = 0;
        for (int i = 0; i < size; i++) {
            maximum += gradeList.get(i).getPercentage();
        }
        return maximum;
    }

    // REQUIRES : isPossible() is true
    // EFFECTS : the result of the analytics by trying one by one
    @Override
    protected void result(float total, int first, int second, int third, int fourth)
            throws ExecutionException, InterruptedException {
        breakPerCapThreads();
        while (total < needed) {
            total = tryOneByOne(LOWER_LIMIT + first, CAP0_10)
                    + tryOneByOne(LOWER_LIMIT + second, CAP11_25)
                    + tryOneByOne(LOWER_LIMIT + third, CAP26_35)
                    + tryOneByOne(LOWER_LIMIT + fourth, CAP36_100);
            if (total >= needed) {
                total++; // just to exit the loop
            } else if (LOWER_LIMIT + first < UPPER_LIMIT) {
                first++;
            } else if (LOWER_LIMIT + second < UPPER_LIMIT) {
                second++;
            } else if (LOWER_LIMIT + EXAM_LIMIT + third < UPPER_LIMIT) {
                third++;
            } else {
                fourth++;
            }
        }
        addToResult(first, second, third, fourth);
    }
}
