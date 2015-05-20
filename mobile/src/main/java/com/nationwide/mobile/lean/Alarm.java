package com.nationwide.mobile.lean;

import android.app.AlarmManager;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.PowerManager;
import android.support.v4.app.NotificationCompat;
import android.util.Log;

/**
 * Created by querct1 on 5/15/2015.
 */
public class Alarm extends BroadcastReceiver
{

    public int alertNumber;
    final public static String ONE_TIME = "onetime";
    @Override
    public void onReceive(Context context, Intent intent) {
        Log.d("Lean", "Received alert");
        PowerManager pm = (PowerManager) context.getSystemService(Context.POWER_SERVICE);
        PowerManager.WakeLock wl = pm.newWakeLock(PowerManager.PARTIAL_WAKE_LOCK, "YOUR TAG");
        //Acquire the lock
        wl.acquire();

        //You can do the processing here update the widget/remote views.
        Bundle extras = intent.getExtras();

        //Toast.makeText(context, msgStr, Toast.LENGTH_LONG).show();
        Intent alarmIntent = new Intent(context, TimeManagement.class);
        alarmIntent.putExtra("ALERT_NUMBER",alertNumber);
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
