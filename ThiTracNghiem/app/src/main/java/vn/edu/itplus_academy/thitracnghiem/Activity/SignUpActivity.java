package vn.edu.itplus_academy.thitracnghiem.Activity;

import android.app.DatePickerDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Calendar;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import retrofit.Callback;
import retrofit.RestAdapter;
import retrofit.RetrofitError;
import retrofit.client.Response;
import vn.edu.itplus_academy.thitracnghiem.API.API;
import vn.edu.itplus_academy.thitracnghiem.R;
import vn.edu.itplus_academy.thitracnghiem.mModel.ResultRegister;

public class SignUpActivity extends AppCompatActivity implements View.OnClickListener{

    private final static String MAINACTIVITY = "MainActivity";
    private String start = null ;
    private EditText edtHoDem,edtTen,edtEmail,edtSoDienThoai,edtNgaySinh,edtDiaChi,edtTruong,edtLop,edtTenPhuHuynh,edtSoDienThoaiPhuHuynh;
    private Button btnDangKy;
    private TextView txtDangNhap;
    private RadioGroup rgrChuongTrinhHoc;
    private RadioButton rbtnTKDH,rbtnLTUD;
    private String chuyenNganh = "";
    private int _year,_month,_day;
    private boolean doubleBackToExitPressedOnce = false;
    ProgressDialog dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        edtHoDem = (EditText) findViewById(R.id.edt_hodem);
        edtTen = (EditText) findViewById(R.id.edt_ten);
        edtEmail = (EditText) findViewById(R.id.edt_email);
        edtSoDienThoai = (EditText) findViewById(R.id.edt_sodienthoai);
        edtNgaySinh = (EditText) findViewById(R.id.edt_ngaysinh);
        edtDiaChi = (EditText) findViewById(R.id.edt_diachi);
        edtTruong = (EditText) findViewById(R.id.edt_truong);
        edtLop = (EditText) findViewById(R.id.edt_lop);
        edtTenPhuHuynh = (EditText) findViewById(R.id.edt_tenphuhuynh);
        edtSoDienThoaiPhuHuynh = (EditText) findViewById(R.id.edt_sodienthoaiphuhuynh);

        btnDangKy = (Button) findViewById(R.id.btn_dangky);
        txtDangNhap = (TextView) findViewById(R.id.txt_dangnhap);

        rgrChuongTrinhHoc = (RadioGroup) findViewById(R.id.rgr_chuongtrinhhoc);
        rbtnLTUD = (RadioButton) findViewById(R.id.rbtn_ltud);
        rbtnTKDH = (RadioButton) findViewById(R.id.rbtn_tkdh);

        btnDangKy.setOnClickListener(this);
        txtDangNhap.setOnClickListener(this);
        edtNgaySinh.setOnClickListener(this);

    }

    private void getData(){
        try {
            Intent intent = getIntent();
            Bundle bundle = intent.getExtras();
            start = bundle.getString("start");
        }catch (Exception e){

        }

    }

    private void startMain(){
        startActivity(new Intent(this, MainActivity.class));
        finish();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_dangky:
                if(kiemTraDangKy()){
                    if(rbtnTKDH.isChecked())
                    {
                        chuyenNganh="DH";
                    }else if(rbtnLTUD.isChecked())
                    {
                        chuyenNganh="LT";
                    }
                    dialog=new ProgressDialog(SignUpActivity.this);
                    dialog.setMessage("Register...");
                    dialog.show();
                    RestAdapter restAdapter=new RestAdapter.Builder().setEndpoint(LogInActivity.URL).build();
                    API api=restAdapter.create(API.class);
                    api.register(edtHoDem.getText().toString(), edtTen.getText().toString(), edtNgaySinh.getText().toString(),
                            edtSoDienThoai.getText().toString(), edtEmail.getText().toString(),
                            edtTruong.getText().toString(), edtLop.getText().toString(), edtDiaChi.getText().toString(), edtTenPhuHuynh.getText().toString(),
                            edtSoDienThoaiPhuHuynh.getText().toString(), chuyenNganh, new Callback<ResultRegister>() {
                                @Override
                                public void success(ResultRegister resultRegister, Response response) {
                                    dialog.dismiss();
                                    if(resultRegister.getSuccess().equals("1"))
                                    {
                                        AlertDialog.Builder alert = new AlertDialog.Builder(SignUpActivity.this);
                                        alert.setTitle("Thông báo");
                                        alert.setMessage("Bạn đã đăng ký thành công.\nMã dự thi của bạn là: "+resultRegister.getCode().toString());
                                        alert.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                            @Override
                                            public void onClick(DialogInterface dialog, int which) {
                                                Intent intentMain = new Intent(SignUpActivity.this,LogInActivity.class);
                                                startActivity(intentMain);
                                                finish();
                                            }
                                        });
                                        alert.show();

                                    }else if(resultRegister.getSuccess().equals("3")){
                                        Toast.makeText(SignUpActivity.this,"Số Điện Thoại đã được sử dụng",Toast.LENGTH_LONG).show();
                                    }else if(resultRegister.getSuccess().equals("2")){
                                        Toast.makeText(SignUpActivity.this,"Có lỗi xảy ra",Toast.LENGTH_LONG).show();
                                    }
                                }

                                @Override
                                public void failure(RetrofitError error) {
                                    dialog.dismiss();
                                    Toast.makeText(SignUpActivity.this,error.toString(),Toast.LENGTH_LONG).show();
                                }
                            });
                }
                break;
            case R.id.txt_dangnhap:
                Intent intentLogIn = new Intent(this,LogInActivity.class);
                startActivity(intentLogIn);
                finish();
                break;
            case R.id.edt_ngaysinh:
                Calendar calendar = Calendar.getInstance();
                DatePickerDialog datePickerDialog = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                        _day = dayOfMonth;
                        _month = monthOfYear;
                        _year = year;
                        String date = dayOfMonth +"/"+(monthOfYear +1)+"/"+year;
                        edtNgaySinh.setText(date.toString());
                    }
                }, calendar.get(Calendar.YEAR),calendar.get(Calendar.MONTH),calendar.get(Calendar.DAY_OF_MONTH));
                datePickerDialog.show();
                break;
            default:
                break;
        }
    }

    public boolean kiemTraDangKy(){
        int i = 0;
        if(edtHoDem.getText().length()<1)
        {
            Toast.makeText(this,"Hãy nhập họ đệm",Toast.LENGTH_SHORT).show();
            return false;
        }else if(edtTen.getText().length()<1)
        {
            Toast.makeText(this,"Hãy nhập tên",Toast.LENGTH_SHORT).show();
            return false;
        }else if(isEmailValid(edtEmail.getText().toString())==false)
        {
            Toast.makeText(this,"Hãy nhập đúng email",Toast.LENGTH_SHORT).show();
            return false;
        }
        else if(edtSoDienThoai.getText().length()<10)
        {
            Toast.makeText(this,"Hãy nhập đúng số điện thoại",Toast.LENGTH_SHORT).show();
            return false;
        }else if(edtNgaySinh.getText().length()<1)
        {
            Toast.makeText(this,"Hãy nhập ngày sinh",Toast.LENGTH_SHORT).show();
            return false;
        }else if(edtDiaChi.getText().length()<1)
        {
            Toast.makeText(this,"Hãy nhập địa chỉ",Toast.LENGTH_SHORT).show();
            return false;
        }else if(edtTruong.getText().length()<1)
        {
            Toast.makeText(this,"Hãy nhập trường",Toast.LENGTH_SHORT).show();
            return false;
        }else if(edtLop.getText().length()<1)
        {
            Toast.makeText(this,"Hãy nhập lớp",Toast.LENGTH_SHORT).show();
            return false;
        }else if(edtTenPhuHuynh.getText().length()<1)
        {
            Toast.makeText(this,"Hãy nhập tên phụ huynh",Toast.LENGTH_SHORT).show();
            return false;
        }else if(edtSoDienThoaiPhuHuynh.getText().length()<10)
        {
            Toast.makeText(this,"Hãy số điện thoại phụ huynh",Toast.LENGTH_SHORT).show();
            return false;
        }
            return true;
    }
    public boolean isEmailValid(String email)
    {
        String regExpn =
                "^(([\\w-]+\\.)+[\\w-]+|([a-zA-Z]{1}|[\\w-]{2,}))@"
                        +"((([0-1]?[0-9]{1,2}|25[0-5]|2[0-4][0-9])\\.([0-1]?"
                        +"[0-9]{1,2}|25[0-5]|2[0-4][0-9])\\."
                        +"([0-1]?[0-9]{1,2}|25[0-5]|2[0-4][0-9])\\.([0-1]?"
                        +"[0-9]{1,2}|25[0-5]|2[0-4][0-9])){1}|"
                        +"([a-zA-Z]+[\\w-]+\\.)+[a-zA-Z]{2,4})$";

        CharSequence inputStr = email;

        Pattern pattern = Pattern.compile(regExpn,Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(inputStr);

        if(matcher.matches())
            return true;
        else
            return false;
    }
}
