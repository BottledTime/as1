package com.example.derekshultz.as1;

/**
 * Created by derekshultz on 2016-10-02.
 */

public class HabitListController {

    // Lazy Singleton
    private static HabitList habitList = null;

    static public HabitList getHabitList() {
        if (habitList == null) {
            habitList = new HabitList();
        }
        return habitList;
    }

    static public void setHabitList(HabitList habitList) {
        HabitListController.habitList = habitList;
    }

    public void addHabit(Habit habit) throws DuplicateHabitNameException {
        try {
            getHabitList().addHabit(habit);
        }
        catch (DuplicateHabitNameException arg) {
            throw new DuplicateHabitNameException();
        }
        getHabitList();
    }

    static public void addHabitCompletion(Habit habit) {
        if(habitList.getHabits().contains(habit)) {
            habitList.addHabitCompletion(habit);
        }
    }

    static public void removeHabitCompletion(Habit habit, String completionString) {
        if(habitList.getHabits().contains(habit) && habit.getCompletionsList().contains(completionString)) {
            habitList.removeHabitCompletion(habit, completionString);
        }
    }
}
