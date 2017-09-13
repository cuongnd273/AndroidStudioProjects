package free.app.com.moviebooking.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;

import free.app.com.moviebooking.R;
import free.app.com.moviebooking.model.LichChieu;

/**
 * Created by CuongNguyen on 08/20/17.
 */

public class AdapterLichChieu extends RecyclerView.Adapter<AdapterLichChieu.ViewHolder> {
    private Context context;
    private ArrayList<LichChieu> list;
    private OnClick onClick;

    public AdapterLichChieu(Context context, ArrayList<LichChieu> list, OnClick onClick) {
        this.context = context;
        this.list = list;
        this.onClick = onClick;
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView thoigian,phongchieu;
        LinearLayout layout;
        public ViewHolder(View itemView) {
            super(itemView);
            thoigian= (TextView) itemView.findViewById(R.id.thoi_gian);
            phongchieu= (TextView) itemView.findViewById(R.id.phong_chieu);
            layout= (LinearLayout) itemView.findViewById(R.id.layout);
        }
    }
    @Override
    public AdapterLichChieu.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater= (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view=inflater.inflate(R.layout.item_lich_chieu,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final AdapterLichChieu.ViewHolder holder, final int position) {
        holder.thoigian.setText(list.get(position).getBatdau()+"-"+list.get(position).getKetthuc());
        holder.phongchieu.setText(list.get(position).getPhongchieu());
        holder.layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onClick.click(v,position);
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }
    public interface OnClick{
        public void click(View v,int position);
    }
}
