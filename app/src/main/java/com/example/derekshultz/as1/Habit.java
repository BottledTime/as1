package com.example.derekshultz.as1;

import java.util.ArrayList;
import java.util.Calendar;

/**
 * Created by derekshultz on 2016-10-01.
 */
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
}
