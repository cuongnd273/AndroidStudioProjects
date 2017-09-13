package cuongnguyen.app.com.musicworld.services;

import android.app.Notification;
import android.app.PendingIntent;
import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.media.MediaPlayer;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v4.app.NotificationCompat;
import android.util.Log;
import android.widget.RemoteViews;
import android.widget.Toast;

import java.io.IOException;
import java.util.ArrayList;

import cuongnguyen.app.com.musicworld.DAO.Constants;
import cuongnguyen.app.com.musicworld.DAO.GetMusicDAO;
import cuongnguyen.app.com.musicworld.R;
import cuongnguyen.app.com.musicworld.activity.PlayActivity;
import cuongnguyen.app.com.musicworld.model.Song;

import static android.content.ContentValues.TAG;

/**
 * Created by CuongNguyen on 4/8/2017.
 */

public class ServiceMusic extends Service {
    private MediaPlayer mediaPlayer;
    private GetMusicDAO dao;
    private ArrayList<Song> mSongList;
    private int positonSong=0;
    private int currentTime=0;
    private RemoteViews views;
    private BroadcastReceiver receiver;
    PendingIntent pendingIntent;
    private Handler handler;
    private Runnable runnable;
    @Override
    public void onCreate() {
        super.onCreate();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        if(intent.getAction().equals(Constants.ACTION_NOTIFICATION.STARTFOREGROUND_START)){
            Song song= (Song) intent.getSerializableExtra("song");
            mediaPlayer=new MediaPlayer();
            dao=new GetMusicDAO(this);
            mSongList=new ArrayList<>();
            mSongList=dao.getAllSong();
            Intent notifiIntent=new Intent(this, PlayActivity.class);
            notifiIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK|Intent.FLAG_ACTIVITY_CLEAR_TASK);
            pendingIntent=PendingIntent.getActivity(this,0,notifiIntent,0);

            views=new RemoteViews(getPackageName(), R.layout.custom_notification_expanded);
            //Pending play
            Intent intentPlay=new Intent(this,ServiceMusic.class);
            intentPlay.setAction(Constants.ACTION_NOTIFICATION.PLAY);
            PendingIntent pendingPlay=PendingIntent.getService(this,0,intentPlay,0);
            views.setOnClickPendingIntent(R.id.notifiPlay,pendingPlay);

            //Pending prev
            Intent intentPrev=new Intent(this,ServiceMusic.class);
            intentPrev.setAction(Constants.ACTION_NOTIFICATION.PREV);
            PendingIntent pendingPrev=PendingIntent.getService(this,0,intentPrev,0);
            views.setOnClickPendingIntent(R.id.notifiPrev,pendingPrev);

            //Pending next
            Intent intentNext=new Intent(this,ServiceMusic.class);
            intentNext.setAction(Constants.ACTION_NOTIFICATION.NEXT);
            PendingIntent pendingNext=PendingIntent.getService(this,0,intentNext,0);
            views.setOnClickPendingIntent(R.id.notifiNext,pendingNext);

            //Pending Close
            Intent intentClose=new Intent(this,ServiceMusic.class);
            intentClose.setAction(Constants.ACTION_NOTIFICATION.CLOSE);
            PendingIntent pendingClose=PendingIntent.getService(this,0,intentClose,0);
            views.setOnClickPendingIntent(R.id.close,pendingClose);

//            Notification notification=new NotificationCompat.Builder(this)
//                    .setCustomBigContentView(views)
//                    .setOngoing(true)
//                    .setSmallIcon(R.mipmap.ic_launcher)
//                    .setContentIntent(pendingIntent)
//                    .build();

            play(song);
            IntentFilter intentFilter=new IntentFilter();
            intentFilter.addAction(Constants.ACTION.PLAY);
            receiver=new BroadcastReceiver() {
                @Override
                public void onReceive(Context context, Intent intent) {
                    if(intent.getAction().equals(Constants.ACTION.PLAY)){
                        play((Song) intent.getSerializableExtra("song"));
                    }
                }
            };
            registerReceiver(receiver,intentFilter);

        }else if(intent.getAction().equals(Constants.ACTION_NOTIFICATION.PLAY)){

        }else if(intent.getAction().equals(Constants.ACTION_NOTIFICATION.PREV)){
            Log.i("Tag", "Prev");
            prev();
        }else if(intent.getAction().equals(Constants.ACTION_NOTIFICATION.NEXT)){
            Log.i("Tag", "Next");
            next();
        }else if(intent.getAction().equals(Constants.ACTION_NOTIFICATION.CLOSE)){
            stopForeground(true);
            stopSelf();
        }
        return START_STICKY;
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
    public void next(){
        if(positonSong<mSongList.size()){
            play(mSongList.get(positonSong+1));
        }else{
            play(mSongList.get(0));
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if(mediaPlayer!=null){
            mediaPlayer.stop();
            mediaPlayer.reset();
        }
        if(handler!=null)handler.sendEmptyMessage(0);
        if(runnable!=null)runnable=null;
    }

    public void prev(){
        if(positonSong>0){
            play(mSongList.get(positonSong-1));
        }else{
            play(mSongList.get(mSongList.size()-1));
        }
    }
    public void play(Song song){
        Log.i("Tag", "Play");
        if(mediaPlayer.isPlaying()){
            mediaPlayer.stop();
            mediaPlayer.reset();
        }
        positonSong=getPositonSong(song);
        views.setTextViewText(R.id.notifiTitle,song.getTitle());
        Notification notification=new NotificationCompat.Builder(this)
                .setCustomBigContentView(views)
                .setOngoing(true)
                .setSmallIcon(R.mipmap.ic_launcher)
                .setContentIntent(pendingIntent)
                .build();
        startForeground(101,notification);
        try {
            mediaPlayer.setDataSource(song.getPath());
            mediaPlayer.prepare();
            mediaPlayer.start();
            updateUI(song);
        } catch (IOException e) {
            e.printStackTrace();
        }
        handler=new Handler();
        runnable=new Runnable() {
            @Override
            public void run() {
                Intent intent=new Intent();
                intent.setAction(Constants.ACTION.UPDATE_TIME);
                intent.putExtra("currentTime",milliSecondsToTimer(mediaPlayer.getCurrentPosition()));
                intent.putExtra("progress",getProgressMp3(mediaPlayer.getCurrentPosition(),mediaPlayer.getDuration()));
                sendBroadcast(intent);
                handler.postDelayed(runnable,500);
            }
        };
        handler.post(runnable);
    }
    public void pause(){
        if(mediaPlayer.isPlaying()){
            currentTime=mediaPlayer.getCurrentPosition();
            mediaPlayer.pause();
        }else{
            mediaPlayer.seekTo(currentTime);
            mediaPlayer.start();
        }
    }
    public void setProgress(int progress){
        int time=mediaPlayer.getDuration()*progress/100;
        mediaPlayer.seekTo(progress);
    }
    public  int getProgressMp3(long currentTime,long durationTime)
    {
        int progress;
        double current=(int)currentTime/1000;
        double duration=(int)durationTime/1000;
        progress= (int) (current*100/duration);
        return progress;
    }
    public String milliSecondsToTimer(long milliseconds) {
        String finalTimerString = "";
        String secondsString = "";

        int hours = (int) (milliseconds / (1000 * 60 * 60));
        int minutes = (int) (milliseconds % (1000 * 60 * 60)) / (1000 * 60);
        int seconds = (int) ((milliseconds % (1000 * 60 * 60)) % (1000 * 60) / 1000);
        // Add hours if there
        if (hours > 0) {
            finalTimerString = hours + ":";
        }

        if (seconds < 10) {
            secondsString = "0" + seconds;
        } else {
            secondsString = "" + seconds;
        }

        finalTimerString = finalTimerString + minutes + ":" + secondsString;

        return finalTimerString;
    }
    public int getPositonSong(Song song){
        for(int i=0;i<mSongList.size();i++){
            if(mSongList.get(i).getKey().equals(song.getKey()))
                return i;
        }
        return 0;
    }
    public void updateUI(Song song){
        Intent intent=new Intent();
        intent.setAction(Constants.ACTION.UPDATE_UI);
        intent.putExtra("album",song.getAlbum());
        intent.putExtra("artist",song.getArtist());
        intent.putExtra("title",song.getTitle());
        intent.putExtra("duration",milliSecondsToTimer(mediaPlayer.getDuration()));
        sendBroadcast(intent);
    }
}
