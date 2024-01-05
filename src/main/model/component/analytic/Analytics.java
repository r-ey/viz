package model.component.analytic;

import model.component.Grade;
import model.component.analytic.thread.OrderedListRunnable;
import model.component.analytic.thread.TryOneByOneRunnable;
import model.util.SimpleList;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

// Creating an analytic for either greedy or regular
public abstract class Analytics {

    // Threads
    protected static final int MAX_THREAD = 4;
    protected ExecutorService pool; // making thread pool

    // limit for grade
    public static final int LOWER_LIMIT = 20;
    public static final int UPPER_LIMIT = 100;

    // since most courses has minimum exam grade of 50%, the other 20 from LOWER_LIMIT
    public static final int EXAM_LIMIT = 30;

    // index for each cap
    public static final int[] CAP_LIMIT_BOTTOM = {0, 11, 26, 36};
    public static final int[] CAP_LIMIT_TOP = {10, 25, 35, 100};
    public static final int CAP0_10 = 0;
    public static final int CAP11_25 = 1;
    public static final int CAP26_35 = 2;
    public static final int CAP36_100 = 3;

    protected float target;
    protected float max;
    protected float needed;
    protected boolean possible;

    protected SimpleList<Grade> gradeList;
    protected SimpleList<SimpleList<Grade>> orderedGradeList;
    protected SimpleList<Integer> resultGrade;

    // EFFECTS : instantiating the ordered grade list and the result list
    public Analytics() {
        orderedGradeList = new SimpleList<>();
        resultGrade = new SimpleList<>();
    }

    public boolean getPossible() {
        return possible;
    }

    // EFFECTS : see if the target is possible or not by max achievable and needed, also instantiating thread pool
    protected boolean isPossible() {
        pool = Executors.newFixedThreadPool(MAX_THREAD);
        if (max >= needed) {
            return true;
        }
        pool.shutdown();
        return false;
    }

    // MODIFIES : this
    // EFFECTS : adding the analytic result to a list
    protected void addToResult(int first, int second, int third, int fourth) {
        resultGrade.add(LOWER_LIMIT + first);
        resultGrade.add(LOWER_LIMIT + second);
        resultGrade.add(LOWER_LIMIT + EXAM_LIMIT + third);
        resultGrade.add(LOWER_LIMIT + EXAM_LIMIT + fourth);
    }

    // EFFECTS : run the analytics
    public abstract SimpleList<Grade> run() throws ExecutionException, InterruptedException;

    // EFFECTS : get the maximum grade
    protected abstract float maxAchievable();

    // REQUIRES : isPossible() is true
    // EFFECTS : the result of the analytics
    protected abstract void result(float total, int first, int second, int third, int fourth)
            throws ExecutionException, InterruptedException;

    // EFFECTS : break the gradeList and nameList per cap category
    protected void breakPerCapThreads() {
        SimpleList<Grade> firstCap = new SimpleList<>();
        SimpleList<Grade> secondCap = new SimpleList<>();
        SimpleList<Grade> thirdCap = new SimpleList<>();
        SimpleList<Grade> fourthCap = new SimpleList<>();

        Runnable firstCapThread = new OrderedListRunnable(CAP0_10, gradeList, firstCap);
        Runnable secondCapThread = new OrderedListRunnable(CAP11_25, gradeList, secondCap);
        Runnable thirdCapThread = new OrderedListRunnable(CAP26_35, gradeList, thirdCap);
        Runnable fourthCapThread = new OrderedListRunnable(CAP36_100, gradeList, fourthCap);

        pool.execute(firstCapThread);
        pool.execute(secondCapThread);
        pool.execute(thirdCapThread);
        pool.execute(fourthCapThread);

        joinToOrderedList(firstCap, secondCap, thirdCap, fourthCap);
    }

    // REQUIRES : LOWER_LIMIT <= grade <= limit of each cap, 0 <= index <= 4
    // EFFECTS : searching for lowest grade to satisfy the target
    protected float tryOneByOne(int grade, int index) throws ExecutionException, InterruptedException {
        TryOneByOneRunnable threads = new TryOneByOneRunnable(orderedGradeList.get(index), index, grade);
        Future<Float> result = pool.submit(threads);
        return result.get();
    }

    // MODIFIES : this
    // EFFECTS : combine first, second, third, and fourth list into lists of list
    protected void joinToOrderedList(SimpleList<Grade> firstCap, SimpleList<Grade> secondCap,
                                     SimpleList<Grade> thirdCap, SimpleList<Grade> fourthCap) {
        orderedGradeList.add(firstCap);
        orderedGradeList.add(secondCap);
        orderedGradeList.add(thirdCap);
        orderedGradeList.add(fourthCap);
    }

    // REQUIRES : g is not null
    // MODIFIES : this
    // EFFECTS : set the result to the list
    protected void setResultGrade(SimpleList<Integer> g) {
        int size = gradeList.size();
        for (int i = 0; i < size; i++) {
            float percentage = gradeList.get(i).getPercentage();
            if (percentage <= (float) CAP_LIMIT_TOP[CAP0_10]) {
                gradeList.get(i).setReal(g.get(CAP0_10) * percentage / UPPER_LIMIT);
            } else if (percentage <= (float) CAP_LIMIT_TOP[CAP11_25]) {
                gradeList.get(i).setReal(g.get(CAP11_25) * percentage / UPPER_LIMIT);
            } else if (percentage <= (float) CAP_LIMIT_TOP[CAP26_35]) {
                gradeList.get(i).setReal(g.get(CAP26_35) * percentage / UPPER_LIMIT);
            } else {
                gradeList.get(i).setReal(g.get(CAP36_100) * percentage / UPPER_LIMIT);
            }
            gradeList.get(i).setIsEmpty(false);
        }
    }
}
