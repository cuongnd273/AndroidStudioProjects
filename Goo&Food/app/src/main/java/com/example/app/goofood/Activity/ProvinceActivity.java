package com.example.app.goofood.Activity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.app.goofood.API.MyServices;
import com.example.app.goofood.GetData.GetListFood;
import com.example.app.goofood.GetData.GetListPlace;
import com.example.app.goofood.GetData.GetProvince;
import com.example.app.goofood.Model.Food;
import com.example.app.goofood.Model.Place;
import com.example.app.goofood.R;
import com.squareup.picasso.Picasso;

import retrofit.Callback;
import retrofit.RestAdapter;
import retrofit.RetrofitError;
import retrofit.client.Response;

public class ProvinceActivity extends AppCompatActivity {
    TextView info,history,weather,title;
    ImageView image;
    RestAdapter restAdapter;
    MyServices git;
    LinearLayout linearLayoutPlace,linearLayoutFood;
    ProgressDialog dialog;
    boolean loaded=false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_province);
        dialog=new ProgressDialog(this);
        dialog.setMessage("Loading...");
        dialog.show();
        GetControl();
        Intent intent=getIntent();
        final String id=intent.getStringExtra("id");
        restAdapter = new RestAdapter.Builder().setLogLevel(RestAdapter.LogLevel.FULL).setEndpoint(MainActivity.URL).build();
        git = restAdapter.create(MyServices.class);
        git.InfoProvince(id, new Callback<GetProvince>() {
            @Override
            public void success(GetProvince getProvince, Response response) {
                title.setText(getProvince.getProvince().getTentinh());
                history.setText(getProvince.getProvince().getLichsu());
                weather.setText(getProvince.getProvince().getKhihau());
                info.setText(getProvince.getProvince().getGioithieu());
                Picasso.with(ProvinceActivity.this).load(getProvince.getProvince().getAnh()).into(image);
                git.ListPlace(id, new Callback<GetListPlace>() {
                    @Override
                    public void success(GetListPlace getListPlace, Response response) {
                        if(getListPlace.getListPlace().size()>0)
                        {
                            for(final Place item : getListPlace.getListPlace())
                            {
                                TextView htext =new TextView(ProvinceActivity.this);
                                htext.setText("+"+item.getTendiadiem());
                                htext.setTypeface(null,Typeface.BOLD);
                                htext.setTextColor(0xff000000);
                                htext.setTextSize(15);
                                htext.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT));
                                linearLayoutPlace.addView(htext);
                                htext.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View v) {
                                        Intent intentPlace=new Intent(ProvinceActivity.this,PlaceActivity.class);
                                        intentPlace.putExtra("id",item.getMadiadiem());
                                        startActivity(intentPlace);
                                    }
                                });
                            }
                        }else{
                            TextView htext =new TextView(ProvinceActivity.this);
                            htext.setText("Không có địa điểm nào");
                            htext.setTypeface(null,Typeface.BOLD);
                            htext.setTextColor(0xff000000);
                            htext.setTextSize(15);
                            htext.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT));
                            linearLayoutPlace.addView(htext);
                        }
                    }

                    @Override
                    public void failure(RetrofitError error) {
                        dialog.dismiss();
                    }
                });
                git.ListFood(id, new Callback<GetListFood>() {
                    @Override
                    public void success(GetListFood getListFood, Response response) {
                        if(getListFood.getListFood().size()>0)
                        {
                            for(final Food item : getListFood.getListFood())
                            {
                                TextView htext =new TextView(ProvinceActivity.this);
                                htext.setText("+"+item.getTenmonan());
                                htext.setTypeface(null,Typeface.BOLD);
                                htext.setTextColor(0xff000000);
                                htext.setTextSize(15);
                                htext.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT));
                                linearLayoutFood.addView(htext);
                                htext.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View v) {
                                        Intent intentPlace=new Intent(ProvinceActivity.this,FoodActivity.class);
                                        intentPlace.putExtra("id",item.getMamonan());
                                        startActivity(intentPlace);
                                    }
                                });
                            }
                        }else{
                            TextView htext =new TextView(ProvinceActivity.this);
                            htext.setText("Không có món ăn nào");
                            htext.setTypeface(null,Typeface.BOLD);
                            htext.setTextColor(0xff000000);
                            htext.setTextSize(15);
                            htext.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT));
                            linearLayoutFood.addView(htext);
                        }
                    }

                    @Override
                    public void failure(RetrofitError error) {
                    }
                });
                dialog.dismiss();
            }

            @Override
            public void failure(RetrofitError error) {

            }
        });
    }
    public void GetControl()
    {
        info=(TextView)findViewById(R.id.infoProvince);
        history=(TextView)findViewById(R.id.historyProvince);
        weather=(TextView)findViewById(R.id.weatherProvince);
        title=(TextView)findViewById(R.id.titleProvince);
        image=(ImageView)findViewById(R.id.imageProvince);
        linearLayoutPlace=(LinearLayout)findViewById(R.id.listPlace);
        linearLayoutFood=(LinearLayout)findViewById(R.id.listFood);
    }
}
