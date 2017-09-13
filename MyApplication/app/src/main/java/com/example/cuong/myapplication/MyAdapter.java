package com.example.cuong.myapplication;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by cuong on 9/6/2017.
 */

public class MyAdapter extends BaseAdapter{
    private Context context;
    private int layout;
    private ArrayList<String> titles;

    public MyAdapter(Context context, int layout, ArrayList<String> titles) {
        this.context = context;
        this.layout = layout;
        this.titles = titles;
    }

    @Override
    public int getCount() {
        return titles.size();
    }

    @Override
    public Object getItem(int position) {
        return titles.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater= (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view=inflater.inflate(layout,parent,false);
        TextView name= (TextView) view.findViewById(R.id.name);
        name.setText(titles.get(position));
        return view;
    }
}
