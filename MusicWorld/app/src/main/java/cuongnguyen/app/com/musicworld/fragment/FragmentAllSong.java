package cuongnguyen.app.com.musicworld.fragment;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import java.io.IOException;
import java.util.ArrayList;

import cuongnguyen.app.com.musicworld.DAO.GetMusicDAO;
import cuongnguyen.app.com.musicworld.R;
import cuongnguyen.app.com.musicworld.activity.PlayActivity;
import cuongnguyen.app.com.musicworld.adapter.CustomItemSong;
import cuongnguyen.app.com.musicworld.model.Song;

/**
 * Created by CuongNguyen on 4/6/2017.
 */

public class FragmentAllSong extends Fragment {
    private ListView mListView;
    private CustomItemSong mAdapter;
    private ArrayList<Song> mSongList;
    GetMusicDAO dao;
    MediaPlayer mediaPlayer=new MediaPlayer();
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mSongList=new ArrayList<>();
        dao=new GetMusicDAO(getContext());
        mSongList=dao.getAllSong();
        mAdapter=new CustomItemSong(getContext(),mSongList);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_all_song,container,false);
        mListView= (ListView) view.findViewById(R.id.listSong);
        mListView.setAdapter(mAdapter);
        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent=new Intent(getContext(), PlayActivity.class);
                intent.putExtra("songKey",mSongList.get(position).getKey());
                startActivity(intent);
            }
        });
        return view;
    }
}
