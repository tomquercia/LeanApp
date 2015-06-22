package com.nationwide.mobile.lean;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Spinner;


public class NotificationsActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notifications);

        final Spinner choices = (Spinner) findViewById(R.id.spinner);

        SharedPreferences prefs = getSharedPreferences("NOTIFICATIONS", Context.MODE_PRIVATE);

        if(prefs.getBoolean("valid", false)){
            String[] options = getResources().getStringArray(R.array.notifications);
            int selection = 0;
            for (int i=0; i<options.length; i++){
                if(options[i].equalsIgnoreCase(prefs.getString("NotificationsChoice", "15 Minutes"))){
                    selection = i;
                }
            }
            choices.setSelection(selection);
        }
        choices.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                SharedPreferences prefs = getSharedPreferences("NOTIFICATIONS", Context.MODE_PRIVATE);
                SharedPreferences.Editor prefsEditor = prefs.edit();
                prefsEditor.putString("NotificationsChoice", choices.getSelectedItem().toString());
                prefsEditor.putBoolean("valid", true);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_notifications, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
