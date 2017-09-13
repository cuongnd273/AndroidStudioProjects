package com.cuongnguyen.appdoan.Activity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import com.cuongnguyen.appdoan.API.API;
import com.cuongnguyen.appdoan.Adapter.AdapterViewPager;
import com.cuongnguyen.appdoan.Database.DBAdapter;
import com.cuongnguyen.appdoan.Fragment.FragmentGroupFood;
import com.cuongnguyen.appdoan.GetData.GetBangGia;
import com.cuongnguyen.appdoan.GetData.GetMonAn;
import com.cuongnguyen.appdoan.GetData.GetNhomMonAn;
import com.cuongnguyen.appdoan.GetData.GetThoiGian;
import com.cuongnguyen.appdoan.Model.BangGia;
import com.cuongnguyen.appdoan.Model.MonAn;
import com.cuongnguyen.appdoan.Model.NetworkUtils;
import com.cuongnguyen.appdoan.Model.NhomDoAn;
import com.cuongnguyen.appdoan.R;

import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import retrofit.Callback;
import retrofit.RestAdapter;
import retrofit.RetrofitError;
import retrofit.client.Response;

public class MainActivity extends AppCompatActivity {

    Toolbar toolbar;
    DBAdapter db=new DBAdapter(MainActivity.this);
    List<NhomDoAn> nhomDoAnList;
    ViewPager viewPager;
    TabLayout tabLayout;
    public static boolean run=false;
    private Handler handler;
    private Timer timer;
    private RestAdapter restAdapter;
    private API api;
    private SharedPreferences sharedPreferences;
    private Handler splashScreenHandler;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        toolbar= (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getData();
        handler=new Handler(){
            @Override
            public void handleMessage(Message msg) {
                super.handleMessage(msg);
                CheckUpdate();
            }
        };
        timer=new Timer();
        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                handler.sendEmptyMessage(1);
                Log.i("ABC","1");
            }
        },10000,10000);
    }
    public void setupViewPager(List<NhomDoAn> list)
    {
        AdapterViewPager adapterViewPager=new AdapterViewPager(getSupportFragmentManager());
        for(NhomDoAn i : list)
        {
            adapterViewPager.addFragment(new FragmentGroupFood(i.getMaNhom()),i);
        }
        viewPager.setAdapter(adapterViewPager);
    }

    @Override
    protected void onPostResume() {
        super.onPostResume();
        getData();
    }

    public void getData()
    {
        nhomDoAnList=db.getAllNhomDonAn();
        viewPager=(ViewPager)findViewById(R.id.viewpager);
        setupViewPager(nhomDoAnList);
        tabLayout=(TabLayout)findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(viewPager);
    }
    public void CheckUpdate()
    {
        boolean check = NetworkUtils.GetStateNetwork(getApplication());
        if(check){
            sharedPreferences = getSharedPreferences("file_config", Context.MODE_PRIVATE);
            String time=sharedPreferences.getString("time",null);
            Log.i("tim",time);
            restAdapter=new RestAdapter.Builder().setEndpoint(SplashScreen.URL).build();
            api=restAdapter.create(API.class);
            api.getthoigian(time, new retrofit.Callback<GetThoiGian>() {
                @Override
                public void success(GetThoiGian getThoiGian, Response response) {
                    if(getThoiGian.getSuccess().equals("1"))
                    {
                        sharedPreferences.edit().putString("time",getThoiGian.getTime()).commit();
                        Log.i("timeCheck",getThoiGian.getTime());
                        db.delBangGia();
                        db.delMonAn();
                        db.delNhomMonAn();
                        Update();
                    }
                }

                @Override
                public void failure(RetrofitError error) {
                    Log.i("error",error.toString());
                }
            });
        }
    }
    public void Update()
    {
        restAdapter=new RestAdapter.Builder().setEndpoint(SplashScreen.URL).build();
        api=restAdapter.create(API.class);
        api.getnhommonan("get", new Callback<GetNhomMonAn>() {
            @Override
            public void success(GetNhomMonAn getNhomMonAn, Response response) {
                if(getNhomMonAn.getSuccess().equals("1"))
                {
                    List<NhomDoAn> list=getNhomMonAn.getList();
                    for(NhomDoAn i : list)
                    {
                        db.insertNhomDoAn(i);
                    }
                }
            }

            @Override
            public void failure(RetrofitError error) {
                Log.i("Error NhomMonAn", error.toString());
            }
        });
        api.getmonan("get", new Callback<GetMonAn>() {
            @Override
            public void success(GetMonAn getMonAn, Response response) {
                if (getMonAn.getSuccess().equals("1")) {
                     List<MonAn> list = getMonAn.getList();
                    for (MonAn i : list) {
                        db.insertMonAn(i);
                    }
                }
            }

            @Override
            public void failure(RetrofitError error) {
                Log.i("Error MonAn", error.toString());
            }
        });
        api.getbanggia("get", new Callback<GetBangGia>() {
            @Override
            public void success(GetBangGia getBangGia, Response response) {
                if(getBangGia.getSuccess().equals("1"))
                {
                    List<BangGia> list=getBangGia.getList();
                    for(BangGia i : list)
                    {
                        db.insertBangGia(i);
                    }
                }
            }

            @Override
            public void failure(RetrofitError error) {
                Log.i("error",error.toString());
            }
        });
        refreshMainActivity(5000);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId()==R.id.refresh)
        {
            Intent intent = getIntent();
            finish();
            startActivity(intent);
        }
        return super.onOptionsItemSelected(item);
    }
    private void refreshMainActivity(long time)
    {
        splashScreenHandler = new Handler();
        splashScreenHandler.postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = getIntent();
                finish();
                startActivity(intent);
            }
        }, time);

    }
}
