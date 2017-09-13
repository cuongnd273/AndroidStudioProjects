package vn.edu.itplus_academy.tracnghiemtienganh.Activitys;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.GestureDetector;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import vn.edu.itplus_academy.tracnghiemtienganh.Adapter.DeThiAdapter;
import vn.edu.itplus_academy.tracnghiemtienganh.Model.DeThiDB;
import vn.edu.itplus_academy.tracnghiemtienganh.Model.Test;
import vn.edu.itplus_academy.tracnghiemtienganh.R;
import vn.edu.itplus_academy.tracnghiemtienganh.databases.DBAdapter;
import vn.edu.itplus_academy.tracnghiemtienganh.getdatas.GetDapAnData;
import vn.edu.itplus_academy.tracnghiemtienganh.getdatas.LoadData;
import vn.edu.itplus_academy.tracnghiemtienganh.interfaces.RecyclerViewOnClickListener;

public class DeThiActivity extends AppCompatActivity implements RecyclerViewOnClickListener {
    private Toolbar mToolbar;
    private RecyclerView recyclerView;
    private DeThiAdapter deThiAdapter;
    private List<Test> testList;
    private List<DeThiDB> lst_Dethi = new ArrayList<DeThiDB>();
    private int maNhomDe;
    ProgressDialog dialog;
    DeThiDB deThi;
    private Handler splashScreenHandler;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_de_thi);
        toolBar();
        list();

        ListRecyclerView();
    }
    /**
     * TOOLBAR
     */
    private void toolBar(){
        mToolbar = (Toolbar) findViewById(R.id.toolbar);
        mToolbar.setTitle(getResources().getString(R.string.dethi));
        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }
    /**
     *   LIST
     */
    private void list(){

        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        maNhomDe = bundle.getInt("nhomde");

        DBAdapter db = new DBAdapter(getApplicationContext());
        db.open();
        lst_Dethi = db.getAll_DeThi(maNhomDe);
        db.close();

//        testList = new ArrayList<>();
//        for (int i = 0; i < lst_Dethi.size() ; i++) {
//            testList.add(new Test(i+1,"Đề thi" ,lst_Dethi.get(i).getThoigian(),lst_Dethi.get(i).getPrg()));
//        }
    }

    /**
     *  LIST RECYCLERVIEW
     */
    private void ListRecyclerView(){
        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
            }
        });
        recyclerView.addOnItemTouchListener(new RecyclerViewTouchListeneer(getApplicationContext(), recyclerView, this));
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(DeThiActivity.this);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(linearLayoutManager);
        deThiAdapter = new DeThiAdapter(DeThiActivity.this,lst_Dethi);
        recyclerView.setAdapter(deThiAdapter);
        deThiAdapter.notifyDataSetChanged();
    }
    /**
     * ClICK
     */
    @Override
    public void onClickListener(View view,int position) {
//        Intent intent = new Intent(DeThiActivity.this, BoDeActivity.class);
//            Bundle bundle = new Bundle();
//            bundle.putInt("madt",lst_Dethi.get(position).getMadt());
//            intent.putExtras(bundle);
//            startActivity(intent);
//            overridePendingTransition(R.anim.animation_in, R.anim.animation_out);

        if(lst_Dethi.get(position).getLoaded() ==1 ){
            deThi=lst_Dethi.get(position);
            Intent intent = new Intent(DeThiActivity.this, BoDeActivity.class);
            Bundle bundle = new Bundle();
            bundle.putSerializable("dethi",deThi);
            intent.putExtras(bundle);
            startActivity(intent);
            overridePendingTransition(R.anim.animation_in, R.anim.animation_out);
        }
        else if(lst_Dethi.get(position).getLoaded()==0){
                        new LoadFileAsyn().execute(position);
        }
    }

    class LoadFileAsyn extends AsyncTask<Integer,Void,String>{

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            dialog = new ProgressDialog(DeThiActivity.this);
            dialog.setMessage("Loading......");
            dialog.show();
        }

        int pos;

        @Override
        protected String doInBackground(Integer... params) {
            pos = params[0];
            File dir = getApplicationContext().getFilesDir();
            File directory = new File(dir.getAbsolutePath() + "/pdf_file");
            if (!directory.exists())
                directory.mkdirs();
            File file =  new File(directory,lst_Dethi.get(pos).getMadt() + "_"+lst_Dethi.get(pos).getMand()+".pdf");
            String path = directory+"/"+ lst_Dethi.get(pos).getMadt() + "_"+lst_Dethi.get(pos).getMand()+".pdf";

           boolean temp = new LoadData().downloadFile_Server("http://"+lst_Dethi.get(pos).getPath(), file);
            if(temp){
                DBAdapter db = new DBAdapter(getApplicationContext());
                db.open();
                db.update_Dethi(lst_Dethi.get(pos).getMadt(), lst_Dethi.get(pos).getMand(), path, lst_Dethi.get(pos).getThoigian(), 1, 0);
                db.close();
                list();
                //getData dapan
                GetDapAnData getDapAnData = new GetDapAnData(getApplicationContext());
                getDapAnData.getDapAn_Data(lst_Dethi.get(pos).getMadt());
            }
            return path;
        }


        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            splashScreenHandler = new Handler();
            splashScreenHandler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    list();
                    deThi = lst_Dethi.get(pos);
                    Intent intent = new Intent(DeThiActivity.this, BoDeActivity.class);
                    Bundle bundle = new Bundle();
                    bundle.putSerializable("dethi", deThi);
                    intent.putExtras(bundle);
                    startActivity(intent);
                    overridePendingTransition(R.anim.animation_in, R.anim.animation_out);
                    dialog.dismiss();
                }
            }, 3000);


        }
    }
    /**
     *  LONGCLICK
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

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if(id == android.R.id.home)
        {
            finish();
            overridePendingTransition(R.anim.animation_finish_in, R.anim.animation_finish_out);
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        list();
        deThiAdapter = new DeThiAdapter(DeThiActivity.this,lst_Dethi);
        recyclerView.setAdapter(deThiAdapter);
        deThiAdapter.notifyDataSetChanged();
    }
}
