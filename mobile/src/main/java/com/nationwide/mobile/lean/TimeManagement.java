package com.nationwide.mobile.lean;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.github.alexkolpa.fabtoolbar.FabToolbar;

import java.util.Arrays;


public class TimeManagement extends ActionBarActivity {

    private Alarm alarm;
    String TITLES[] = {"Edit My Profile",
            "Notification Settings",
            "Clock in/out"};
    int ICONS[] = {R.drawable.ic_profile,
            R.drawable.ic_settings,
            R.drawable.ic_clock_dark};

    String NAME = "Tom Quercia";
    String EMAIL = "tomquercia@gmail.com";
    int PROFILE = R.mipmap.ic_launcher;
    private FabToolbar fabToolbar;
    private Toolbar toolbar;
    RecyclerView mRecyclerView;
    RecyclerView.Adapter mAdapter;
    RecyclerView.LayoutManager mLayoutManager;
    DrawerLayout Drawer;
    ActionBarDrawerToggle mDrawerToggle;
    ListView listView;
    ListView hiddenView;
    int enabled = 0;
    String[] HOURS = new String[]{
      "9:00", "9:15", "9:30", "9:45", "10:00", "10:15", "10:30", "10:45", "11:00", "11:15", "11:30", "11:45", "12:00"
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_time_management);
        alarm = new Alarm();
        toolbar = (Toolbar) findViewById(R.id.tool_bar);
        setSupportActionBar(toolbar);

        HOURS = getResources().getStringArray(R.array.times);

        listView = (ListView) findViewById(R.id.listView_future);
        listView.setAdapter(new TimeAdapter(this, Arrays.copyOfRange(HOURS,HOURS.length/2, HOURS.length), false));

        hiddenView = (ListView)findViewById(R.id.listView_previous);
        hiddenView.setAdapter(new TimeAdapter(this, Arrays.copyOfRange(HOURS, 0, HOURS.length / 2 - 1), true));

        Button showPast = (Button) findViewById(R.id.button_previous_times);
        showPast.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(enabled == 0) {
                    hiddenView.setVisibility(View.VISIBLE);
                    enabled = 1;
                }
                else{
                    hiddenView.setVisibility(View.GONE);
                    enabled=0;
                }
            }
        });
        Log.d("Lean", "Adapter set");



        mRecyclerView = (RecyclerView) findViewById(R.id.RecyclerView); // Assigning the RecyclerView Object to the xml View

        mRecyclerView.setHasFixedSize(true);                            // Letting the system know that the list objects are of fixed size

        mAdapter = new MyAdapter(TITLES,ICONS,NAME,EMAIL,PROFILE);       // Creating the Adapter of MyAdapter class
        // And passing the titles,icons,header view name, header view email,
        // and header view profile picture

        mRecyclerView.setAdapter(mAdapter);                              // Setting the adapter to RecyclerView

        mLayoutManager = new LinearLayoutManager(this);                 // Creating a layout Manager

        mRecyclerView.setLayoutManager(mLayoutManager);                 // Setting the layout Manager


        Drawer = (DrawerLayout) findViewById(R.id.drawer_layout1);        // Drawer object Assigned to the view
        mDrawerToggle = new ActionBarDrawerToggle(this,Drawer,toolbar,R.string.navigation_drawer_open,R.string.navigation_drawer_close){

            @Override
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
                // code here will execute once the drawer is opened
            }

            @Override
            public void onDrawerClosed(View drawerView) {
                super.onDrawerClosed(drawerView);
                // Code here will execute once drawer is closed
            }



        }; // Drawer Toggle Object Made
        Drawer.setDrawerListener(mDrawerToggle); // Drawer Listener set to the Drawer toggle
        mDrawerToggle.syncState();


/*
        alarm = new Alarm();
        Button clockOut = (Button) findViewById(R.id.button_check_out);
        clockOut.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(alarm != null) {
                    alarm.CancelAlarm(getApplicationContext());
                    Toast.makeText(getApplicationContext(), "You won't receive any more notifications until you check in tomorrow.", Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(getApplicationContext(), "Alarm is null", Toast.LENGTH_SHORT).show();
                }
            }
        });
*/

        fabToolbar = ((FabToolbar) findViewById(R.id.fab_toolbar));
        fabToolbar.setColor(getResources().getColor(R.color.nw_red));
        TextView title = (TextView) findViewById(R.id.go);
        title.setText(getResources().getString(R.string.clock_out));
        fabToolbar.attachToListView(listView);
        findViewById(R.id.attach).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(TimeManagement.this, MainActivity.class);
                startActivity(intent);
                fabToolbar.hide();
                finish();
            }
        });

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
}
