package com.cuongnguyen.appdoan.Activity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.TextView;

import com.cuongnguyen.appdoan.API.API;
import com.cuongnguyen.appdoan.Database.DBAdapter;
import com.cuongnguyen.appdoan.GetData.GetBangGia;
import com.cuongnguyen.appdoan.GetData.GetMonAn;
import com.cuongnguyen.appdoan.GetData.GetNhomMonAn;
import com.cuongnguyen.appdoan.GetData.GetThoiGian;
import com.cuongnguyen.appdoan.Model.BangGia;
import com.cuongnguyen.appdoan.Model.MonAn;
import com.cuongnguyen.appdoan.Model.NetworkUtils;
import com.cuongnguyen.appdoan.Model.NhomDoAn;
import com.cuongnguyen.appdoan.R;
import com.flaviofaria.kenburnsview.KenBurnsView;

import java.util.List;

import retrofit.Callback;
import retrofit.RestAdapter;
import retrofit.RetrofitError;
import retrofit.client.Response;

public class SplashScreen extends AppCompatActivity {
    private KenBurnsView img;
    private TextView notifi,appName;
    public static final String URL="http://appdoan.hol.es/";
    private SharedPreferences sharedPreferences;
    private DBAdapter db=new DBAdapter(SplashScreen.this);
    private Handler splashScreenHandler;
    private RestAdapter restAdapter=new RestAdapter.Builder().setEndpoint(URL).build();
    private API api=restAdapter.create(API.class);
    public static boolean status=false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);
        img=(KenBurnsView)findViewById(R.id.img);
        notifi=(TextView)findViewById(R.id.notifi);
        appName=(TextView)findViewById(R.id.appName);
        Typeface tf = Typeface.createFromAsset(this.getAssets(),
                "VN3D.TTF");
        appName.setTypeface(tf);
        status=true;
        sharedPreferences = getSharedPreferences("file_config", Context.MODE_PRIVATE);
        checkConnectNetwork();
    }
    private Boolean checkRunAppfirst()
    {
        if (sharedPreferences.getBoolean("first_run", true)) {

            return true;
        }
        return false;
    }
    private void checkConnectNetwork() {
        boolean check = NetworkUtils.GetStateNetwork(getApplication());
        if (check) {
            if (checkRunAppfirst()) {
                api.getthoigian("1", new Callback<GetThoiGian>() {
                    @Override
                    public void success(GetThoiGian getThoiGian, Response response) {
                        if (getThoiGian.getSuccess().equals("1")) {
                            String time = getThoiGian.getTime();
                            sharedPreferences.edit().putString("time", time).commit();
                            sharedPreferences.edit().putBoolean("first_run", false).commit();
                            UpdateData();
                            toMainActivity(1000);
                        }
                    }
                    @Override
                    public void failure(RetrofitError error) {

                    }
                });
            }else{
                String time=sharedPreferences.getString("time",null);
                Log.i("timeApp",time);
                restAdapter=new RestAdapter.Builder().setEndpoint(URL).build();
                api=restAdapter.create(API.class);
                api.getthoigian(time, new Callback<GetThoiGian>() {
                    @Override
                    public void success(GetThoiGian getThoiGian, Response response) {
                        if(getThoiGian.getSuccess().equals("1"))
                        {
                            Log.i("timeServer",getThoiGian.getTime());
                            sharedPreferences.edit().putString("time",getThoiGian.getTime()).commit();
                            db.delBangGia();
                            db.delMonAn();
                            db.delNhomMonAn();
                            UpdateData();
                            toMainActivity(3000);
                        }else{
                            toMainActivity(1000);
                        }
                    }

                    @Override
                    public void failure(RetrofitError error) {
                        Log.i("error",error.toString());
                    }
                });
            }
        } else {
            if(checkRunAppfirst())
            {
                notifi.setText("Không có kết nối mạng");
            }else{
                toMainActivity(3000);
            }
        }
    }
    private void toMainActivity(long time)
    {
        splashScreenHandler = new Handler();
        splashScreenHandler.postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(SplashScreen.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        }, time);

    }
    public void UpdateData()
    {
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
                    for (final MonAn i : list) {
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
    }
}
