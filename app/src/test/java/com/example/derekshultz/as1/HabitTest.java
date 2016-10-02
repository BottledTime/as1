package com.example.derekshultz.as1;

import android.provider.Settings;

import org.junit.*;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.InvalidPropertiesFormatException;
import java.util.Scanner;
import java.util.StringTokenizer;

import static org.junit.Assert.*;

/**
 * Created by derekshultz on 2016-10-01.
 */
public class HabitTest {

    private Habit habit;
    private String habitName;
    private ArrayList<String> daysOfWeek;
    private Calendar startDate;

    @Before
    public void setUp() throws Exception {
        habitName = "Test";
        daysOfWeek = new ArrayList<String>();
        daysOfWeek.add("Monday");
        startDate = Calendar.getInstance();
        habit = new Habit(habitName, daysOfWeek, startDate);
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void setBlankName() {
        try {
            Habit badHabit = new Habit("", daysOfWeek, startDate);
            assertTrue(false);
        }
        catch (IllegalArgumentException arg) {
            assertTrue(true);
        }
    }

    @Test
    public void setNoDaysOfWeek() {
        try {
            Habit badHabit = new Habit(habitName, new ArrayList<String>(), startDate);
            assertTrue(false);
        }
        catch (IllegalArgumentException arg) {
            assertTrue(true);
        }
    }

    @Test
    public void getName() {
        assertEquals(habit.getName(), habitName);
    }

    @Test
    public void getCompletedToday() {
        assertFalse(habit.getCompletedToday());
    }

    @Test
    public void setCompletedToday() {
        habit.setCompletedToday(true);
        assertTrue(habit.getCompletedToday());
        habit.setCompletedToday(false);
        assertFalse(habit.getCompletedToday());
    }

    @Test
    public void getCompletionsList() {
        assertEquals(habit.getCompletionsList().getClass(), new ArrayList<String>().getClass());
        assertEquals(habit.getCompletionsList().size(), 0);
    }

    @Test
    public void addCompletion() {
        habit.addCompletion();
        assertTrue(habit.getCompletionsList().contains("Test was completed on " + Calendar.getInstance().getTime().toString() + "."));
    }

    @Test
    public void removeCompletion() {
        habit.addCompletion();
        assertEquals(habit.getCompletionsList().size(), 1);
        habit.removeCompletion("Test was completed on " + Calendar.getInstance().getTime().toString() + ".");
        assertEquals(habit.getCompletionsList().size(), 0);
    }

    @Test
    public void numberOfFulfillments() {
        habit.setNumberOfFulfillments();
        assertEquals(habit.getNumberOfFullfilments(), 0);
        habit.addCompletion();
        assertEquals(habit.getNumberOfFullfilments(), 1);
        habit.removeCompletion("Test was completed on " + Calendar.getInstance().getTime().toString() + ".");
        assertEquals(habit.getNumberOfFullfilments(), 0);
    }

    @Test
    public void getDaysForHabit() {
        ArrayList<String> dayList = new ArrayList<String>();
        dayList.add("Monday");
        assertEquals(habit.getDaysForHabit(), dayList);
    }

    @Test
    public void getStartDate() {
        Habit habit2 = new Habit("Test2", daysOfWeek, Calendar.getInstance());
        assertEquals(habit2.getStartDate(), Calendar.getInstance());
    }

}