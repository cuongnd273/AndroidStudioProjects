package vn.edu.itplus_academy.tracnghiemtienganh.Activitys;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.GestureDetector;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.memetix.mst.language.Language;
import com.memetix.mst.translate.Translate;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import vn.edu.itplus_academy.tracnghiemtienganh.Adapter.CauHoiAdapter;
import vn.edu.itplus_academy.tracnghiemtienganh.Adapter.DapAnAdapter;
import vn.edu.itplus_academy.tracnghiemtienganh.Model.CauHoi;
import vn.edu.itplus_academy.tracnghiemtienganh.Model.DapAn;
import vn.edu.itplus_academy.tracnghiemtienganh.Model.ListKetQua;
import vn.edu.itplus_academy.tracnghiemtienganh.R;
import vn.edu.itplus_academy.tracnghiemtienganh.databases.DBAdapter;
import vn.edu.itplus_academy.tracnghiemtienganh.interfaces.RecyclerViewOnClickListener;
import vn.edu.itplus_academy.tracnghiemtienganh.models.CauHoiKT;

public class TestActivity extends AppCompatActivity implements RecyclerViewOnClickListener,View.OnClickListener,AdapterView.OnItemClickListener{

    private static final int TYPE_TEST = 1;
    private Toolbar mToolbar;
    private int test;
    private DrawerLayout drawer;
    private List<CauHoi> cauHoiList;
    private List<DapAn> dapAnList;
    private DapAnAdapter dapAnAdapter;
    private RecyclerView RecyclerDapAn;
    private ListView listViewCauHoi;
    private CauHoiAdapter cauHoiAdapter;
    private ProgressBar progressBarTime;
    private TextView txtTime,txtNameCauHoi,txt_cauHoi;
    private Handler handler;
    private Button btnNopBai,btnPrev,btnNext;
    private ImageView btnGoogleDich;
    ProgressDialog pdialog;
    int MAX = 20;
    int timeMax = 1500;
    int time = timeMax;
    int ID = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);
        txtNameCauHoi = (TextView) findViewById(R.id.txt_nameCauHoi);
        txt_cauHoi = (TextView) findViewById(R.id.txt_cauHoi);

        Translate.setClientId("12345678912");
        Translate.setClientSecret("123456789123456789123456789");

        toolBar();

        //getData();

        navigationView();

        list();

        ListRecyclerView();

        listView();

        button();

        AlertDialog.Builder alert=new AlertDialog.Builder(this);
        alert.setTitle("Thi Trắc Nghiệm ");
        alert.setMessage("Hãy nhấn ok để bắt đầu Bài Thi ");
        alert.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Time();
            }
        });
        alert.show();

    }
    private void list(){

        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        int start_id =bundle.getInt("test_index");
        test = start_id;
        int temp_id = (start_id*50)+1;

        List<CauHoiKT> lst_cauhoi_kt = new ArrayList<CauHoiKT>();

        DBAdapter db = new DBAdapter(getApplicationContext());
        db.open();
        lst_cauhoi_kt = db.getAll_Cauhoi_KT(temp_id);
        db.close();

        lst_cauhoi_kt = RandomCauHoi(lst_cauhoi_kt);
        //Toast.makeText(getApplicationContext(),lst_cauhoi_kt.size()+"---" + start_id,Toast.LENGTH_LONG).show();

        String[] txtQuestionID={"A","B","C","D"};

        cauHoiList = new ArrayList<>();

        for(int i=0; i<50; i++){

            List<DapAn> dapAnList = new ArrayList<>();
            dapAnList.add(new DapAn(txtQuestionID[0], lst_cauhoi_kt.get(i).getDapan_A()));
            dapAnList.add(new DapAn(txtQuestionID[1], lst_cauhoi_kt.get(i).getDapan_B()));
            dapAnList.add(new DapAn(txtQuestionID[2], lst_cauhoi_kt.get(i).getDapan_C()));
            dapAnList.add(new DapAn(txtQuestionID[3], lst_cauhoi_kt.get(i).getDapan_D()));

            cauHoiList.add(new CauHoi(i+1,lst_cauhoi_kt.get(i).getCauhoi(),dapAnList,checkDapanDung(lst_cauhoi_kt.get(i).getKetqua()),0,-1,0));
        }
    }
    public List RandomCauHoi(List<CauHoiKT> listCH)
    {
        List<CauHoiKT> listRandom=new ArrayList();
        while (listCH.size()>0) {
            Random r = new Random();
            int random=r.nextInt(listCH.size());
            listRandom.add(listCH.get(random));
            listCH.remove(random);
        }
        return listRandom;
    }
    private int checkDapanDung(String temp)
    {

        if("A".equals(temp.trim().toString()))
        {
            return 0;
        }
        if("B".equals(temp.trim().toString()))
        {
            return 1;
        }
        if("C".equals(temp.trim().toString()))
        {
            return 2;
        }
        if("D".equals(temp.trim().toString()))
        {
            return 3;
        }

        return 0;
    }
    /**
     * LOAD CÂU HỎI
     *
     */
    public void loadCauHoi(CauHoi cauhoi){
        txtNameCauHoi.setText("Câu :" + String.valueOf(cauhoi.getTxtID()) + " / "+cauHoiList.size());
        txt_cauHoi.setText(cauhoi.getTxtCauHoi().toString());
        dapAnAdapter = new DapAnAdapter(TestActivity.this,cauhoi);
        dapAnAdapter.notifyDataSetChanged();
        RecyclerDapAn.setAdapter(dapAnAdapter);

    }
    /**
     * BUTTON
     */
    private void button() {
        btnNopBai = (Button) findViewById(R.id.btn_nopBai);
        btnPrev = (Button) findViewById(R.id.btn_prev);
        btnNext = (Button) findViewById(R.id.btn_next);
        btnGoogleDich = (ImageView) findViewById(R.id.btn_googleDich);
        btnPrev.setOnClickListener(this);
        btnNext.setOnClickListener(this);
        btnNopBai.setOnClickListener(this);
        btnGoogleDich.setOnClickListener(this);
    }
    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_nopBai:
                onClickButtonNopBai();
                break;
            case R.id.btn_prev:
                onClickButtonPrev();
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
    public void onClickButtonNopBai() {
        ListKetQua ketqua = new ListKetQua(cauHoiList,TYPE_TEST,test);
        ketqua.setCauHoiList(cauHoiList);
        Intent intent = new Intent(TestActivity.this,KetQuaActivity.class);
        Bundle bundle = new Bundle();
        bundle.putSerializable("ketqua", ketqua);
        intent.putExtras(bundle);
        startActivity(intent);
        overridePendingTransition(R.anim.animation_in, R.anim.animation_out);
        finish();
    }

    public void onClickButtonPrev() {
        if(ID != 0){
            ID--;
            loadCauHoi(cauHoiList.get(ID));
        }
    }

    public void onClickButtonNext() {
        if(ID != 49){
            ID++;
            loadCauHoi(cauHoiList.get(ID));
        }
    }
    public void onClickButtonDich() {
        new TranslateAsyncTask().execute();
    }
    class TranslateAsyncTask extends AsyncTask<Void,Integer,String> {

        String translated;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            pdialog = new ProgressDialog(TestActivity.this);
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
            android.app.AlertDialog.Builder ad=new android.app.AlertDialog.Builder(TestActivity.this);
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

    /**
     * TIME
     */
    public void Time(){
        progressBarTime = (ProgressBar) findViewById(R.id.progressBar_time);
        progressBarTime.setMax(timeMax);
        txtTime = (TextView) findViewById(R.id.txt_time);

        handler = new Handler(){
            @Override
            public void handleMessage(Message msg) {
                super.handleMessage(msg);
                if(msg.what != -11) {
                    progressBarTime.setProgress(msg.what);
                    if (msg.what < timeMax/2) {
                        progressBarTime.setProgressDrawable(getResources().getDrawable(
                                R.drawable.cutom_progressbar20));
                    } else if (msg.what < timeMax*2/3) {
                        progressBarTime.setProgressDrawable(getResources().getDrawable(
                                R.drawable.cutom_progressbar60));
                    } else {
                        progressBarTime.setProgressDrawable(getResources().getDrawable(
                                R.drawable.cutom_progressbar100));
                    }

                    doitime(msg.what);
                } else {
                    onClickButtonNopBai();
                }
            }
        };
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                while (time >= 0){
                    handler.sendEmptyMessage(time);
                    time -- ;
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                if(time < 0){
                    handler.sendEmptyMessage(-11);
                }
            }
        });

        thread.start();

    }

    public void doitime(int totalSecs) {
        int hours = totalSecs / 3600;
        int minutes = (totalSecs % 3600) / 60;
        int seconds = totalSecs % 60;
        txtTime.setText(minutes +"m"+seconds+"s");

    }
    /**
     * LIST RECYCLERVIEW
     */
    private void ListRecyclerView(){
        RecyclerDapAn = (RecyclerView) findViewById(R.id.list_dapAn);
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
        RecyclerDapAn.addOnItemTouchListener(new RecyclerViewTouchListeneer(TestActivity.this, RecyclerDapAn, this));

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(TestActivity.this);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        RecyclerDapAn.setLayoutManager(linearLayoutManager);
        loadCauHoi(cauHoiList.get(ID));


    }

    /**
     * ClICK
     */
    @Override
    public void onClickListener(View view, int position) {
        dapAnAdapter.chonItem(position);
        dapAnAdapter.notifyDataSetChanged();
        RecyclerDapAn.setAdapter(dapAnAdapter);
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

    /**
     * TOOLBAR
     */
    private void toolBar(){
        mToolbar = (Toolbar) findViewById(R.id.toolbar);
        mToolbar.setTitle("Trắc Nghiệm");
        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    /**
     * LISTVIEW
     */
    public void listView(){
        listViewCauHoi = (ListView) findViewById(R.id.listViewCauHoi);
        cauHoiAdapter = new CauHoiAdapter(TestActivity.this,cauHoiList);
        listViewCauHoi.setAdapter(cauHoiAdapter);
        listViewCauHoi.setOnItemClickListener(this);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        ID =position;
        loadCauHoi(cauHoiList.get(position));
        drawer.closeDrawer(GravityCompat.END);
    }
    /**
     *  NAVIGATIONVIEW
     */
    private void navigationView(){
        drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
    }
    /**
     *   MENU
     */
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.test_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();


        if (id == R.id.action_cauhoi) {
            if (drawer.isDrawerOpen(GravityCompat.END)) {
                drawer.closeDrawer(GravityCompat.END);
            } else {
                drawer.openDrawer(GravityCompat.END);
            }
            return true;
        }
        if(id == android.R.id.home)
        {
            AlertDialog.Builder alert=new AlertDialog.Builder(this);
            alert.setTitle("Thi Trắc Nghiệm ");
            alert.setMessage("Bạn muốn kết thúc bài thi ?");
            alert.setPositiveButton("Có", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    finish();
                    overridePendingTransition(R.anim.animation_finish_in, R.anim.animation_finish_out);
                }
            });
            alert.setNegativeButton("Không", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    dialog.cancel();
                }
            });
            alert.show();
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {

//        super.onBackPressed();
        AlertDialog.Builder alert=new AlertDialog.Builder(this);
        alert.setTitle("Thi Trắc Nghiệm ");
        alert.setMessage("Bạn muốn kết thúc bài thi ?");
        alert.setPositiveButton("Có", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                finish();
                overridePendingTransition(R.anim.animation_finish_in, R.anim.animation_finish_out);
            }
        });
        alert.setNegativeButton("Không", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });
        alert.show();
    }
}
