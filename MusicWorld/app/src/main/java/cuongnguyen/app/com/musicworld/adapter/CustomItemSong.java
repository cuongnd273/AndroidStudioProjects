package cuongnguyen.app.com.musicworld.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

import cuongnguyen.app.com.musicworld.R;
import cuongnguyen.app.com.musicworld.model.Song;

/**
 * Created by CuongNguyen on 4/6/2017.
 */

public class CustomItemSong extends BaseAdapter {
    private Context mContext;
    private List<Song> mSongList;

    public CustomItemSong(Context mContext, List<Song> mSongList) {
        this.mContext = mContext;
        this.mSongList = mSongList;
    }

    @Override
    public int getCount() {
        return mSongList.size();
    }

    @Override
    public Object getItem(int position) {
        return mSongList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater= (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view=convertView;
        ViewHolder viewHolder;
        if(view==null){
            view=inflater.inflate(R.layout.item_song,parent,false);
            viewHolder=new ViewHolder();
            viewHolder.title= (TextView) view.findViewById(R.id.titleSong);
            view.setTag(viewHolder);
        }
        viewHolder= (ViewHolder) view.getTag();
        viewHolder.title.setText(mSongList.get(position).getTitle());
        return view;
    }
    class ViewHolder{
        TextView title;
    }
}
