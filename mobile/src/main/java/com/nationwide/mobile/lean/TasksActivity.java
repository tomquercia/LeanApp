package com.nationwide.mobile.lean;

import android.content.Context;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.CheckedTextView;
import android.widget.ExpandableListView;
import android.widget.TextView;

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

    protected Context mContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tasks);
        mContext = this;

        toolbar = (Toolbar) findViewById(R.id.tool_bar);
        //toolbar.setTitle(new SimpleDateFormat("EEEE, MMMM dd, yyyy").format(new Date()));
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        categoriesList = (ExpandableListView)findViewById(R.id.categories);
        Category.context = this;
        categories = Category.getCategories();
        adapter = new ListAdapter(this,
                categories, categoriesList);
        categoriesList.setAdapter(adapter);

        categoriesList.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {

            @Override
            public boolean onChildClick(ExpandableListView parent, View v,
                                        int groupPosition, int childPosition, long id) {


                CheckedTextView checkbox = (CheckedTextView) v.findViewById(R.id.list_item_text_child);
                checkbox.toggle();


                // find parent view by tag
                View parentView = categoriesList.findViewWithTag(categories.get(groupPosition).name);
                if (parentView != null) {
                    TextView sub = (TextView) parentView.findViewById(R.id.list_item_text_subscriptions);
                    TextView parentText = (TextView) parentView.findViewById(R.id.list_item_text_view);

                    if (sub != null) {
                        Category category = categories.get(groupPosition);
                        if (checkbox.isChecked()) {
                            // add child category to parent's selection list
                            category.selection.add(checkbox.getText().toString());

                            // sort list in alphabetical order
                            parentText.setTextColor(getResources().getColor(R.color.nw_lightblue));
                            Collections.sort(category.selection, new CustomComparator());
                        } else {
                            // remove child category from parent's selection list
                            category.selection.remove(checkbox.getText().toString());
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
