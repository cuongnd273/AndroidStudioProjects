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
import cuongnguyen.app.com.musicworld.activity.SongOfArtistActivity;
import cuongnguyen.app.com.musicworld.adapter.CustomItemArtist;
import cuongnguyen.app.com.musicworld.model.Artist;

/**
 * Created by CuongNguyen on 4/6/2017.
 */

public class FragmentArtist extends Fragment {
    private ListView mListView;
    private CustomItemArtist mAdapter;
    private ArrayList<Artist> mArtistList;
    private GetMusicDAO dao;
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mArtistList=new ArrayList<>();
        dao=new GetMusicDAO(getContext());
        mArtistList=dao.getAllArtist();
        mAdapter=new CustomItemArtist(getContext(),mArtistList);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_artist_song,container,false);
        mListView= (ListView) view.findViewById(R.id.listArtist);
        mListView.setAdapter(mAdapter);
        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent=new Intent(getContext(), SongOfArtistActivity.class);
                intent.putExtra("artistTitle",mArtistList.get(position).getTitle());
                intent.putExtra("artistKey",mArtistList.get(position).getKey());
                startActivity(intent);
            }
        });
        return view;
    }
}
