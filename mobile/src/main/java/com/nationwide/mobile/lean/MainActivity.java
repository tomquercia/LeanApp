package com.nationwide.mobile.lean;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.text.InputType;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.github.alexkolpa.fabtoolbar.FabToolbar;
import com.google.android.gms.analytics.GoogleAnalytics;
import com.google.android.gms.analytics.HitBuilders;
import com.google.android.gms.analytics.Tracker;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GooglePlayServicesUtil;


public class MainActivity extends ActionBarActivity {

    String TITLES[] = {"Edit My Profile",
            "Notification Settings",
            "Clock in/out"};
    int ICONS[] = {R.drawable.ic_profile,
            R.drawable.ic_settings,
            R.drawable.ic_clock_dark};

    String NAME = "Tom Quercia";
    String EMAIL = "tomquercia@gmail.com";
    int PROFILE = R.mipmap.ic_launcher;
    private Alarm alarm;
    private FabToolbar fabToolbar;
    private Toolbar toolbar;
    RecyclerView mRecyclerView;
    RecyclerView.Adapter mAdapter;
    RecyclerView.LayoutManager mLayoutManager;
    DrawerLayout Drawer;
    ActionBarDrawerToggle mDrawerToggle;
    public static GoogleAnalytics analytics;
    public static Tracker tracker;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

/*
        if (GooglePlayServicesUtil.isGooglePlayServicesAvailable(this) == ConnectionResult.SUCCESS) {
            analytics = GoogleAnalytics.getInstance(getApplicationContext());
            analytics.setLocalDispatchPeriod(1800);

            tracker = analytics.newTracker("UA-63001923-2"); // Replace with actual tracker/property Id
            tracker.enableExceptionReporting(true);
            tracker.enableAdvertisingIdCollection(true);
            tracker.enableAutoActivityTracking(true);
        }
*/

        alarm = new Alarm();
        toolbar = (Toolbar) findViewById(R.id.tool_bar);
        setSupportActionBar(toolbar);

        mRecyclerView = (RecyclerView) findViewById(R.id.RecyclerView); // Assigning the RecyclerView Object to the xml View

        mRecyclerView.setHasFixedSize(true);                            // Letting the system know that the list objects are of fixed size

        mAdapter = new MyAdapter(TITLES,ICONS,NAME,EMAIL,PROFILE);       // Creating the Adapter of MyAdapter class
        // And passing the titles,icons,header view name, header view email,
        // and header view profile picture

        mRecyclerView.setAdapter(mAdapter);                              // Setting the adapter to RecyclerView

        mLayoutManager = new LinearLayoutManager(this);                 // Creating a layout Manager

        mRecyclerView.setLayoutManager(mLayoutManager);                 // Setting the layout Manager


        Drawer = (DrawerLayout) findViewById(R.id.drawer_layout);        // Drawer object Assigned to the view
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
        ActionBar actionBar = getSupportActionBar();
        actionBar.setBackgroundDrawable(new ColorDrawable(getResources().getColor(R.color.nw_blue)));
*/
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
        fabToolbar.setColor(getResources().getColor(R.color.nw_green));
        TextView title = (TextView) findViewById(R.id.go);
        title.setText(getResources().getString(R.string.clock_in3));
        findViewById(R.id.attach).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
/*
                GoogleAnalytics analytics = GoogleAnalytics.getInstance(MainActivity.this);
                Tracker tracker = analytics.newTracker("UA-63001923-2");

                tracker.setScreenName("main screen");

                tracker.send(new HitBuilders.EventBuilder()
                        .setCategory("UX")
                        .setAction("click")
                        .setLabel("Check in")
                        .build());
*/
                Intent intent = new Intent(MainActivity.this, TimeManagement.class);
                startActivity(intent);

                //Toast.makeText(getApplicationContext(), "You clicked the button", Toast.LENGTH_SHORT).show();
                fabToolbar.hide();
            }
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
}
