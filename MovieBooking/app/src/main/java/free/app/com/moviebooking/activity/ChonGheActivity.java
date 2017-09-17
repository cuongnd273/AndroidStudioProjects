package free.app.com.moviebooking.activity;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import org.json.JSONArray;

import java.util.ArrayList;
import java.util.List;

import by.anatoldeveloper.hallscheme.hall.HallScheme;
import by.anatoldeveloper.hallscheme.hall.ScenePosition;
import by.anatoldeveloper.hallscheme.hall.SeatListener;
import by.anatoldeveloper.hallscheme.view.ZoomableImageView;
import free.app.com.moviebooking.R;
import free.app.com.moviebooking.api.MovieAPI;
import free.app.com.moviebooking.constant.Constants;
import free.app.com.moviebooking.model.GheNgoi;
import free.app.com.moviebooking.model.MySeat;
import free.app.com.moviebooking.model.User;
import free.app.com.moviebooking.response.ResponseDatVe;
import free.app.com.moviebooking.response.ResponseGheNgoi;
import free.app.com.moviebooking.response.ResponseLichChieu;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static android.R.attr.data;
import static android.R.attr.scheme;

public class ChonGheActivity extends AppCompatActivity implements View.OnClickListener{
    Toolbar toolbar;
    int malichchieu=0;
    ZoomableImageView imageView;
    List<GheNgoi> listGheDaDat,listChonGhe;
    TextView soghe;
    Button thanhtoan;
    int matk=0,giave=0,giamgia=0;
    ProgressDialog progressDialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chon_ghe);
        Intent intent=getIntent();
        malichchieu= Integer.parseInt(intent.getStringExtra("malichchieu"));
        getControls();
        responseGhe();
    }
    public void getControls(){
        imageView = (ZoomableImageView) findViewById(R.id.zoomable_image);
        toolbar= (Toolbar) findViewById(R.id.toolbar);
        soghe= (TextView) findViewById(R.id.soghe);
        thanhtoan= (Button) findViewById(R.id.thanhtoan);
        thanhtoan.setOnClickListener(this);
        listChonGhe=new ArrayList<>();
        listGheDaDat=new ArrayList<>();
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Chọn ghế");
        progressDialog=new ProgressDialog(this);
        progressDialog.setMessage("Loading...");
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId()==android.R.id.home)finish();
        return super.onOptionsItemSelected(item);
    }

    public by.anatoldeveloper.hallscheme.hall.Seat[][] basicScheme(int rows,int cols) {
        by.anatoldeveloper.hallscheme.hall.Seat seats[][] = new by.anatoldeveloper.hallscheme.hall.Seat[rows][cols];
        int k = 0;
        for (int i = 0; i < rows; i++)
            for(int j = 0; j < cols; j++) {
                MySeat mySeat = new MySeat();
                mySeat.status = HallScheme.SeatStatus.FREE;
                    mySeat.id = ++k;
                    mySeat.selectedSeatMarker = String.valueOf(k);
                    for(GheNgoi item : listGheDaDat){
                        if(k==item.getVitri())
                            mySeat.status = HallScheme.SeatStatus.BUSY;
                    }
                seats[i][j] = mySeat;
            }
        return seats;
    }
    void responseGhe(){
        Gson gson = new GsonBuilder()
                .setLenient()
                .create();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Constants.URL)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();

        MovieAPI gerritAPI = retrofit.create(MovieAPI.class);

        Call<ResponseGheNgoi> call = gerritAPI.ghengoi(malichchieu);
        call.enqueue(new Callback<ResponseGheNgoi>() {
            @Override
            public void onResponse(Call<ResponseGheNgoi> call, Response<ResponseGheNgoi> response) {
                if(response.body().getStatus()==200){
                    int rows=(response.body().getSoghe())/10,cols=10;
                    giave=response.body().getGiave();
                    for(GheNgoi item : response.body().getGhengois()){
                        listGheDaDat.add(item);
                    }
                    HallScheme scheme = new HallScheme(imageView, basicScheme(rows,cols), ChonGheActivity.this);
                    scheme.setSceneName("Screen");
                    scheme.setBackgroundColor(Color.TRANSPARENT);
                    scheme.setScenePosition(ScenePosition.NORTH);
                    scheme.setSeatListener(new SeatListener() {

                        @Override
                        public void selectSeat(int id) {
                            listChonGhe.add(new GheNgoi(id));
                            soghe.setText("Đã chọn : "+listChonGhe.size() +" ghế");
                        }

                        @Override
                        public void unSelectSeat(int id) {
                            for(GheNgoi item : listChonGhe){
                                if(item.getVitri()==id)
                                    listChonGhe.remove(item);
                            }
                            soghe.setText("Đã chọn : "+listChonGhe.size() +" ghế");
                        }
                    });
                }
            }

            @Override
            public void onFailure(Call<ResponseGheNgoi> call, Throwable t) {

            }
        });
    }
    void responseDatVe(int matk,int giave,int giamgia,String ghes){
        Gson gson = new GsonBuilder()
                .setLenient()
                .create();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Constants.URL)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();

        MovieAPI gerritAPI = retrofit.create(MovieAPI.class);

        Call<ResponseDatVe> call = gerritAPI.datve(matk,malichchieu,giave,giamgia,ghes);
        call.enqueue(new Callback<ResponseDatVe>() {
            @Override
            public void onResponse(Call<ResponseDatVe> call, Response<ResponseDatVe> response) {
                progressDialog.dismiss();
                if(response.body().getStatus()==200){
                    AlertDialog.Builder builder=new AlertDialog.Builder(ChonGheActivity.this);
                    builder.setMessage("Bạn đã đặt vé thành công.");
                    builder.setCancelable(false);
                    builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            finish();
                        }
                    });
                    AlertDialog alertDialog=builder.create();
                    alertDialog.show();
                }else{
                    Toast.makeText(ChonGheActivity.this, "Có lỗi xảy ra khi đặt vé, xin vui lòng thử lại sau", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ResponseDatVe> call, Throwable t) {
                String mess=call.toString();
                Log.i("LoiDatVe",mess);
            }
        });
    }
    @Override
    public void onClick(View v) {
        if(v.getId()==R.id.thanhtoan){
            if(listChonGhe.size()>0){
                AlertDialog.Builder builder = new AlertDialog.Builder(ChonGheActivity.this);
                builder.setMessage("Bạn có phiếu giảm giá không?");
                builder.setCancelable(true);

                builder.setPositiveButton(
                        "Yes",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                dialog.cancel();
                                Intent intent=new Intent(ChonGheActivity.this,PhieuGiamGiaActivity.class);
                                startActivityForResult(intent,1);
                            }
                        });

                builder.setNegativeButton(
                        "No",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                dialog.cancel();
                                progressDialog.show();
                                JSONArray ghes=new JSONArray();
                                for(GheNgoi item : listChonGhe){
                                    ghes.put(String.valueOf(item.getVitri()));
                                }
                                User user=User.getUser(ChonGheActivity.this);
                                responseDatVe(user.getMataikhoan(),giave,giamgia,ghes.toString());
                            }
                        });

                AlertDialog alertDialog = builder.create();
                alertDialog.show();
            }else{
                Toast.makeText(this, "Hãy chọn ghế", Toast.LENGTH_SHORT).show();
            }
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode==1){
            if(resultCode==1){
                giamgia=data.getIntExtra("giamgia",0);
                JSONArray ghes=new JSONArray();
                for(GheNgoi item : listChonGhe){
                    ghes.put(item.getVitri());
                }
                User user=User.getUser(this);
                responseDatVe(user.getMataikhoan(),giave,giamgia,ghes.toString());
            }else if(resultCode==2){
                giamgia=0;
                Toast.makeText(this, "Phiếu không hợp lệ hoặc đã xảy ra lỗi.", Toast.LENGTH_SHORT).show();
            }else if(resultCode==3){
                giamgia=0;
                Toast.makeText(this, "Có lỗi xảy ra", Toast.LENGTH_SHORT).show();
            }
        }
    }
}
