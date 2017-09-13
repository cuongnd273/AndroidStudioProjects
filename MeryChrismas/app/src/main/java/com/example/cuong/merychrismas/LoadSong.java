package com.example.cuong.merychrismas;

import android.app.Activity;
import android.app.ProgressDialog;
import android.media.MediaPlayer;
import android.os.AsyncTask;

import java.io.IOException;

/**
 * Created by Cuong on 21-12-2015.
 */
public class LoadSong extends AsyncTask<String,Void,MediaPlayer> {
    private Activity activity;
    private ProgressDialog dialog;
    private LoadSongFinish finish;
    public LoadSong(Activity activity,LoadSongFinish finish) {
        super();
        this.activity=activity;
        this.finish=finish;
    }
    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        dialog=new ProgressDialog(activity);
        dialog.setMessage("Đang load bài hát...");
        dialog.show();
    }

    @Override
    protected void onPostExecute(MediaPlayer mediaPlayer) {
        super.onPostExecute(mediaPlayer);
        if(dialog.isShowing())
        {
            dialog.dismiss();
            finish.Finish(mediaPlayer);
        }
    }

    @Override
    protected MediaPlayer doInBackground(String... params) {
        String link=params[0];
        MediaPlayer player=new MediaPlayer();
        try {
            player.setDataSource(link);
            player.prepare();
            return player;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
