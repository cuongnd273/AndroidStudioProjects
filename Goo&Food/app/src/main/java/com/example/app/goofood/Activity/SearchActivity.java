package com.example.app.goofood.Activity;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.app.goofood.API.MyServices;
import com.example.app.goofood.GetData.GetSearch;
import com.example.app.goofood.Model.Food;
import com.example.app.goofood.Model.Place;
import com.example.app.goofood.R;

import retrofit.Callback;
import retrofit.RestAdapter;
import retrofit.RetrofitError;
import retrofit.client.Response;

public class SearchActivity extends AppCompatActivity {
    RestAdapter restAdapter;
    MyServices git;
    LinearLayout lnPlace,lnFood;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        GetControl();
        Intent intent=getIntent();
        String search=intent.getStringExtra("search");
        restAdapter=new RestAdapter.Builder().setLogLevel(RestAdapter.LogLevel.FULL).setEndpoint(MainActivity.URL).build();
        git=restAdapter.create(MyServices.class);
        git.Search(search, new Callback<GetSearch>() {
            @Override
            public void success(GetSearch getSearch, Response response) {
                if(getSearch.getListPlace().size()==0)
                {
                    TextView htext =new TextView(SearchActivity.this);
                    htext.setText("Không tìm thấy địa danh nào.");
                    htext.setTextColor(0xff000000);
                    htext.setTextSize(15);
                    htext.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT));
                    lnPlace.addView(htext);
                }else{
                    for(final Place item : getSearch.getListPlace())
                    {
                        TextView htext =new TextView(SearchActivity.this);
                        htext.setText("+"+item.getTendiadiem());
                        htext.setTypeface(null, Typeface.BOLD);
                        htext.setTextColor(0xff000000);
                        htext.setTextSize(15);
                        htext.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT));
                        lnPlace.addView(htext);
                        htext.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                Intent intentPlace=new Intent(SearchActivity.this,PlaceActivity.class);
                                intentPlace.putExtra("id",item.getMadiadiem());
                                startActivity(intentPlace);
                            }
                        });
                    }
                }
                if(getSearch.getListFood().size()==0)
                {
                    TextView htext =new TextView(SearchActivity.this);
                    htext.setText("Không tìm thấy món ăn nào.");
                    htext.setTextColor(0xff000000);
                    htext.setTextSize(15);
                    htext.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT));
                    lnFood.addView(htext);
                }else{
                    for(final Food item : getSearch.getListFood())
                    {
                        TextView htext =new TextView(SearchActivity.this);
                        htext.setText("+"+item.getTenmonan());
                        htext.setTypeface(null,Typeface.BOLD);
                        htext.setTextColor(0xff000000);
                        htext.setTextSize(15);
                        htext.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT));
                        lnFood.addView(htext);
                        htext.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                Intent intentPlace=new Intent(SearchActivity.this,FoodActivity.class);
                                intentPlace.putExtra("id",item.getMamonan());
                                startActivity(intentPlace);
                            }
                        });
                    }
                }
            }

            @Override
            public void failure(RetrofitError error) {

            }
        });
    }
    public void GetControl(){
        lnPlace=(LinearLayout)findViewById(R.id.layoutPlace);
        lnFood=(LinearLayout)findViewById(R.id.layoutFood);
    }
}
