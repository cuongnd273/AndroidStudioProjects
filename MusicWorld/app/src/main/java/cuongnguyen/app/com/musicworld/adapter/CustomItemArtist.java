package cuongnguyen.app.com.musicworld.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

import cuongnguyen.app.com.musicworld.R;
import cuongnguyen.app.com.musicworld.model.Artist;

/**
 * Created by CuongNguyen on 4/6/2017.
 */

public class CustomItemArtist extends BaseAdapter {
    private Context mContext;
    private List<Artist> mArtistList;

    public CustomItemArtist(Context mContext, List<Artist> mArtistList) {
        this.mContext = mContext;
        this.mArtistList = mArtistList;
    }

    @Override
    public int getCount() {
        return mArtistList.size();
    }

    @Override
    public Object getItem(int position) {
        return mArtistList.get(position);
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
            view=inflater.inflate(R.layout.item_artist,parent,false);
            viewHolder=new ViewHolder();
            viewHolder.title= (TextView) view.findViewById(R.id.titleArtist);
            view.setTag(viewHolder);
        }
        viewHolder= (ViewHolder) view.getTag();
        viewHolder.title.setText(mArtistList.get(position).getTitle());
        return view;
    }
    class ViewHolder{
        TextView title;
    }
}
