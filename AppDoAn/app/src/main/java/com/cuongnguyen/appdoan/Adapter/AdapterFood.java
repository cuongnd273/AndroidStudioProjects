package com.cuongnguyen.appdoan.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.cuongnguyen.appdoan.Model.MonAn;
import com.cuongnguyen.appdoan.R;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by CUONG on 7/21/2016.
 */
public class AdapterFood extends BaseAdapter {
    private Context context;
    private int layout;
    private List<MonAn> list;
    public AdapterFood(Context context,int layout,List<MonAn> list) {
        this.context=context;
        this.layout=layout;
        this.list=list;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater= (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view=convertView;
        if(view==null)
        {
            view=inflater.inflate(layout,parent,false);
            ViewHolder viewHolder=new ViewHolder();
            viewHolder.imageFood=(ImageView)view.findViewById(R.id.imageFood);
            viewHolder.nameFood=(TextView)view.findViewById(R.id.nameFood);
            view.setTag(viewHolder);
        }
        ViewHolder viewHolder= (ViewHolder) view.getTag();
        viewHolder.nameFood.setText(list.get(position).getTenMonAn());
        Picasso.with(context).load(list.get(position).getLinkAnh()).fit().into(viewHolder.imageFood);
        return view;
    }
    private class ViewHolder{
        ImageView imageFood;
        TextView nameFood;
    }
}
