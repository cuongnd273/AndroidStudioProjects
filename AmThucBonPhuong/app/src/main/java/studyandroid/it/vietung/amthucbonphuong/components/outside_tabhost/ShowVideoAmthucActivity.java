package studyandroid.it.vietung.amthucbonphuong.components.outside_tabhost;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import com.google.android.youtube.player.YouTubeBaseActivity;
import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubePlayer;
import com.google.android.youtube.player.YouTubePlayerView;

import studyandroid.it.vietung.amthucbonphuong.R;

public class ShowVideoAmthucActivity extends YouTubeBaseActivity implements YouTubePlayer.OnInitializedListener {

    private final static String API_KEY = "AIzaSyDiPS4nO0R9-op0TZoAqjkyN4j2RCrejJI";
    private  String ID_VIDEO ;
    private YouTubePlayerView view;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_video_amthuc);

        Intent intent = getIntent();

        ID_VIDEO = intent.getStringExtra("id_video_youtube");

        view = (YouTubePlayerView) findViewById(R.id.view);
        view.initialize(API_KEY,this);

    }

    @Override
    public void onInitializationSuccess(YouTubePlayer.Provider provider, YouTubePlayer youTubePlayer, boolean b) {

        youTubePlayer.setPlaybackEventListener(playback);
        youTubePlayer.setPlayerStateChangeListener(playstate);

        if(!b)
        {
            youTubePlayer.cueVideo(ID_VIDEO);
        }
    }

    @Override
    public void onInitializationFailure(YouTubePlayer.Provider provider, YouTubeInitializationResult youTubeInitializationResult) {
        Toast.makeText(ShowVideoAmthucActivity.this,"Fail init",Toast.LENGTH_LONG).show();
    }

    private YouTubePlayer.PlaybackEventListener playback = new YouTubePlayer.PlaybackEventListener() {
        @Override
        public void onPlaying() {

        }

        @Override
        public void onPaused() {

        }

        @Override
        public void onStopped() {

        }

        @Override
        public void onBuffering(boolean b) {

        }

        @Override
        public void onSeekTo(int i) {

        }
    };
    private YouTubePlayer.PlayerStateChangeListener playstate = new YouTubePlayer.PlayerStateChangeListener() {
        @Override
        public void onLoading() {

        }

        @Override
        public void onLoaded(String s) {

        }

        @Override
        public void onAdStarted() {

        }

        @Override
        public void onVideoStarted() {

        }

        @Override
        public void onVideoEnded() {

        }

        @Override
        public void onError(YouTubePlayer.ErrorReason errorReason) {

        }
    };
}
