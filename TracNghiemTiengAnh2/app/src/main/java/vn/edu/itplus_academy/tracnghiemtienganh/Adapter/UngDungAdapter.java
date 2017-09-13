package vn.edu.itplus_academy.tracnghiemtienganh.Adapter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

import vn.edu.itplus_academy.tracnghiemtienganh.R;
import vn.edu.itplus_academy.tracnghiemtienganh.interfaces.RecyclerViewOnClickListener;
import vn.edu.itplus_academy.tracnghiemtienganh.models.UngDung;

/**
 * Created by tuananh on 12/04/2016.
 */
public class UngDungAdapter extends RecyclerView.Adapter<UngDungAdapter.MyViewHolder> {

    private Activity mContext;
    private List<UngDung> list;
    private LayoutInflater mLayoutInflater;
    private RecyclerViewOnClickListener mRecyclerViewOnClickListener;

    public UngDungAdapter(Activity context, List<UngDung> list) {
        this.mContext = context;
        this.list = list;
        mLayoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }
    @Override
    public void registerAdapterDataObserver(RecyclerView.AdapterDataObserver observer) {
        super.registerAdapterDataObserver(observer);
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view;
        view = mLayoutInflater.inflate(R.layout.item_ungdung, parent, false);
        MyViewHolder myViewHolder = new MyViewHolder(view);
        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder,final int position) {
        holder.img.setImageURI(Uri.parse(list.get(position).getAnh()));
        Picasso.with(mContext)
                .load(list.get(position).getAnh())
                .error(R.drawable.cancel_icon)
                .into(holder.img);
        holder.txtName.setText(list.get(position).getTenud());
        holder.txtThongTin.setText(list.get(position).getMota());
        holder.btnLownLoad.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_VIEW);
                intent.setData(Uri.parse(list.get(position).getLinkud()));
                mContext.startActivity(intent);
            }
        });

    }


    @Override
    public int getItemCount() {
        return list != null ? list.size() : 0;
    }

    /**
     * ADD ITEM
     */
    public void  addListItem(UngDung ungDung, int position ){
        list.add(ungDung);//thêm item
        notifyItemInserted(position);//làm mới adapter
    }

    /**
     *  XOA ITEM
     */
    public void removeListItem(int position){
        list.remove(position);//xóa item positon
        notifyItemRemoved(position);//làm mới adapter
    }

    /**
     * SET RECYECLERVIEW ON CLICK
     */
    public void setRecyclerViewOnClickListener(RecyclerViewOnClickListener r){
        mRecyclerViewOnClickListener = r;
    }

    /**
     * MY VIEWHOLDER
     */
    public class MyViewHolder extends  RecyclerView.ViewHolder implements View.OnClickListener{
        public ImageView img;
        public TextView txtName;
        public TextView txtThongTin;
        public Button btnLownLoad;
        public MyViewHolder(View itemView) {
            super(itemView);
            img = (ImageView) itemView.findViewById(R.id.img);
            txtName = (TextView) itemView.findViewById(R.id.txt_name);
            txtThongTin = (TextView) itemView.findViewById(R.id.txt_thongtin);
            btnLownLoad = (Button) itemView.findViewById(R.id.btn_download);
            itemView.setOnClickListener(this);
        }

        /**
         *  CLICK ITEM
         */
        @Override
        public void onClick(View v) {
            if(mRecyclerViewOnClickListener != null){
                mRecyclerViewOnClickListener.onClickListener(v,getPosition());
            }

        }
    }
}
