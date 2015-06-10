package com.nationwide.mobile.lean;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.util.Log;

import com.google.gson.Gson;

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
            Gson gson = new Gson();
            String json = prefs.getString("quarterHour", "");
            Log.d("Lean", "Reading the json!!! " + json);
            QuarterHour ret = gson.fromJson(json, QuarterHour.class);
/*

            ArrayList<Category> categories = new ArrayList<>();
            QuarterHour ret = new QuarterHour(quarterHour, categories);
            try {
                ret = (QuarterHour)ObjectSerializer.deserialize(prefs.getString("quarterHour", ObjectSerializer.serialize(new QuarterHour(quarterHour,categories))));
            } catch (IOException e){
                e.printStackTrace();
            }*/

            return ret;
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
        QuarterHour ret = new QuarterHour(quarterHour,categories);
        Gson gson = new Gson();
        String json = gson.toJson(ret);
        prefsEditor.putString("quarterHour", json);
        Log.d("Lean", "Saving the json "+json);
/*        try {
            prefsEditor.putString("categories", ObjectSerializer.serialize(categories));
        } catch (IOException e){
            e.printStackTrace();
        }
        prefsEditor.putString("quarterHour", quarterHour);*/
/*
        try {
            prefsEditor.putString("quarterHour", ObjectSerializer.serialize(ret));
            Log.d("Lean", "Serialization complete "+ObjectSerializer.serialize(ret));
        } catch (IOException e){
            e.printStackTrace();
        }*/
        prefsEditor.putBoolean("valid", true);
        prefsEditor.commit();
        return ret;
    }
}