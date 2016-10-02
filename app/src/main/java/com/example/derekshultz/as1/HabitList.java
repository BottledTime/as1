package com.example.derekshultz.as1;

import java.util.ArrayList;

/**
 * Created by derekshultz on 2016-10-01.
 */
public class HabitList {
    private ArrayList<Habit> habitList;

    public HabitList() {
        habitList = new ArrayList<Habit>();
    }

    public void addHabit(Habit habit) {
        for (Habit aHabit: habitList) {
            if (aHabit.getName().equals(habit.getName())) {
                throw new IllegalArgumentException();
            }
        }
        habitList.add(habit);
    }

    public ArrayList<Habit> getHabits() {
        return habitList;
    }

    public void removeHabit(Habit habit) {
        habitList.remove(habit);
    }
}
