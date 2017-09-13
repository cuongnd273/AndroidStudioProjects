package com.cuongnguyen.appdoan.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.cuongnguyen.appdoan.Model.BangGia;
import com.cuongnguyen.appdoan.R;

import java.util.List;

/**
 * Created by CUONG on 7/21/2016.
 */
public class AdapterPrice extends BaseAdapter {
    private Context context;
    private int layout;
    private List<BangGia> list;
    public AdapterPrice(Context context,int layout,List<BangGia> list) {
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
            viewHolder.price=(TextView)view.findViewById(R.id.price);
            view.setTag(viewHolder);

        }
        ViewHolder viewHolder= (ViewHolder) view.getTag();
        viewHolder.price.setText(list.get(position).getGia()+"/"+list.get(position).getSoLuong());
        return view;
    }
    class ViewHolder{
        TextView price;
    }
}
