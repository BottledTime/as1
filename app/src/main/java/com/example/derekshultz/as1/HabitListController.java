package com.example.derekshultz.as1;

import android.widget.CheckBox;
import android.widget.EditText;

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

    public void addHabit(Habit habit) throws DuplicateHabitNameException {
        try {
            getHabitList().addHabit(habit);
        }
        catch (DuplicateHabitNameException arg) {
            throw new DuplicateHabitNameException();
        }
        getHabitList();
    }
}
