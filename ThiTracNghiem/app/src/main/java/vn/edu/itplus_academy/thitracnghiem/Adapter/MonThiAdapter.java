package vn.edu.itplus_academy.thitracnghiem.Adapter;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.daimajia.androidanimations.library.Techniques;
import com.daimajia.androidanimations.library.YoYo;

import java.util.ArrayList;
import java.util.List;

import retrofit.Callback;
import retrofit.RestAdapter;
import retrofit.RetrofitError;
import retrofit.client.Response;
import vn.edu.itplus_academy.thitracnghiem.API.API;
import vn.edu.itplus_academy.thitracnghiem.Activity.DeThiActivity;
import vn.edu.itplus_academy.thitracnghiem.Activity.LogInActivity;
import vn.edu.itplus_academy.thitracnghiem.DataBase.DBAdapter;
import vn.edu.itplus_academy.thitracnghiem.Model.CauHoi;
import vn.edu.itplus_academy.thitracnghiem.Model.DapAn;
import vn.edu.itplus_academy.thitracnghiem.Model.MonThi;
import vn.edu.itplus_academy.thitracnghiem.Model.ThiSinh;
import vn.edu.itplus_academy.thitracnghiem.R;
import vn.edu.itplus_academy.thitracnghiem.mModel.ResultGetQuestion;
import vn.edu.itplus_academy.thitracnghiem.mModel.answer;
import vn.edu.itplus_academy.thitracnghiem.mModel.question;
import vn.edu.itplus_academy.thitracnghiem.widgets.CircleImageView;


public class MonThiAdapter extends RecyclerView.Adapter<MonThiAdapter.MyViewHolder> {

    private final static String CHUALAM = "ChuaLam";
    private final static String DANGLAM = "DangLam";
    private final static String DALAM = "DaLam";
    private final static String MAMONTHI = "MaMonThi";
    public final static String CAUHOIIMG = "img";
    public final static String CAUHOITXT = "txt";
    private Activity mContext;
    private List<MonThi> list;
    private LayoutInflater mLayoutInflater;
    private DBAdapter db;
    private List<ThiSinh> thiSinhs;
    private ProgressDialog dialog;

    public MonThiAdapter(Activity context, List<MonThi> list) {
        this.mContext = context;
        this.list = list;
        mLayoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        db = new DBAdapter(context);
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view;
        view = mLayoutInflater.inflate(R.layout.monthi_item, parent, false);
        MyViewHolder myViewHolder = new MyViewHolder(view);
        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder,final int position) {
        holder.txtTitleMonThi.setText(list.get(position).getTitleMonThi());
//        holder.imgMonThi.setImageResource(list.get(position).getImgMonThi());
        holder.imgMonThi.setImageResource(R.mipmap.imagei);
        try{
            YoYo.with(Techniques.BounceIn)
                    .duration(1000)
                    .playOn(holder.itemView);
        }catch (Exception e){

        }

        thiSinhs = new ArrayList<>();
        thiSinhs = db.getAll_ThiSinh();
        Log.d("thiSinh",thiSinhs.get(0).getStatusThiSinh());
        if(!thiSinhs.get(0).getStatusThiSinh().equals(DALAM)){
            holder.layoutMonThi.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(db.get_CauHoi_MT(list.get(position).getMaMonThi()).size()==0){
                        String code = thiSinhs.get(0).getMaDuThi();
                        String phone = thiSinhs.get(0).getSodienthoai();
                        int  tqid = list.get(position).getMaMonThi();
                        dialog=new ProgressDialog(mContext);
                        dialog.setMessage("Loading...");
                        dialog.show();
                        RestAdapter restAdapter=new RestAdapter.Builder().setEndpoint(LogInActivity.URL).build();
                        API api=restAdapter.create(API.class);
                        api.getquestion(code, phone, String.valueOf(tqid), new Callback<ResultGetQuestion>() {
                            @Override
                            public void success(ResultGetQuestion resultGetQuestion, Response response) {
                                if(resultGetQuestion.getSuccess().equals("1")) {
                                    List<answer> answerList = resultGetQuestion.getAnswers();
                                    List<question> questionlist = resultGetQuestion.getQuestionses();
                                    for(int i =0 ; i < questionlist.size() ;i++){
                                        List<DapAn> dapAnList = new ArrayList<>();
                                        for(int j = 0 ;j < answerList.size(); j++){
                                            if(answerList.get(j).getQid() == questionlist.get(i).getId() ){
                                                dapAnList.add(new DapAn(answerList.get(j).getId(),"",answerList.get(j).getLabel()));
                                                Log.i("madapan__",String.valueOf(answerList.get(j).getId()));
                                            }
                                        }

                                        CauHoi cauHoi = new CauHoi(questionlist.get(i).getId(),questionlist.get(i).getAnswer().trim(),questionlist.get(i).getName().trim(),questionlist.get(i).getValue(),dapAnList,-1,"",questionlist.get(i).getTqid());
                                        db.insert_CauHoi(cauHoi);
                                    }
                                    dialog.dismiss();
                                    Intent intent = new Intent(mContext, DeThiActivity.class);
                                    Bundle bundle = new Bundle();
                                    bundle.putInt(MAMONTHI, list.get(position).getMaMonThi());
                                    intent.putExtras(bundle);
                                    mContext.startActivity(intent);
                                    mContext.overridePendingTransition(R.anim.animation_in, R.anim.animation_out);
                                }
//                            Toast.makeText(mContext,resultGetQuestion.getSuccess().toString(),Toast.LENGTH_SHORT).show();
                            }

                            @Override
                            public void failure(RetrofitError error) {
                                Toast.makeText(mContext,error.toString(),Toast.LENGTH_SHORT).show();
                                dialog.dismiss();
                            }
                        });

                    }else {
                        Intent intent = new Intent(mContext, DeThiActivity.class);
                        Bundle bundle = new Bundle();
                        bundle.putInt(MAMONTHI, list.get(position).getMaMonThi());
                        intent.putExtras(bundle);
                        mContext.startActivity(intent);
                        mContext.overridePendingTransition(R.anim.animation_in, R.anim.animation_out);
                    }

                }
            });
        }
        if(list.get(position).getStatusMonThi().equals(DALAM) || thiSinhs.get(0).getStatusThiSinh().equals(DALAM)){
            holder.layoutMonThi.setBackgroundResource(R.color.colorOrange);
        }

    }

    @Override
    public int getItemCount() {
            return list != null ? list.size() : 0;
    }


    public class MyViewHolder extends  RecyclerView.ViewHolder{
        public TextView txtTitleMonThi;
        public CircleImageView imgMonThi;
        public CardView layoutMonThi;
        public MyViewHolder(View itemView) {
            super(itemView);
            txtTitleMonThi = (TextView) itemView.findViewById(R.id.txt_title_monthi);
            imgMonThi = (CircleImageView) itemView.findViewById(R.id.img_monthi);
            layoutMonThi = (CardView) itemView.findViewById(R.id.layout_monthi);
        }
    }
}
