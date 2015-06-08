package com.nationwide.mobile.lean;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

/**
 * Created by querct1 on 5/26/2015.
 */
public class TimeAdapter extends BaseAdapter {
    private Context context;
    String[] hours;
    LayoutInflater inflater = null;
    boolean white;
    String[] fullHours;

    public TimeAdapter(Context context, String[] hours, String[] fullHours, boolean white){
        this.context=context;
        this.hours=hours;
        this.white=white;
        inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        this.fullHours = fullHours;
        Log.d("Lean", "Adapter has been set");
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View listView;
        if(position > 0){
            position = position*4;
        }

        if(convertView == null){
            //get layout from hours_list.xml
            listView = inflater.inflate(R.layout.hours_list, null);
        }
        else{
            listView = (View)convertView;
        }


        final View boxView1 = listView.findViewById(R.id.box1);
        boxView1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, TasksActivity.class);
                TextView t = (TextView)boxView1.findViewById(R.id.textview_time1);
                String time = t.getText().toString();
                intent.putExtra("TIME", time);
                context.startActivity(intent);
            }
        });
        final View boxView2 = listView.findViewById(R.id.box2);
        boxView2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, TasksActivity.class);
                TextView t = (TextView)boxView2.findViewById(R.id.textview_time2);
                String time = t.getText().toString();
                intent.putExtra("TIME", time);
                context.startActivity(intent);
            }
        });
        final View boxView3 = listView.findViewById(R.id.box3);
        boxView3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, TasksActivity.class);
                TextView t = (TextView)boxView3.findViewById(R.id.textview_time3);
                String time = t.getText().toString();
                intent.putExtra("TIME", time);
                context.startActivity(intent);
            }
        });
        final View boxView4 = listView.findViewById(R.id.box4);
        boxView4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, TasksActivity.class);
                TextView t = (TextView)boxView4.findViewById(R.id.textview_time4);
                String time = t.getText().toString();
                intent.putExtra("TIME", time);
                context.startActivity(intent);
            }
        });

        Log.d("Lean", "Iteration " + position);
        TextView top = (TextView) listView.findViewById(R.id.textView_hour);
        TextView textView1 = (TextView) listView.findViewById(R.id.textview_time1);
        TextView textView2 = (TextView) listView.findViewById(R.id.textview_time2);
        TextView textView3 = (TextView) listView.findViewById(R.id.textview_time3);
        TextView textView4 = (TextView) listView.findViewById(R.id.textview_time4);
        TextView bottomTextView1 = (TextView) listView.findViewById(R.id.textview_time_until1);
        TextView bottomTextView2 = (TextView) listView.findViewById(R.id.textview_time_until2);
        TextView bottomTextView3 = (TextView) listView.findViewById(R.id.textview_time_until3);
        TextView bottomTextView4 = (TextView) listView.findViewById(R.id.textview_time_until4);

        textView1.setText(hours[position]);
        textView2.setText(hours[position+1]);
        textView3.setText(hours[position+2]);
        textView4.setText(hours[position+3]);
        top.setText(fullHours[position/4]);


        if(white){
            top.setTextColor(context.getResources().getColor(R.color.white_67));
            boxView1.setBackgroundColor(context.getResources().getColor(R.color.nw_green_card));
            boxView2.setBackgroundColor(context.getResources().getColor(R.color.nw_green_card));
            boxView3.setBackgroundColor(context.getResources().getColor(R.color.nw_green_card));
            boxView4.setBackgroundColor(context.getResources().getColor(R.color.nw_green_card));
            textView1.setTextColor(context.getResources().getColor(R.color.white_87));
            textView2.setTextColor(context.getResources().getColor(R.color.white_87));
            textView3.setTextColor(context.getResources().getColor(R.color.white_87));
            textView4.setTextColor(context.getResources().getColor(R.color.white_87));
            bottomTextView1.setTextColor(context.getResources().getColor(R.color.white_54));
            bottomTextView2.setTextColor(context.getResources().getColor(R.color.white_54));
            bottomTextView3.setTextColor(context.getResources().getColor(R.color.white_54));
            bottomTextView4.setTextColor(context.getResources().getColor(R.color.white_54));
        } else {
            top.setTextColor(context.getResources().getColor(R.color.black_67));
            boxView1.setBackgroundColor(context.getResources().getColor(R.color.grey));
            boxView2.setBackgroundColor(context.getResources().getColor(R.color.grey));
            boxView3.setBackgroundColor(context.getResources().getColor(R.color.grey));
            boxView4.setBackgroundColor(context.getResources().getColor(R.color.grey));
            textView1.setTextColor(context.getResources().getColor(R.color.black_87));
            textView2.setTextColor(context.getResources().getColor(R.color.black_87));
            textView3.setTextColor(context.getResources().getColor(R.color.black_87));
            textView4.setTextColor(context.getResources().getColor(R.color.black_87));
            bottomTextView1.setTextColor(context.getResources().getColor(R.color.black_54));
            bottomTextView2.setTextColor(context.getResources().getColor(R.color.black_54));
            bottomTextView3.setTextColor(context.getResources().getColor(R.color.black_54));
            bottomTextView4.setTextColor(context.getResources().getColor(R.color.black_54));
        }

        return listView;
    }

    @Override
    public int getCount() {
        return hours.length/4;
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
