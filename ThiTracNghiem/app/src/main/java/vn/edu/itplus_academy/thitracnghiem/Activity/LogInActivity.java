package vn.edu.itplus_academy.thitracnghiem.Activity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import retrofit.Callback;
import retrofit.RestAdapter;
import retrofit.RetrofitError;
import retrofit.client.Response;
import vn.edu.itplus_academy.thitracnghiem.API.API;
import vn.edu.itplus_academy.thitracnghiem.DataBase.DBAdapter;
import vn.edu.itplus_academy.thitracnghiem.Model.MonThi;
import vn.edu.itplus_academy.thitracnghiem.Model.ThiSinh;
import vn.edu.itplus_academy.thitracnghiem.R;
import vn.edu.itplus_academy.thitracnghiem.mModel.ResultLogin;

public class LogInActivity extends AppCompatActivity implements View.OnClickListener{

    private final static String MAINACTIVITY = "MainActivity";
    private final static String LTUP = "LTUD";
    private final static String TKDH = "TKDH";
    private final static String CHUALAM = "ChuaLam";
    private final static String DANGLAM = "DangLam";
    private final static String DALAM = "DaLam";
    public final static String CAUHOIIMG = "img";
    public final static String CAUHOITXT = "txt";
    private String start = null ;
    private EditText edtMaDuThi,edtSoDienThoai;
    private String maDuThi,soDienThoai;
    private Button btnDangNhap;
    private TextView txtDangKy,txtError;
    private boolean doubleBackToExitPressedOnce = false;
    public final static String URL="http://itpsoft.com.vn/englishexam/";
    private DBAdapter db = new DBAdapter(LogInActivity.this);;
    private ThiSinh thiSinh;
    private MonThi toan, ly , hoa , van, anh , sinh , su , dia;
    ProgressDialog dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_in);
        edtMaDuThi = (EditText) findViewById(R.id.edt_maduthi);
        edtSoDienThoai = (EditText) findViewById(R.id.edt_sodienthoai);
        btnDangNhap = (Button) findViewById(R.id.btn_dangnhap);
        txtDangKy = (TextView) findViewById(R.id.txt_dangky);
        txtError = (TextView) findViewById(R.id.txt_error);

        btnDangNhap.setOnClickListener(this);
        txtDangKy.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_dangnhap:
                dialog=new ProgressDialog(LogInActivity.this);
                dialog.setMessage("Login...");
                dialog.show();
                maDuThi = edtMaDuThi.getText().toString().trim();
                soDienThoai = edtSoDienThoai.getText().toString().trim();
                RestAdapter adapter=new RestAdapter.Builder().setEndpoint(URL).build();
                API api=adapter.create(API.class);
                api.login(maDuThi, soDienThoai, new Callback<ResultLogin>() {
                    @Override
                    public void success(ResultLogin resultLogin, Response response) {
                        dialog.dismiss();
                        if(resultLogin.getSuccess().equals("2"))
                        {
                            Toast.makeText(LogInActivity.this,"Thông tin đăng nhập sai",Toast.LENGTH_SHORT).show();
                        }else if(resultLogin.getSuccess().equals("1"))
                        {
                            thiSinh= new ThiSinh(maDuThi,soDienThoai,"",resultLogin.getStatus().toString());
                            db.insert_ThiSinh(thiSinh);
                            for(MonThi i : (ArrayList<MonThi>) resultLogin.getMonThis())
                            {
                                MonThi monThi=new MonThi(i.getMaMonThi(),i.getTitleMonThi(),CHUALAM);
                                db.insert_MonThi(monThi);
                            }
                            Intent intent = new Intent(LogInActivity.this, MainActivity.class);
                            startActivity(intent);
                            overridePendingTransition(R.anim.animation_in, R.anim.animation_out);
                            finish();
                        }
                    }
                    @Override
                    public void failure(RetrofitError error) {
                        dialog.dismiss();
                        Toast.makeText(LogInActivity.this,error.toString(),Toast.LENGTH_SHORT).show();
                    }
                });
                break;
            case R.id.txt_dangky:
                Intent intentSignUp = new Intent(this,SignUpActivity.class);
                startActivity(intentSignUp);
                finish();
                break;
            default:
                break;
        }
    }

    public boolean kiemTraDangNhap(){
        maDuThi = edtMaDuThi.getText().toString().trim();
        soDienThoai = edtSoDienThoai.getText().toString().trim();
        if(!maDuThi.equals("") && !soDienThoai.equals(""))
            return true;
        else
            return false;
    }
}
