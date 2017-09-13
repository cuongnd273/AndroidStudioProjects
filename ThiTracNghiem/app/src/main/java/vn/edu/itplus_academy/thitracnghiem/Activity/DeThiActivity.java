package vn.edu.itplus_academy.thitracnghiem.Activity;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.Point;
import android.graphics.Rect;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.GestureDetector;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.DecelerateInterpolator;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import retrofit.Callback;
import retrofit.RestAdapter;
import retrofit.RetrofitError;
import retrofit.client.Response;
import vn.edu.itplus_academy.thitracnghiem.API.API;
import vn.edu.itplus_academy.thitracnghiem.Adapter.CauHoiAdapter;
import vn.edu.itplus_academy.thitracnghiem.Adapter.DapAnAdapter;
import vn.edu.itplus_academy.thitracnghiem.Adapter.DapAnImgAdapter;
import vn.edu.itplus_academy.thitracnghiem.DataBase.DBAdapter;
import vn.edu.itplus_academy.thitracnghiem.Model.CauHoi;
import vn.edu.itplus_academy.thitracnghiem.Model.DapAn;
import vn.edu.itplus_academy.thitracnghiem.Model.MonThi;
import vn.edu.itplus_academy.thitracnghiem.Model.ThiSinh;
import vn.edu.itplus_academy.thitracnghiem.R;
import vn.edu.itplus_academy.thitracnghiem.Service.TimeIntentService;
import vn.edu.itplus_academy.thitracnghiem.mModel.ResultAddResult;
import vn.edu.itplus_academy.thitracnghiem.mModel.ketqua;
import vn.edu.itplus_academy.thitracnghiem.mModel.resultchoises;
import vn.edu.itplus_academy.thitracnghiem.mModel.resulttexts;
import vn.edu.itplus_academy.thitracnghiem.widgets.RecyclerViewOnClickListener;

public class DeThiActivity extends AppCompatActivity implements RecyclerViewOnClickListener,View.OnClickListener,AdapterView.OnItemClickListener{

    private final static String CHUALAM = "ChuaLam";
    private final static String DANGLAM = "DangLam";
    private final static String DALAM = "DaLam";
    private final static String MAMONTHI = "MaMonThi";
    private final static String CAUHOIIMG = "image";
    private final static String CAUHOITXT = "text";
    private final static String DOANVAN = "doanvan";
    private Toolbar mToolbar;
    private DrawerLayout drawer;
    private List<CauHoi> cauHoiList;
    private List<DapAn> dapAnList;
    private DapAnAdapter dapAnAdapter;
    private DapAnImgAdapter dapAnImgAdapter;
    private RecyclerView RecyclerDapAn;
    private ListView listViewCauHoi;
    private CauHoiAdapter cauHoiAdapter;
    private TextView txtNameCauHoi,txtCauHoi;
    private ImageView imgCauHoi;
    private EditText edtDapAn;
    private Button btnNopBai,btnQuayLai,btnTiepTheo,btnXong;
    ProgressBar progressBarTime;
    TextView txtTime;
    int timeMax = 3600;
    int ID = 0;
    private DBAdapter db;
    private List<ThiSinh> thiSinhs;
    private int maMonThi ;
    private int maCauHoi;
    private int mShortAnimationDuration;
    private Animator mCurrentAnimator;

    private Intent serviceIntent;

    private ResponseReceiver receiver = new ResponseReceiver();




    // Broadcast component
    // Class mô phỏng một bộ thu sóng
    // (Thu tín hiệu gửi từ Service).
    public class ResponseReceiver extends BroadcastReceiver {

        // on broadcast received
        @Override
        public void onReceive(Context context, Intent intent) {

            // Kiểm tra nhiệm vụ của Intent gửi đến.
            if(intent.getAction().equals(TimeIntentService.ACTION_1)) {
                int value = intent.getIntExtra("time", -1);

                new ShowProgressBarTask().execute(value);

            }
        }
    }

    // Class làm nhiệm vụ hiển thị giá trị cho ProgressBar.
    class ShowProgressBarTask extends AsyncTask<Integer, Integer, Integer> {

        @Override
        protected Integer doInBackground(Integer... args) {

            return args[0];
        }

        @Override
        protected void onPostExecute(Integer result) {
            super.onPostExecute(result);

            progressBarTime.setProgress(result);
            if (result < timeMax/2) {
                progressBarTime.setProgressDrawable(getResources().getDrawable(
                        R.drawable.cutom_progressbar20));
            } else if (result < timeMax*2/3) {
                progressBarTime.setProgressDrawable(getResources().getDrawable(
                        R.drawable.cutom_progressbar60));
            } else {
                progressBarTime.setProgressDrawable(getResources().getDrawable(
                        R.drawable.cutom_progressbar100));
            }
            doitime(result);


        }
    }



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_de_thi);
        txtNameCauHoi = (TextView) findViewById(R.id.txt_namecauhoi);
        txtCauHoi = (TextView) findViewById(R.id.txt_cauhoi);
        imgCauHoi = (ImageView) findViewById(R.id.img_cauhoi);
        edtDapAn = (EditText) findViewById(R.id.edt_dapan);
        progressBarTime = (ProgressBar) findViewById(R.id.progressBar_time);
        progressBarTime.setMax(timeMax);
        txtTime = (TextView) findViewById(R.id.txt_time);
        db = new DBAdapter(this);

        getData();

        toolBar();

        button();

        navigationView();

        list();

        ListRecyclerView();

        listView();

        thiSinhs = new ArrayList<>();
        thiSinhs = db.getAll_ThiSinh();
        if(thiSinhs.get(0).getStatusThiSinh().equals(CHUALAM)) {
            AlertDialog.Builder alert = new AlertDialog.Builder(this);
            alert.setTitle("Thi Trắc Nghiệm ");
            alert.setMessage("Bạn sẽ thi 5 môn trong vòng 60 phut .Hãy nhấn ok để bắt đầu Bài Thi ");
            alert.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    ThiSinh thiSinh = new ThiSinh();
                    thiSinh = thiSinhs.get(0);
                    thiSinh.setStatusThiSinh(DANGLAM);
                    db.update_ThiSinh(thiSinh);
                    startTime();
                }
            });
            alert.show();
        }
    }

    public void getData() {
        try {
            Intent intent = getIntent();
            Bundle bundle = intent.getExtras();
            maMonThi = bundle.getInt(MAMONTHI);
        }catch (Exception e){

        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        // Đăng ký bộ thu sóng với Activity.
        registerReceiver(receiver, new IntentFilter(
                TimeIntentService.ACTION_1));
    }

    @Override
    protected void onStop() {
        super.onStop();
        // Hủy đăng ký bộ thu sóng với Activity.
        unregisterReceiver(receiver);
    }

    // Phương thức được gọi khi người dùng nhấn vào nút Start.
    public void startTime()  {
        // Intent yêu cầu gửi đến Service.
        serviceIntent = new Intent(this, TimeIntentService.class);

        // Chạy dịch vụ.
        startService(serviceIntent);
        Log.d("service_","Start");

    }


    public void stopTime()  {
        if(serviceIntent == null){
            serviceIntent = new Intent(this, TimeIntentService.class);
            stopService(serviceIntent);
        }else {
            stopService(serviceIntent);
        }

    }

    private void list(){

        cauHoiList = new ArrayList<>();
        cauHoiList = db.get_CauHoi_MT(maMonThi);
        if(cauHoiList.size()== 0){
            finish();
            overridePendingTransition(R.anim.animation_finish_in, R.anim.animation_finish_out);
        }
    }
    /**
     * LOAD CÂU HỎI
     */
    public void loadCauHoi(final CauHoi cauhoi){
        int cau = ID + 1;

        if(cauhoi.getStatusCauHoi().equals(CAUHOITXT)){
            txtNameCauHoi.setText(cauhoi.getTxtID());
            imgCauHoi.setVisibility(View.INVISIBLE);
            txtCauHoi.setVisibility(View.VISIBLE);
            maCauHoi = cauhoi.getMaCauHoi();

            txtCauHoi.setText(cauhoi.getTxtCauHoi());
            if(cauhoi.getDapAnList().size() != 0){
                RecyclerDapAn.setVisibility(View.VISIBLE);
                edtDapAn.setVisibility(View.INVISIBLE);
                btnXong.setVisibility(View.INVISIBLE);
                dapAnAdapter = new DapAnAdapter(this,cauhoi);
                dapAnAdapter.notifyDataSetChanged();
                RecyclerDapAn.setAdapter(dapAnAdapter);
            }else {
                RecyclerDapAn.setVisibility(View.INVISIBLE);
                edtDapAn.setVisibility(View.VISIBLE);
                btnXong.setVisibility(View.VISIBLE);
                edtDapAn.setText(cauhoi.getDapAnTxt());
            }

        }else if(cauhoi.getStatusCauHoi().equals(CAUHOIIMG)){
            txtNameCauHoi.setText(cauhoi.getTxtID());
            txtCauHoi.setVisibility(View.INVISIBLE);
            imgCauHoi.setVisibility(View.VISIBLE);
            maCauHoi = cauhoi.getMaCauHoi();

            Picasso.with(this)
                    .load(cauhoi.getTxtCauHoi())
                    .into(imgCauHoi);
            if(cauhoi.getDapAnList().size() != 0){
                RecyclerDapAn.setVisibility(View.VISIBLE);
                edtDapAn.setVisibility(View.INVISIBLE);
                btnXong.setVisibility(View.INVISIBLE);
                dapAnImgAdapter = new DapAnImgAdapter(this,cauhoi);
                dapAnImgAdapter.notifyDataSetChanged();
                RecyclerDapAn.setAdapter(dapAnImgAdapter);
            }else {
                RecyclerDapAn.setVisibility(View.INVISIBLE);
                edtDapAn.setVisibility(View.VISIBLE);
                btnXong.setVisibility(View.VISIBLE);
                edtDapAn.setText(cauhoi.getDapAnTxt());
                imgCauHoi.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        zoomImageFromThumb(imgCauHoi, cauhoi.getTxtCauHoi());
                    }
                });
                mShortAnimationDuration = getResources().getInteger(
                        android.R.integer.config_shortAnimTime);
            }
        }else if(cauhoi.getStatusCauHoi().equals(DOANVAN)){
            txtNameCauHoi.setText("");
            imgCauHoi.setVisibility(View.INVISIBLE);
            txtCauHoi.setVisibility(View.VISIBLE);
            RecyclerDapAn.setVisibility(View.INVISIBLE);
            edtDapAn.setVisibility(View.INVISIBLE);
            maCauHoi = cauhoi.getMaCauHoi();
            txtCauHoi.setText(cauhoi.getTxtCauHoi());
        }


    }
    /**
     * BUTTON
     */
    private void button() {
        btnNopBai = (Button) findViewById(R.id.btn_nopbai);
        btnQuayLai = (Button) findViewById(R.id.btn_quaylai);
        btnTiepTheo = (Button) findViewById(R.id.btn_tieptheo);
        btnXong = (Button) findViewById(R.id.btn_xong);
        btnQuayLai.setOnClickListener(this);
        btnTiepTheo.setOnClickListener(this);
        btnNopBai.setOnClickListener(this);
        btnXong.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_nopbai:
                onClickButtonNopBai();
                break;
            case R.id.btn_quaylai:
                onClickButtonQuayLai();
                break;
            case R.id.btn_tieptheo:
                onClickButtonTiepTheo();
                break;
            case R.id.btn_xong:
                onClickButtonXong();
                break;
            default:
                break;
        }

    }


    public void onClickButtonNopBai() {
        if(!db.get_MonThi(maMonThi).getStatusMonThi().equals(DALAM) ){
            int somondalam = 0;
            final List<MonThi> monThiList = db.getAll_MonThi();
            for(int i = 0; i < monThiList.size();i++){
                if(monThiList.get(i).getStatusMonThi().equals(DALAM)){
                    somondalam++;
                }
            }
            final  int so = somondalam;
            AlertDialog.Builder alert=new AlertDialog.Builder(this);
            alert.setTitle("Thi Trắc Nghiệm ");
            alert.setMessage("Bạn Có Chắc chắn muốn nộp bài  ");
            alert.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    if(so == monThiList.size()-1){
                        thiSinhs = new ArrayList<>();
                        thiSinhs = db.getAll_ThiSinh();
                        ThiSinh thiSinh=thiSinhs.get(0);
                        AlertDialog.Builder alert=new AlertDialog.Builder(DeThiActivity.this);
                        alert.setTitle("Thi Trắc Nghiệm ");
                        alert.setMessage("Chúc mừng thí sinh SBD: " + thiSinh.getMaDuThi() + "\nĐã hoàn thành bài kiểm tra kỹ năng CNTT của Viện CNTT – Đại học Quốc Gia Hà Nội");
                        alert.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                stopTime();
                                guiketqua();
                            }
                        });
                        alert.show();
                        db.update_Status_MonThi(maMonThi,DALAM);


                    }else {
                        guiketqua();


                    }

                }
            });
            alert.setNegativeButton("No", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {

                }
            });
            alert.show();


        }

    }

    public void onClickButtonQuayLai() {
        if(ID != 0){
            ID--;
            loadCauHoi(cauHoiList.get(ID));
        }
    }

    public void onClickButtonTiepTheo() {
        if(ID != cauHoiList.size()-1){
            ID++;
            loadCauHoi(cauHoiList.get(ID));
        }
    }

    private void onClickButtonXong() {
        if(!db.get_MonThi(maMonThi).getStatusMonThi().equals(DALAM) ) {
            db.update_DapAn_TxT_CauHoi(maCauHoi, edtDapAn.getText().toString());
            Toast.makeText(this,"Đã lưu câu trả lời",Toast.LENGTH_SHORT).show();
            list();
        }
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
        RecyclerDapAn = (RecyclerView) findViewById(R.id.rv_dapan);
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
        RecyclerDapAn.addOnItemTouchListener(new RecyclerViewTouchListeneer(DeThiActivity.this, RecyclerDapAn, this));

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        RecyclerDapAn.setLayoutManager(linearLayoutManager);
        loadCauHoi(cauHoiList.get(ID));


    }

    /**
     * ClICK
     */
    @Override
    public void onClickListener(View view, int position) {

        if(!db.get_MonThi(maMonThi).getStatusMonThi().equals(DALAM) ){
            if(cauHoiList.get(ID).getStatusCauHoi().equals(CAUHOITXT)){
                dapAnAdapter.chonItem(position);
                dapAnAdapter.notifyDataSetChanged();
                RecyclerDapAn.setAdapter(dapAnAdapter);
                db.update_DapAn_CauHoi(maCauHoi,position);
            }else {
                dapAnImgAdapter.chonItem(position);
                dapAnImgAdapter.notifyDataSetChanged();
                RecyclerDapAn.setAdapter(dapAnImgAdapter);
                db.update_DapAn_CauHoi(maCauHoi,position);
            }

        }
    }

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
        listViewCauHoi = (ListView) findViewById(R.id.lv_cauhoi);
        cauHoiAdapter = new CauHoiAdapter(this,cauHoiList);
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
        getMenuInflater().inflate(R.menu.main, menu);
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
            finish();
            overridePendingTransition(R.anim.animation_finish_in, R.anim.animation_finish_out);
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {
        finish();
        overridePendingTransition(R.anim.animation_finish_in, R.anim.animation_finish_out);
    }

    public void guiketqua(){
        list();
        List<resulttexts> resulttextsList = new ArrayList<>();
        for (int i = 0 ; i < cauHoiList.size();i++){
            if(cauHoiList.get(i).getDapAnList().size()== 0){
                resulttexts resulttexts = new resulttexts();
                resulttexts.setQid(cauHoiList.get(i).getMaCauHoi());
                resulttexts.setValue(cauHoiList.get(i).getDapAnTxt());
                resulttextsList.add(resulttexts);
            }
        }
        List<resultchoises> resultchoisesList = new ArrayList<>();
        for (int i = 0 ; i < cauHoiList.size();i++){
            if(cauHoiList.get(i).getDapAnList().size()!=0){
                resultchoises resultchoises = new resultchoises();
                resultchoises.setQid(cauHoiList.get(i).getMaCauHoi());
                for(int j = 0 ; j < cauHoiList.get(i).getDapAnList().size(); j++){
                    if( j == cauHoiList.get(i).getDapAn()){
                        resultchoises.setAid(cauHoiList.get(i).getDapAnList().get(j).getMaDapAn());
                    }
                }
                resultchoisesList.add(resultchoises);
            }
        }
        ketqua ketqua = new ketqua();
        ketqua.setResulttexts(resulttextsList);
        ketqua.setResultchoises(resultchoisesList);
        ketqua.setCode(thiSinhs.get(0).getMaDuThi());
        ketqua.setPhonenum(thiSinhs.get(0).getSodienthoai());
        ketqua.setTqid(maMonThi);
        Gson gson=new Gson();
        String json=gson.toJson(ketqua);
        Log.i("JSON",json);
        RestAdapter restAdapter=new RestAdapter.Builder().setEndpoint(LogInActivity.URL).build();
        API api=restAdapter.create(API.class);
        api.addresult(json, new Callback<ResultAddResult>() {
            @Override
            public void success(ResultAddResult resultAddResult, Response response) {
                if(resultAddResult.getSuccess().equals("1"))
                {
                    Toast.makeText(DeThiActivity.this,"Nộp bài môn thành công",Toast.LENGTH_LONG).show();
                    db.update_Status_MonThi(maMonThi,DALAM);
                    finish();
                    overridePendingTransition(R.anim.animation_finish_in, R.anim.animation_finish_out);
                }
            }

            @Override
            public void failure(RetrofitError error) {
                   Log.i("guiketqua",error.toString());
            }
        });
    }
    private void zoomImageFromThumb(final View thumbView, String imageResId) {
        if (mCurrentAnimator != null) {
            mCurrentAnimator.cancel();
        }
        final ImageView expandedImageView = (ImageView) findViewById(
                R.id.expanded_image);
        Picasso.with(this)
                .load(imageResId)
                .into(expandedImageView);
        final Rect startBounds = new Rect();
        final Rect finalBounds = new Rect();
        final Point globalOffset = new Point();

        thumbView.getGlobalVisibleRect(startBounds);
        findViewById(R.id.container)
                .getGlobalVisibleRect(finalBounds, globalOffset);
        startBounds.offset(-globalOffset.x, -globalOffset.y);
        finalBounds.offset(-globalOffset.x, -globalOffset.y);

        float startScale;
        if ((float) finalBounds.width() / finalBounds.height()
                > (float) startBounds.width() / startBounds.height()) {
            startScale = (float) startBounds.height() / finalBounds.height();
            float startWidth = startScale * finalBounds.width();
            float deltaWidth = (startWidth - startBounds.width()) / 2;
            startBounds.left -= deltaWidth;
            startBounds.right += deltaWidth;
        } else {
            startScale = (float) startBounds.width() / finalBounds.width();
            float startHeight = startScale * finalBounds.height();
            float deltaHeight = (startHeight - startBounds.height()) / 2;
            startBounds.top -= deltaHeight;
            startBounds.bottom += deltaHeight;
        }

        thumbView.setAlpha(0f);
        expandedImageView.setVisibility(View.VISIBLE);

        expandedImageView.setPivotX(0f);
        expandedImageView.setPivotY(0f);

        AnimatorSet set = new AnimatorSet();
        set
                .play(ObjectAnimator.ofFloat(expandedImageView, View.X,
                        startBounds.left, finalBounds.left))
                .with(ObjectAnimator.ofFloat(expandedImageView, View.Y,
                        startBounds.top, finalBounds.top))
                .with(ObjectAnimator.ofFloat(expandedImageView, View.SCALE_X,
                        startScale, 1f)).with(ObjectAnimator.ofFloat(expandedImageView,
                View.SCALE_Y, startScale, 1f));
        set.setDuration(mShortAnimationDuration);
        set.setInterpolator(new DecelerateInterpolator());
        set.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                mCurrentAnimator = null;
            }

            @Override
            public void onAnimationCancel(Animator animation) {
                mCurrentAnimator = null;
            }
        });
        set.start();
        mCurrentAnimator = set;

        final float startScaleFinal = startScale;
        expandedImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mCurrentAnimator != null) {
                    mCurrentAnimator.cancel();
                }

                AnimatorSet set = new AnimatorSet();
                set.play(ObjectAnimator
                        .ofFloat(expandedImageView, View.X, startBounds.left))
                        .with(ObjectAnimator
                                .ofFloat(expandedImageView,
                                        View.Y, startBounds.top))
                        .with(ObjectAnimator
                                .ofFloat(expandedImageView,
                                        View.SCALE_X, startScaleFinal))
                        .with(ObjectAnimator
                                .ofFloat(expandedImageView,
                                        View.SCALE_Y, startScaleFinal));
                set.setDuration(mShortAnimationDuration);
                set.setInterpolator(new DecelerateInterpolator());
                set.addListener(new AnimatorListenerAdapter() {
                    @Override
                    public void onAnimationEnd(Animator animation) {
                        thumbView.setAlpha(1f);
                        expandedImageView.setVisibility(View.GONE);
                        mCurrentAnimator = null;
                    }

                    @Override
                    public void onAnimationCancel(Animator animation) {
                        thumbView.setAlpha(1f);
                        expandedImageView.setVisibility(View.GONE);
                        mCurrentAnimator = null;
                    }
                });
                set.start();
                mCurrentAnimator = set;
            }
        });
    }
}
