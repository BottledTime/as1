package com.example.derekshultz.as1;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
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

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

public class AllHabitsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.all_habits);
        ListView listView = (ListView) findViewById(R.id.allHabitsListView);
//        loadFromFile();
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

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                HabitListController.addHabitCompletion(list.get(position));
            }
        });

        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
//                Dialog dialog = new Dialog(AllHabitsActivity.this);
//                dialog.setContentView(R.layout.remove_dialog);
//                dialog.setTitle("This is my custom dialog box");
//                dialog.setCancelable(true);
//                ListView completionsListView = (ListView) findViewById(R.id.removableCompletionsListView);
//                Collection<String> completions = list.get(position).getCompletionsList();
//                final ArrayList<String> completionList = new ArrayList<String>(completions);
//                final ArrayAdapter<String> completionAdapter = new ArrayAdapter<String>(AllHabitsActivity.this, android.R.layout.simple_list_item_1, completionList);
//                completionsListView.setAdapter(completionAdapter);
//
//                HabitListController.getHabitList().addListener(new Listener() {
//                    @Override
//                    public void update() {
//                        completionList.clear();
//                        Collection<String> habits = completion;
//                        completionList.addAll(habits);
//                        completionAdapter.notifyDataSetChanged();
//                    }
//                });

                AlertDialog.Builder adb = new AlertDialog.Builder(AllHabitsActivity.this);
                adb.setMessage(list.get(position).getName() + " Completions (Click any you want to delete)");
                adb.setCancelable(true);
                final String[] completions = list.get(position).getCompletionsList().toArray(new String[0]);
                final List<String> completionsList = Arrays.asList(completions);
                final boolean[] checkedCompletions = new boolean[list.get(position).getCompletionsList().size()];
                adb.setMultiChoiceItems(completions, checkedCompletions, new DialogInterface.OnMultiChoiceClickListener() {

                    @Override
                    public void onClick(DialogInterface dialog, int which, boolean isChecked) {
                        checkedCompletions[which] = isChecked;
                        String currentItem = completionsList.get(which);
                        Toast.makeText(AllHabitsActivity.this, currentItem + " " + isChecked, Toast.LENGTH_SHORT).show();
                    }
                });
                final int finalPosition = position;
                adb.setPositiveButton("Delete Habit", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Habit habit = list.get(finalPosition);
                        HabitListController.getHabitList().removeHabit(habit);
                    }
                });
                adb.setNeutralButton("Delete Completions", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        for (int i = 0; i < list.get(finalPosition).getCompletionsList().size(); i++) {
                            if (checkedCompletions[i]) {
                                Habit habit = list.get(finalPosition);
                                HabitListController.removeHabitCompletion(habit, completions[i]);
                            }
                        }
                    }
                });
                adb.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                    }
                });
                AlertDialog dialog = adb.create();
                dialog.show();
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

    public void goToAddHabits(MenuItem item) {
        Toast.makeText(this, "Add Habit", Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(AllHabitsActivity.this, AddHabitActivity.class);
        startActivity(intent);
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
