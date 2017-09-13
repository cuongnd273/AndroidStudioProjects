package vn.edu.itplus_academy.tracnghiemtienganh.Adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.daimajia.androidanimations.library.Techniques;
import com.daimajia.androidanimations.library.YoYo;

import java.util.List;

import vn.edu.itplus_academy.tracnghiemtienganh.Model.DeThiDB;
import vn.edu.itplus_academy.tracnghiemtienganh.R;
import vn.edu.itplus_academy.tracnghiemtienganh.interfaces.RecyclerViewOnClickListener;

public class DeThiAdapter extends RecyclerView.Adapter<DeThiAdapter.MyViewHolder> {

    private Context mContext;
    private List<DeThiDB> list;
    private LayoutInflater mLayoutInflater;
    private RecyclerViewOnClickListener mRecyclerViewOnClickListener;

    public DeThiAdapter(Context context, List<DeThiDB> list) {
        this.mContext = context;
        this.list = list;
        mLayoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view;
        view = mLayoutInflater.inflate(R.layout.item_dethi, parent, false);
        MyViewHolder myViewHolder = new MyViewHolder(view);
        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {


        holder.txtName.setText("Đề thi "+(position+1));
        holder.txtTime.setText(String.valueOf(list.get(position).getThoigian()));
        holder.txtProgressBarTest.setText(String.valueOf(list.get(position).getPrg())+"%");;



        //ANIM
        try{
            YoYo.with(Techniques.BounceInLeft)
                    .duration(700)
                    .playOn(holder.itemView);
        }catch (Exception e){

        }
        holder.progressBarTest.setProgress(list.get(position).getPrg());
        if(list.get(position).getPrg() < 50){
            holder.progressBarTest.setProgressDrawable(holder.itemView.getResources().getDrawable(R.drawable.cutom_progressbar20));
        }else if(list.get(position).getPrg() < 80){
            holder.progressBarTest.setProgressDrawable(holder.itemView.getResources().getDrawable(R.drawable.cutom_progressbar60));
        }else {
            holder.progressBarTest.setProgressDrawable(holder.itemView.getResources().getDrawable(R.drawable.cutom_progressbar100));
        }
    }

    @Override
    public int getItemCount() {
            return list != null ? list.size() : 0;
    }
    //ADD ITEM
    public void  addListItem(DeThiDB test, int position ){
        list.add(test);//thêm item
        notifyItemInserted(position);//làm mới adapter
    }
    //SET RECYECLERVIEW ON CLICK
    public void setRecyclerViewOnClickListener(RecyclerViewOnClickListener r){
        mRecyclerViewOnClickListener = r;
    }
    //MY VIEWHOLDER
    public class MyViewHolder extends  RecyclerView.ViewHolder implements View.OnClickListener{

        public TextView txtName;
        public TextView txtTime;
        public ProgressBar progressBarTest;
        public TextView txtProgressBarTest;

        public MyViewHolder(View itemView) {
            super(itemView);

            txtName = (TextView) itemView.findViewById(R.id.txt_nameTest);
            txtTime = (TextView) itemView.findViewById(R.id.txt_timeTest);
            progressBarTest = (ProgressBar) itemView.findViewById(R.id.progressBar_test);
            txtProgressBarTest = (TextView) itemView.findViewById(R.id.txt_progressBar_test);

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
