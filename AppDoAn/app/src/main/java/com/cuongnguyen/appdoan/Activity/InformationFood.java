package com.cuongnguyen.appdoan.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import com.cuongnguyen.appdoan.API.API;
import com.cuongnguyen.appdoan.Adapter.AdapterPrice;
import com.cuongnguyen.appdoan.Database.DBAdapter;
import com.cuongnguyen.appdoan.GetData.GetThucDon;
import com.cuongnguyen.appdoan.Model.BangGia;
import com.cuongnguyen.appdoan.Model.ExpandableHeightListView;
import com.cuongnguyen.appdoan.Model.MonAn;
import com.cuongnguyen.appdoan.R;
import com.squareup.picasso.Picasso;

import java.util.List;

import retrofit.Callback;
import retrofit.RestAdapter;
import retrofit.RetrofitError;
import retrofit.client.Response;

public class InformationFood extends AppCompatActivity {
    private List<BangGia> list;
    private MonAn monAn;
    final DBAdapter db=new DBAdapter(InformationFood.this);
    private TextView name,info,soluong;
    private ExpandableHeightListView listPrice;
    AdapterPrice adapterPrice;
    private ImageView imageFood;
    String sl="0";
    Toolbar toolbar;
    public static boolean run=false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_information_food);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        addControll();
        Intent intent=getIntent();
        monAn= (MonAn) intent.getSerializableExtra("food");
        RestAdapter restAdapter=new RestAdapter.Builder().setEndpoint(SplashScreen.URL).build();
        API api=restAdapter.create(API.class);
        api.getthucdon(monAn.getMaMonAn(), new Callback<GetThucDon>() {
            @Override
            public void success(GetThucDon getThucDon, Response response) {
                if (getThucDon.getSuccess().equals("1")) {
                    sl = getThucDon.getSoluong();
                } else {
                    sl = "0";
                }
                soluong.setText("Số lượng: " + sl);
            }

            @Override
            public void failure(RetrofitError error) {
                soluong.setText("Số lượng: " + sl);
            }
        });
        list = db.getAllBangGia(monAn.getMaMonAn());
        adapterPrice = new AdapterPrice(InformationFood.this, R.layout.item_price, list);
        listPrice.setAdapter(adapterPrice);
        listPrice.setExpanded(true);
        name.setText("Tên món ăn: " + monAn.getTenMonAn());
        info.setText("Giới thiệu: " + monAn.getGioiThieu());
        Picasso.with(InformationFood.this).load(monAn.getLinkAnh()).fit().into(imageFood);
        run=true;
    }
    public void addControll()
    {
        name=(TextView)findViewById(R.id.nameInfo);
        info=(TextView)findViewById(R.id.infoInfo);
        listPrice=(ExpandableHeightListView) findViewById(R.id.listPrice);
        imageFood=(ImageView)findViewById(R.id.imageInfo);
        soluong=(TextView)findViewById(R.id.soluong);
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case android.R.id.home:
                onBackPressed();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onStop() {
        super.onStop();
        run=false;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        run=false;
    }

    @Override
    protected void onPostResume() {
        super.onPostResume();
        run=true;
    }
}
