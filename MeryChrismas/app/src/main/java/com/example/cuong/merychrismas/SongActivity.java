package com.example.cuong.merychrismas;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

public class SongActivity extends AppCompatActivity implements MediaPlayer.OnCompletionListener {

    boolean status=false;
    RelativeLayout layout;
    MediaPlayer song;
    ArrayList loiNhan,tieuDe;
    ArrayList<Integer> baiHat;
    TextView LoiNhan;
    ListView list;
    ArrayList<Song> listSong=new ArrayList<Song>();
    MyAdapter adapter;
    Integer[] listImage={};
    int songPlayed;
    Handler handler;
    Timer timer;
    int imageNow=0;
    @Override
    protected void onDestroy() {
        super.onDestroy();
        song.stop();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_song);
        layout=(RelativeLayout)findViewById(R.id.songactivity);
        LoadData();
        LoiNhan=(TextView)findViewById(R.id.loiNhan);
        LoiNhan.setText("Chúc mứng giáng sinh an lành,hạnh phúc");
        song=MediaPlayer.create(this,R.raw.jingle_bell);
        song.start();
        list=(ListView)findViewById(R.id.listSong);
        new GetAllSong(this, new GetAllSongFinish() {
            @Override
            public void Finish(ArrayList<Song> list) {
                listSong=list;
            }
        }).execute("http://cuongit.esy.es/getAllSong.php");
        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, final int position, long id) {
                song.stop();
                String link=listSong.get(position).getLink();
                Log.e("LINK", link);
                new LoadSong(SongActivity.this, new LoadSongFinish() {
                    @Override
                    public void Finish(MediaPlayer player) {
                        song=player;
                        song.start();
                        Random r=new Random();
                        LoiNhan.setText(loiNhan.get(r.nextInt(6)).toString());
                        HieuUng();
                    }
                }).execute(link);
            }
        });
        handler=new Handler(){
            @Override
            public void handleMessage(Message msg) {
                super.handleMessage(msg);
                layout.setBackgroundResource(listImage[msg.what]);
            }
        };
        timer=new Timer();
        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                int res;
                if(imageNow<23)
                {
                    res=imageNow+1;
                    imageNow=res;
                }
                else
                {
                    res=0;
                    imageNow=0;
                }
                handler.sendEmptyMessage(res);
            }
        }, 8000, 2000);
    }
    public void LoadData()
    {
        loiNhan=new ArrayList();
        loiNhan.add("Hạnh phúc không phải là một chiếc đồng hồ đắt tiền, hay một chiếc xe hơi sang trọng. Hạnh phúc là tôi được sống vui vẻ bên bạn bè thân yêu của mình. Merry Chrismas!");
        loiNhan.add("Em thật đặc biệt. Em thật tuyệt vời! Chúc Giáng Sinh này của em cũng đặc biệt và tuyệt vời như em vậy.");
        loiNhan.add("Tình Yêu, An Bình và Niềm Vui đã đến trên địa cầu trong lễ Giáng Sinh để làm cho em hạnh phúc và hân hoan. Chúc cho niềm hạnh phúc tràn ngập cuộc đời em!");
        loiNhan.add("Ấm áp không phải khi bạn dùng hai tay xuýt xoa, mà là khi tay ai kia khẽ nắm lấy bàn tay bạn.");
        loiNhan.add("Chúc cho ngày Giáng sinh tràn đầy niềm vui; hạnh phúc vừa đủ và bình yên thật nhiều! Không chỉ là nụ cười mà đôi khi những giọt nước mắt cũng là niềm hạnh phúc; không có tình yêu nào là vĩnh cửu, chỉ có những giây phút vĩnh cửu của tình yêu.");
        loiNhan.add(" Mùa đông lạnh nhưng rất lãng mạn, nắng của mùa đông yếu nhưng đủ làm ấm trái tim một ai đó. Noel là dịp bạn và những người xung quanh tận hưởng những giây phút ngọt ngào của tình yêu thương.");

    }
    public void HieuUng()
    {
        Animation animation= AnimationUtils.loadAnimation(getApplicationContext(),R.anim.scale);
        animation.reset();
        LoiNhan.clearAnimation();
        LoiNhan.setAnimation(animation);
        animation.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                Animation anim=AnimationUtils.loadAnimation(getApplicationContext(),R.anim.alpha);
                animation.reset();
                LoiNhan.clearAnimation();
                LoiNhan.setAnimation(anim);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {
            }
        });
    }

    @Override
    public void onCompletion(MediaPlayer mp) {
            //code chuyen bai hat o day
        }
}
