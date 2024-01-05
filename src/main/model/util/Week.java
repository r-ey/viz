package model.util;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

// Create a Week class to keep track of weeks from the start term
public class Week {

    private static int week;

    // EFFECTS : construct week object with static variable to keep track of weeks
    public Week() {
        week = 0;
    }

    public int getWeek() {
        return Math.min(week, 14);
    }

    // REQUIRES : both startWeek and endWeek aren't null, in yyyy-mm-dd format
    // EFFECTS : return how many weeks between start and end
    public void searchWeek(String startWeek, String endWeek) {
        LocalDate start = LocalDate.parse(startWeek);
        LocalDate end = LocalDate.parse(endWeek);
        week =  (int) ChronoUnit.WEEKS.between(start, end) + 1;
    }
}
