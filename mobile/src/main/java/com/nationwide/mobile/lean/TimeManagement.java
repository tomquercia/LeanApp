package com.nationwide.mobile.lean;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.GestureDetector;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.Transformation;
import android.widget.Button;
import android.widget.GridView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.github.alexkolpa.fabtoolbar.FabToolbar;
import com.google.gson.Gson;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.lang.reflect.Array;
import java.sql.Time;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;


public class TimeManagement extends ActionBarActivity {

    private Alarm alarm;
    String TITLES[] = {"Edit My Profile",
            "Notification Settings",
            "Clock in/out"};
    int ICONS[] = {R.drawable.ic_profile,
            R.drawable.ic_settings,
            R.drawable.ic_clock_dark};

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
    public User user;
    String[] HOURS = new String[]{
            "9:00", "9:15", "9:30", "9:45", "10:00", "10:15", "10:30", "10:45", "11:00", "11:15", "11:30", "11:45", "12:00"
    };
    String[] FULLHOURS = new String[]{
            "9:00", "10:00", "11:00", "12:00"
    };
    public static ArrayList<String> unfilledHours = new ArrayList<String>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_time_management);
        alarm = new Alarm();
        toolbar = (Toolbar) findViewById(R.id.tool_bar);
        toolbar.setTitle(new SimpleDateFormat("EEEE, MMMM dd, yyyy").format(new Date()));
        setSupportActionBar(toolbar);

        HOURS = getResources().getStringArray(R.array.times);
        FULLHOURS = getResources().getStringArray(R.array.full_hours);

        Calendar cal = Calendar.getInstance();
        final int hour = cal.get(Calendar.HOUR_OF_DAY)-7;
        final int minute = cal.get(Calendar.MINUTE);
        final int quarter;

        quarter=(int)Math.floor(minute/15);

        SharedPreferences sharedPrefs = getApplicationContext().getSharedPreferences("CHECKINHOUR", Context.MODE_PRIVATE);
        int checkInTime = sharedPrefs.getInt("checkInTime", 7);
        int checkInMinute = sharedPrefs.getInt("checkInMinute",0);
        int firstUnfinished = hour;
        /*hour=hour-7;
        hour=hour%12;*/

        //HAVE TO GET THE LAST FULL QUARTER HOUR HERE!

        /*Toast.makeText(TimeManagement.this, "The checkintime is "+checkInTime,Toast.LENGTH_SHORT).show();
        Toast.makeText(TimeManagement.this, "The current minute is "+quarter,Toast.LENGTH_SHORT).show();*/


        for(int i=(checkInTime-7)*4+checkInMinute; i<HOURS.length && i<(hour)*4+quarter; i++){
            if(QuarterHourCreator.getQuarterHour(getApplicationContext(), TimeManagement.this, HOURS[i])==null){
                Log.d("Lean", "We're running through the quarter hours with "+HOURS[i]);
/*
                Toast.makeText(TimeManagement.this, "The most recent unfilled time is "+HOURS[i],Toast.LENGTH_SHORT).show();
*/
                unfilledHours.add(HOURS[i]);
                if(firstUnfinished==hour){
                    firstUnfinished=i/4;
                }
            }
        }
/*
        Toast.makeText(TimeManagement.this, "The spilt is at "+firstUnfinished*4, Toast.LENGTH_SHORT).show();
        Toast.makeText(TimeManagement.this, "The length of the unfilled time is "+unfilledHours.size(), Toast.LENGTH_SHORT).show();*/

        int unfinishedHour=0;


        if(unfilledHours.size() != 0){
            for(int i=0;i<HOURS.length; i++){
                if(HOURS[i].equalsIgnoreCase(unfilledHours.get(0))){
                    unfinishedHour=i;
                }
            }
            unfinishedHour = (int)Math.floor(unfinishedHour/4);
            unfinishedHour = unfinishedHour*4;

/*
            Toast.makeText(TimeManagement.this, "The unfinished Hour that should work is "+unfinishedHour, Toast.LENGTH_SHORT).show();
*/
            Log.d("Lean", "The unfinished Hour that should work is "+unfinishedHour);

            String[] quarterHoursStart = Arrays.copyOfRange(HOURS,unfinishedHour, HOURS.length);
            String[] fullHourStart = Arrays.copyOfRange(FULLHOURS, unfinishedHour/4, FULLHOURS.length);
            for(int i=0; i<quarterHoursStart.length;i++){
                Log.d("Lean", "QuarterHoursStart "+quarterHoursStart[i]);
            }

            for(int i=0; i<fullHourStart.length;i++){
                Log.d("Lean", "fullHourStart "+fullHourStart[i]);
            }


            String[] quarterHoursFinish = Arrays.copyOfRange(HOURS, 0, unfinishedHour);
            String[] fullHoursFinish = Arrays.copyOfRange(FULLHOURS, 0, unfinishedHour/4);

            for(int i=0; i<quarterHoursFinish.length;i++){
                Log.d("Lean", "QuarterHoursStart "+quarterHoursFinish[i]);
            }

            for(int i=0; i<fullHoursFinish.length;i++){
                Log.d("Lean", "fullHourStart "+fullHoursFinish[i]);
            }

            listView = (ListView) findViewById(R.id.listView_future);
            listView.setAdapter(new TimeAdapter(this, quarterHoursStart, fullHourStart, false));

            hiddenView = (ListView)findViewById(R.id.listView_previous);
            hiddenView.setAdapter(new TimeAdapter(this, quarterHoursFinish, fullHoursFinish, true));
        }else {

            listView = (ListView) findViewById(R.id.listView_future);
            listView.setAdapter(new TimeAdapter(this, Arrays.copyOfRange(HOURS, hour * 4, HOURS.length), Arrays.copyOfRange(FULLHOURS, hour, FULLHOURS.length), false));

            hiddenView = (ListView) findViewById(R.id.listView_previous);
            hiddenView.setAdapter(new TimeAdapter(this, Arrays.copyOfRange(HOURS, 0, hour * 4), Arrays.copyOfRange(FULLHOURS, 0, hour), true));
        }
        final Button showPast = (Button) findViewById(R.id.button_previous_times);
        showPast.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(enabled == 0) {
                    hiddenView.setVisibility(View.VISIBLE);
                    expand((LinearLayout) findViewById(R.id.times_drawer));
                    showPast.setText(getResources().getString(R.string.hide_previous));
                    hiddenView.setSelection(hiddenView.getCount() - 1);
                    enabled = 1;
                }
                else{
                    collapse((LinearLayout)findViewById(R.id.times_drawer));
                    showPast.setText(getResources().getString(R.string.show_previous));
                    //hiddenView.setVisibility(View.GONE);
                    enabled=0;
                }
            }
        });
        Log.d("Lean", "Adapter set");

        user=UserCreator.getUser(getApplicationContext(), this);

        mRecyclerView = (RecyclerView) findViewById(R.id.RecyclerView); // Assigning the RecyclerView Object to the xml View

        mRecyclerView.setHasFixedSize(true);                            // Letting the system know that the list objects are of fixed size

        mAdapter = new MyAdapter(TITLES,ICONS,user.getFirstName()+" "+user.getLastName(),user.getTeam(),PROFILE);       // Creating the Adapter of MyAdapter class
        // And passing the titles,icons,header view name, header view email,
        // and header view profile picture

        mRecyclerView.setAdapter(mAdapter);                              // Setting the adapter to RecyclerView

        final GestureDetector mGestureDetector = new GestureDetector(TimeManagement.this, new GestureDetector.SimpleOnGestureListener() {

            @Override public boolean onSingleTapUp(MotionEvent e) {
                return true;
            }

        });

        mRecyclerView.addOnItemTouchListener(new RecyclerView.OnItemTouchListener() {
            @Override
            public boolean onInterceptTouchEvent(RecyclerView recyclerView, MotionEvent motionEvent) {
                View child = recyclerView.findChildViewUnder(motionEvent.getX(),motionEvent.getY());



                if(child!=null && mGestureDetector.onTouchEvent(motionEvent)){
                    Drawer.closeDrawers();
/*
                    Toast.makeText(TimeManagement.this,"The Item Clicked is: "+recyclerView.getChildPosition(child),Toast.LENGTH_SHORT).show();
*/
                    if(recyclerView.getChildPosition(child)==1){
                        Intent intent = new Intent(TimeManagement.this, ProfileActivity.class);
                        startActivity(intent);
                    }else if(recyclerView.getChildPosition(child)==2){

                    }else if(recyclerView.getChildPosition(child)==3){
                        alarm.CancelAlarm(getApplicationContext());
                        Intent intent = new Intent(TimeManagement.this, MainActivity.class);
                        startActivity(intent);
                        fabToolbar.hide();
                        finish();
                    }

                    return true;

                }

                return false;
            }

            @Override
            public void onTouchEvent(RecyclerView recyclerView, MotionEvent motionEvent) {

            }
        });

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

        fabToolbar = ((FabToolbar) findViewById(R.id.fab_toolbar));
        fabToolbar.setColor(getResources().getColor(R.color.nw_red));
        TextView title = (TextView) findViewById(R.id.go);
        title.setText(getResources().getString(R.string.clock_out));
        fabToolbar.attachToListView(listView);
        findViewById(R.id.attach).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                alarm.CancelAlarm(getApplicationContext());
                fabToolbar.hide();
                User user = UserCreator.getUser(getApplicationContext(), TimeManagement.this);
                Gson gson = new Gson();
                String jsonUser = gson.toJson(user);
                String output = "{\"user\":"+jsonUser;
                for(int i=0; i<HOURS.length; i++){
                    SharedPreferences prefs = getApplicationContext().getSharedPreferences(HOURS[i], Context.MODE_PRIVATE);;
/*
                    QuarterHour qh = QuarterHourCreator.getQuarterHour(getApplicationContext(), TimeManagement.this, HOURS[i]);
*/
                    if(prefs.getBoolean("valid", false)){
                        Log.d("Lean", "User Profile Already Exists");
                        String json = prefs.getString("quarterHour", "");
                        Log.d("Lean", "Reading the json!!! " + json);
                        output += ",\""+HOURS[i]+"\":"+json;
                    }else{
                        Log.d("Lean", "Saved time does not exist");
                    }
                }
                output+="}";

                try {
                    // catches IOException below
       /* We have to use the openFileOutput()-method
       * the ActivityContext provides, to
       * protect your file from others and
       * This is done for security-reasons.
       * We chose MODE_WORLD_READABLE, because
       *  we have nothing to hide in our file */
                    Calendar cal = Calendar.getInstance();
                    String date = cal.getTime().toString();
                    FileOutputStream fOut = openFileOutput(user.getFirstName().charAt(0)+user.getLastName().charAt(0)+date,
                            MODE_WORLD_READABLE);
                    OutputStreamWriter osw = new OutputStreamWriter(fOut);

                    // Write the string to the file
                    osw.write(output);

       /* ensure that everything is
        * really written out and close */
                    osw.flush();
                    osw.close();

//Reading the file back...

       /* We have to use the openFileInput()-method
        * the ActivityContext provides.
        * Again for security reasons with
        * openFileInput(...) */

                    FileInputStream fIn = openFileInput(user.getFirstName()+user.getLastName());
                    InputStreamReader isr = new InputStreamReader(fIn);

        /* Prepare a char-Array that will
         * hold the chars we read back in. */
                    char[] inputBuffer = new char[output.length()];

                    // Fill the Buffer with data from the file
                    isr.read(inputBuffer);

                    // Transform the chars to a String
                    String readString = new String(inputBuffer);

                    // Check if we read back the same chars that we had written out
                    boolean isTheSame = output.equals(readString);

                    Log.i("File Reading stuff", "success = " + isTheSame);

                } catch (IOException ioe)
                {ioe.printStackTrace();}

                Intent intent = new Intent(TimeManagement.this, MainActivity.class);
                startActivity(intent);

                finish();
            }
        });

    }

    public static void expand(final View v) {
        v.measure(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        final int targetHeight = v.getMeasuredHeight();

        v.getLayoutParams().height = 0;
        v.setVisibility(View.VISIBLE);
        Animation a = new Animation()
        {
            @Override
            protected void applyTransformation(float interpolatedTime, Transformation t) {
                v.getLayoutParams().height = interpolatedTime == 1
                        ? ViewGroup.LayoutParams.WRAP_CONTENT
                        : (int)(targetHeight * interpolatedTime);
                v.requestLayout();
            }

            @Override
            public boolean willChangeBounds() {
                return true;
            }
        };

        // 1dp/ms
        a.setDuration((int)(targetHeight / v.getContext().getResources().getDisplayMetrics().density));
        v.startAnimation(a);
    }

    public static void collapse(final View v) {
        final int initialHeight = v.getMeasuredHeight();

        Animation a = new Animation()
        {
            @Override
            protected void applyTransformation(float interpolatedTime, Transformation t) {
                if(interpolatedTime == 1){
                    v.setVisibility(View.GONE);
                }else{
                    v.getLayoutParams().height = initialHeight - (int)(initialHeight * interpolatedTime);
                    v.requestLayout();
                }
            }

            @Override
            public boolean willChangeBounds() {
                return true;
            }
        };

        // 1dp/ms
        a.setDuration((int) (initialHeight / v.getContext().getResources().getDisplayMetrics().density));
        v.startAnimation(a);
    }

    @Override
    public void onStart(){
        super.onStart();
/*        Log.d("Lean", "in the on start");
        DrawerLayout drawerLayout = (DrawerLayout)findViewById(R.id.drawer_layout1);
        drawerLayout.invalidate();

        HOURS = getResources().getStringArray(R.array.times);
        FULLHOURS = getResources().getStringArray(R.array.full_hours);

        Calendar cal = Calendar.getInstance();
        int hour = cal.get(Calendar.HOUR_OF_DAY);

        listView = (ListView) findViewById(R.id.listView_future);
        listView.setAdapter(new TimeAdapter(this, Arrays.copyOfRange(HOURS, hour * 4, HOURS.length), Arrays.copyOfRange(FULLHOURS, hour, FULLHOURS.length), false));

        hiddenView = (ListView)findViewById(R.id.listView_previous);
        hiddenView.setAdapter(new TimeAdapter(this, Arrays.copyOfRange(HOURS, 0, hour * 4), Arrays.copyOfRange(FULLHOURS, 0, hour), true));*/

        HOURS = getResources().getStringArray(R.array.times);
        FULLHOURS = getResources().getStringArray(R.array.full_hours);

        Calendar cal = Calendar.getInstance();
        final int hour = cal.get(Calendar.HOUR_OF_DAY)-7;
        final int minute = cal.get(Calendar.MINUTE);
        final int quarter;

        quarter=(int)Math.floor(minute/15);

        SharedPreferences sharedPrefs = getApplicationContext().getSharedPreferences("CHECKINHOUR", Context.MODE_PRIVATE);
        int checkInTime = sharedPrefs.getInt("checkInTime", 7);
        int checkInMinute = sharedPrefs.getInt("checkInMinute",0);
        int firstUnfinished = hour;
        /*hour=hour-7;
        hour=hour%12;*/

        //HAVE TO GET THE LAST FULL QUARTER HOUR HERE!

        /*Toast.makeText(TimeManagement.this, "The checkintime is "+checkInTime,Toast.LENGTH_SHORT).show();
        Toast.makeText(TimeManagement.this, "The current minute is "+quarter,Toast.LENGTH_SHORT).show();*/


        for(int i=(checkInTime-7)*4+checkInMinute; i<(hour)*4+quarter; i++){
            if(QuarterHourCreator.getQuarterHour(getApplicationContext(), TimeManagement.this, HOURS[i])==null){
                Log.d("Lean", "We're running through the quarter hours with "+HOURS[i]);
/*
                Toast.makeText(TimeManagement.this, "The most recent unfilled time is "+HOURS[i],Toast.LENGTH_SHORT).show();
*/
                unfilledHours.add(HOURS[i]);
                if(firstUnfinished==hour){
                    firstUnfinished=i/4;
                }
            }
        }

/*        Toast.makeText(TimeManagement.this, "The spilt is at "+firstUnfinished*4, Toast.LENGTH_SHORT).show();
        Toast.makeText(TimeManagement.this, "The length of the unfilled time is "+unfilledHours.size(), Toast.LENGTH_SHORT).show();*/

        int unfinishedHour=0;


        if(unfilledHours.size() != 0){
            for(int i=0;i<HOURS.length; i++){
                if(HOURS[i].equalsIgnoreCase(unfilledHours.get(0))){
                    unfinishedHour=i;
                }
            }
            unfinishedHour = (int)Math.floor(unfinishedHour/4);
            unfinishedHour = unfinishedHour*4;

/*
            Toast.makeText(TimeManagement.this, "The unfinished Hour that should work is "+unfinishedHour, Toast.LENGTH_SHORT).show();
*/
            Log.d("Lean", "The unfinished Hour that should work is "+unfinishedHour);

            String[] quarterHoursStart = Arrays.copyOfRange(HOURS,unfinishedHour, HOURS.length);
            String[] fullHourStart = Arrays.copyOfRange(FULLHOURS, unfinishedHour/4, FULLHOURS.length);
            for(int i=0; i<quarterHoursStart.length;i++){
                Log.d("Lean", "QuarterHoursStart "+quarterHoursStart[i]);
            }

            for(int i=0; i<fullHourStart.length;i++){
                Log.d("Lean", "fullHourStart "+fullHourStart[i]);
            }


            String[] quarterHoursFinish = Arrays.copyOfRange(HOURS, 0, unfinishedHour);
            String[] fullHoursFinish = Arrays.copyOfRange(FULLHOURS, 0, unfinishedHour/4);

            for(int i=0; i<quarterHoursFinish.length;i++){
                Log.d("Lean", "QuarterHoursStart "+quarterHoursFinish[i]);
            }

            for(int i=0; i<fullHoursFinish.length;i++){
                Log.d("Lean", "fullHourStart "+fullHoursFinish[i]);
            }

            listView = (ListView) findViewById(R.id.listView_future);
            listView.setAdapter(new TimeAdapter(this, quarterHoursStart, fullHourStart, false));

            hiddenView = (ListView)findViewById(R.id.listView_previous);
            hiddenView.setAdapter(new TimeAdapter(this, quarterHoursFinish, fullHoursFinish, true));
        }else {

            listView = (ListView) findViewById(R.id.listView_future);
            listView.setAdapter(new TimeAdapter(this, Arrays.copyOfRange(HOURS, hour * 4, HOURS.length), Arrays.copyOfRange(FULLHOURS, hour, FULLHOURS.length), false));

            hiddenView = (ListView) findViewById(R.id.listView_previous);
            hiddenView.setAdapter(new TimeAdapter(this, Arrays.copyOfRange(HOURS, 0, hour * 4), Arrays.copyOfRange(FULLHOURS, 0, hour), true));
        }
    }

    public static ArrayList<String> getUnfilledHours(){
        return unfilledHours;
    }

    public static void setHourFilled(String toRemove){
        unfilledHours.remove(unfilledHours.indexOf(toRemove));
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
