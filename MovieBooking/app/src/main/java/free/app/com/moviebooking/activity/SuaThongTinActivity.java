package free.app.com.moviebooking.activity;

import android.app.ProgressDialog;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
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
import free.app.com.moviebooking.response.ResponseSuaThongTin;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class SuaThongTinActivity extends AppCompatActivity implements Callback<ResponseSuaThongTin> {
    TextInputEditText matkhaucu,matkhaumoi,xacnhanmatkhau,hoten,sdt,diachi,ngaysinh;
    User user;
    Button capnhap;
    ProgressDialog dialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sua_thong_tin);
        user= User.getUser(this);
        getControls();
    }
    public void getControls(){
        matkhaucu= (TextInputEditText) findViewById(R.id.matkhaucu);
        matkhaumoi= (TextInputEditText) findViewById(R.id.matkhaumoi);
        xacnhanmatkhau= (TextInputEditText) findViewById(R.id.xacnhanmatkhau);
        hoten= (TextInputEditText) findViewById(R.id.hoten);
        sdt= (TextInputEditText) findViewById(R.id.sdt);
        diachi= (TextInputEditText) findViewById(R.id.diachi);
        ngaysinh= (TextInputEditText) findViewById(R.id.ngaysinh);
        capnhap= (Button) findViewById(R.id.capnhap);
        dialog=new ProgressDialog(this);
        dialog.setMessage("Loading...");

        hoten.setText(user.getHoten());
        ngaysinh.setText(user.getNgaysinh());
        sdt.setText(user.getSdt());
        diachi.setText(user.getDiachi());
        capnhap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(matkhaucu.getText().length()==0)
                    Toast.makeText(SuaThongTinActivity.this, "Hãy nhập mật khẩu cũ", Toast.LENGTH_SHORT).show();
                else if(matkhaumoi.getText().length()<5 || matkhaumoi.getText().length()>16)
                    Toast.makeText(SuaThongTinActivity.this, "Hãy nhập mật khẩu từ 6 đến 16 ký tự", Toast.LENGTH_SHORT).show();
                else if(xacnhanmatkhau.getText().length()==0)
                    Toast.makeText(SuaThongTinActivity.this, "Hãy xác nhận mật khẩu", Toast.LENGTH_SHORT).show();
                else if(!xacnhanmatkhau.getText().toString().equals(matkhaumoi.getText().toString()))
                    Toast.makeText(SuaThongTinActivity.this, "Hãy nhập lại đúng mật khẩu mới", Toast.LENGTH_SHORT).show();
                else if(hoten.getText().length()==0)
                    Toast.makeText(SuaThongTinActivity.this, "Hãy nhập họ tên", Toast.LENGTH_SHORT).show();
                else if(sdt.getText().length()==0)
                    Toast.makeText(SuaThongTinActivity.this, "Hãy nhập số điện thoại", Toast.LENGTH_SHORT).show();
                else if(diachi.getText().length()==0)
                    Toast.makeText(SuaThongTinActivity.this, "Hãy nhập địa chỉ", Toast.LENGTH_SHORT).show();
                else
                    update(user.getMataikhoan(),matkhaucu.getText().toString(),matkhaumoi.getText().toString(),hoten.getText().toString(),sdt.getText().toString(),diachi.getText().toString(),ngaysinh.getText().toString());

            }
        });
    }
    public void update(int mataikhoan,String mkcu,String mkmoi,String ht,String dt,String dc,String ns){
        dialog.show();
        Gson gson = new GsonBuilder()
                .setLenient()
                .create();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Constants.URL)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();

        MovieAPI gerritAPI = retrofit.create(MovieAPI.class);

        Call<ResponseSuaThongTin> call = gerritAPI.suathongtin(mataikhoan,mkcu,mkmoi,ht,dt,dc,ns);
        call.enqueue(this);
    }

    @Override
    public void onResponse(Call<ResponseSuaThongTin> call, Response<ResponseSuaThongTin> response) {
        dialog.dismiss();
        ResponseSuaThongTin responseSuaThongTin =response.body();
        if(response.body().getStatus()==200){
            Toast.makeText(this, "Cập nhập thành công", Toast.LENGTH_SHORT).show();
            User user=new User();
            user.setMataikhoan(responseSuaThongTin.getMataikhoan());
            user.setTaikhoan(responseSuaThongTin.getTentaikhoan());
            user.setCmnd(responseSuaThongTin.getCmnd());
            user.setHoten(responseSuaThongTin.getHoten());
            user.setNgaysinh(responseSuaThongTin.getNgaysinh());
            user.setEmail(responseSuaThongTin.getEmail());
            user.setDiachi(responseSuaThongTin.getDiachi());
            user.setSdt(responseSuaThongTin.getSdt());
            User.setUser(this,user);
            finish();
        }else if(response.body().getStatus()==202){
            Toast.makeText(this, "Sai mật khẩu", Toast.LENGTH_SHORT).show();
        }else {
            Toast.makeText(this, "Có lỗi xảy ra", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onFailure(Call<ResponseSuaThongTin> call, Throwable t) {
        dialog.dismiss();
        Toast.makeText(this, "Có lỗi xảy ra error", Toast.LENGTH_SHORT).show();
    }
}
