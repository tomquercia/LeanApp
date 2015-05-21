package com.nationwide.mobile.lean;

import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.github.alexkolpa.fabtoolbar.FabToolbar;


public class MainActivity extends ActionBarActivity {

    private Alarm alarm;
    private FabToolbar fabToolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        alarm = new Alarm();
        ActionBar actionBar = getSupportActionBar();
        actionBar.setBackgroundDrawable(new ColorDrawable(getResources().getColor(R.color.nw_blue)));
/*        Button clockIn = (Button) findViewById(R.id.button_check_in);
        clockIn.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), "State is "+fabToolbar.getState(), Toast.LENGTH_SHORT).show();
/*
                if(alarm != null) {
                    alarm.SetAlarm(getApplicationContext());
                }else{
                    Toast.makeText(getApplicationContext(), "Alarm is null", Toast.LENGTH_SHORT).show();
                }

                Intent intent = new Intent(getApplicationContext(), TimeManagement.class);
                startActivity(intent);

/*
            }
        });*/

        fabToolbar = ((FabToolbar) findViewById(R.id.fab_toolbar));
        findViewById(R.id.attach).setOnClickListener(v -> {
           Toast.makeText(getApplicationContext(), "You clicked the button", Toast.LENGTH_SHORT).show();
            fabToolbar.hide();
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    @Override
    public void onBackPressed(){
        if(fabToolbar.getState() == 1){
            fabToolbar.hide();
        }
        else{
            finish();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
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
