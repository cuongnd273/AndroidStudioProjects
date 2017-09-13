package vn.edu.itplus_academy.thitracnghiem.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

import vn.edu.itplus_academy.thitracnghiem.DataBase.DBAdapter;
import vn.edu.itplus_academy.thitracnghiem.Model.ThiSinh;
import vn.edu.itplus_academy.thitracnghiem.R;


public class ScreenActivity extends AppCompatActivity {
    private final static String LTUP = "LTUD";
    private final static String TKDH = "TKDH";
    private DBAdapter db;
    private List<ThiSinh> thiSinh;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_screen);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                db = new DBAdapter(ScreenActivity.this);
                thiSinh = new ArrayList<>();
                thiSinh = db.getAll_ThiSinh();
                if(thiSinh.size() == 0) {
                    startLogIn();
                }else {
                    startMain();
                }
            }
        },3000);


    }

    public void startLogIn(){
        Intent intentLogIn = new Intent(ScreenActivity.this, MainActivity.class);
        startActivity(intentLogIn);
        overridePendingTransition(R.anim.animation_in, R.anim.animation_out);
        finish();
    }

    public void startMain(){
        Intent intentMain = new Intent(ScreenActivity.this, MainActivity.class);
        startActivity(intentMain);
        overridePendingTransition(R.anim.animation_in, R.anim.animation_out);
        finish();
    }
}
