/*
    HabitTracker - maintains a list of recurring daily to-do habits to complete each day
    Copyright (C) 2016  Derek Shultz

    This program is free software: you can redistribute it and/or modify
    it under the terms of the GNU General Public License as published by
    the Free Software Foundation, either version 3 of the License, or
    (at your option) any later version.

    This program is distributed in the hope that it will be useful,
    but WITHOUT ANY WARRANTY; without even the implied warranty of
    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
    GNU General Public License for more details.

    You should have received a copy of the GNU General Public License
    along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */

package com.example.derekshultz.as1;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

public class TodaysHabitsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.todayshabits);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        //Inflates the menu this adds to the action bar if it is present
        getMenuInflater().inflate(R.menu.todayshabitsmenu, menu);
        return true;
    }

    public void goToAllAddHabits(MenuItem item) {
        Toast.makeText(this, "All/Add Habits", Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(TodaysHabitsActivity.this, AllAddHabitsActivity.class);
        startActivity(intent);
    }
}
