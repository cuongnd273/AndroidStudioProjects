package com.example.cuong.merychrismas;

import android.content.Context;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by Cuong on 21-12-2015.
 */
public class MyAdapter extends ArrayAdapter<Song> {
    private Context context;
    private int layout;
    private ArrayList<Song> list;
    public MyAdapter(Context context,int layout,ArrayList<Song> list)
    {
        super(context,layout,list);
        this.context=context;
        this.layout=layout;
        this.list=list;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView==null)
        {
            LayoutInflater inflater=(LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView=inflater.inflate(layout,parent,false);
        }
        Typeface typeface=Typeface.createFromAsset(context.getAssets(),"tp2.ttf");
        TextView name=(TextView)convertView.findViewById(R.id.textView);
        name.setTypeface(typeface);
        ImageView image=(ImageView)convertView.findViewById(R.id.imageView2);
        Song song=list.get(position);
        name.setText(song.getName());
        image.setImageResource(R.drawable.heart);
        Animation animation= AnimationUtils.loadAnimation(context, R.anim.alpha);
        animation.reset();
        convertView.setAnimation(animation);
        return convertView;
    }
}
