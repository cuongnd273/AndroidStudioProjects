package vn.edu.itplus_academy.tracnghiemtienganh.Fragment;


import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.GestureDetector;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.memetix.mst.language.Language;
import com.memetix.mst.translate.Translate;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import vn.edu.itplus_academy.tracnghiemtienganh.Activitys.KetQuaActivity;
import vn.edu.itplus_academy.tracnghiemtienganh.Adapter.DapAnAdapter;
import vn.edu.itplus_academy.tracnghiemtienganh.Model.CauHoi;
import vn.edu.itplus_academy.tracnghiemtienganh.Model.DapAn;
import vn.edu.itplus_academy.tracnghiemtienganh.Model.ListKetQua;
import vn.edu.itplus_academy.tracnghiemtienganh.R;
import vn.edu.itplus_academy.tracnghiemtienganh.databases.DBAdapter;
import vn.edu.itplus_academy.tracnghiemtienganh.interfaces.RecyclerViewOnClickListener;
import vn.edu.itplus_academy.tracnghiemtienganh.models.CauHoiLT;


public class ExercisesFragment extends Fragment implements RecyclerViewOnClickListener,View.OnClickListener{

    private static final int TYPE_EXERCISES = 0;
    private static final int SHOW_TIME = 1;
    private View view;
    private int mcd;
    private String grammar;
    private List<CauHoiLT> lst_Cauhoi_LT = new ArrayList<CauHoiLT>();
    private List<CauHoi> cauHoiList;
    private DapAnAdapter dapAnAdapter;
    private RecyclerView RecyclerDapAn;
    private ProgressBar progressBarTime;
    private TextView txtTime,txtNameCauHoi,txt_cauHoi;
    private Handler handler;
    private  Thread thread;
    private Button btnKienTra;
    private ImageView btnNext,btn50_50,btnPhone,btnGoogleDich;
    private String translatedText="";

    ProgressDialog pdialog;

    int timeMAX = 30;
    int time = timeMAX;
    int btn = 0;
    int ID = 0;

    public ExercisesFragment() {
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_exercises, container, false);
        txtNameCauHoi = (TextView) view.findViewById(R.id.txt_nameCauHoi);
        txt_cauHoi = (TextView) view.findViewById(R.id.txt_cauHoi);

        Translate.setClientId("12345678912");
        Translate.setClientSecret("123456789123456789123456789");

        progressBarTime = (ProgressBar) view.findViewById(R.id.progressBar_time);
        progressBarTime.setMax(timeMAX);
        txtTime = (TextView) view.findViewById(R.id.txt_time);

        handler = new Handler(){
            @Override
            public void handleMessage(Message msg) {
                super.handleMessage(msg);
                if( msg.what != -11){
                    progressBarTime.setProgress(msg.what);
                    if(msg.what < timeMAX/3) {
                        progressBarTime.setProgressDrawable(view.getResources().getDrawable(
                                R.drawable.cutom_progressbar20));
                    }else if(msg.what < timeMAX*2/3){
                        progressBarTime.setProgressDrawable(view.getResources().getDrawable(
                                R.drawable.cutom_progressbar60));
                    }else {
                        progressBarTime.setProgressDrawable(view.getResources().getDrawable(
                                R.drawable.cutom_progressbar100));
                    }

                    txtTime.setText(String.valueOf(msg.what)+".0s");
                }else {
//                    kiemtra();
                }
            }
        };


        list();

        ListRecyclerView();

        buttonKiemTra();

        troGiup();
        btnKienTra.setBackgroundResource(R.drawable.bg_item);




        return view;
    }
    /**
     * GETDATA
     */
    public void getData(){
        Bundle bundle = getArguments();
        mcd = bundle.getInt("macd");
        grammar = bundle.getString("grammar");

    }
    /**
     * SHOW TIME
     */
    public void showTime(int msg){
        if(msg == SHOW_TIME){
            Time();
        }
    }
    /**
     * LIST
     */
    private void list(){

        getData();

        DBAdapter db = new DBAdapter(getContext());
        db.open();
        lst_Cauhoi_LT=db.getAll_Cauhoi_LT(mcd);
        db.close();

        ArrayList<CauHoiLT> lst_temp = new ArrayList<CauHoiLT>();
        lst_temp = GetRandomFromTo();


        String[] txtQuestionID={getResources().getString(R.string.a),getResources().getString(R.string.b),getResources().getString(R.string.c),getResources().getString(R.string.d)};



        cauHoiList = new ArrayList<>();
        for(int i=0; i<20; i++){

            List<DapAn> dapAnList = new ArrayList<>();
            dapAnList.add(new DapAn(txtQuestionID[0], lst_temp.get(i).getDapan_A()));
            dapAnList.add(new DapAn(txtQuestionID[1], lst_temp.get(i).getDapan_B()));
            dapAnList.add(new DapAn(txtQuestionID[2], lst_temp.get(i).getDapan_C()));
            dapAnList.add(new DapAn(txtQuestionID[3], lst_temp.get(i).getDapan_D()));


            cauHoiList.add(new CauHoi(i+1,lst_temp.get(i).getCauhoi(),dapAnList,checkDapanDung(lst_temp.get(i).getKetqua()),0,-1,0));
        }
    }

    private int checkDapanDung(String temp)
    {

        if(getResources().getString(R.string.a).equals(temp.trim().toString()))
        {
            return 0;
        }
        if(getResources().getString(R.string.b).equals(temp.trim().toString()))
        {
            return 1;
        }
        if(getResources().getString(R.string.c).equals(temp.trim().toString()))
        {
            return 2;
        }
        if(getResources().getString(R.string.d).equals(temp.trim().toString()))
        {
            return 3;
        }

        return 0;
    }

    private ArrayList<CauHoiLT> GetRandomFromTo() {
        ArrayList<CauHoiLT> lst = new ArrayList<CauHoiLT>();
        List<Integer> lst_int = new ArrayList<Integer>();
        Random rd = new Random();
        int iNew ;
        int temp = lst_Cauhoi_LT.size();
        int i = 0;



        while( i < 20) {
            iNew =rd.nextInt(temp);
            if (!lst_int.contains(iNew)) {
                i++;
                lst_int.add(iNew);
                lst.add(lst_Cauhoi_LT.get(iNew));

            }
        }
        return lst;
    }
    /**
     * LOAD CAUHOI
     * @param cauhoi
     */
    public void loadCauHoi(CauHoi cauhoi){
        txtNameCauHoi.setText("Câu :" + String.valueOf(cauhoi.getTxtID()) + " / " + cauHoiList.size());
        txt_cauHoi.setText(cauhoi.getTxtCauHoi().toString());
        dapAnAdapter = new DapAnAdapter(getActivity(),cauhoi);
        dapAnAdapter.notifyDataSetChanged();
        RecyclerDapAn.setAdapter(dapAnAdapter);

    }
    /**
     * BUTTON KIEM TRA
     */
    public void buttonKiemTra(){
        btnKienTra = (Button) view.findViewById(R.id.btn_kiemTra);
        btnKienTra.setOnClickListener(this);
    }
    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_kiemTra:
                onClickButtonKiemTra();
                break;
            case R.id.btn_phone:
                onClickButtonPhone();
                break;
            case R.id.btn_50_50:
                onClickButton5050();
                break;
            case R.id.btn_next:
                onClickButtonNext();
                break;
            case R.id.btn_googleDich:
                onClickButtonDich();
                break;
            default:
                break;
        }

    }
    public void onClickButtonKiemTra(){
        switch (btn){
            case 0:
                break;
            case 1:
                kiemtra();
                break;
            case 2:
                cauHoiList.get(ID).setTime(timeMAX - time);
                if(ID == cauHoiList.size()-1){
                    ListKetQua ketqua = new ListKetQua(cauHoiList,TYPE_EXERCISES,mcd);
                    ketqua.setCauHoiList(cauHoiList);
                    Intent intent = new Intent(getActivity(),KetQuaActivity.class);
                    Bundle bundle = new Bundle();
                    bundle.putSerializable("ketqua", ketqua);
                    bundle.putInt("macd", mcd);
                    bundle.putString("grammar", grammar);
                    intent.putExtras(bundle);
                    startActivity(intent);
                    getActivity().overridePendingTransition(R.anim.animation_in, R.anim.animation_out);
                    getActivity().finish();
                }else {
                    chuyencau();
                    Animation animation = AnimationUtils.loadAnimation(getActivity(), R.anim.animation_btn);
                    btnKienTra.startAnimation(animation);
                    btnKienTra.setBackgroundResource(R.drawable.bg_item);
                    btnKienTra.setText(getResources().getString(R.string.kiemtra));
                    btnKienTra.setTextColor(getResources().getColor(android.R.color.black));
                }
                break;
            default:
                break;

        }
    }
    /**
     * BUTTON TRỢ GIÚP
     */
    public void troGiup(){
        btnPhone = (ImageView) view.findViewById(R.id.btn_phone);
        btn50_50 = (ImageView) view.findViewById(R.id.btn_50_50);
        btnNext = (ImageView) view.findViewById(R.id.btn_next);
        btnGoogleDich = (ImageView) view.findViewById(R.id.btn_googleDich);
        btnPhone.setOnClickListener(this);
        btn50_50.setOnClickListener(this);
        btnNext.setOnClickListener(this);
        btnGoogleDich.setOnClickListener(this);
    }
    private void onClickButtonPhone() {
        if(btn!=2){
            dapAnAdapter.chonItem(cauHoiList.get(ID).getIDDapAnDung());
            kiemtra();
            btnPhone.setVisibility(View.INVISIBLE);
        }
    }
    private void onClickButton5050() {
        if(btn!=2){
            btn50_50.setVisibility(View.INVISIBLE);
            List<Integer>lst_5050 = new ArrayList<>();
            for (int i = 0 ;i <=3 ; i++){
                if( i != cauHoiList.get(ID).getIDDapAnDung())
                lst_5050.add(i);
            }
            ArrayList<Integer> lst = new ArrayList<>();
            lst = GetRandomFromTo5050(lst_5050);


            if(lst.get(0)>lst.get(1))
            {
                if(lst.get(0)<cauHoiList.get(ID).getIDDapAnDung()){
                    dapAnAdapter.removeListItem(lst.get(0));
                    dapAnAdapter.removeListItem(lst.get(1));
                    cauHoiList.get(ID).setIDDapAnDung(cauHoiList.get(ID).getIDDapAnDung() - 2);
                }
                if(lst.get(1)>cauHoiList.get(ID).getIDDapAnDung()){
                    dapAnAdapter.removeListItem(lst.get(0));
                    dapAnAdapter.removeListItem(lst.get(1));

                }

                if(lst.get(1)<cauHoiList.get(ID).getIDDapAnDung() && lst.get(0)>cauHoiList.get(ID).getIDDapAnDung()){
                    dapAnAdapter.removeListItem(lst.get(0));
                    dapAnAdapter.removeListItem(lst.get(1));
                    cauHoiList.get(ID).setIDDapAnDung(cauHoiList.get(ID).getIDDapAnDung() - 1);
                }
            }else
            {
                if(lst.get(1)<cauHoiList.get(ID).getIDDapAnDung()){
                    dapAnAdapter.removeListItem(lst.get(1));
                    dapAnAdapter.removeListItem(lst.get(0));
                    cauHoiList.get(ID).setIDDapAnDung(cauHoiList.get(ID).getIDDapAnDung() - 2);
                }
                if(lst.get(0)>cauHoiList.get(ID).getIDDapAnDung()){
                    dapAnAdapter.removeListItem(lst.get(1));
                    dapAnAdapter.removeListItem(lst.get(0));

                }

                if(lst.get(0)<cauHoiList.get(ID).getIDDapAnDung() && lst.get(1)>cauHoiList.get(ID).getIDDapAnDung()){
                    dapAnAdapter.removeListItem(lst.get(1));
                    dapAnAdapter.removeListItem(lst.get(0));
                    cauHoiList.get(ID).setIDDapAnDung(cauHoiList.get(ID).getIDDapAnDung() - 1);
                }
            }

        }
    }

    private ArrayList<Integer> GetRandomFromTo5050(List<Integer> lst_5050 ) {
        ArrayList<Integer> lst = new ArrayList<>();
        List<Integer> lst_int = new ArrayList<Integer>();
        Random rd = new Random();
        int iNew ;
        int temp = lst_5050.size();
        int i = 0;
        while( i <=1) {
            iNew =rd.nextInt(temp);
            if (!lst_int.contains(iNew)) {
                i++;
                lst_int.add(iNew);
                lst.add(lst_5050.get(iNew));

            }
        }
        return lst;
    }
    private void onClickButtonNext() {
        if(btn!=2){
            btnNext.setVisibility(View.INVISIBLE);
            chuyencau();
        }
    }

    /**
     * DICH
     */

    private void onClickButtonDich() {
        new TranslateAsyncTask().execute();


    }
    class TranslateAsyncTask extends  AsyncTask<Void,Integer,String>{

        String translated;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            pdialog = new ProgressDialog(getContext());
            pdialog.setMessage("Loading......");
            pdialog.show();
        }

        @Override
        protected String doInBackground(Void... params) {
            try {
                translated = Translate.execute(cauHoiList.get(ID).getTxtCauHoi().toString(), Language.ENGLISH, Language.VIETNAMESE);
            } catch (Exception e) {
                translated = e.toString();
            }
            return translated;
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            pdialog.dismiss();
            AlertDialog.Builder ad=new AlertDialog.Builder(getContext());
            ad.setTitle("Dịch câu:");
            ad.setMessage(s.toString());
            ad.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    dialog.dismiss();
                }
            });
            ad.show();
        }


    }




    private void kiemtra() {
        btn = 2;
        Animation animation =  AnimationUtils.loadAnimation(getActivity(), R.anim.animation_btn);
        btnKienTra.startAnimation(animation);
        btnKienTra.setBackgroundResource(R.drawable.bg_btn_tieptheo);
        btnKienTra.setText(getResources().getString(R.string.tieptheo));
        btnKienTra.setTextColor(getResources().getColor(android.R.color.white));
        dapAnAdapter.showDapAn(1);
        dapAnAdapter.notifyDataSetChanged();
    }

    private void chuyencau() {
        time = timeMAX;
        btn = 0;
        Time();
        ID++;
        loadCauHoi(cauHoiList.get(ID));
    }
    /**
     * TIME
     */
    private void Time(){

        thread = new Thread(new Runnable() {
            @Override
            public void run() {
                while (time >= 0 && btn != 2){
                    handler.sendEmptyMessage(time);
                    time -- ;
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                if(time < 0)
                    handler.sendEmptyMessage(-11);
                btn= 2;
            }
        });
        thread.start();

    }


    /**
     * LIST RECYCLERVIEW
     */
    private void ListRecyclerView(){
        RecyclerDapAn = (RecyclerView) view.findViewById(R.id.list_dapAn);
        RecyclerDapAn.setHasFixedSize(true);
        RecyclerDapAn.setOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
            }
        });
        RecyclerDapAn.addOnItemTouchListener(new RecyclerViewTouchListeneer(getActivity(), RecyclerDapAn, this));

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        RecyclerDapAn.setLayoutManager(linearLayoutManager);
        loadCauHoi(cauHoiList.get(ID));
    }
    /**
     * ClICK
     */
    @Override
    public void onClickListener(View view1, int position) {
        if(btn!=2){
            dapAnAdapter.chonItem(position);
            dapAnAdapter.notifyDataSetChanged();
            RecyclerDapAn.setAdapter(dapAnAdapter);
            if (btn == 0) {
                btnKienTra.setBackgroundResource(R.drawable.bg_btn_kiemtra);
                btnKienTra.setTextColor(getResources().getColor(android.R.color.white));
                btn = 1;
            }
        }
    }
    /**
     * LONGCLICK
     */
    @Override
    public void onLongClickListener(View view, int position) {
    }
    private static class RecyclerViewTouchListeneer implements RecyclerView.OnItemTouchListener{
        private Context mContext;
        private GestureDetector mGestureDetector;
        private RecyclerViewOnClickListener mRecyclerViewOnClickListener;
        public RecyclerViewTouchListeneer(Context context,final RecyclerView mRecyclerView, final RecyclerViewOnClickListener mRecyclerViewOnClickListener) {
            this.mContext = context;
            this.mRecyclerViewOnClickListener = mRecyclerViewOnClickListener;
            mGestureDetector = new GestureDetector(mContext,new GestureDetector.SimpleOnGestureListener(){
                @Override
                public void onLongPress(MotionEvent e) {
                    super.onLongPress(e);
                    View view = mRecyclerView.findChildViewUnder(e.getX(),e.getY());

                    if (view != null && mRecyclerViewOnClickListener != null){
                        mRecyclerViewOnClickListener.onLongClickListener(view, mRecyclerView.getChildPosition(view));
                    }
                }

                @Override
                public boolean onSingleTapUp(MotionEvent e) {
                    View view = mRecyclerView.findChildViewUnder(e.getX(),e.getY());

                    if (view != null && mRecyclerViewOnClickListener != null){
                        mRecyclerViewOnClickListener.onClickListener(view, mRecyclerView.getChildPosition(view));
                    }
                    return (true);
                }
            });
        }
        @Override
        public boolean onInterceptTouchEvent(RecyclerView rv, MotionEvent e) {
            mGestureDetector.onTouchEvent(e);
            return false;}
        @Override
        public void onTouchEvent(RecyclerView rv, MotionEvent e) {}
        @Override
        public void onRequestDisallowInterceptTouchEvent(boolean disallowIntercept) {}
    }


}
