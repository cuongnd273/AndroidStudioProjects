package com.example.app.goofood.Activity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.example.app.goofood.API.MyServices;
import com.example.app.goofood.GetData.GetRegistry;
import com.example.app.goofood.R;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import retrofit.Callback;
import retrofit.RestAdapter;
import retrofit.RetrofitError;
import retrofit.client.Response;

public class RegistryActivity extends AppCompatActivity {
    EditText taikhoan,matkhau,hoten,sdt,email,diachi;
    RadioGroup gioitinh;
    Button ok,canlce;
    RestAdapter restAdapter;
    MyServices git;
    ProgressDialog dialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registry);
        GetControl();
        restAdapter = new RestAdapter.Builder().setLogLevel(RestAdapter.LogLevel.FULL).setEndpoint(MainActivity.URL).build();
        git = restAdapter.create(MyServices.class);
        ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(taikhoan.getText().length()<5)
                {
                    Toast.makeText(RegistryActivity.this,"Tên đăng nhập phải lớn hơn hoặc bằng 5 ký tự",Toast.LENGTH_SHORT).show();
                    return;
                }else if(matkhau.getText().length()<5)
                {
                    Toast.makeText(RegistryActivity.this,"Mật khẩu phải lớn hơn hoặc bằng 5 ký tự",Toast.LENGTH_SHORT).show();
                    return;
                }else if(hoten.getText().length()<5)
                {
                    Toast.makeText(RegistryActivity.this,"Họ tên phải lớn hơn hoặc bằng 5 ký tự",Toast.LENGTH_SHORT).show();
                    return;
                }else if(sdt.getText().length()<10)
                {
                    Toast.makeText(RegistryActivity.this,"Hãy nhập đúng số điện thoại",Toast.LENGTH_SHORT).show();
                    return;
                }else if(emailValidator(email.getText().toString())==false)
                {
                    Toast.makeText(RegistryActivity.this,"Hãy nhập đúng email",Toast.LENGTH_SHORT).show();
                    return;
                }else if(diachi.getText().length()<2)
                {
                    Toast.makeText(RegistryActivity.this,"Hãy nhập đúng địa chỉ",Toast.LENGTH_SHORT).show();
                    return;
                }
                dialog=new ProgressDialog(RegistryActivity.this);
                dialog.setMessage("Loading...");
                dialog.show();
                final String regisTK=taikhoan.getText().toString();
                final String regisMK=matkhau.getText().toString();
                String regisrHT=hoten.getText().toString();
                String regisSDT=sdt.getText().toString();
                String regisEmail=email.getText().toString();
                String regisDC=diachi.getText().toString();
                String regisGT;
                if(gioitinh.getCheckedRadioButtonId()==R.id.checkNam)regisGT="1";
                else regisGT="0";
                git.Registry(regisTK, regisMK, regisrHT, regisSDT, regisDC, regisEmail, regisGT, new Callback<GetRegistry>() {
                    @Override
                    public void success(GetRegistry getRegistry, Response response) {
                        if(getRegistry.getSuccess()==1)
                        {
                            Intent intent=new Intent(RegistryActivity.this,LoginActivity.class);
                            intent.putExtra("taikhoan",regisTK);
                            intent.putExtra("matkhau",regisMK);
                            startActivity(intent);
                            finish();
                        }else if(getRegistry.getSuccess()==2){
                            Toast.makeText(RegistryActivity.this,"Tài khoản đã được sử dụng",Toast.LENGTH_SHORT).show();
                        }else if(getRegistry.getSuccess()==3){
                            Toast.makeText(RegistryActivity.this,"Email đã được sử dụng",Toast.LENGTH_SHORT).show();
                        }
                        dialog.dismiss();
                    }

                    @Override
                    public void failure(RetrofitError error) {
                        dialog.dismiss();
                    }
                });
            }
        });
    }
    public void GetControl()
    {
        taikhoan=(EditText)findViewById(R.id.registryAcc);
        matkhau=(EditText)findViewById(R.id.regsitryPass);
        hoten=(EditText)findViewById(R.id.registryName);
        sdt=(EditText)findViewById(R.id.registryPhone);
        email=(EditText)findViewById(R.id.registryEmail);
        diachi=(EditText)findViewById(R.id.registryAdd);
        gioitinh=(RadioGroup)findViewById(R.id.groupGioiTinh);
        ok=(Button)findViewById(R.id.registryOK);
        canlce=(Button)findViewById(R.id.registryCancle);
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
}
