package com.cuongnguyen.appdoan.GetData;

import android.app.IntentService;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

import com.cuongnguyen.appdoan.API.API;
import com.cuongnguyen.appdoan.Activity.SplashScreen;
import com.cuongnguyen.appdoan.Database.DBAdapter;
import com.cuongnguyen.appdoan.Model.BangGia;
import com.cuongnguyen.appdoan.Model.ImageSaver;
import com.cuongnguyen.appdoan.Model.MonAn;
import com.cuongnguyen.appdoan.Model.NhomDoAn;

import java.util.List;

import retrofit.Callback;
import retrofit.RestAdapter;
import retrofit.RetrofitError;
import retrofit.client.Response;

/**
 * Created by cuong on 8/17/2016.
 */
public class UpdateIntentService extends IntentService {
    /**
     * Creates an IntentService.  Invoked by your subclass's constructor.
     *
     * @param name Used to name the worker thread, important only for debugging.
     */
    private RestAdapter restAdapter;
    private DBAdapter db;
    private API api;
    private Context context;
    private ProgressDialog dialog;
    public UpdateIntentService() {
        super("UpdateIntent");
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        UpdateData();
    }
    public void UpdateData()
    {
//        dialog=new ProgressDialog(this);
//        dialog.setMessage("Cập nhập dữ liệu");
//        dialog.setTitle("Loading...");
//        dialog.show();
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
                    final List<MonAn> list = getMonAn.getList();
                    for (final MonAn i : list) {
                        new ImageSaver(context,i).execute(i.getLinkAnh());
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
//        dialog.dismiss();
    }
}
