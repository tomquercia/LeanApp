package com.nationwide.mobile.lean;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.util.Log;

/**
 * Created by querct1 on 6/2/2015.
 */
public class UserCreator {
    public static User getUser(Context context, Context activity){
        SharedPreferences prefs = context.getSharedPreferences("USER", Context.MODE_PRIVATE);

        if(prefs.getBoolean("valid", false)){
            Log.d("Lean", "User Profile Already Exists");
            String firstName = prefs.getString("firstName","");
            String lastName = prefs.getString("lastName","");
            String role = prefs.getString("role","");
            String managerFirstName = prefs.getString("managerFirstName","");
            String managerLastName = prefs.getString("managerLastName","");
            String city = prefs.getString("city","");
            String building = prefs.getString("building","");
            String floorNumber = prefs.getString("floorNumber", "");
            return new User (firstName,
                    lastName,
                    role,
                    managerFirstName,
                    managerLastName,
                    city,
                    building,
                    floorNumber);
        }else{
            Log.d("Lean", "User Profile Does Not Exist");
            Intent intent = new Intent(activity, ProfileActivity.class);
            activity.startActivity(intent);
            return null;
        }
    }

    public static User createUser(Context context,
                                  String firstName,
                                  String lastName,
                                  String role,
                                  String managerFirstName,
                                  String managerLastName,
                                  String city,
                                  String building,
                                  String floorNumber){

        Log.d("Lean", "Creating User Profile");
        SharedPreferences prefs = context.getSharedPreferences("USER", Context.MODE_PRIVATE);
        SharedPreferences.Editor prefsEditor = prefs.edit();
        prefsEditor.putString("firstName", firstName);
        prefsEditor.putString("lastName", lastName);
        prefsEditor.putString("role", role);
        prefsEditor.putString("managerFirstName", managerFirstName);
        prefsEditor.putString("managerLastName", managerLastName);
        prefsEditor.putString("city", city);
        prefsEditor.putString("building", building);
        prefsEditor.putString("floorNumber", floorNumber);
        prefsEditor.putBoolean("valid", true);
        prefsEditor.commit();
        return new User(firstName,lastName,role,managerFirstName,managerLastName,city,building,floorNumber);
    }
}
