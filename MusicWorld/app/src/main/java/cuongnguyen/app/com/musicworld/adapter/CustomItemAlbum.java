package cuongnguyen.app.com.musicworld.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

import cuongnguyen.app.com.musicworld.R;
import cuongnguyen.app.com.musicworld.model.Album;

/**
 * Created by CuongNguyen on 4/6/2017.
 */

public class CustomItemAlbum extends BaseAdapter {
    private Context mContext;
    private List<Album> mAlbumList;

    public CustomItemAlbum(Context mContext, List<Album> mAlbumList) {
        this.mContext = mContext;
        this.mAlbumList = mAlbumList;
    }

    @Override
    public int getCount() {
        return mAlbumList.size();
    }

    @Override
    public Object getItem(int position) {
        return mAlbumList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater= (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view=convertView;
        ViewHoder viewHoder;
        if(view==null){
            view=inflater.inflate(R.layout.item_album,parent,false);
            viewHoder=new ViewHoder();
            viewHoder.title= (TextView) view.findViewById(R.id.titleAlbum);
            view.setTag(viewHoder);
        }
        viewHoder= (ViewHoder) view.getTag();
        viewHoder.title.setText(mAlbumList.get(position).getTitle());
        return view;
    }
    class ViewHoder{
        TextView title;
    }
}
