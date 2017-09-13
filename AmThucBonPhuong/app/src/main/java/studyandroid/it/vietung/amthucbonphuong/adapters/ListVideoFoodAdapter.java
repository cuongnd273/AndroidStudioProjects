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

import studyandroid.it.vietung.amthucbonphuong.DTO.VideoFood;
import studyandroid.it.vietung.amthucbonphuong.R;
import studyandroid.it.vietung.amthucbonphuong.components.outside_tabhost.ShowVideoAmthucActivity;

/**
 * Created by VietUng on 19/01/2016.
 */
public class ListVideoFoodAdapter extends ArrayAdapter<VideoFood> {
    private Context context;
    private int layout;
    private ArrayList<VideoFood> listvideo;

    public ListVideoFoodAdapter(Context context, int layout, ArrayList<VideoFood> listvideo) {
        super(context, layout, listvideo);
        this.context = context;
        this.layout = layout;
        this.listvideo = listvideo;
    }

    class ViewHolder {
        ImageView imageVideoFood;
        TextView nameVideoFood;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {

        LayoutInflater inflater = LayoutInflater.from(context);

        final ViewHolder holder;

        if (convertView == null) {
            holder = new ViewHolder();
            convertView = inflater.inflate(layout, null);
            holder.imageVideoFood = (ImageView) convertView.findViewById(R.id.imageVideoFood);
            holder.nameVideoFood = (TextView) convertView.findViewById(R.id.nameVideoFood);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        String url_img = "http://img.youtube.com/vi/"+listvideo.get(position).getVideo_id()+"/default.jpg" ;
        Picasso.with(context).load(url_img).error(R.drawable.temp_img).into(holder.imageVideoFood);
        holder.nameVideoFood.setText(listvideo.get(position).getNamevideo());

        convertView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, ShowVideoAmthucActivity.class);
                intent.putExtra("id_video_youtube", listvideo.get(position).getVideo_id());
                context.startActivity(intent);
            }
        });

        return convertView;
    }
}
