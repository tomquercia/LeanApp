package com.nationwide.mobile.lean;

import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import java.util.Calendar;


public class LauncherActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //this activity is a generic launcher activity.
        //in here, we will make decisions on where to go to when the app is launched - i.e. time entry page, startup page, profile page, etc
        if(UserCreator.getUser(getApplicationContext(), this) != null){
            Intent intent;
            //if the user is already checked in, take them directly to the time entry activity
            boolean alarmUp = (PendingIntent.getBroadcast(getApplicationContext(), 0,
                    new Intent(getApplicationContext(), Alarm.class),
                    PendingIntent.FLAG_NO_CREATE) != null);

            Calendar cal = Calendar.getInstance();
            final int hour = cal.get(Calendar.HOUR_OF_DAY);
            if(hour<7 || hour>20){
                Intent intent1 = new Intent(LauncherActivity.this, OvertimeActivity.class);
                startActivity(intent1);
            }
            else{
                if(alarmUp){
                    Log.d("Lean", "Alarm already exists");
                    intent = new Intent(this, TimeManagement.class);
                    startActivity(intent);
                }else{
                    Log.d("Lean", "There is no alarm yet");
                    intent = new Intent(this, MainActivity.class);
                    startActivity(intent);
                }
            }
            //if they haven't checked in, take them to the check in page
            //if they haven't registered, they will automatically be forwarded to the registration page.

        }
        finish();
        setContentView(R.layout.activity_launcher);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_launcher, menu);
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
