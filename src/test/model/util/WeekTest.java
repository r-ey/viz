package model.util;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

// Unit tests for Node class
public class WeekTest {

    @Test
    public void testGetWeek() {
        Week testWeek = new Week();
        String startWeek = "2023-09-07";
        String endWeek = "2023-12-07";
        testWeek.searchWeek(startWeek, endWeek);
        assertEquals(13 + 1, testWeek.getWeek());
    }
}
