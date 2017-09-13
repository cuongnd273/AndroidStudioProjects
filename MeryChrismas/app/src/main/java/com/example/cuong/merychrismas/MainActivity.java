package com.example.cuong.merychrismas;


import android.content.Context;
import android.content.Intent;
import android.media.MediaPlayer;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private static final int TIME_INTERVAL = 2000; // # milliseconds, desired time passed between two back presses.
    private long mBackPressed;
    ImageView image;
    MediaPlayer player;

    @Override
    protected void onResume() {
        super.onResume();
        if(!player.isPlaying())
        {
            player =MediaPlayer.create(this,R.raw.jingle_bell);
            player.start();
        }
    }

    @Override
    public void onBackPressed() {
        if (mBackPressed + TIME_INTERVAL > System.currentTimeMillis()) {
            player.stop();
            super.onBackPressed();
            return;
        } else {
            Toast.makeText(getBaseContext(), "Tap back button in order to exit", Toast.LENGTH_SHORT).show();
        }
        mBackPressed = System.currentTimeMillis();
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        image = (ImageView) findViewById(R.id.imageView);
        player =MediaPlayer.create(this,R.raw.jingle_bell);
        player.start();
        Toast.makeText(this,"Nhấn vào hình ảnh kìa",Toast.LENGTH_LONG).show();
        if (!player.isPlaying()) {
            player.start();
        }
        image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                player.stop();
                if(isNetworkAvailable(MainActivity.this))
                {
                    Intent intent = new Intent(MainActivity.this, SongActivity.class);
                    startActivity(intent);
                }
                else
                {
                    Toast.makeText(MainActivity.this,"Bật mạng lên đã, không sao chạy được",Toast.LENGTH_SHORT).show();
                }
            }
        });
        Animation animation = AnimationUtils.loadAnimation(this, R.anim.alpha);
        animation.reset();
        image.clearAnimation();
        image.setAnimation(animation);
    }
    public boolean isNetworkAvailable(final Context context) {
        final ConnectivityManager connectivityManager = ((ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE));
        return connectivityManager.getActiveNetworkInfo() != null && connectivityManager.getActiveNetworkInfo().isConnected();
    }
}
