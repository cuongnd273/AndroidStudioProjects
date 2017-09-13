package vn.edu.itplus_academy.tracnghiemtienganh.Activitys;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;

import vn.edu.itplus_academy.tracnghiemtienganh.R;

public class SplashScreenActivity extends AppCompatActivity {

    private Handler splashScreenHandler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_screen_saver);

        splashScreenHandler = new Handler();
        splashScreenHandler.postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(SplashScreenActivity.this, UpdateDataActivity.class);
                startActivity(intent);
                finish();
            }
        }, 3000);
    }



}
