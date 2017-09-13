package vn.edu.itplus_academy.tracnghiemtienganh.Adapter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.daimajia.androidanimations.library.Techniques;
import com.daimajia.androidanimations.library.YoYo;

import java.util.List;

import vn.edu.itplus_academy.tracnghiemtienganh.Activitys.GrammarsActivity;
import vn.edu.itplus_academy.tracnghiemtienganh.CustomImageView.CircleImageView;
import vn.edu.itplus_academy.tracnghiemtienganh.R;
import vn.edu.itplus_academy.tracnghiemtienganh.interfaces.RecyclerViewOnClickListener;
import vn.edu.itplus_academy.tracnghiemtienganh.models.ChuDe;

/**
 * Created by tuananh on 05/04/2016.
 */
public class GrammarAdapter extends RecyclerView.Adapter<GrammarAdapter.MyViewHolder> {

    private static final int FRAGMENT_LYTHUYET = 0;
    private static final int FRAGMENT_LUYENTAP = 1;
    private Activity mContext;
    private List<ChuDe> list;
    private LayoutInflater mLayoutInflater;
    private RecyclerViewOnClickListener mRecyclerViewOnClickListener;

    public GrammarAdapter(Activity context, List<ChuDe> list) {
        this.mContext = context;
        this.list = list;
        mLayoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view;
        view = mLayoutInflater.inflate(R.layout.item_grammars, parent, false);
        MyViewHolder myViewHolder = new MyViewHolder(view);
        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder,final int position) {
        holder.txtItemGrammar.setText(list.get(position).getTenCD());
        holder.imgItemGrammar.setImageResource(R.drawable.image_lythuyet);

        try{
            YoYo.with(Techniques.BounceIn)
                    .duration(1000)
                    .playOn(holder.itemView);
        }catch (Exception e){

        }

        holder.btnItenGrammar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mContext, GrammarsActivity.class);
                Bundle bundle = new Bundle();
                bundle.putInt("ID",FRAGMENT_LUYENTAP);
                bundle.putString("grammar", list.get(position).getLink());
                bundle.putInt("macd",list.get(position).getMacd());
                intent.putExtras(bundle);
                mContext.startActivity(intent);
                mContext.overridePendingTransition(R.anim.animation_in, R.anim.animation_out);

            }
        });
        holder.grammars.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mContext, GrammarsActivity.class);
                Bundle bundle = new Bundle();
                bundle.putInt("ID", FRAGMENT_LYTHUYET);
                bundle.putString("grammar", list.get(position).getLink());
                bundle.putInt("macd",list.get(position).getMacd());
                intent.putExtras(bundle);
                mContext.startActivity(intent);
                mContext.overridePendingTransition(R.anim.animation_in, R.anim.animation_out);
            }
        });
    }

    @Override
    public int getItemCount() {
            return list != null ? list.size() : 0;
    }

    /**
     *  ADD ITEM
     */
    public void  addListItem(ChuDe grammar, int position ){
        list.add(grammar);//thêm item
        notifyItemInserted(position);//làm mới adapter
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
        public TextView txtItemGrammar;
        public CircleImageView imgItemGrammar;
        public Button btnItenGrammar;
        public LinearLayout grammars;
        public MyViewHolder(View itemView) {
            super(itemView);
            txtItemGrammar = (TextView) itemView.findViewById(R.id.txt_itemGrammar);
            imgItemGrammar = (CircleImageView) itemView.findViewById(R.id.img_itemGrammar);
            btnItenGrammar = (Button) itemView.findViewById(R.id.btn_itemGrammar);
            grammars = (LinearLayout) itemView.findViewById(R.id.grammars);
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
