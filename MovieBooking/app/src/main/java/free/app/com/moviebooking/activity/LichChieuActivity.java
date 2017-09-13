package free.app.com.moviebooking.activity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.ArrayList;
import java.util.List;

import free.app.com.moviebooking.R;
import free.app.com.moviebooking.adapter.AdapterNgayChieu;
import free.app.com.moviebooking.adapter.AdapterLichChieu;
import free.app.com.moviebooking.api.MovieAPI;
import free.app.com.moviebooking.constant.Constants;
import free.app.com.moviebooking.model.NgayChieu;
import free.app.com.moviebooking.model.LichChieu;
import free.app.com.moviebooking.model.Phim;
import free.app.com.moviebooking.model.User;
import free.app.com.moviebooking.response.ResponseLichChieu;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static android.icu.lang.UCharacter.GraphemeClusterBreak.L;

public class LichChieuActivity extends AppCompatActivity implements Callback<ResponseLichChieu> {
    Toolbar toolbar;
    RecyclerView lichChieu,thoiGian;
    ArrayList<NgayChieu> listNgayChieu;
    ArrayList<LichChieu> listLichChieu,listThoiGian;
    AdapterNgayChieu adapterNgayChieu;
    AdapterLichChieu adapterLichChieu;
    TextView ngaychieu;
    int maphim=0;
    ProgressDialog progressDialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ngay_chieu);
        getControls();
        Intent intent=getIntent();
        maphim= intent.getIntExtra("maphim",0);
        progressDialog.show();
        responseAPI();
    }
    public void getControls(){
        toolbar= (Toolbar) findViewById(R.id.toolbar);
        lichChieu= (RecyclerView) findViewById(R.id.list_ngay_chieu);
        thoiGian= (RecyclerView) findViewById(R.id.list_thoi_gian);
        ngaychieu= (TextView) findViewById(R.id.ngay_chieu);
        progressDialog=new ProgressDialog(this);
        progressDialog.setMessage("Loading...");
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Lịch chiếu");

        listNgayChieu =new ArrayList<>();
        listLichChieu =new ArrayList<>();
        listThoiGian=new ArrayList<>();
        adapterNgayChieu=new AdapterNgayChieu(this, listNgayChieu, new AdapterNgayChieu.NgayChieuEvent() {
            @Override
            public void onClick(AdapterNgayChieu.ViewHolder holder,View v, int position) {
                ngaychieu.setText(listNgayChieu.get(position).getNgaychieu());
                for(int i=0;i<listNgayChieu.size();i++)
                {
                    if(i==position)listNgayChieu.get(i).setSelected(true);
                    else listNgayChieu.get(i).setSelected(false);
                    listThoiGian.clear();
                    for(int j=0;j<listLichChieu.size();j++){
                        if(listLichChieu.get(j).getNgaychieu().equals(listNgayChieu.get(position).getNgaychieu()))
                            listThoiGian.add(listLichChieu.get(j));
                        adapterLichChieu.notifyDataSetChanged();
                    }
                }
            }
        });
        lichChieu.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false));
        adapterLichChieu =new AdapterLichChieu(this, listThoiGian, new AdapterLichChieu.OnClick() {
            @Override
            public void click(View v,int position) {
                User user=User.getUser(LichChieuActivity.this);
                if(user.getTaikhoan()==""){
                    Intent intent=new Intent(LichChieuActivity.this,DangNhapActivity.class);
                    startActivity(intent);
                }else{
                    Intent intent=new Intent(LichChieuActivity.this,ChonGheActivity.class);
                    intent.putExtra("malichchieu",listThoiGian.get(position).getMalichchieu());
                    startActivity(intent);
                }
            }
        });
        thoiGian.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false));
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId()==android.R.id.home)finish();
        return super.onOptionsItemSelected(item);
    }
    void responseAPI(){
        Gson gson = new GsonBuilder()
                .setLenient()
                .create();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Constants.URL)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();

        MovieAPI gerritAPI = retrofit.create(MovieAPI.class);

        Call<ResponseLichChieu> call = gerritAPI.lichchieu(maphim);
        call.enqueue(this);
    }

    @Override
    public void onResponse(Call<ResponseLichChieu> call, Response<ResponseLichChieu> response) {
        if(response.body().getStatus()==200){
           for(int i=0;i<response.body().getLichchieu().size();i++){
               List<LichChieu> lichChieus=response.body().getLichchieu().get(i);
               if(i==0)listNgayChieu.add(new NgayChieu(lichChieus.get(0).getNgaychieu(),true));
               else listNgayChieu.add(new NgayChieu(lichChieus.get(0).getNgaychieu(),false));
               for(int j=0;j<lichChieus.size();j++){
                   listLichChieu.add(lichChieus.get(j));
                   if(i==0)listThoiGian.add(lichChieus.get(j));
               }
           }
            ngaychieu.setText(listNgayChieu.get(0).getNgaychieu());
            lichChieu.setAdapter(adapterNgayChieu);
            thoiGian.setAdapter(adapterLichChieu);
        }
        progressDialog.dismiss();
    }

    @Override
    public void onFailure(Call<ResponseLichChieu> call, Throwable t) {
        progressDialog.dismiss();
        finish();
    }
}
