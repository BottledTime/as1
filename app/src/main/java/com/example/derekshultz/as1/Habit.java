package com.example.derekshultz.as1;

import java.util.ArrayList;
import java.util.Calendar;

/**
 * Created by derekshultz on 2016-10-01.
 */
// This is a model object meant to include and do management of all basic data that is associated
// with a habit.
//
// It contains all the methods and attributes needed to express what makes up a habit. Originally,
// all of the types of data that make up a habit were their own seperate class, but then it was
// realized that that caused too many very small, reasonably tightly-coupled classes and that they
// would be better off in one class known as Habit.
//
// Probably the biggest shortcoming of this class is that it does not currently have a way of
// figuring out if it has been completed in the last day or not. When trying to use a basic timer,
// there were errors about the execution time being too long.
public class Habit {

    private String habitName;
    private ArrayList<String> daysOfWeek;
    private Calendar startDate;
    boolean completedToday;
    private ArrayList<String> completionsList;
    private int numberOfFulfilments;

    public Habit(String habitName, ArrayList<String> daysOfWeek, Calendar startDate)
            throws NoHabitNameException, NoDayOfWeekException {
        if (habitName.equals("")) {
            throw new NoHabitNameException();
        }
        if (daysOfWeek.isEmpty()) {
            throw new NoDayOfWeekException();
        }
        this.habitName = habitName;
        this.daysOfWeek = daysOfWeek;
        this.startDate = startDate;
        this.completionsList = new ArrayList<String>();
        this.numberOfFulfilments = 0;
    }

    public String getName() {
        return habitName;
    }

    public boolean getCompletedToday() {
        return completedToday;
    }

    public void setCompletedToday(boolean completedToday) {
        this.completedToday = completedToday;
    }

    public void addCompletion() {
        this.completionsList.add(habitName + " was completed on " + Calendar.getInstance().getTime().toString() + ".");
        this.setNumberOfFulfillments();
        this.setCompletedToday(true);
    }

    public ArrayList<String> getCompletionsList() {
        return this.completionsList;
    }

    public void setNumberOfFulfillments() {
        this.numberOfFulfilments = completionsList.size();
    }

    public int getNumberOfFullfilments() {
        return numberOfFulfilments;
    }

    public void removeCompletion(String completionString) {
        this.completionsList.remove(completionString);
        this.setNumberOfFulfillments();
    }

    public ArrayList<String> getDaysForHabit() {
        return daysOfWeek;
    }

    public Calendar getStartDate() {
        return startDate;
    }

    public String toString() {
        return "Habit name: " + getName() + "\nStart date: " + startDate.getTime().toString() +
                "\nDays of Week: " + daysOfWeek.toString() + "\nNumber of Fulfillments: " +
                getNumberOfFullfilments() + "\nCompleted today: " + completedToday;
    }
}
