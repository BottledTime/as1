package com.example.derekshultz.as1;

/**
 * Created by derekshultz on 2016-10-02.
 */

// This class is meant to be a controller object between the habit list and the activities. It
// contains a lazy singleton habitlist and can manipulate it and check for proper input.
//
// The most outstanding problem with this class is that it might not be necessary to have a
// controller like this between the model and the views. HabitList might be enough for this purpose.
// This isn't a problem with functionality, but it might be making the code less maintainable. A
// lot of it's methods are pretty similar to HabitList's too.
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
