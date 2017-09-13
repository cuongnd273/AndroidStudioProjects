package free.app.com.moviebooking.adapter;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import free.app.com.moviebooking.R;
import free.app.com.moviebooking.model.NgayChieu;

/**
 * Created by CuongNguyen on 08/20/17.
 */

public class AdapterNgayChieu extends RecyclerView.Adapter<AdapterNgayChieu.ViewHolder> {
    private Context context;
    private ArrayList<NgayChieu> list;
    private NgayChieuEvent ngayChieuEvent;

    public AdapterNgayChieu(Context context, ArrayList<NgayChieu> list, NgayChieuEvent ngayChieuEvent) {
        this.context = context;
        this.list = list;
        this.ngayChieuEvent = ngayChieuEvent;
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        public TextView ngay,thu;
        LinearLayout layout;
        public ViewHolder(View itemView) {
            super(itemView);
            ngay= (TextView) itemView.findViewById(R.id.ngay);
            thu= (TextView) itemView.findViewById(R.id.thu);
            layout= (LinearLayout) itemView.findViewById(R.id.layout);
        }
    }
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater= (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view=inflater.inflate(R.layout.item_ngay_chieu,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        Date date = null;
        try {
            date = format.parse(list.get(position).getNgaychieu());
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(date);
            holder.ngay.setText(String.valueOf(calendar.get(Calendar.DAY_OF_MONTH)));
            holder.thu.setText(getDayOfWeek(calendar.get(Calendar.DAY_OF_WEEK)));
            holder.layout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    ngayChieuEvent.onClick(holder,v,position);
                    notifyDataSetChanged();
                }
            });
            if(list.get(position).isSelected()==true){
                holder.ngay.setTextColor(Color.WHITE);
                GradientDrawable bgShape = (GradientDrawable)holder.ngay.getBackground();
                bgShape.setColor(Color.RED);
            }else{
                holder.ngay.setTextColor(Color.BLACK);
                GradientDrawable bgShape = (GradientDrawable)holder.ngay.getBackground();
                bgShape.setColor(Color.WHITE);
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }

    }

    @Override
    public int getItemCount() {
        return list.size();
    }
    String getDayOfWeek(int value){
        String day="";
        switch (value){
            case 1:
                day="CN";
                break;
            case 2:
                day="T2";
                break;
            case 3:
                day="T3";
                break;
            case 4:
                day="T4";
                break;
            case 5:
                day="T5";
                break;
            case 6:
                day="T6";
                break;
            case 7:
                day="T7";
                break;
        }
        return day;
    }
    public interface NgayChieuEvent{
        public void onClick(ViewHolder viewHolder,View v,int position);
    }
}
