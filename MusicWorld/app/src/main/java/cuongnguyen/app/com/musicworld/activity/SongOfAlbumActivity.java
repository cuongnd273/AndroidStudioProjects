package cuongnguyen.app.com.musicworld.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;

import cuongnguyen.app.com.musicworld.DAO.GetMusicDAO;
import cuongnguyen.app.com.musicworld.R;
import cuongnguyen.app.com.musicworld.adapter.CustomItemSong;
import cuongnguyen.app.com.musicworld.model.Song;

import static java.security.AccessController.getContext;

public class SongOfAlbumActivity extends AppCompatActivity {
    ListView mListView;
    ArrayList<Song> mSongList;
    CustomItemSong mAdapter;
    GetMusicDAO dao;
    Toolbar toolbar;
    String albumTitle,albumKey;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_song_of_album);
        Intent intent=getIntent();
        albumTitle=intent.getStringExtra("albumTitle");
        albumKey=intent.getStringExtra("albumKey");
        getControls();
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    void getControls(){
        mListView= (ListView) findViewById(R.id.listSong);
        toolbar= (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle(albumTitle);
        setSupportActionBar(toolbar);
        mSongList=new ArrayList<>();
        dao=new GetMusicDAO(this);
        mSongList=dao.getSongOfAlbum(albumKey);
        mAdapter=new CustomItemSong(this,mSongList);
        mListView.setAdapter(mAdapter);
        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent=new Intent(SongOfAlbumActivity.this, PlayActivity.class);
                intent.putExtra("songKey",mSongList.get(position).getKey());
                startActivity(intent);
            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId()==android.R.id.home)finish();
        return super.onOptionsItemSelected(item);
    }
}
