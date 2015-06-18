package com.nationwide.mobile.lean;

import android.app.AlarmManager;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.os.PowerManager;
import android.support.v4.app.NotificationCompat;
import android.util.Log;

import com.google.gson.Gson;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by querct1 on 5/15/2015.
 */
public class Alarm extends BroadcastReceiver
{

    public int alertNumber;
    final public static String ONE_TIME = "onetime";
    final public static String ENDOFDAY = "ENDOFDAY";
    @Override
    public void onReceive(Context context, Intent intent) {

        Calendar cal = Calendar.getInstance();
        final int hour = cal.get(Calendar.HOUR_OF_DAY);

        String time = intent.getStringExtra(ENDOFDAY);

        if(time.equalsIgnoreCase(ENDOFDAY)){
            Alarm alarm = new Alarm();
            alarm.CancelAlarm(context.getApplicationContext());
            Intent intent1 = new Intent(context.getApplicationContext(), Alarm.class);
            intent1.putExtra("ENDOFDAY", Boolean.FALSE);
            PendingIntent pi = PendingIntent.getBroadcast(context.getApplicationContext(), 1, intent1, 0);
            AlarmManager alarmManager = (AlarmManager) context.getSystemService(context.ALARM_SERVICE);
            alarmManager.cancel(pi);
            pi.cancel();
            User user = UserCreator.getUser(context.getApplicationContext(), context);
            Gson gson = new Gson();
            String jsonUser = gson.toJson(user);
            String output = "{\"user\":"+jsonUser;

            String[] HOURS = context.getResources().getStringArray(R.array.times);
            for(int i=0; i<HOURS.length; i++){
                SharedPreferences prefs = context.getApplicationContext().getSharedPreferences(HOURS[i], Context.MODE_PRIVATE);;
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
                Calendar calendar = Calendar.getInstance();
                String date = calendar.getTime().toString();
                FileOutputStream fOut = context.openFileOutput(user.getFirstName().charAt(0)+user.getLastName().charAt(0)+date,
                        context.MODE_WORLD_READABLE);
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

                FileInputStream fIn = context.openFileInput(user.getFirstName()+user.getLastName());
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



        }
        else if(hour>7 && hour<20) {
            Log.d("Lean", "Received alert");
            PowerManager pm = (PowerManager) context.getSystemService(Context.POWER_SERVICE);
            PowerManager.WakeLock wl = pm.newWakeLock(PowerManager.PARTIAL_WAKE_LOCK, "YOUR TAG");
            //Acquire the lock
            wl.acquire();

            //You can do the processing here update the widget/remote views.
            Bundle extras = intent.getExtras();

            //Toast.makeText(context, msgStr, Toast.LENGTH_LONG).show();
            Intent alarmIntent = new Intent(context, LauncherActivity.class);
            alarmIntent.putExtra("ALERT_NUMBER", alertNumber);
            PendingIntent resultPendingIntent =
                    PendingIntent.getActivity(
                            context,
                            0,
                            alarmIntent,
                            PendingIntent.FLAG_UPDATE_CURRENT
                    );
            NotificationCompat.Builder noti = new NotificationCompat.Builder(context)
                    .setContentTitle("Time to log your progress!")
                    .setContentText("Please!")
                    .setSmallIcon(R.drawable.ic_notify)
                    .setVibrate(new long[]{0, 250, 250, 250, 250})
                    .setLights(Color.RED, 500, 700)
                    .setContentIntent(resultPendingIntent)
                    .setAutoCancel(true);
//                .setLargeIcon(aBitmap)
            NotificationManager mNotifyMgr = (NotificationManager) context.getSystemService(context.NOTIFICATION_SERVICE);
            mNotifyMgr.notify(alertNumber, noti.build());
            alertNumber++;

            //Release the lock
            wl.release();
        }else if (hour == 20){
            Log.d("Lean", "Received alert");
            PowerManager pm = (PowerManager) context.getSystemService(Context.POWER_SERVICE);
            PowerManager.WakeLock wl = pm.newWakeLock(PowerManager.PARTIAL_WAKE_LOCK, "YOUR TAG");
            //Acquire the lock
            wl.acquire();

            //You can do the processing here update the widget/remote views.
            Bundle extras = intent.getExtras();

            //Toast.makeText(context, msgStr, Toast.LENGTH_LONG).show();
            Intent alarmIntent = new Intent(context, LauncherActivity.class);
            alarmIntent.putExtra("ALERT_NUMBER", alertNumber);
            PendingIntent resultPendingIntent =
                    PendingIntent.getActivity(
                            context,
                            0,
                            alarmIntent,
                            PendingIntent.FLAG_UPDATE_CURRENT
                    );
            NotificationCompat.Builder noti = new NotificationCompat.Builder(context)
                    .setContentTitle("Don't forget to clock out!")
                    .setContentText("Please!")
                    .setSmallIcon(R.drawable.ic_notify)
                    .setVibrate(new long[]{0, 250, 250, 250, 250})
                    .setLights(Color.RED, 500, 700)
                    .setContentIntent(resultPendingIntent)
                    .setAutoCancel(true);
//                .setLargeIcon(aBitmap)
            NotificationManager mNotifyMgr = (NotificationManager) context.getSystemService(context.NOTIFICATION_SERVICE);
            mNotifyMgr.notify(alertNumber, noti.build());
            alertNumber++;

            //Release the lock
            wl.release();
        }

    }
    public void SetAlarm(Context context)
    {
        Log.d("Lean", "Setting Alarm");
        AlarmManager am=(AlarmManager)context.getSystemService(Context.ALARM_SERVICE);
        Intent intent = new Intent(context, Alarm.class);
        intent.putExtra(ONE_TIME, Boolean.FALSE);
        PendingIntent pi = PendingIntent.getBroadcast(context, 0, intent, 0);
//        Long nextQuarterHour = Math.round( (double)( (double)System.currentTimeMillis()/(double)(15*60*1000) ) ) * (15*60*1000) ;
        Log.d("Lean", "Current time is "+System.currentTimeMillis());
        am.setRepeating(AlarmManager.RTC_WAKEUP, nextQuarterHour(), 900000 , pi);
    }

    public void SetOneTimeAlarm(Context context)
    {
        Log.d("Lean", "Setting Alarm");
        AlarmManager am=(AlarmManager)context.getSystemService(Context.ALARM_SERVICE);
        Intent intent = new Intent(context, Alarm.class);
        intent.putExtra(ENDOFDAY, Boolean.FALSE);
        PendingIntent pi = PendingIntent.getBroadcast(context, 1, intent, 0);
//        Long nextQuarterHour = Math.round( (double)( (double)System.currentTimeMillis()/(double)(15*60*1000) ) ) * (15*60*1000) ;
        Log.d("Lean", "Current time is "+System.currentTimeMillis());

        Date dat  = new Date();//initializes to now
        Calendar cal_alarm = Calendar.getInstance();
        Calendar cal_now = Calendar.getInstance();
        cal_now.setTime(dat);
        cal_alarm.setTime(dat);
        cal_alarm.set(Calendar.HOUR_OF_DAY,9);//set the alarm time
        cal_alarm.set(Calendar.MINUTE, 0);
        cal_alarm.set(Calendar.SECOND, 0);
        if(cal_alarm.before(cal_now)){//if its in the past increment
            cal_alarm.add(Calendar.DATE,1);
        }


        am.set(AlarmManager.RTC_WAKEUP,cal_alarm.getTimeInMillis(), pi);
    }

    public Long nextQuarterHour(){
        Long nextQuarterHour = Math.round( (double)( (double)System.currentTimeMillis()/(double)(15*60*1000) ) ) * (15*60*1000) ;
        if(nextQuarterHour < System.currentTimeMillis()){
            nextQuarterHour+=900000;
        }
        Log.d("Lean", "Next Quarter Hour will be "+ nextQuarterHour);
        return nextQuarterHour;
    }

    public void CancelAlarm(Context context)
    {
        Intent intent = new Intent(context, Alarm.class);
        PendingIntent sender = PendingIntent.getBroadcast(context, 0, intent, 0);
        AlarmManager alarmManager = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
        alarmManager.cancel(sender);
    }
}
