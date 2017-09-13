package cuongnguyen.app.com.musicworld.activity;
import android.support.design.widget.TabLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v4.view.ViewPager;
import android.os.Bundle;

import cuongnguyen.app.com.musicworld.R;
import cuongnguyen.app.com.musicworld.adapter.ViewPagerCustom;
import cuongnguyen.app.com.musicworld.fragment.FragmentAlbum;
import cuongnguyen.app.com.musicworld.fragment.FragmentAllSong;
import cuongnguyen.app.com.musicworld.fragment.FragmentArtist;


public class MainActivity extends AppCompatActivity {
    private ViewPager mViewPager;
    private TabLayout tabLayout;
    private ViewPagerCustom viewPagerCustom;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tabLayout= (TabLayout) findViewById(R.id.tabs);
        mViewPager= (ViewPager) findViewById(R.id.container);
        viewPagerCustom=new ViewPagerCustom(getSupportFragmentManager());
        viewPagerCustom.add(new FragmentAllSong(),"All");
        viewPagerCustom.add(new FragmentAlbum(),"Album");
        viewPagerCustom.add(new FragmentArtist(),"Artist");
        mViewPager.setAdapter(viewPagerCustom);
        tabLayout.setupWithViewPager(mViewPager);
        tabLayout.getTabAt(0).setIcon(R.drawable.song_icon_tab);
        tabLayout.getTabAt(1).setIcon(R.drawable.album_song_tab);
        tabLayout.getTabAt(2).setIcon(R.drawable.artist_song_tab);
    }
}
