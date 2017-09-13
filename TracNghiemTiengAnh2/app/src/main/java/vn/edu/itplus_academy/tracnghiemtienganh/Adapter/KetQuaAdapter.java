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
import vn.edu.itplus_academy.tracnghiemtienganh.R;
import vn.edu.itplus_academy.tracnghiemtienganh.interfaces.RecyclerViewOnClickListener;

/**
 * Created by tuananh on 10/04/2016.
 */
public class KetQuaAdapter extends RecyclerView.Adapter<KetQuaAdapter.MyViewHolder> {
    private static final  int TYPE_HEAD = 0;
    private static final int TYPE_LIST = 1;
    private Activity mContext;
    private List<CauHoi> list;
    private LayoutInflater mLayoutInflater;
    private int chon = -1;
    private int dapan = -1;
    private RecyclerViewOnClickListener mRecyclerViewOnClickListener;

    public KetQuaAdapter(Activity context, List<CauHoi> list) {
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
        if(viewType == TYPE_HEAD){
            view = mLayoutInflater.inflate(R.layout.item_ketqua, parent, false);
            MyViewHolder myViewHolder = new MyViewHolder(view,viewType);
            return myViewHolder;
        }else if(viewType == TYPE_LIST) {
            view = mLayoutInflater.inflate(R.layout.item_ketqua, parent, false);
            MyViewHolder myViewHolder = new MyViewHolder(view, viewType);
            return myViewHolder;
        }
        return null;
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder,final int position) {

        if(holder.view_type == TYPE_LIST){
            holder.txtID.setText(String.valueOf(list.get(position-1).getTxtID()));
            holder.txtDapAn.setText(doi(list.get(position-1).getDapAn()));
            holder.txtDapAnDung.setText(doi(list.get(position-1).getIDDapAnDung()));
            holder.txtTime.setText(list.get(position-1).getTime()+".0 s");
            try{
                YoYo.with(Techniques.BounceInUp)
                        .duration(2000)
                        .playOn(holder.itemView);
            }catch (Exception e){

            }

            if( list.get(position-1).getDapAn() == -1){
                holder.layoutKetQua.setBackgroundResource(R.color.colorYellowA);
                holder.txtTime.setText("__.__ s");
            }else if( list.get(position-1).getDapAn() == list.get(position-1).getIDDapAnDung()){
                holder.layoutKetQua.setBackgroundResource(R.color.colorGreenA);
            }else {
                holder.layoutKetQua.setBackgroundResource(R.color.colorRedA);
            }

        }else if(holder.view_type == TYPE_HEAD){
            holder.ID.setText(mContext.getResources().getString(R.string.cauhoi).toString() );
            holder.ID.setTextColor(mContext.getResources().getColor(R.color.colorWhite));
            holder.DapAn.setText(mContext.getResources().getString(R.string.dapan).toString());
            holder.DapAn.setTextColor(mContext.getResources().getColor(R.color.colorWhite));
            holder.DapAnDung.setText(mContext.getResources().getString(R.string.dapandung).toString());
            holder.DapAnDung.setTextColor(mContext.getResources().getColor(R.color.colorWhite));
            holder.Time.setText(mContext.getResources().getString(R.string.time).toString());
            holder.Time.setTextColor(mContext.getResources().getColor(R.color.colorWhite));
            holder.layoutKetQua.setBackgroundResource(R.color.colorBlue800);
        }


    }
    public String doi(int i){
        switch (i){
            case -1:
                return "_";
            case 0:
                return "A";
            case 1:
                return "B";
            case 2:
                return "C";
            case 3:
                return "D";
        }
        return "";
    }

    @Override
    public int getItemCount() {
        return list != null ? list.size()+1 : 0;
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

        int view_type;
        //list
        public TextView txtID;
        public TextView txtDapAn;
        public TextView txtDapAnDung;
        public TextView txtTime;
        public LinearLayout layoutKetQua;
        //header
        public TextView ID,DapAn,DapAnDung,Time;
        public MyViewHolder(View itemView,int viewType) {
            super(itemView);

            if(viewType==TYPE_LIST){
                txtID = (TextView) itemView.findViewById(R.id.txt_id);
                txtDapAn = (TextView) itemView.findViewById(R.id.txt_dapAn);
                txtDapAnDung = (TextView) itemView.findViewById(R.id.txt_dapAnDung);
                txtTime = (TextView) itemView.findViewById(R.id.txt_time);
                layoutKetQua = (LinearLayout) itemView.findViewById(R.id.layout_ketqua);
                view_type = 1;
            }
            else if(viewType == TYPE_HEAD){
                ID = (TextView) itemView.findViewById(R.id.txt_id);
                DapAn = (TextView) itemView.findViewById(R.id.txt_dapAn);
                DapAnDung = (TextView) itemView.findViewById(R.id.txt_dapAnDung);
                Time = (TextView) itemView.findViewById(R.id.txt_time);
                layoutKetQua = (LinearLayout) itemView.findViewById(R.id.layout_ketqua);
                view_type = 0;
            }

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

    @Override
    public int getItemViewType(int position) {
        if(position == 0)
            return TYPE_HEAD;
        return TYPE_LIST;
    }
}

