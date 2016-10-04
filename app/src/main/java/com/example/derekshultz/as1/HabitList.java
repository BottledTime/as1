package com.example.derekshultz.as1;

import java.util.ArrayList;

/**
 * Created by derekshultz on 2016-10-01.
 */

// This is another class that's meant to be a model object. It holds an array of all the habits
// and contains methods with which to manipulate the list.
public class HabitList {
    private ArrayList<Habit> habitList;
    private ArrayList<Listener> listeners;

    public HabitList() {
        habitList = new ArrayList<Habit>();
        listeners = new ArrayList<Listener>();
    }

    public void addHabit(Habit habit) throws DuplicateHabitNameException {
        for (Habit aHabit: habitList) {
            if (aHabit.getName().equals(habit.getName())) {
                throw new DuplicateHabitNameException();
            }
        }
        habitList.add(habit);
        notifyListeners();
    }

    public ArrayList<Habit> getHabits() {
        return habitList;
    }

    public void removeHabit(Habit habit) {
        habitList.remove(habit);
        notifyListeners();
    }

    public void notifyListeners() {
        for (Listener listener: listeners) {
            listener.update();
        }
    }

    public void addListener(Listener l) {
        listeners.add(l);
    }

    public void removeListener(Listener l) {
        listeners.remove(l);
    }

    public void addHabitCompletion(Habit habit) {
        habit.addCompletion();
        notifyListeners();
    }

    public void removeHabitCompletion(Habit habit, String completionString) {
        habit.removeCompletion(completionString);
        notifyListeners();
    }
}
