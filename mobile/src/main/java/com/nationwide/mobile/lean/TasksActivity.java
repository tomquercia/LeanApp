package com.nationwide.mobile.lean;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.Transformation;
import android.widget.Button;
import android.widget.CheckedTextView;
import android.widget.ExpandableListView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;


public class TasksActivity extends ActionBarActivity {
    private ListAdapter adapter;
    private ExpandableListView categoriesList;
    private ArrayList<Category> categories;
    private Toolbar toolbar;
    private SharedPreferences helpPref;
    protected Context mContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tasks);
        mContext = this;
        toolbar = (Toolbar) findViewById(R.id.tool_bar);
        final RelativeLayout rel = (RelativeLayout)findViewById(R.id.helpbox);
        //toolbar.setTitle(new SimpleDateFormat("EEEE, MMMM dd, yyyy").format(new Date()));


        helpPref = this.getSharedPreferences("HELP", Context.MODE_PRIVATE);

        if(helpPref.getBoolean("valid", false)){
            Log.d("Lean", "User has acknowledged help");
            rel.setVisibility(View.GONE);
        }else{
            Log.d("Lean", "User has not acknowledged help");
            rel.setVisibility(View.VISIBLE);
        }
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        categoriesList = (ExpandableListView)findViewById(R.id.categories);
        Category.context = this;
        categories = Category.getCategories();
        adapter = new ListAdapter(this,
                categories, categoriesList);
        categoriesList.setAdapter(adapter);
        Button okButton = (Button) findViewById(R.id.button_ok_got_it);
        okButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //set up the animations here!
                collapse(rel);
                SharedPreferences prefs = TasksActivity.this.getSharedPreferences("HELP", Context.MODE_PRIVATE);
                SharedPreferences.Editor prefsEditor = prefs.edit();
                prefsEditor.putBoolean("valid", true);
                prefsEditor.commit();
            }
        });


        categoriesList.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {

            @Override
            public boolean onChildClick(ExpandableListView parent, View v,
                                        int groupPosition, int childPosition, long id) {


                CheckedTextView checkbox = (CheckedTextView) v.findViewById(R.id.list_item_text_child);
                checkbox.toggle();

                Category category = categories.get(groupPosition);
                if (checkbox.isChecked()) {
                    // add child category to parent's selection list
                    category.selection.add(checkbox.getText().toString());
                    // sort list in alphabetical order
                    Collections.sort(category.selection, new CustomComparator());
                } else {
                    // remove child category from parent's selection list
                    category.selection.remove(checkbox.getText().toString());
                }


                // find parent view by tag
                View parentView = categoriesList.findViewWithTag(categories.get(groupPosition).name);
                if (parentView != null) {
                    TextView sub = (TextView) parentView.findViewById(R.id.list_item_text_subscriptions);
                    TextView parentText = (TextView) parentView.findViewById(R.id.list_item_text_view);

                    if (sub != null) {
                        category = categories.get(groupPosition);
                        if (checkbox.isChecked()) {
                            parentText.setTextColor(getResources().getColor(R.color.nw_lightblue));
                            Collections.sort(category.selection, new CustomComparator());
                        } else {
                            if(category.selection.isEmpty()){
                                parentText.setTextColor(getResources().getColor(R.color.darkgrey));
                            }
                        }

                        // display selection list
                        sub.setText(category.selection.toString());
                    }
                }
                return true;
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

    public class CustomComparator implements Comparator<String> {
        @Override
        public int compare(String o1, String o2) {
            return o1.compareTo(o2);
        }
    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_tasks, menu);
        return true;
    }

}
