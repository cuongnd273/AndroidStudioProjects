package com.example.cuong.merychristmas;

import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

public class SongActivity extends AppCompatActivity {

    MediaPlayer song;
    ArrayList loiNhan,tieuDe;
    ArrayList<Integer> baiHat;
    TextView LoiNhan;
    ListView list;

    @Override
    protected void onDestroy() {
        super.onDestroy();
        song.stop();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_song);
        LoadData();
        LoiNhan=(TextView)findViewById(R.id.loiNhan);
        LoiNhan.setText("Chúc mứng giáng sinh an lành,hạnh phúc");
        list=(ListView)findViewById(R.id.listSong);
        ArrayAdapter adapter=new ArrayAdapter(this,android.R.layout.simple_list_item_1,tieuDe);
        list.setAdapter(adapter);
        song=MediaPlayer.create(this,R.raw.jingle_bell);
        song.start();
        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                song.stop();
                song = MediaPlayer.create(getApplicationContext(), baiHat.get(position));
                song.start();
                LoiNhan.setText(loiNhan.get(position).toString());
                HieuUng();
            }
        });
        if(!song.isPlaying())
        {
            song=MediaPlayer.create(this,R.raw.jingle_bell);
            song.start();
        }
    }
    public void LoadData()
    {
        baiHat=new ArrayList();
        loiNhan=new ArrayList();
        tieuDe=new ArrayList();
        baiHat.add(R.raw.camonnguoidaroixatoi);
        tieuDe.add("Cảm ơn người đã rời xa tôi");
        baiHat.add(R.raw.dukhongladinhmenh);
        tieuDe.add("Dù không là định mệnh");
        baiHat.add(R.raw.saulunganhsela);
        tieuDe.add("Sau lưng anh sẽ là");
        baiHat.add(R.raw.vitoiconsong);
        tieuDe.add("Vì tôi còn sống");

        loiNhan.add("Hạnh phúc không phải là một chiếc đồng hồ đắt tiền, hay một chiếc xe hơi sang trọng. Hạnh phúc là tôi được sống vui vẻ bên bạn bè thân yêu của mình. Merry Chrismas!");
        loiNhan.add("Em thật đặc biệt. Em thật tuyệt vời! Chúc Giáng Sinh này của em cũng đặc biệt và tuyệt vời như em vậy.");
        loiNhan.add("Tình Yêu, An Bình và Niềm Vui đã đến trên địa cầu trong lễ Giáng Sinh để làm cho em hạnh phúc và hân hoan. Chúc cho niềm hạnh phúc tràn ngập cuộc đời em!");
        loiNhan.add("Ấm áp không phải khi bạn dùng hai tay xuýt xoa, mà là khi tay ai kia khẽ nắm lấy bàn tay bạn.");
    }
    public void HieuUng()
    {
        Animation animation= AnimationUtils.loadAnimation(getApplicationContext(),R.anim.scale);
        animation.reset();
        LoiNhan.clearAnimation();
        LoiNhan.setAnimation(animation);
    }

}
