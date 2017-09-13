package vn.edu.itplus_academy.tracnghiemtienganh.Activitys;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Canvas;
import android.os.Bundle;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import com.joanzapata.pdfview.PDFView;
import com.joanzapata.pdfview.listener.OnDrawListener;
import com.joanzapata.pdfview.listener.OnLoadCompleteListener;
import com.joanzapata.pdfview.listener.OnPageChangeListener;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import vn.edu.itplus_academy.tracnghiemtienganh.Adapter.DapAnBoDeAdapter;
import vn.edu.itplus_academy.tracnghiemtienganh.Model.CauHoi;
import vn.edu.itplus_academy.tracnghiemtienganh.Model.DeThiDB;
import vn.edu.itplus_academy.tracnghiemtienganh.Model.ListKetQua;
import vn.edu.itplus_academy.tracnghiemtienganh.R;
import vn.edu.itplus_academy.tracnghiemtienganh.databases.DBAdapter;
import vn.edu.itplus_academy.tracnghiemtienganh.models.DapAn;

public class BoDeActivity extends AppCompatActivity implements View.OnClickListener,OnDrawListener, OnLoadCompleteListener, OnPageChangeListener {
    private static final int TYPE_BODE = 2;
    private int bode;
    private Toolbar mToolbar;
    private DrawerLayout drawer;
    private RecyclerView RecyclerCauHoi;
    private DapAnBoDeAdapter dapAnBoDeAdapter;
    private List<CauHoi> cauHoiList;
    private Button btnNopBai;
    private int madt;
    private String linkdt;
    private DeThiDB deThi;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bo_de);
        toolBar();
        navigationView();

        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        deThi= (DeThiDB)bundle.getSerializable("dethi");
        madt = deThi.getMadt();
        linkdt = deThi.getPath();

        list();

        ListRecyclerView();
        btnNopBai = (Button) findViewById(R.id.btn_nopBai);
        btnNopBai.setOnClickListener(this);

        PDFView pdfView= (PDFView) findViewById(R.id.pdfView);
        String url = linkdt;
        File file = new File(url);
        pdfView.fromFile(file)
                .defaultPage(1)
                .showMinimap(false)
                .enableSwipe(true)
                .onDraw(this)
                .onLoad(this)
                .onPageChange(this)
                .load();
    }

    @Override
    public void onClick(View v) {
        cauHoiList = dapAnBoDeAdapter.getList();
        ListKetQua ketqua = new ListKetQua(cauHoiList,TYPE_BODE,bode);
        ketqua.setCauHoiList(cauHoiList);
        Intent intent = new Intent(BoDeActivity.this,KetQuaActivity.class);
        Bundle bundle = new Bundle();
        bundle.putSerializable("ketqua", ketqua);
        bundle.putSerializable("dethi",deThi);
        intent.putExtras(bundle);
        startActivity(intent);
        overridePendingTransition(R.anim.animation_in, R.anim.animation_out);
        finish();
    }


    /**
     * LIST
     */
    private void list(){

        cauHoiList = new ArrayList<>();
        ArrayList<DapAn> lst_dapan = new ArrayList<DapAn>();
        String[] txtQuestionID={getResources().getString(R.string.a),getResources().getString(R.string.b),getResources().getString(R.string.c),getResources().getString(R.string.d)};
        DBAdapter db = new DBAdapter(getApplicationContext());
        db.open();
        lst_dapan = db.getAll_DapAn(madt);
        db.close();

        for(int i=0; i<lst_dapan.size(); i++){

            cauHoiList.add(new CauHoi(i+1,"Cauhoi",null,checkDapanDung(lst_dapan.get(i).getDa()),0,-1,0));
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


    /**
     * LIST RECYCLERVIEW
     */
    private void ListRecyclerView(){
        RecyclerCauHoi = (RecyclerView) findViewById(R.id.list_cauhoi);
        //RecyclerCauHoi.setHasFixedSize(true);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(BoDeActivity.this);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        RecyclerCauHoi.setLayoutManager(linearLayoutManager);

        dapAnBoDeAdapter = new DapAnBoDeAdapter(BoDeActivity.this,cauHoiList);

        RecyclerCauHoi.setAdapter(dapAnBoDeAdapter);
    }

    /**
     * TOOLBAR
     */
    private void toolBar(){
        mToolbar = (Toolbar) findViewById(R.id.toolbar);
        mToolbar.setTitle(getResources().getString(R.string.bode));
        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
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
    public void onLayerDrawn(Canvas canvas, float pageWidth, float pageHeight, int displayedPage) {

    }

    @Override
    public void loadComplete(int nbPages) {

    }

    @Override
    public void onPageChanged(int page, int pageCount) {

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
