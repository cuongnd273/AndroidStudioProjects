package free.app.com.moviebooking.activity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import free.app.com.moviebooking.R;
import free.app.com.moviebooking.api.MovieAPI;
import free.app.com.moviebooking.constant.Constants;
import free.app.com.moviebooking.model.User;
import free.app.com.moviebooking.response.ResponseDangNhap;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class DangNhapActivity extends AppCompatActivity implements Callback<ResponseDangNhap>{
    Toolbar toolbar;
    TextInputEditText taikhoan,matkhau;
    Button dangnhap,dangky;
    ProgressDialog progressDialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dang_nhap);
        getControls();
    }
    public void getControls(){
        toolbar= (Toolbar) findViewById(R.id.toolbar);
        taikhoan= (TextInputEditText) findViewById(R.id.taikhoan);
        matkhau= (TextInputEditText) findViewById(R.id.matkhau);
        dangnhap= (Button) findViewById(R.id.dangnhap);
        dangky= (Button) findViewById(R.id.dangky);
        progressDialog=new ProgressDialog(this);
        progressDialog.setMessage("Loading...");
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        dangnhap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(taikhoan.getText().length()>0 && matkhau.getText().length()>0){
                    progressDialog.show();
                    login(taikhoan.getText().toString(),matkhau.getText().toString());
                }else{
                    Toast.makeText(DangNhapActivity.this,"Hãy nhập tài khoản và mật khẩu",Toast.LENGTH_SHORT).show();
                }
            }
        });
        dangky.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(DangNhapActivity.this,DangKyActivity.class);
                startActivity(intent);
            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case android.R.id.home:
                finish();
                break;
        }
        return super.onOptionsItemSelected(item);
    }
    public void login(String tk,String mk){
        Gson gson = new GsonBuilder()
                .setLenient()
                .create();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Constants.URL)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();

        MovieAPI gerritAPI = retrofit.create(MovieAPI.class);

        Call<ResponseDangNhap> call = gerritAPI.login(tk,mk);
        call.enqueue(this);
    }

    @Override
    public void onResponse(Call<ResponseDangNhap> call, Response<ResponseDangNhap> response) {
        ResponseDangNhap responseDangNhap =response.body();
        if(responseDangNhap.getStatus()==200){
            User user=new User();
            user.setMataikhoan(responseDangNhap.getMataikhoan());
            user.setTaikhoan(responseDangNhap.getTentaikhoan());
            user.setCmnd(responseDangNhap.getCmnd());
            user.setHoten(responseDangNhap.getHoten());
            user.setNgaysinh(responseDangNhap.getNgaysinh());
            user.setEmail(responseDangNhap.getEmail());
            user.setDiachi(responseDangNhap.getDiachi());
            user.setSdt(responseDangNhap.getSdt());

            User.setUser(this,user);
            progressDialog.dismiss();
            Intent intent=new Intent(this,MainActivity.class);
            startActivity(intent);
        }else if(responseDangNhap.getStatus()==201){
            progressDialog.dismiss();
            Toast.makeText(this, "Sai thông tin đăng nhập", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onFailure(Call<ResponseDangNhap> call, Throwable t) {
        progressDialog.dismiss();
        Toast.makeText(this, "Có lỗi xảy ra", Toast.LENGTH_SHORT).show();
    }
}
