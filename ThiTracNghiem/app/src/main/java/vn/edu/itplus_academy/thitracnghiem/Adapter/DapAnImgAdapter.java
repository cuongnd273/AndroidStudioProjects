package vn.edu.itplus_academy.thitracnghiem.Adapter;

import android.app.Activity;
import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

import vn.edu.itplus_academy.thitracnghiem.Model.CauHoi;
import vn.edu.itplus_academy.thitracnghiem.Model.DapAn;
import vn.edu.itplus_academy.thitracnghiem.R;
import vn.edu.itplus_academy.thitracnghiem.widgets.RecyclerViewOnClickListener;

public class DapAnImgAdapter extends RecyclerView.Adapter<DapAnImgAdapter.MyViewHolder> {

    private Activity mContext;
    private CauHoi mCauHoi;
    private List<DapAn> list;
    private LayoutInflater mLayoutInflater;
    private RecyclerViewOnClickListener mRecyclerViewOnClickListener;

    public DapAnImgAdapter(Activity context, CauHoi cauHoi) {
        this.mContext = context;
        this.mCauHoi = cauHoi;
        list = cauHoi.getDapAnList();
        mLayoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    public DapAnImgAdapter(Activity context, List<DapAn> list) {
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
        view = mLayoutInflater.inflate(R.layout.item_dapan_img, parent, false);
        MyViewHolder myViewHolder = new MyViewHolder(view);
        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder,final int position) {
        holder.txtID.setText(list.get(position).getTxtID());
        Picasso.with(mContext)
                .load(list.get(position).getTxtDapAn())
                .into(holder.imgDapAn);

        if(position == mCauHoi.getDapAn()){
            holder.layout_dapAn.setBackgroundResource(R.drawable.bg_btn_kiemtra);
        }


    }


    @Override
    public int getItemCount() {
        return list != null ? list.size() : 0;
    }

    /**
     * CHON ITEM
     */
    public void chonItem( int position){
        mCauHoi.setDapAn(position);

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
        public TextView txtID;
        public ImageView imgDapAn;
        public LinearLayout layout_dapAn;
        public MyViewHolder(View itemView) {
            super(itemView);
            txtID = (TextView) itemView.findViewById(R.id.txt_id);
            imgDapAn = (ImageView) itemView.findViewById(R.id.img_dapan);
            layout_dapAn = (LinearLayout) itemView.findViewById(R.id.layout_dapAn);
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

