package com.nationwide.mobile.lean;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by querct1 on 5/26/2015.
 */
public class TimeAdapter extends BaseAdapter {
    private Context context;
    String[] hours;
    LayoutInflater inflater = null;

    public TimeAdapter(Context context, String[] hours){
        this.context=context;
        this.hours=hours;
        inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        Log.d("Lean", "Adapter has been set");
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View listView;

        if(convertView == null){
            //get layout from hours_list.xml
            listView = inflater.inflate(R.layout.hours_list, null);
        }
        else{
            listView = (View)convertView;
        }

        Log.d("Lean", "Iteration " + position);
        TextView textView1 = (TextView) listView.findViewById(R.id.textview_time1);
        TextView textView2 = (TextView) listView.findViewById(R.id.textview_time2);
        TextView textView3 = (TextView) listView.findViewById(R.id.textview_time3);
        TextView textView4 = (TextView) listView.findViewById(R.id.textview_time4);

        textView1.setText(hours[position]);
        textView2.setText(hours[position]);
        textView3.setText(hours[position]);
        textView4.setText(hours[position]);

        return listView;
    }

    @Override
    public int getCount() {
        return hours.length;
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

}
