package model.component.analytic;

import model.component.Grade;
import model.util.SimpleList;

import java.util.concurrent.ExecutionException;

// create an analytics based on percentage priority, but with cap
public class RegularAnalytics extends Analytics {

    // Constant for Regular Analytics
    // when a component is worth between 0 - 10, max is 100
    // when a component is worth between 11 - 25, max is 95
    // when a component is worth between 26 - 35, max is 88
    // when a component is worth between 36 - 100, max is 85
    private static final int[] CAP = {100, 95, 88, 85};

    // REQUIRES : all not null
    // EFFECTS : construct regular analytics
    public RegularAnalytics(float t, float n, SimpleList<Grade> g) {
        super();
        target = t;
        gradeList = g;
        needed = n;
        max = maxAchievable();
        possible = isPossible();
    }

    // MODIFIES : this
    // EFFECTS : run the analytics and return the list
    @Override
    public SimpleList<Grade> run() throws ExecutionException, InterruptedException {
        if (Math.abs(needed - max) < 0.1) {
            resultGrade.add(CAP[CAP0_10]);
            resultGrade.add(CAP[CAP11_25]);
            resultGrade.add(CAP[CAP26_35]);
            resultGrade.add(CAP[CAP36_100]);
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
            float percentage = gradeList.get(i).getPercentage();
            if (percentage <= (float) CAP_LIMIT_TOP[CAP0_10]) {
                percentage *= (float)(CAP[CAP0_10]) / UPPER_LIMIT;
            } else if (percentage <= (float) CAP_LIMIT_TOP[CAP11_25]) {
                percentage *= (float)(CAP[CAP11_25]) / UPPER_LIMIT;
            } else if (percentage <= (float) CAP_LIMIT_TOP[CAP26_35]) {
                percentage *= (float)(CAP[CAP26_35]) / UPPER_LIMIT;
            } else {
                percentage *= (float)(CAP[CAP36_100]) / UPPER_LIMIT;
            }
            maximum += percentage;
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
            } else if (LOWER_LIMIT + first < CAP[CAP0_10]) {
                first++;
            } else if (LOWER_LIMIT + second < CAP[CAP11_25]) {
                second++;
            } else if (LOWER_LIMIT + EXAM_LIMIT + third < CAP[CAP26_35]) {
                third++;
            } else {
                fourth++;
            }
        }
        addToResult(first, second, third, fourth);
    }
}
