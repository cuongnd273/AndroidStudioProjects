package cuongnguyen.app.com.musicworld.activity;

import android.app.ActivityManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.LinearInterpolator;
import android.view.animation.RotateAnimation;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Iterator;
import java.util.List;

import cuongnguyen.app.com.musicworld.DAO.Constants;
import cuongnguyen.app.com.musicworld.DAO.GetMusicDAO;
import cuongnguyen.app.com.musicworld.R;
import cuongnguyen.app.com.musicworld.model.Song;
import cuongnguyen.app.com.musicworld.services.ServiceMusic;

public class PlayActivity extends AppCompatActivity implements View.OnClickListener{
    Toolbar toolbar;
    GetMusicDAO dao;
    Song song;
    ImageView prev,pause,next,random,repeat,imageAlbum;
    BroadcastReceiver receiver;
    SeekBar seekBar;
    TextView durationTime,currentTime;
    RotateAnimation rotate;
    TextView infoAlbum,infoArtist;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play);
        getControls();
        startAnimation();
        song=dao.getSong(getIntent().getStringExtra("songKey"));
        setSupportActionBar(toolbar);
        toolbar.setTitleTextColor(Color.WHITE);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        if(!isServiceRunning(Constants.ACTION_NOTIFICATION.SERVICE_NAME)){
            Intent intent=new Intent(PlayActivity.this, ServiceMusic.class);
            intent.setAction(Constants.ACTION_NOTIFICATION.STARTFOREGROUND_START);
            intent.putExtra("song",song);
            startService(intent);
        }else{
            Intent intent=new Intent();
            intent.putExtra("song",song);
            intent.setAction(Constants.ACTION.PLAY);
            sendBroadcast(intent);
        }
        IntentFilter intentFilter=new IntentFilter();
        intentFilter.addAction(Constants.ACTION.UPDATE_TIME);
        intentFilter.addAction(Constants.ACTION.UPDATE_UI);
        receiver=new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                if(intent.getAction().equals(Constants.ACTION.UPDATE_TIME)){
                    currentTime.setText(intent.getStringExtra("currentTime"));
                    seekBar.setProgress(intent.getIntExtra("progress",0));
                }else if(intent.getAction().equals(Constants.ACTION.UPDATE_UI)){
                    getSupportActionBar().setTitle(intent.getStringExtra("title"));
                    durationTime.setText(intent.getStringExtra("duration"));
                    infoAlbum.setText(intent.getStringExtra("album"));
                    infoArtist.setText(intent.getStringExtra("artist"));
                }
            }
        };
        registerReceiver(receiver,intentFilter);
    }
    private boolean isServiceRunning(String serviceName){
        boolean serviceRunning = false;
        ActivityManager am = (ActivityManager) this.getSystemService(ACTIVITY_SERVICE);
        List<ActivityManager.RunningServiceInfo> l = am.getRunningServices(50);
        Iterator<ActivityManager.RunningServiceInfo> i = l.iterator();
        while (i.hasNext()) {
            ActivityManager.RunningServiceInfo runningServiceInfo = (ActivityManager.RunningServiceInfo) i
                    .next();
            if(runningServiceInfo.service.getClassName().equals(serviceName)){
                serviceRunning = true;
//                if(runningServiceInfo.foreground)
//                {
//
//                }
            }
        }
        return serviceRunning;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId()==android.R.id.home)finish();
        return super.onOptionsItemSelected(item);
    }
    public void getControls(){
        prev= (ImageView) findViewById(R.id.prev);
        pause= (ImageView) findViewById(R.id.play);
        next= (ImageView) findViewById(R.id.next);
        random= (ImageView) findViewById(R.id.random);
        repeat= (ImageView) findViewById(R.id.repeat);
        seekBar= (SeekBar) findViewById(R.id.seekBarMusic);
        durationTime= (TextView) findViewById(R.id.durationTime);
        currentTime= (TextView) findViewById(R.id.currentTime);
        toolbar= (Toolbar) findViewById(R.id.toolbar);
        imageAlbum= (ImageView) findViewById(R.id.imageAlbum);
        infoAlbum= (TextView) findViewById(R.id.infoAlbum);
        infoArtist= (TextView) findViewById(R.id.infoArtist);
        dao=new GetMusicDAO(this);
        prev.setOnClickListener(this);
        pause.setOnClickListener(this);
        pause.setImageLevel(2);
        next.setOnClickListener(this);
        random.setOnClickListener(this);
        repeat.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch(v.getId()){
            case R.id.prev:
                Toast.makeText(this,"Prev",Toast.LENGTH_SHORT).show();
                break;
            case R.id.play:
                Toast.makeText(this,"Play",Toast.LENGTH_SHORT).show();
                break;
            case R.id.next:
                Toast.makeText(this,"Next",Toast.LENGTH_SHORT).show();
                break;
            case R.id.random:
                Toast.makeText(this,"Random",Toast.LENGTH_SHORT).show();
                break;
            case R.id.repeat:
                Toast.makeText(this,"Repeat",Toast.LENGTH_SHORT).show();
                break;
        }
    }
    public void startAnimation()
    {
        rotate = new RotateAnimation(0, 360, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
        rotate.setDuration(20000);
        rotate.setInterpolator(new LinearInterpolator());
        rotate.setRepeatCount(-1);
        imageAlbum.startAnimation(rotate);
    }
}
