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



/*
                LinearLayout linear= new LinearLayout(MainActivity.this);
                linear.setOrientation(LinearLayout.VERTICAL);
                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                builder.setTitle("Profile creation");

// Set up the input
                final EditText name = new EditText(getApplicationContext());
                final EditText role = new EditText(getApplicationContext());
                final EditText team = new EditText(getApplicationContext());
                final EditText manager = new EditText(getApplicationContext());
                final EditText city = new EditText(getApplicationContext());
                final EditText floor = new EditText(getApplicationContext());
// Specify the type of input expected; this, for example, sets the input as a password, and will mask the text
                name.setInputType(InputType.TYPE_CLASS_TEXT);
                name.setHint("Your Name");
                name.setHintTextColor(getResources().getColor(R.color.nw_blue));
                linear.addView(name);

                role.setInputType(InputType.TYPE_CLASS_TEXT);
                role.setHint("Your Role");
                role.setHintTextColor(getResources().getColor(R.color.nw_blue));
                linear.addView(role);

                team.setInputType(InputType.TYPE_CLASS_TEXT);
                team.setHint("Your Team");
                team.setHintTextColor(getResources().getColor(R.color.nw_blue));
                linear.addView(team);

                manager.setInputType(InputType.TYPE_CLASS_TEXT);
                manager.setHint("Your Manager");
                manager.setHintTextColor(getResources().getColor(R.color.nw_blue));
                linear.addView(manager);

                city.setInputType(InputType.TYPE_CLASS_TEXT);
                city.setHint("Your City");
                city.setHintTextColor(getResources().getColor(R.color.nw_blue));
                linear.addView(city);

                floor.setInputType(InputType.TYPE_CLASS_NUMBER);
                floor.setHint("Your Floor");
                floor.setHintTextColor(getResources().getColor(R.color.nw_blue));
                linear.addView(floor);

                builder.setView(linear);

// Set up the buttons
                builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(getApplicationContext(), name.getText().toString()
                                        + role.getText().toString()
                                        + team.getText().toString()
                                        + manager.getText().toString()
                                        + city.getText().toString()
                                        + floor.getText().toString(),
                                Toast.LENGTH_SHORT).show();
                        //m_Text = input.getText().toString();
                    }
                });
                builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                });

                builder.show();
*/

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
