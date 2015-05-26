package com.nationwide.mobile.lean;

import android.app.Application;
import android.widget.Toast;

import com.google.android.gms.analytics.GoogleAnalytics;
import com.google.android.gms.analytics.Tracker;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GooglePlayServicesUtil;

/**
 * Created by querct1 on 5/22/2015.
 */
public class App extends Application {
    public static GoogleAnalytics analytics;
    public static Tracker tracker;

    @Override
    public void onCreate() {
/*        if (GooglePlayServicesUtil.isGooglePlayServicesAvailable(this) == ConnectionResult.SUCCESS) {
            analytics = GoogleAnalytics.getInstance(getApplicationContext());
            analytics.setLocalDispatchPeriod(1800);

            tracker = analytics.newTracker("UA-63001923-2"); // Replace with actual tracker/property Id
            tracker.enableExceptionReporting(true);
            tracker.enableAdvertisingIdCollection(true);
            tracker.enableAutoActivityTracking(true);
        }*/
    }
}
