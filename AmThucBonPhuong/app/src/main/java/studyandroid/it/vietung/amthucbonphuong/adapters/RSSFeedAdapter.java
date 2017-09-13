package studyandroid.it.vietung.amthucbonphuong.adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import studyandroid.it.vietung.amthucbonphuong.DTO.ItemNews;
import studyandroid.it.vietung.amthucbonphuong.R;
import studyandroid.it.vietung.amthucbonphuong.components.outside_tabhost.WebviewActivity;

/**
 * Created by VietUng on 20/01/2016.
 */
public class RSSFeedAdapter extends ArrayAdapter<ItemNews> {

    private Context context;
    private int layout;
    private ArrayList<ItemNews> list;

    public RSSFeedAdapter(Context context, int layout, ArrayList<ItemNews> list) {
        super(context, layout, list);

        this.context = context;
        this.layout = layout;
        this.list = list;
    }

    class Holder
    {
        private ImageView img_itemnews;
        private TextView txt_title_itemnews;
        private TextView txt_date_itemnews;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {

        LayoutInflater inflater = LayoutInflater.from(context);
        View view = convertView;

        final Holder holder;

        if (convertView == null) {
            holder = new Holder();
            view = inflater.inflate(layout, null);
            holder.img_itemnews = (ImageView) view.findViewById(R.id.img_itemnews);
            holder.txt_title_itemnews = (TextView) view.findViewById(R.id.txt_title_itemnews);
            holder.txt_date_itemnews = (TextView) view.findViewById(R.id.txt_date_itemnews);
            view.setTag(holder);
        } else {
            holder = (Holder) view.getTag();
        }

        holder.txt_title_itemnews.setText(list.get(position).getTitle());
        holder.txt_date_itemnews.setText(getDate(list.get(position).getPubdate()));
        Picasso.with(context).load(getImage(list.get(position).getDescription())).error(R.drawable.temp_img).into(holder.img_itemnews);

        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, WebviewActivity.class);
                intent.putExtra("link_web",list.get(position).getLink());
                context.startActivity(intent);
            }
        });

        return view;
    }

    private String getImage(String description)
    {
        int src = description.indexOf("src=");
        int start = description.indexOf("\"",src);
        int end = description.indexOf("\"",start+1);
        return description.substring(start+1,end);
    }
    private static String getDate(String description)
    {
        int src = description.indexOf(",");
        return description.substring(src + 2,src + 13);
    }
}
