package com.example.derekshultz.as1;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.Window;
import android.widget.Toast;

public class AllAddHabitsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.alladdhabits);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        //Inflates the menu this adds to the action bar if it is present
        getMenuInflater().inflate(R.menu.alladdhabitsmenu, menu);
        return true;
    }

    public void goToTodaysHabits(MenuItem item) {
        Toast.makeText(this, "Today\'s Habits", Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(AllAddHabitsActivity.this, TodaysHabitsActivity.class);
        startActivity(intent);
    }
}
