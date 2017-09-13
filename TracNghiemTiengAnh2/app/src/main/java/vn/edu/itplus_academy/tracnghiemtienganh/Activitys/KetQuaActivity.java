
package vn.edu.itplus_academy.tracnghiemtienganh.Activitys;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.text.SpannableString;
import android.text.style.ForegroundColorSpan;
import android.text.style.RelativeSizeSpan;
import android.text.style.StyleSpan;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.daimajia.androidanimations.library.Techniques;
import com.daimajia.androidanimations.library.YoYo;
import com.github.mikephil.charting.animation.Easing;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.Legend.LegendPosition;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.formatter.PercentFormatter;
import com.github.mikephil.charting.highlight.Highlight;
import com.github.mikephil.charting.listener.OnChartValueSelectedListener;
import com.github.mikephil.charting.utils.ColorTemplate;
import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.InterstitialAd;

import java.util.ArrayList;
import java.util.List;

import vn.edu.itplus_academy.tracnghiemtienganh.Adapter.KetQua2Adapter;
import vn.edu.itplus_academy.tracnghiemtienganh.Adapter.KetQuaAdapter;
import vn.edu.itplus_academy.tracnghiemtienganh.Model.CauHoi;
import vn.edu.itplus_academy.tracnghiemtienganh.Model.DeThiDB;
import vn.edu.itplus_academy.tracnghiemtienganh.Model.ListKetQua;
import vn.edu.itplus_academy.tracnghiemtienganh.R;
import vn.edu.itplus_academy.tracnghiemtienganh.databases.DBAdapter;

public class KetQuaActivity extends AppCompatActivity implements OnChartValueSelectedListener ,View.OnClickListener{

    private static final int TYPE_EXERCISES = 0;
    private static final int TYPE_TEST = 1;
    private static final int TYPE_BODE = 2;
    private static final int CHITIET = 0;
    private static final int THUGON = 1;
    private Toolbar mToolbar;
    private PieChart mChart;
    private ImageView star;
    private TextView txtDiem;
    private Button btnChiTiet,btnReset,btnNext;
    private RecyclerView recyclerView;
    private KetQuaAdapter ketQuaAdapter;
    private KetQua2Adapter ketQua2Adapter;
    private List<CauHoi> cauHoiList;
    private int type;
    private int ID;

    private float dung = 0;
    private float sai = 0;
    private float chualam =0;
    private float diem;
    private int ct =0;
    private int macd;
    private String grammar;
    private Typeface tf;
    private InterstitialAd mInterstitialAd;
    private AdRequest adRequest;
    private int madt;
    private String linkdt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_ketqua);

        mInterstitialAd = new InterstitialAd(this);
        mInterstitialAd.setAdUnitId(getString(R.string.interstitial_full_screen));
        adRequest = new AdRequest.Builder()
                .addTestDevice(AdRequest.DEVICE_ID_EMULATOR)
                .addTestDevice("60BA8E9F5A7E51F6347623DE66C30799")
                .build();

        toolBar();

        button();

        getData();

        PicChart();

        ListRecyclerView();

        mInterstitialAd.setAdListener(new AdListener() {
            @Override
            public void onAdClosed() {
                super.onAdClosed();
                finish();
                overridePendingTransition(R.anim.animation_finish_in, R.anim.animation_finish_out);
            }

            @Override
            public void onAdLoaded() {
                super.onAdLoaded();
                if (mInterstitialAd.isLoaded()) {
                    mInterstitialAd.show();
                }
            }
        });

    }
    /**
     * RECYCLERVIEW
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

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(KetQuaActivity.this);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(linearLayoutManager);
    }

    /**
     * BUTTON
     */
    public void button(){
        btnChiTiet = (Button) findViewById(R.id.btn_chitiet);
        btnReset = (Button) findViewById(R.id.btn_reset);
        btnNext = (Button) findViewById(R.id.btn_next);
        btnChiTiet.setOnClickListener(this);
        btnReset.setOnClickListener(this);
        btnNext.setOnClickListener(this);
    }
    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_chitiet:
                if(ct== CHITIET){
                    List<CauHoi> x = cauHoiList;
                    if(type == TYPE_EXERCISES){
                        ketQuaAdapter = new KetQuaAdapter(KetQuaActivity.this,x);
                        ketQuaAdapter.notifyDataSetChanged();
                        recyclerView.setAdapter(ketQuaAdapter);
                    }else if(type == TYPE_TEST){
                        ketQua2Adapter = new KetQua2Adapter(KetQuaActivity.this,x);
                        ketQua2Adapter.notifyDataSetChanged();
                        recyclerView.setAdapter(ketQua2Adapter);
                    }else if(type == TYPE_BODE){
                        ketQua2Adapter = new KetQua2Adapter(KetQuaActivity.this,x);
                        ketQua2Adapter.notifyDataSetChanged();
                        recyclerView.setAdapter(ketQua2Adapter);

                    }
                    btnChiTiet.setText(getResources().getString(R.string.thugon).toString());
                    ct = 1;
                }else if(ct == THUGON){
                    List<CauHoi> x = null;
                    if(type == TYPE_EXERCISES){
                        ketQuaAdapter = new KetQuaAdapter(KetQuaActivity.this,x);
                        ketQuaAdapter.notifyDataSetChanged();
                        recyclerView.setAdapter(ketQuaAdapter);
                    }else if(type == TYPE_TEST){
                        ketQua2Adapter = new KetQua2Adapter(KetQuaActivity.this,x);
                        ketQua2Adapter.notifyDataSetChanged();
                        recyclerView.setAdapter(ketQua2Adapter);

                    }else if(type == TYPE_BODE){
                        ketQua2Adapter = new KetQua2Adapter(KetQuaActivity.this,x);
                        ketQua2Adapter.notifyDataSetChanged();
                        recyclerView.setAdapter(ketQua2Adapter);

                    }
                    btnChiTiet.setText(getResources().getString(R.string.chitiet).toString());
                    ct = 0;
                }

                break;
            case R.id.btn_reset:
                if(type == TYPE_EXERCISES){
                    Intent intent = new Intent(KetQuaActivity.this,GrammarsActivity.class);
                    Bundle bundle = new Bundle();
                    bundle.putInt("ID", 1);
                    bundle.putString("grammar", grammar);
                    bundle.putInt("macd",macd);
                    intent.putExtras(bundle);
                    startActivity(intent);
                    mInterstitialAd.loadAd(adRequest);
//                    finish();
//                    overridePendingTransition(R.anim.animation_in, R.anim.animation_out);
                }else if(type == TYPE_TEST){
                    Intent intent = new Intent(KetQuaActivity.this, TestActivity.class);
                    Bundle bundle = new Bundle();
                    bundle.putInt("test_index",ID);
                    intent.putExtras(bundle);
                    startActivity(intent);
                    mInterstitialAd.loadAd(adRequest);
//                    finish();
//                    overridePendingTransition(R.anim.animation_in, R.anim.animation_out);
                }
                else if(type == TYPE_BODE){
                    Intent intent = new Intent(KetQuaActivity.this, BoDeActivity.class);
                    Bundle bundle = new Bundle();
                    bundle.putInt("madt", madt);
                    bundle.putString("linkdt", linkdt);
                    intent.putExtras(bundle);
                    startActivity(intent);
                    mInterstitialAd.loadAd(adRequest);
//                    finish();
//                    overridePendingTransition(R.anim.animation_in, R.anim.animation_out);
                }
                break;
            case R.id.btn_next:
                mInterstitialAd.loadAd(adRequest);
                break;
        }
    }
    /**
     * GET DATA
     */
    public  void  getData(){
        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        ListKetQua ketquas = (ListKetQua) bundle.getSerializable("ketqua");
        DeThiDB deThi=null;
        cauHoiList = ketquas.getCauHoiList();
        type = ketquas.getType();
        ID = ketquas.getID();
        if(type == TYPE_EXERCISES){
            macd= bundle.getInt("macd");
            grammar = bundle.getString("grammar");
        }else if(type == TYPE_BODE){
            deThi=(DeThiDB)bundle.getSerializable("dethi");
            madt=deThi.getMadt();
            linkdt=deThi.getPath();
        }
        for(int i=0 ; i< cauHoiList.size();i++){
            CauHoi cauHoi = cauHoiList.get(i);
            if( cauHoi.getDapAn() == -1){
                chualam ++;
            }else if( cauHoi.getDapAn() == cauHoi.getIDDapAnDung()){
                dung ++;
            }else {
                sai ++;
            }
        }
        int x = (int) ((dung)*100/(dung+sai+chualam));
        diem = ((float)x)/10;
        if(type==TYPE_BODE)
        {
            DBAdapter db = new DBAdapter(getApplicationContext());
            db.open();
            long kq=db.update_Dethi(deThi.getMadt(), deThi.getMand(), deThi.getPath(), deThi.getThoigian(), 1, x);
            db.close();
        }
        txtDiem = (TextView) findViewById(R.id.txt_diem);
        txtDiem.setText(String.valueOf((int) diem));
        try{
            YoYo.with(Techniques.Landing)
                    .duration(4000)
                    .playOn(txtDiem);
        }catch (Exception e){

        }
        star = (ImageView) findViewById(R.id.star);
        star.setImageLevel((int) diem);
        try{
            YoYo.with(Techniques.Flash)
                    .duration(4000)
                    .playOn(star);
        }catch (Exception e){

        }
    }

    /**
     * PICCHART
     */
    public void PicChart(){
        mChart = (PieChart) findViewById(R.id.chart1);
        mChart.setUsePercentValues(true);
        mChart.setDescription("");
        mChart.setExtraOffsets(5, 10, 5, 5);

        mChart.setDragDecelerationFrictionCoef(0.95f);

        mChart.setCenterText(generateCenterSpannableText());

        mChart.setDrawHoleEnabled(true);
        mChart.setHoleColor(Color.WHITE);

        mChart.setTransparentCircleColor(Color.WHITE);
        mChart.setTransparentCircleAlpha(180);

        mChart.setHoleRadius(56f);
        mChart.setTransparentCircleRadius(64f);

        mChart.setDrawCenterText(true);

        mChart.setRotationAngle(0);
        mChart.setRotationEnabled(true);
        mChart.setHighlightPerTapEnabled(true);

        mChart.setOnChartValueSelectedListener(this);

        setData(3, dung, sai, chualam);

        mChart.animateY(1400, Easing.EasingOption.EaseInOutQuad);

        Legend l = mChart.getLegend();
        l.setPosition(LegendPosition.RIGHT_OF_CHART);
        l.setXEntrySpace(7f);
        l.setYEntrySpace(0f);
        l.setYOffset(0f);
    }

    /**
     * DỔ DATA VÀO PICCHART
     */
    private void setData(int count, float dung ,float sai, float chualam) {
        ArrayList<Entry> yVals1 = new ArrayList<Entry>();
        ArrayList<String> xVals = new ArrayList<String>();
        ArrayList<Integer> colors = new ArrayList<Integer>();
        int i = 0 ;
        if(dung != 0){
            yVals1.add(new Entry(dung,i));
            i++;
            xVals.add(getResources().getString(R.string.dung).toString());
            colors.add(getResources().getColor(R.color.colorGreenA400));
        }
        if(sai != 0){
            yVals1.add(new Entry(sai,i));
            i++;
            xVals.add(getResources().getString(R.string.sai).toString());
            colors.add(getResources().getColor(R.color.colorRedA400));
        }
        if(chualam != 0){
            yVals1.add(new Entry(chualam,i));
            xVals.add(getResources().getString(R.string.chualam).toString());
            colors.add(getResources().getColor(R.color.colorYellowA200));
        }

        PieDataSet dataSet = new PieDataSet(yVals1, "Election Results");
        dataSet.setSliceSpace(3f);
        dataSet.setSelectionShift(5f);

        dataSet.setColors(colors);

        PieData data = new PieData(xVals, dataSet);
        data.setValueFormatter(new PercentFormatter());
        data.setValueTextSize(11f);
        data.setValueTextColor(getResources().getColor(R.color.colorBlue300));
        data.setValueTypeface(tf);
        mChart.setData(data);

        mChart.highlightValues(null);

        mChart.invalidate();
    }

    /**
     * TEXT PICCHART
     * @return
     */
    private SpannableString generateCenterSpannableText() {
        SpannableString s = new SpannableString(getResources().getString( R.string.ketquabaikt)+ diem);
        s.setSpan(new RelativeSizeSpan(2.2f), 0, 7, 0);
        s.setSpan(new RelativeSizeSpan(1.3f), 7, 20, 0);
        s.setSpan(new StyleSpan(Typeface.ITALIC), 7,20 , 0);
        s.setSpan(new ForegroundColorSpan(ColorTemplate.getHoloBlue()),7, 20, 0);
        s.setSpan(new RelativeSizeSpan(3.0f), 20, s.length(), 0);
        s.setSpan(new StyleSpan(Typeface.NORMAL), 20, s.length(), 0);
        s.setSpan(new ForegroundColorSpan(Color.RED), 20, s.length(), 0);
        return s;
    }

    @Override
    public void onValueSelected(Entry e, int dataSetIndex, Highlight h) {

        if (e == null)
            return;
        Log.i("VAL SELECTED", "Value: " + e.getVal() + ", xIndex: " + e.getXIndex() + ", DataSet index: " + dataSetIndex);
    }

    @Override
    public void onNothingSelected() {
        Log.i("PieChart", "nothing selected");
    }
    /**
     * TOOLBAR
     */
    private void toolBar(){
        mToolbar = (Toolbar) findViewById(R.id.toolbar);
        mToolbar.setTitle(getResources().getString(R.string.ketqua));
        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
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

        if(id == android.R.id.home)
        {
            finish();
            overridePendingTransition(R.anim.animation_finish_in, R.anim.animation_finish_out);
        }

        return super.onOptionsItemSelected(item);
    }

}
