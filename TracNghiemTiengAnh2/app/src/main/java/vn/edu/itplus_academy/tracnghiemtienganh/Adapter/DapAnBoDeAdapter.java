package vn.edu.itplus_academy.tracnghiemtienganh.Adapter;

import android.app.Activity;
import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import java.util.List;

import vn.edu.itplus_academy.tracnghiemtienganh.Model.CauHoi;
import vn.edu.itplus_academy.tracnghiemtienganh.R;
import vn.edu.itplus_academy.tracnghiemtienganh.interfaces.RecyclerViewOnClickListener;

public class DapAnBoDeAdapter extends RecyclerView.Adapter<DapAnBoDeAdapter.MyViewHolder> {

    private static final int SHOW_DAPAN = 1;
    private Activity mContext;
    private List<CauHoi> list;
    private LayoutInflater mLayoutInflater;
    private RecyclerViewOnClickListener mRecyclerViewOnClickListener;

    public DapAnBoDeAdapter(Activity context, List<CauHoi> list) {
        this.mContext = context;
        this.list = list;
        mLayoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    public List<CauHoi> getList(){
        return list;
    }

    @Override
    public void registerAdapterDataObserver(RecyclerView.AdapterDataObserver observer) {
        super.registerAdapterDataObserver(observer);
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view;
        view = mLayoutInflater.inflate(R.layout.item_cauhoi_bode, parent, false);
        MyViewHolder myViewHolder = new MyViewHolder(view);
        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder,final int position) {
        final CauHoi cauHoi = list.get(position);
        holder.txtCauHoi.setText("Câu : " + cauHoi.getTxtID());

        setRadio(holder, cauHoi.getDapAn());


        holder.rbA.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                cauHoi.setDapAn(0);
                setRadio(holder,cauHoi.getDapAn());
                notifyItemChanged(position);
            }
        });
        holder.rbB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                cauHoi.setDapAn(1);
                setRadio(holder,cauHoi.getDapAn());
                notifyItemChanged(position);
            }
        });
        holder.rbC.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                cauHoi.setDapAn(2);
                setRadio(holder, cauHoi.getDapAn());
                notifyItemChanged(position);
            }
        });
        holder.rbD.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                cauHoi.setDapAn(3);
                setRadio(holder, cauHoi.getDapAn());
                notifyItemChanged(position);
            }
        });



    }

    private void setRadio(final MyViewHolder holder, int selection) {

        if (selection == 0){
            holder.rbA.setChecked(true);
        }
        else if (selection == 1){
            holder.rbB.setChecked(true);
        }else if (selection == 2){
            holder.rbC.setChecked(true);
        }else if (selection == 3){
            holder.rbD.setChecked(true);
        }else {
            holder.rgCauHoi.clearCheck();
        }
    }

    @Override
    public int getItemCount() {
        return list != null ? list.size() : 0;
    }
    //ADD ITEM
    public void  addListItem(CauHoi cauHoi, int position ){
        list.add(cauHoi);//thêm item
        notifyItemInserted(position);//làm mới adapter
    }
    //SET RECYECLERVIEW ON CLICK
    public void setRecyclerViewOnClickListener(RecyclerViewOnClickListener r){
        mRecyclerViewOnClickListener = r;
    }
    //MY VIEWHOLDER
    public class MyViewHolder extends  RecyclerView.ViewHolder implements View.OnClickListener{
        public TextView txtCauHoi;
        public RadioGroup rgCauHoi;
        public RadioButton rbA,rbB,rbC,rbD;


        public MyViewHolder(View itemView) {
            super(itemView);
            txtCauHoi = (TextView) itemView.findViewById(R.id.txt_cauhoi);
            rgCauHoi = (RadioGroup) itemView.findViewById(R.id.rg_cauhoi);
            rbA = (RadioButton) itemView.findViewById(R.id.rb_a);
            rbB = (RadioButton) itemView.findViewById(R.id.rb_b);
            rbC = (RadioButton) itemView.findViewById(R.id.rb_c);
            rbD = (RadioButton) itemView.findViewById(R.id.rb_d);

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
