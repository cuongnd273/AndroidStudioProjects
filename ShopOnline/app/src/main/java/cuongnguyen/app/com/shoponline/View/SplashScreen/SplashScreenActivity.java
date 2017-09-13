package cuongnguyen.app.com.shoponline.View.SplashScreen;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import java.util.Timer;
import java.util.TimerTask;
import java.util.logging.Handler;

import cuongnguyen.app.com.shoponline.R;
import cuongnguyen.app.com.shoponline.View.Home.HomeActivity;

public class SplashScreenActivity extends AppCompatActivity {
    Timer timer;
    Handler handler;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);
        timer=new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                Intent intent=new Intent(SplashScreenActivity.this, HomeActivity.class);
                startActivity(intent);
                finish();
            }
        },1500);
    }
}
