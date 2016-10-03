package com.example.derekshultz.as1;

import android.widget.CheckBox;
import android.widget.EditText;

/**
 * Created by derekshultz on 2016-10-02.
 */

public class HabitListController {

    // Lazy Singleton
    private static HabitList habitList = null;
//    private static final String FILENAME = "file.sav";

    static public HabitList getHabitList() {
        if (habitList == null) {
            habitList = new HabitList();
        }
        return habitList;
    }

    static public void setHabitList(HabitList habitList) {
        HabitListController.habitList = habitList;
    }

//    static public String getFILENAME() {
//        return FILENAME;
//    }

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
