package com.example.derekshultz.as1;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;

public class AddHabitActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_habit);
//        loadFromFile();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        //Inflates the menu this adds to the action bar if it is present
        getMenuInflater().inflate(R.menu.addhabitmenu, menu);
        return true;
    }

    public void goToAllHabits(MenuItem item) {
        Toast.makeText(this, "All/Add Habits", Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(AddHabitActivity.this, AllHabitsActivity.class);
        startActivity(intent);
    }

    public ArrayList<String> getInputDaysOfWeek() {
        ArrayList<String> inputDaysOfWeek = new ArrayList<String>();

        CheckBox dayCheckBox = (CheckBox) findViewById(R.id.mondayCheckBox);
        if(dayCheckBox.isChecked()) {
            inputDaysOfWeek.add("Monday");
        }
        dayCheckBox = (CheckBox) findViewById(R.id.tuesdayCheckBox);
        if(dayCheckBox.isChecked()) {
            inputDaysOfWeek.add("Tuesday");
        }
        dayCheckBox = (CheckBox) findViewById(R.id.wednesdayCheckBox);
        if(dayCheckBox.isChecked()) {
            inputDaysOfWeek.add("Wednesday");
        }
        dayCheckBox = (CheckBox) findViewById(R.id.thursdayCheckBox);
        if(dayCheckBox.isChecked()) {
            inputDaysOfWeek.add("Thursday");
        }
        dayCheckBox = (CheckBox) findViewById(R.id.fridayCheckBox);
        if(dayCheckBox.isChecked()) {
            inputDaysOfWeek.add("Friday");
        }
        dayCheckBox = (CheckBox) findViewById(R.id.saturdayCheckBox);
        if(dayCheckBox.isChecked()) {
            inputDaysOfWeek.add("Saturday");
        }
        dayCheckBox = (CheckBox) findViewById(R.id.sundayCheckBox);
        if(dayCheckBox.isChecked()) {
            inputDaysOfWeek.add("Sunday");
        }
        return inputDaysOfWeek;
    }

    public Calendar getInputStartDate() {
        DatePicker startDateView = (DatePicker) findViewById(R.id.startDatePicker);
        Calendar startDateReceived = Calendar.getInstance();
        startDateReceived.set(startDateView.getYear(), startDateView.getMonth(),
                startDateView.getDayOfMonth(), 0, 0, 0);
        return startDateReceived;
    }

    public void addHabitAction(View v) {
        Toast.makeText(this, "Adding Habit", Toast.LENGTH_SHORT).show();
        HabitListController habitListController = new HabitListController();
        EditText habitNameView = (EditText) findViewById(R.id.enterHabitName);
        Habit habit;
        try {
            habit = new Habit(habitNameView.getText().toString(), getInputDaysOfWeek(), getInputStartDate());
        }
        catch (NoHabitNameException arg) {
            Toast.makeText(this, "Name Must Be Entered", Toast.LENGTH_LONG).show();
            return;
        }
        catch (NoDayOfWeekException arg) {
            Toast.makeText(this, "Habit Must Have At Least One Day A Week", Toast.LENGTH_LONG).show();
            return;
        }
        try {
            habitListController.addHabit(habit);
        }
        catch (DuplicateHabitNameException arg) {
            Toast.makeText(this, "There Is Already A Habit With This Name", Toast.LENGTH_LONG).show();
        }
//        saveInFile();
    }

//    private void loadFromFile() {
//        //ArrayList<String> tweets = new ArrayList<String>();
//        try {
//            FileInputStream fis = openFileInput(HabitListController.getFILENAME());
//            BufferedReader in = new BufferedReader(new InputStreamReader(fis));
//            Gson gson = new Gson();
//            //Code taken from http://stackoverflow.com/questions/12384064/gson-convert-from-json-to-a-typed-arraylistt September 22, 2016
//            Type listType =new TypeToken<ArrayList<Habit>>(){}.getType();
//            HabitListController.setHabitList((HabitList) gson.fromJson(in, listType));
//        } catch (FileNotFoundException e) {
//            // TODO Auto-generated catch block
//            saveInFile();
//            //throw new RuntimeException();
//        }
//    }
//
//    private void saveInFile() {
//        try {
//
//            FileOutputStream fos = openFileOutput(HabitListController.getFILENAME(), 0);
//            OutputStreamWriter writer = new OutputStreamWriter(fos);
//            Gson gson = new Gson();
//            gson.toJson(HabitListController.getHabitList(), writer);
//            writer.flush();
//        } catch (FileNotFoundException e) {
//            // TODO Auto-generated catch block
//            throw new RuntimeException();
//        } catch (IOException e) {
//            // TODO Auto-generated catch block
//            throw new RuntimeException();
//        }
//    }

}
