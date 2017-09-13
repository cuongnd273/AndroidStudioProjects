package com.example.app.goofood.Activity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.app.goofood.API.MyServices;
import com.example.app.goofood.GetData.GetFood;
import com.example.app.goofood.R;
import com.squareup.picasso.Picasso;

import retrofit.Callback;
import retrofit.RestAdapter;
import retrofit.RetrofitError;
import retrofit.client.Response;

public class FoodActivity extends AppCompatActivity {

    RestAdapter restAdapter;
    MyServices git;
    TextView title,info;
    ImageView image;
    ProgressDialog dialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_food);
        GetControl();
        dialog=new ProgressDialog(this);
        dialog.setMessage("Loading...");
        dialog.show();
        Intent intent=getIntent();
        String id=intent.getStringExtra("id");
        restAdapter=new RestAdapter.Builder().setLogLevel(RestAdapter.LogLevel.FULL).setEndpoint(MainActivity.URL).build();
        git=restAdapter.create(MyServices.class);
        git.InfoFood(id, new Callback<GetFood>() {
            @Override
            public void success(GetFood getFood, Response response) {
                title.setText(getFood.getFood().getTenmonan());
                info.setText(getFood.getFood().getThongtinmonan());
                Picasso.with(FoodActivity.this).load(getFood.getFood().getAnh()).into(image);
                dialog.dismiss();
                Log.i("WW",getFood.getFood().getTenmonan());
            }

            @Override
            public void failure(RetrofitError error) {

            }
        });
    }
    public void GetControl()
    {
        title=(TextView)findViewById(R.id.titleFood);
        info=(TextView)findViewById(R.id.infoFood);
        image=(ImageView)findViewById(R.id.imageFood);
    }
}
