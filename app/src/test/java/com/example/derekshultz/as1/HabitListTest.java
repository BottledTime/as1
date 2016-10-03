package com.example.derekshultz.as1;

import org.junit.*;

import java.util.ArrayList;
import java.util.Calendar;

import static org.junit.Assert.*;

/**
 * Created by derekshultz on 2016-10-01.
 */
public class HabitListTest {

    HabitList habitList;
    Habit habit;
    String habitName;
    ArrayList<String> daysOfWeek;
    Calendar startDate;

    @Before
    public void setUp() throws Exception{
        habitList = new HabitList();
        habitName = "Test";
        daysOfWeek = new ArrayList<String>();
        daysOfWeek.add("Monday");
        startDate = Calendar.getInstance();
        try {
            habit = new Habit(habitName, daysOfWeek, startDate);
        }
        catch (NoHabitNameException | NoDayOfWeekException arg) {
            assertTrue(false);
        }
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void getHabits() {
        assertEquals(habitList.getHabits().size(), 0);
    }

    @Test
    public void addHabit() {
        try {
            habitList.addHabit(habit);
        }
        catch (DuplicateHabitNameException arg) {
            assertTrue(false);
        }
        assertEquals(habitList.getHabits().size(), 1);
        assertTrue(habitList.getHabits().contains(habit));
    }

    @Test
    public void setHabitWithSameName() {
        try {
            habitList.addHabit(habit);
            habitList.addHabit(habit);
            assertTrue(false);
        }
        catch (DuplicateHabitNameException arg) {
            assertTrue(true);
        }
        assertEquals(habitList.getHabits().size(), 1);
        try {
            ArrayList<String> anotherDaysOfWeek = new ArrayList<String>();
            anotherDaysOfWeek.add("Tuesday");
            Habit anotherHabit = new Habit("Test", anotherDaysOfWeek, Calendar.getInstance());
            habitList.addHabit(anotherHabit);
            assertTrue(false);
        }
        catch (DuplicateHabitNameException arg) {
            assertTrue(true);
        }
        catch (NoHabitNameException | NoDayOfWeekException arg) {
            assertTrue(false);
        }
    }

    @Test
    public void removeHabit() {
        assertEquals(habitList.getHabits().size(), 0);
        try {
            habitList.addHabit(habit);
        }
        catch (DuplicateHabitNameException arg) {
            assertTrue(false);
        }
        assertEquals(habitList.getHabits().size(), 1);
        assertTrue(habitList.getHabits().contains(habit));
        habitList.removeHabit(habit);
        assertEquals(habitList.getHabits().size(), 0);
        assertFalse(habitList.getHabits().contains(habit));
    }

    boolean updated = false;

    @Test
    public void testNotifyListeners() {
        updated = false;
        Listener l = new Listener() {
            public void update() {
                HabitListTest.this.updated = true;
            }
        };
        habitList.addListener(l);
        try {
            habitList.addHabit(habit);
            assertTrue(true);
        } catch (DuplicateHabitNameException e) {
            assertTrue(false);
        }
        assertTrue(this.updated);
        updated = false;
        habitList.removeHabit(habit);
        assertTrue(this.updated);
    }

    @Test
    public void testRemoveListeners() {
        updated = false;
        Listener l = new Listener() {
            public void update() {
                HabitListTest.this.updated = true;
            }
        };
        habitList.addListener(l);
        habitList.removeListener(l);
        try {
            habitList.addHabit(habit);
            assertTrue(true);
        } catch (DuplicateHabitNameException e) {
            assertTrue(false);
        }
        assertFalse(this.updated);
    }

}

