package com.example.derekshultz.as1;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Collection;

public class AllHabitsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.all_habits);
        ListView listView = (ListView) findViewById(R.id.allHabitsListView);
        Collection<Habit> habits = HabitListController.getHabitList().getHabits();
        final ArrayList<Habit> list = new ArrayList<Habit>(habits);
        final ArrayAdapter<Habit> habitAdapter = new ArrayAdapter<Habit>(this, android.R.layout.simple_list_item_1, list);
        listView.setAdapter(habitAdapter);

        // Added an observer
        HabitListController.getHabitList().addListener(new Listener() {
            @Override
            public void update() {
                list.clear();
                Collection<Habit> habits = HabitListController.getHabitList().getHabits();
                list.addAll(habits);
                habitAdapter.notifyDataSetChanged();
            }
        });

        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(AllHabitsActivity.this,
                        "Delete " + list.get(position).getName(),
                        Toast.LENGTH_SHORT).show();
                Habit habit = list.get(position);
                HabitListController.getHabitList().removeHabit(habit);
                return false;
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        //Inflates the menu this adds to the action bar if it is present
        getMenuInflater().inflate(R.menu.allhabitsmenu, menu);
        return true;
    }

    public void goToTodaysHabits(MenuItem item) {
        Toast.makeText(this, "Today\'s Habits", Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(AllHabitsActivity.this, TodaysHabitsActivity.class);
        startActivity(intent);
    }

    public void goToAddHabits(MenuItem item) {
        Toast.makeText(this, "Add Habit", Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(AllHabitsActivity.this, AddHabitActivity.class);
        startActivity(intent);
    }
}
