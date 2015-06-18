package com.nationwide.mobile.lean;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.drawable.ColorDrawable;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
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
                TextView t = (TextView) boxView1.findViewById(R.id.textview_time1);
                String time = t.getText().toString();
                intent.putExtra("TIME", time);
                context.startActivity(intent);
            }
        });
/*        boxView1.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                Toast.makeText(context, "Long click achieved", Toast.LENGTH_SHORT).show();

                TextView t = (TextView) boxView1.findViewById(R.id.textview_time1);
                String time = t.getText().toString();

                final QuarterHour box1QH = QuarterHourCreator.getQuarterHour(context.getApplicationContext(), context, time);

                if (box1QH != null) {
                    AlertDialog.Builder builder = new AlertDialog.Builder(context);
                    builder.setMessage("Do you want to delete this time entry?")
                            .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int id) {
                                    SharedPreferences prefs = context.getApplicationContext().getSharedPreferences(box1QH.getQuarterHour(), Context.MODE_PRIVATE);
                                    Toast.makeText(context, "The deleting hour is "+box1QH.getQuarterHour(), Toast.LENGTH_SHORT).show();
                                    SharedPreferences.Editor prefsEditor = prefs.edit();
                                    prefsEditor.remove(box1QH.getQuarterHour());
                                    prefsEditor.commit();

                                    Toast.makeText(context, "Deleted time: "+ prefs.getString(box1QH.getQuarterHour(), "null"), Toast.LENGTH_SHORT).show();

                                    //DO WE HAVE TO INVALIDATE THE VIEW HERE???????
                                }
                            })
                            .setNegativeButton("No", new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int id) {
                                    // User cancelled the dialog
                                }
                            });
                    // Create the AlertDialog object and return it
                    builder.create();
                    builder.show();
                }
                else{
                    Toast.makeText(context, "The dialog was null!", Toast.LENGTH_SHORT).show();
                }
                return true;
            }
        });*/


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
        ImageView imageView1 = (ImageView) listView.findViewById(R.id.imageView1);
        ImageView imageView2 = (ImageView) listView.findViewById(R.id.imageView2);
        ImageView imageView3 = (ImageView) listView.findViewById(R.id.imageView3);
        ImageView imageView4 = (ImageView) listView.findViewById(R.id.imageView4);

        textView1.setText(hours[position]);
        textView2.setText(hours[position+1]);
        textView3.setText(hours[position+2]);
        textView4.setText(hours[position+3]);
        top.setText(fullHours[position/4]);


        if(white){
            top.setTextColor(context.getResources().getColor(R.color.white_67));

            String time = textView1.getText().toString();
            if(QuarterHourCreator.getQuarterHour(context.getApplicationContext(), context, time) != null){
                boxView1.setBackgroundColor(context.getResources().getColor(R.color.nw_green));
                textView1.setTextColor(context.getResources().getColor(R.color.white));
                bottomTextView1.setTextColor(context.getResources().getColor(R.color.white));
                imageView1.setImageDrawable(context.getResources().getDrawable(R.drawable.ic_check));
                //imageView1.setPadding(0, 0, 0, 0);
                bottomTextView1.setText("Completed");
            }else {
                boxView1.setBackgroundColor(context.getResources().getColor(R.color.nw_green_card));
                textView1.setTextColor(context.getResources().getColor(R.color.white_87));
                bottomTextView1.setTextColor(context.getResources().getColor(R.color.white_54));
                imageView1.setImageDrawable(context.getResources().getDrawable(R.drawable.chevron));
                //imageView1.setPadding(0, 3, 0, 3);
                bottomTextView1.setText("Not Entered");
            }

            String time2 = textView2.getText().toString();
            if(QuarterHourCreator.getQuarterHour(context.getApplicationContext(), context, time2) != null){
                boxView2.setBackgroundColor(context.getResources().getColor(R.color.nw_green));
                textView2.setTextColor(context.getResources().getColor(R.color.white));
                bottomTextView2.setTextColor(context.getResources().getColor(R.color.white));
                imageView2.setImageDrawable(context.getResources().getDrawable(R.drawable.ic_check));
                //imageView2.setPadding(0, 0, 0, 0);
                bottomTextView2.setText("Completed");
            }else {
                boxView2.setBackgroundColor(context.getResources().getColor(R.color.nw_green_card));
                textView2.setTextColor(context.getResources().getColor(R.color.white_87));
                bottomTextView2.setTextColor(context.getResources().getColor(R.color.white_54));
                imageView2.setImageDrawable(context.getResources().getDrawable(R.drawable.chevron));
                //imageView2.setPadding(0, 3, 0, 3);
                bottomTextView2.setText("Not Entered");
            }

            String time3 = textView3.getText().toString();
            if(QuarterHourCreator.getQuarterHour(context.getApplicationContext(), context, time3) != null){
                boxView3.setBackgroundColor(context.getResources().getColor(R.color.nw_green));
                textView3.setTextColor(context.getResources().getColor(R.color.white));
                bottomTextView3.setTextColor(context.getResources().getColor(R.color.white));
                imageView3.setImageDrawable(context.getResources().getDrawable(R.drawable.ic_check));
                //imageView3.setPadding(0, 0, 0, 0);
                bottomTextView3.setText("Completed");
            }else {
                boxView3.setBackgroundColor(context.getResources().getColor(R.color.nw_green_card));
                textView3.setTextColor(context.getResources().getColor(R.color.white_87));
                bottomTextView3.setTextColor(context.getResources().getColor(R.color.white_54));
                imageView3.setImageDrawable(context.getResources().getDrawable(R.drawable.chevron));
                //imageView3.setPadding(0, 3, 0, 3);
                bottomTextView3.setText("Not Entered");
            }

            String time4 = textView4.getText().toString();
            if(QuarterHourCreator.getQuarterHour(context.getApplicationContext(), context, time4) != null){
                boxView4.setBackgroundColor(context.getResources().getColor(R.color.nw_green));
                textView4.setTextColor(context.getResources().getColor(R.color.white));
                bottomTextView4.setTextColor(context.getResources().getColor(R.color.white));
                imageView4.setImageDrawable(context.getResources().getDrawable(R.drawable.ic_check));
                //imageView4.setPadding(0, 0, 0, 0);
                bottomTextView4.setText("Completed");
            }else {
                boxView4.setBackgroundColor(context.getResources().getColor(R.color.nw_green_card));
                textView4.setTextColor(context.getResources().getColor(R.color.white_87));
                bottomTextView4.setTextColor(context.getResources().getColor(R.color.white_54));
                imageView4.setImageDrawable(context.getResources().getDrawable(R.drawable.chevron));
                //imageView4.setPadding(0, 3, 0, 3);
                bottomTextView4.setText("Not Entered");
            }

        } else {
            top.setTextColor(context.getResources().getColor(R.color.black_67));

            String time = textView1.getText().toString();
            if(QuarterHourCreator.getQuarterHour(context.getApplicationContext(), context, time) != null){
                boxView1.setBackgroundColor(context.getResources().getColor(R.color.nw_green));
                textView1.setTextColor(context.getResources().getColor(R.color.white));
                bottomTextView1.setTextColor(context.getResources().getColor(R.color.white));
                imageView1.setImageDrawable(context.getResources().getDrawable(R.drawable.ic_check));
                //imageView1.setPadding(0, 0, 0, 0);
                bottomTextView1.setText("Completed");
            }else {
                if(TimeManagement.getUnfilledHours()!=null&&TimeManagement.getUnfilledHours().contains(time)){
                        boxView1.setBackgroundColor(context.getResources().getColor(R.color.red));
                        textView1.setTextColor(context.getResources().getColor(R.color.white));
                        bottomTextView1.setTextColor(context.getResources().getColor(R.color.white));
                        imageView1.setImageDrawable(context.getResources().getDrawable(R.drawable.chevron));
                        //imageView1.setPadding(0, 3, 0, 3);
                        bottomTextView1.setText("Not Entered ");
                }else {
                    boxView1.setBackgroundColor(context.getResources().getColor(R.color.grey));
                    textView1.setTextColor(context.getResources().getColor(R.color.black_87));
                    bottomTextView1.setTextColor(context.getResources().getColor(R.color.black_54));
                    imageView1.setImageDrawable(context.getResources().getDrawable(R.drawable.chevron));
                    //imageView1.setPadding(0, 3, 0, 3);
                    bottomTextView1.setText("Not Entered ");
                }
            }

            String time2 = textView2.getText().toString();
            if(QuarterHourCreator.getQuarterHour(context.getApplicationContext(), context, time2) != null){
                boxView2.setBackgroundColor(context.getResources().getColor(R.color.nw_green));
                textView2.setTextColor(context.getResources().getColor(R.color.white));
                bottomTextView2.setTextColor(context.getResources().getColor(R.color.white));
                imageView2.setImageDrawable(context.getResources().getDrawable(R.drawable.ic_check));
                //imageView2.setPadding(0, 0, 0, 0);
                bottomTextView2.setText("Completed");
            }else {
                if(TimeManagement.getUnfilledHours()!= null && TimeManagement.getUnfilledHours().contains(time2)){
                        boxView2.setBackgroundColor(context.getResources().getColor(R.color.red));
                        textView2.setTextColor(context.getResources().getColor(R.color.white));
                        bottomTextView2.setTextColor(context.getResources().getColor(R.color.white));
                        imageView2.setImageDrawable(context.getResources().getDrawable(R.drawable.chevron));
                        //imageView1.setPadding(0, 3, 0, 3);
                        bottomTextView2.setText("Not Entered ");
                }else {
                    boxView2.setBackgroundColor(context.getResources().getColor(R.color.grey));
                    textView2.setTextColor(context.getResources().getColor(R.color.black_87));
                    bottomTextView2.setTextColor(context.getResources().getColor(R.color.black_54));
                    imageView2.setImageDrawable(context.getResources().getDrawable(R.drawable.chevron));
                    //imageView2.setPadding(0, 3, 0, 3);
                    bottomTextView2.setText("Not Entered ");
                }
            }


            String time3 = textView3.getText().toString();
            if(QuarterHourCreator.getQuarterHour(context.getApplicationContext(), context, time3) != null){
                boxView3.setBackgroundColor(context.getResources().getColor(R.color.nw_green));
                textView3.setTextColor(context.getResources().getColor(R.color.white));
                bottomTextView3.setTextColor(context.getResources().getColor(R.color.white));
                imageView3.setImageDrawable(context.getResources().getDrawable(R.drawable.ic_check));
                //imageView3.setPadding(0, 0, 0, 0);
                bottomTextView3.setText("Completed");
            }else {
                if(TimeManagement.getUnfilledHours()!=null && TimeManagement.getUnfilledHours().contains(time3)){
                    boxView3.setBackgroundColor(context.getResources().getColor(R.color.red));
                    textView3.setTextColor(context.getResources().getColor(R.color.white));
                    bottomTextView3.setTextColor(context.getResources().getColor(R.color.white));
                    imageView3.setImageDrawable(context.getResources().getDrawable(R.drawable.chevron));
                    //imageView1.setPadding(0, 3, 0, 3);
                    bottomTextView3.setText("Not Entered ");
                }else {
                    boxView3.setBackgroundColor(context.getResources().getColor(R.color.grey));
                    textView3.setTextColor(context.getResources().getColor(R.color.black_87));
                    bottomTextView3.setTextColor(context.getResources().getColor(R.color.black_54));
                    imageView3.setImageDrawable(context.getResources().getDrawable(R.drawable.chevron));
                    //imageView3.setPadding(0, 3, 0, 3);
                    bottomTextView3.setText("Not Entered ");
                }
            }

            String time4 = textView4.getText().toString();
            if(QuarterHourCreator.getQuarterHour(context.getApplicationContext(), context, time4) != null){
                boxView4.setBackgroundColor(context.getResources().getColor(R.color.nw_green));
                textView4.setTextColor(context.getResources().getColor(R.color.white));
                bottomTextView4.setTextColor(context.getResources().getColor(R.color.white));
                imageView4.setImageDrawable(context.getResources().getDrawable(R.drawable.ic_check));
                //imageView4.setPadding(0, 0, 0, 0);
                bottomTextView4.setText("Completed");
            }else {
                if(TimeManagement.getUnfilledHours()!=null && TimeManagement.getUnfilledHours().contains(time4)){
                    boxView4.setBackgroundColor(context.getResources().getColor(R.color.red));
                    textView4.setTextColor(context.getResources().getColor(R.color.white));
                    bottomTextView4.setTextColor(context.getResources().getColor(R.color.white));
                    imageView4.setImageDrawable(context.getResources().getDrawable(R.drawable.chevron));
                    //imageView1.setPadding(0, 3, 0, 3);
                    bottomTextView4.setText("Not Entered ");
                }else {
                    boxView4.setBackgroundColor(context.getResources().getColor(R.color.grey));
                    textView4.setTextColor(context.getResources().getColor(R.color.black_87));
                    bottomTextView4.setTextColor(context.getResources().getColor(R.color.black_54));
                    imageView4.setImageDrawable(context.getResources().getDrawable(R.drawable.chevron));
                    //imageView4.setPadding(0, 3, 0, 3);
                    bottomTextView4.setText("Not Entered ");
                }
            }
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
