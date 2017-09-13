package com.cuongnguyen.appdoan.GetData;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.support.annotation.Nullable;
import android.util.Log;

import com.cuongnguyen.appdoan.API.API;
import com.cuongnguyen.appdoan.Activity.SplashScreen;
import com.cuongnguyen.appdoan.Database.DBAdapter;
import com.cuongnguyen.appdoan.Model.NetworkUtils;

import java.util.Timer;
import java.util.TimerTask;

import retrofit.RestAdapter;
import retrofit.RetrofitError;
import retrofit.client.Response;

/**
 * Created by cuong on 8/5/2016.
 */
public class UpdateServices extends Service {
    private Handler handler;
    private Timer timer;
    private RestAdapter restAdapter;
    private DBAdapter db=new DBAdapter(this);
    private API api;
    private SharedPreferences sharedPreferences;
    private Context context;

    @Override
    public void onCreate() {
        super.onCreate();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        handler=new Handler(){
            @Override
            public void handleMessage(Message msg) {
                super.handleMessage(msg);
                if(msg.what==1)
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
                                        sharedPreferences.edit().putString("time",getThoiGian.getTime());
                                        db.delBangGia();
                                        db.delMonAn();
                                        db.delNhomMonAn();
                                        Intent intent1=new Intent(getApplicationContext(),UpdateIntentService.class);
                                        startService(intent1);
                                    }
                                }

                                @Override
                                public void failure(RetrofitError error) {
                                    Log.i("error",error.toString());
                                }
                            });
                        }
                }
            }
        };
        timer=new Timer();
        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                handler.sendEmptyMessage(1);
                Log.i("StatusServices","1");
            }
        },10000,10000);
        return START_STICKY;
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        startService(new Intent(this, UpdateServices.class));
    }
}
