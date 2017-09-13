package free.app.com.moviebooking.activity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.squareup.picasso.Picasso;

import java.util.List;

import free.app.com.moviebooking.R;
import free.app.com.moviebooking.api.MovieAPI;
import free.app.com.moviebooking.constant.Constants;
import free.app.com.moviebooking.model.Phim;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static free.app.com.moviebooking.R.drawable.call;

public class ThongTinPhimActivity extends AppCompatActivity implements View.OnClickListener, Callback<Phim>{
    Toolbar toolbar;
    Button datve;
    int maphim;
    TextView tenphim,daodien,dienvien,ngaychieu,dodai,tomtat;
    ImageView anh;
    ProgressDialog dialog;
    int type=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_thong_tin_phim);
        getControls();
        dialog.show();
        maphim= Integer.parseInt(getIntent().getStringExtra("maphim"));
        getData();
        type=getIntent().getIntExtra("type",0);
        if(type==0)datve.setVisibility(View.INVISIBLE);
    }
    public void getControls(){
        toolbar= (Toolbar) findViewById(R.id.toolbar);
        datve= (Button) findViewById(R.id.bt_dat_ve);
        tenphim= (TextView) findViewById(R.id.ten_phim);
        daodien= (TextView) findViewById(R.id.dao_dien);
        dienvien= (TextView) findViewById(R.id.dien_vien);
        ngaychieu= (TextView) findViewById(R.id.ngay_chieu);
        dodai= (TextView) findViewById(R.id.do_dai);
        tomtat= (TextView) findViewById(R.id.tom_tat);
        anh= (ImageView) findViewById(R.id.image);
        datve.setOnClickListener(this);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Thông tin phim");
        dialog=new ProgressDialog(this);
        dialog.setMessage("Loading...");
    }
    public void getData(){
        Gson gson = new GsonBuilder()
                .setLenient()
                .create();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Constants.URL)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();

        MovieAPI gerritAPI = retrofit.create(MovieAPI.class);

        Call<Phim> call = gerritAPI.phim(maphim);
        call.enqueue(this);
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId()==android.R.id.home)finish();
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onClick(View v) {
        if(v.getId()==R.id.bt_dat_ve){
            Intent intent=new Intent(ThongTinPhimActivity.this,LichChieuActivity.class);
            intent.putExtra("maphim",maphim);
            startActivity(intent);
        }
    }

    @Override
    public void onResponse(Call<Phim> call, Response<Phim> response) {
        Phim phim=response.body();
        Picasso.with(this).load(phim.getAnh()).into(anh);
        tenphim.setText(phim.getTenphim());
        daodien.setText("Đạo diễn : "+phim.getDaodien());
        dienvien.setText("Diễn viên : "+phim.getDienvien());
        ngaychieu.setText("Ngày chiếu : "+phim.getNgaybatdau());
        dodai.setText("Thời lượng : "+phim.getThoiluong()+" phút");
        tomtat.setText(phim.getTomtat());
        dialog.dismiss();
    }

    @Override
    public void onFailure(Call<Phim> call, Throwable t) {
        Log.i("Loi", "onFailure: "+t.getMessage());
        finish();
    }
}
