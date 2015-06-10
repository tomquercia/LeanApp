package com.nationwide.mobile.lean;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.util.Log;

import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by querct1 on 6/9/2015.
 */
public class QuarterHourCreator {
    public static QuarterHour getQuarterHour(Context context, Context activity, String quarterHour){
        SharedPreferences prefs = context.getSharedPreferences(quarterHour, Context.MODE_PRIVATE);

        if(prefs.getBoolean("valid", false)){
            Log.d("Lean", "User Profile Already Exists");
            ArrayList<Category> categories = new ArrayList<>();
            try {
                categories = (ArrayList<Category>)ObjectSerializer.deserialize(prefs.getString("categories", ObjectSerializer.serialize(new ArrayList<Category>())));
            } catch (IOException e){
                e.printStackTrace();
            }

            return new QuarterHour (quarterHour, categories);
        }else{
            Log.d("Lean", "Saved time does not exist");
            return null;
        }
    }

    public static QuarterHour createQuarterHour(Context context,
                                                ArrayList<Category> categories,
                                                String quarterHour
    ){

        Log.d("Lean", "saving quarter hour");
        SharedPreferences prefs = context.getSharedPreferences(quarterHour, Context.MODE_PRIVATE);
        SharedPreferences.Editor prefsEditor = prefs.edit();
        try {
            prefsEditor.putString("categories", ObjectSerializer.serialize(categories));
        } catch (IOException e){
            e.printStackTrace();
        }
        prefsEditor.putString("quarterHour", quarterHour);
        prefsEditor.putBoolean("valid", true);
        prefsEditor.commit();
        return new QuarterHour(quarterHour,categories);
    }
}