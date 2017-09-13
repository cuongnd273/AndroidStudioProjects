package com.example.app.goofood.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.app.goofood.Model.Comment;
import com.example.app.goofood.R;

import java.util.List;
public class AdapterComment extends BaseAdapter {
    private Context context;
    private int layout;
    private List<Comment> list;

    public AdapterComment(Context context, int layout, List<Comment> list) {
        this.context = context;
        this.layout = layout;
        this.list = list;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
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
            viewHolder.nguoidang=(TextView)view.findViewById(R.id.nguoidang);
            viewHolder.comment=(TextView)view.findViewById(R.id.binhluan);
            view.setTag(viewHolder);
        }
        ViewHolder viewHolder= (ViewHolder) view.getTag();
        viewHolder.nguoidang.setText(list.get(position).getTennguoidang());
        viewHolder.comment.setText(list.get(position).getBinhluan());
        return view;
    }
    public class ViewHolder{
        TextView nguoidang;
        TextView comment;
    }
}
