package free.app.com.moviebooking.activity;

import android.app.DatePickerDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Build;
import android.support.annotation.IdRes;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.InputType;
import android.text.format.Time;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.DatePicker;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import free.app.com.moviebooking.R;
import free.app.com.moviebooking.api.MovieAPI;
import free.app.com.moviebooking.constant.Constants;
import free.app.com.moviebooking.response.ResponseDangKy;
import free.app.com.moviebooking.response.ResponseGheNgoi;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
public class DangKyActivity extends AppCompatActivity implements Callback<ResponseDangKy>{
    Toolbar toolbar;
    TextInputEditText cmnd,taikhoan,matkhau,xacnhanmatkhau,hoten,email,sdt,diachi,ngaysinh;
    Button dangky;
    ProgressDialog dialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dang_ky);
        getControls();
    }
    public void getControls(){
        toolbar= (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Đăng ký");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        cmnd= (TextInputEditText) findViewById(R.id.cmnd);
        taikhoan= (TextInputEditText) findViewById(R.id.taikhoan);
        matkhau= (TextInputEditText) findViewById(R.id.matkhau);
        xacnhanmatkhau= (TextInputEditText) findViewById(R.id.xacnhanmatkhau);
        hoten= (TextInputEditText) findViewById(R.id.hoten);
        email= (TextInputEditText) findViewById(R.id.email);
        sdt= (TextInputEditText) findViewById(R.id.sdt);
        diachi= (TextInputEditText) findViewById(R.id.diachi);
        ngaysinh= (TextInputEditText) findViewById(R.id.ngaysinh);
        dialog=new ProgressDialog(this);
        dialog.setMessage("Loading...");
        if (Build.VERSION.SDK_INT >= 11) {
            ngaysinh.setRawInputType(InputType.TYPE_CLASS_TEXT);
            ngaysinh.setTextIsSelectable(true);
        } else {
            ngaysinh.setRawInputType(InputType.TYPE_NULL);
            ngaysinh.setFocusable(true);
        }
        dangky= (Button) findViewById(R.id.dangky);
        ngaysinh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatePickerDialog.OnDateSetListener dpd = new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int monthOfYear,
                                          int dayOfMonth) {

                        int s=monthOfYear+1;
                        String a = year+"-"+s+"-"+dayOfMonth;
                        ngaysinh.setText(""+a);
                    }
                };

                Time date = new Time();
                DatePickerDialog d = new DatePickerDialog(DangKyActivity.this, dpd, date.year ,date.month, date.monthDay);
                d.show();
            }
        });
        dangky.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(cmnd.getText().length()<9)
                    Toast.makeText(DangKyActivity.this, "Hãy nhập số chứng minh nhân dân", Toast.LENGTH_SHORT).show();
                else if(taikhoan.getText().length()==0)
                    Toast.makeText(DangKyActivity.this, "Hãy nhập tài khoản", Toast.LENGTH_SHORT).show();
                else if(matkhau.getText().length()<5 || matkhau.getText().length()>16)
                    Toast.makeText(DangKyActivity.this, "Hãy nhập mật khẩu từ 6 đến 16 ký tự", Toast.LENGTH_SHORT).show();
                else if(xacnhanmatkhau.getText().length()==0)
                    Toast.makeText(DangKyActivity.this, "Hãy nhập lại mật khẩu", Toast.LENGTH_SHORT).show();
                else if(!matkhau.getText().toString().equals(xacnhanmatkhau.getText().toString()))
                    Toast.makeText(DangKyActivity.this, "Hãy nhập lại mật khẩu chính xác", Toast.LENGTH_SHORT).show();
                else if(hoten.getText().length()==0)
                    Toast.makeText(DangKyActivity.this, "Hãy nhập họ tên", Toast.LENGTH_SHORT).show();
                else if(!emailValidator(email.getText().toString()))
                    Toast.makeText(DangKyActivity.this, "Hãy nhập đúng email", Toast.LENGTH_SHORT).show();
                else if(sdt.getText().length()==0)
                    Toast.makeText(DangKyActivity.this, "Hãy nhập số điện thoại", Toast.LENGTH_SHORT).show();
                else if(diachi.getText().length()==0)
                    Toast.makeText(DangKyActivity.this, "Hãy nhập địa chỉ", Toast.LENGTH_SHORT).show();
                else{
                    dialog.show();
                    responseAPI(cmnd.getText().toString(),taikhoan.getText().toString(),matkhau.getText().toString(),hoten.getText().toString(),email.getText().toString(),sdt.getText().toString(),diachi.getText().toString(),ngaysinh.getText().toString());
                }
            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId()==android.R.id.home)finish();
        return super.onOptionsItemSelected(item);
    }
    public boolean emailValidator(String email)
    {
        Pattern pattern;
        Matcher matcher;
        final String EMAIL_PATTERN = "^[_A-Za-z0-9-]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
        pattern = Pattern.compile(EMAIL_PATTERN);
        matcher = pattern.matcher(email);
        return matcher.matches();
    }
    void responseAPI(String cmnd,String taikhoan,String matkhau,String hoten,String email,String sdt,String diachi,String ngaysinh){
        Gson gson = new GsonBuilder()
                .setLenient()
                .create();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Constants.URL)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();

        MovieAPI gerritAPI = retrofit.create(MovieAPI.class);

        Call<ResponseDangKy> call = gerritAPI.dangky(cmnd,taikhoan,matkhau,hoten,email,sdt,diachi,ngaysinh);
        call.enqueue(this);
    }

    @Override
    public void onResponse(Call<ResponseDangKy> call, Response<ResponseDangKy> response) {
        dialog.dismiss();
        if(response.body().getStatus()==200){
            AlertDialog.Builder builder;
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                builder = new AlertDialog.Builder(this, android.R.style.Theme_Material_Dialog_Alert);
            } else {
                builder = new AlertDialog.Builder(this);
            }
            builder.setCancelable(false);
            builder.setTitle("Đăng ký")
                    .setMessage("Bạn đã đăng ký thành công.")
                    .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            Intent intent=new Intent(DangKyActivity.this,DangNhapActivity.class);
                            startActivity(intent);
                            finish();
                        }
                    })
                    .setIcon(android.R.drawable.ic_dialog_alert)
                    .show();
        }else if(response.body().getStatus()==201){
            Toast.makeText(this, "Chứng minh nhân dân đã được sử dụng", Toast.LENGTH_SHORT).show();
        }else if(response.body().getStatus()==202){
            Toast.makeText(this, "Email đã được sử dụng", Toast.LENGTH_SHORT).show();
        }else if(response.body().getStatus()==203){
            Toast.makeText(this, "Tài khoản đã được sử dụng", Toast.LENGTH_SHORT).show();
        }else if(response.body().getStatus()==204){
            Toast.makeText(this, "Có lỗi xảy ra", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onFailure(Call<ResponseDangKy> call, Throwable t) {
        dialog.dismiss();
        Toast.makeText(this, "Có lỗi xảy ra", Toast.LENGTH_SHORT).show();
    }
}
