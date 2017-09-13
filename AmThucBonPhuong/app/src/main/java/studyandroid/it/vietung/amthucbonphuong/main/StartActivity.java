package studyandroid.it.vietung.amthucbonphuong.main;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.os.SystemClock;
import android.widget.ProgressBar;

import com.parse.Parse;

import java.util.concurrent.atomic.AtomicBoolean;

import studyandroid.it.vietung.amthucbonphuong.R;

public class StartActivity extends Activity {

    private ProgressBar prb;
    private Handler handler;
    private AtomicBoolean isrunning = new AtomicBoolean(false);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);

        Parse.enableLocalDatastore(this);
        Parse.initialize(this, "SpeQnsJwTdy72gnWi8t8Thd1KkSW1IY0pDtGfvkL", "bKzudxUi2BpU7zGAwAQTwTZOGOy4yoehXpZYPTfV");

        prb = (ProgressBar) findViewById(R.id.progressBar1);
        handler = new Handler() {
            public void handleMessage(Message msg) {
                super.handleMessage(msg);
                prb.setProgress(msg.arg1);
            }
        };
        prb.setProgress(0);
        isrunning.set(false);
        Thread th = new Thread(new Runnable() {

            @Override
            public void run() {
                // TODO Auto-generated method stub
                for (int i = 0; i <= 100 && isrunning.get();i=i+5) {

                    SystemClock.sleep(100);
                    Message msg = handler.obtainMessage();
                    msg.arg1 = i;
                    handler.sendMessage(msg);
                    if (i == 100) {
                        Next();
                    }
                }
            }
        });
        isrunning.set(true);
        th.start();
    }

    public void Next() {
        Intent in = new Intent(this, MainActivity.class);
        startActivity(in);
        finish();
    }


}
