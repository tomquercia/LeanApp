package com.nationwide.mobile.lean;

import android.content.Intent;
import android.provider.ContactsContract;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;


public class ProfileActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        Button save = (Button) findViewById(R.id.button_save_profile);
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText firstName = (EditText) findViewById(R.id.first_name);
                EditText lastName = (EditText) findViewById(R.id.last_name);
                EditText role = (EditText) findViewById(R.id.role);
                EditText managerFirstName = (EditText) findViewById(R.id.manager_first_name);
                EditText managerLastName = (EditText) findViewById(R.id.manager_last_name);
                EditText city = (EditText) findViewById(R.id.edittext_city);
                EditText floorNumber = (EditText) findViewById(R.id.edittext_floor);
                EditText building = (EditText) findViewById(R.id.edittext_building);
                UserCreator.createUser(getApplicationContext(),
                        firstName.getText().toString(),
                        lastName.getText().toString(),
                        role.getText().toString(),
                        managerFirstName.getText().toString(),
                        managerLastName.getText().toString(),
                        city.getText().toString(),
                        building.getText().toString(),
                        floorNumber.getText().toString());
                Intent intent = new Intent(ProfileActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_profile, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
