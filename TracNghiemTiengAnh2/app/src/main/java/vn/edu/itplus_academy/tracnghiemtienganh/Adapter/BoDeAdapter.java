package vn.edu.itplus_academy.tracnghiemtienganh.Adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.daimajia.androidanimations.library.Techniques;
import com.daimajia.androidanimations.library.YoYo;

import java.util.List;

import vn.edu.itplus_academy.tracnghiemtienganh.Model.BoDe;
import vn.edu.itplus_academy.tracnghiemtienganh.R;
import vn.edu.itplus_academy.tracnghiemtienganh.interfaces.RecyclerViewOnClickListener;

/**
 * Created by tuananh on 18/05/2016.
 */
public class BoDeAdapter extends RecyclerView.Adapter<BoDeAdapter.MyViewHolder> {

    private Context mContext;
    private List<BoDe> list;
    private LayoutInflater mLayoutInflater;
    private RecyclerViewOnClickListener mRecyclerViewOnClickListener;

    public BoDeAdapter(Context context, List<BoDe> list) {
        this.mContext = context;
        this.list = list;
        mLayoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view;
        view = mLayoutInflater.inflate(R.layout.item_bode, parent, false);
        MyViewHolder myViewHolder = new MyViewHolder(view);
        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {

        holder.txtID.setText(String.valueOf(list.get(position).getTxtID()));
        holder.txtName.setText(list.get(position).getTxtName());

        switch (list.get(position).getTxtID()%2){
            case 0:
                holder.txtName.setTextColor(mContext.getResources().getColor(R.color.colorRed));
                holder.txtID.setBackgroundResource(R.drawable.bg_itemtest1);
                break;
            case 1:
                holder.txtName.setTextColor(mContext.getResources().getColor(R.color.colorBlue));
                holder.txtID.setBackgroundResource(R.drawable.bg_itemtest2);
                break;
        }


        //ANIM
        try{
            YoYo.with(Techniques.BounceInLeft)
                    .duration(700)
                    .playOn(holder.itemView);
        }catch (Exception e){

        }
    }

    @Override
    public int getItemCount() {
        return list != null ? list.size() : 0;
    }
    //ADD ITEM
    public void  addListItem(BoDe bode, int position ){
        list.add(bode);//thêm item
        notifyItemInserted(position);//làm mới adapter
    }
    //SET RECYECLERVIEW ON CLICK
    public void setRecyclerViewOnClickListener(RecyclerViewOnClickListener r){
        mRecyclerViewOnClickListener = r;
    }
    //MY VIEWHOLDER
    public class MyViewHolder extends  RecyclerView.ViewHolder implements View.OnClickListener{
        public TextView txtID;
        public TextView txtName;

        public MyViewHolder(View itemView) {
            super(itemView);
            txtID = (TextView) itemView.findViewById(R.id.txt_idBoDe);
            txtName = (TextView) itemView.findViewById(R.id.txt_nameBoDe);
            itemView.setOnClickListener(this);
        }
        //CLICK ITEM
        @Override
        public void onClick(View v) {
            if(mRecyclerViewOnClickListener != null){
                mRecyclerViewOnClickListener.onClickListener(v,getPosition());
            }
        }
    }
}
