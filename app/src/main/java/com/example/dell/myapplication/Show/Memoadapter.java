package com.example.dell.myapplication.Show;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Typeface;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.dell.myapplication.Activitybo;
import com.example.dell.myapplication.R;

/**
 * Created by DELL on 5/21/2016.
 */
public class Memoadapter extends BaseAdapter {
    private Context context;
    private Activitybo[] items;


    public Memoadapter(Context context, Activitybo[] items) {
        this.context = context;
        this.items = items;
    }
    public int getCount() {
        return items.length;
    }

    @Override
    public Object getItem(int position) {
        return items[position];
    }

    @Override
    public long getItemId(int position) {
        return Long.parseLong(items[position].toString());
    }


    public View getView(int position, View convertView, ViewGroup parent) {
        View v=View.inflate(context, R.layout.demandholdintaxheader,null);
        v.setBackgroundColor(Color.parseColor("#FFFFFF"));
        TextView iName=((TextView)v.findViewById(R.id.Desc));
        iName.setTypeface(Typeface.DEFAULT);
        iName.setText(items[position].getDescription());
        TextView iPrice=((TextView)v.findViewById(R.id.Location));
        iPrice.setTypeface(Typeface.DEFAULT);
        iPrice.setText(items[position].getLocation());
        TextView idate=((TextView)v.findViewById(R.id.mdate));
        idate.setTypeface(Typeface.DEFAULT);
        idate.setText(items[position].getDate());
        TextView itime=((TextView)v.findViewById(R.id.mtime));
        itime.setTypeface(Typeface.DEFAULT);
        itime.setText(items[position].getTime());

        return v;

    }
}

