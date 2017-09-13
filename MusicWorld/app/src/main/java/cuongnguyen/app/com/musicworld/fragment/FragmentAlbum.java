package cuongnguyen.app.com.musicworld.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;

import cuongnguyen.app.com.musicworld.DAO.GetMusicDAO;
import cuongnguyen.app.com.musicworld.R;
import cuongnguyen.app.com.musicworld.activity.SongOfAlbumActivity;
import cuongnguyen.app.com.musicworld.adapter.CustomItemAlbum;
import cuongnguyen.app.com.musicworld.model.Album;

/**
 * Created by CuongNguyen on 4/6/2017.
 */

public class FragmentAlbum extends Fragment{
    private ListView mListView;
    private CustomItemAlbum mAdapter;
    private ArrayList<Album> mAlbumList;
    private GetMusicDAO dao;
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mAlbumList=new ArrayList<>();
        dao=new GetMusicDAO(getContext());
        mAlbumList=dao.getAllAlbum();
        mAdapter=new CustomItemAlbum(getContext(),mAlbumList);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_album_song,container,false);
        mListView= (ListView) view.findViewById(R.id.listAlbum);
        mListView.setAdapter(mAdapter);
        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent=new Intent(getContext(), SongOfAlbumActivity.class);
                intent.putExtra("albumTitle",mAlbumList.get(position).getTitle());
                intent.putExtra("albumKey",mAlbumList.get(position).getKey());
                startActivity(intent);
            }
        });
        return view;
    }
}
