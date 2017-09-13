package vn.edu.itplus_academy.tracnghiemtienganh.Adapter;

import android.app.Activity;
import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.daimajia.androidanimations.library.Techniques;
import com.daimajia.androidanimations.library.YoYo;

import java.util.List;

import vn.edu.itplus_academy.tracnghiemtienganh.Model.CauHoi;
import vn.edu.itplus_academy.tracnghiemtienganh.Model.DapAn;
import vn.edu.itplus_academy.tracnghiemtienganh.R;
import vn.edu.itplus_academy.tracnghiemtienganh.interfaces.RecyclerViewOnClickListener;

/**
 * Created by tuananh on 09/04/2016.
 */
public class DapAnAdapter extends RecyclerView.Adapter<DapAnAdapter.MyViewHolder> {

    private static final int SHOW_DAPAN = 1;
    private Activity mContext;
    private CauHoi mCauHoi;
    private List<DapAn> list;
    private LayoutInflater mLayoutInflater;
    private RecyclerViewOnClickListener mRecyclerViewOnClickListener;

    public DapAnAdapter(Activity context, CauHoi cauHoi) {
        this.mContext = context;
        this.mCauHoi = cauHoi;
        list = cauHoi.getDapAnList();
        mLayoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    public DapAnAdapter(Activity context, List<DapAn> list) {
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
        view = mLayoutInflater.inflate(R.layout.item_dapan, parent, false);
        MyViewHolder myViewHolder = new MyViewHolder(view);
        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder,final int position) {
        holder.txtID.setText(list.get(position).getTxtID());
        holder.txtDapan.setText(list.get(position).getTxtDapAn());

        if(position == mCauHoi.getDapAn()){
            holder.layout_dapAn.setBackgroundResource(R.drawable.bg_btn_kiemtra);
        }
        if(SHOW_DAPAN ==mCauHoi.getShowDapAn()){
            if(mCauHoi.getIDDapAnDung() == mCauHoi.getDapAn()){
                if(position == mCauHoi.getIDDapAnDung()){
                    holder.layout_dapAn.setBackgroundResource(R.drawable.bg_dung);
                }
            }else {
                if(position == mCauHoi.getIDDapAnDung()){
                    holder.layout_dapAn.setBackgroundResource(R.drawable.bg_sai);
                    try{
                        YoYo.with(Techniques.Shake)
                                .duration(500)
                                .playOn(holder.itemView);
                    }catch (Exception e){

                    }
                }
            }
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
     * DAP AN DUNG
     */
    public void showDapAn( int position){
        mCauHoi.setShowDapAn(position);
        notifyItemInserted(mCauHoi.getIDDapAnDung());
    }
    /**
     * ADD ITEM
     */
    public void  addListItem(DapAn dapAn, int position ){
        list.add(dapAn);//thêm item
        notifyItemInserted(position);//làm mới adapter
    }

    /**
     *  XOA ITEM
     */
    public void removeListItem(int position ){
        list.remove(position);
//        list.remove(list.get(position));
//        list.get(position).setTxtDapAn("");
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
        public TextView txtID;
        public TextView txtDapan;
        public LinearLayout layout_dapAn;
        public MyViewHolder(View itemView) {
            super(itemView);
            txtID = (TextView) itemView.findViewById(R.id.txt_id);
            txtDapan = (TextView) itemView.findViewById(R.id.txt_dapan);
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

