package free.app.com.moviebooking.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import free.app.com.moviebooking.R;
import free.app.com.moviebooking.model.Phim;

/**
 * Created by CuongNguyen on 08/17/17.
 */

public class AdapterFilm extends RecyclerView.Adapter<AdapterFilm.ViewHolder> {
    private ArrayList<Phim> list;
    private Context context;
    private ClickListener clickListener;
    public AdapterFilm(ArrayList<Phim> list, Context context, ClickListener clickListener) {
        this.list = list;
        this.context = context;
        this.clickListener=clickListener;
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        ImageView image;
        TextView name,start;
        ProgressBar progressBar;
        public ViewHolder(View itemView) {
            super(itemView);
            image= (ImageView) itemView.findViewById(R.id.image_film);
            name= (TextView) itemView.findViewById(R.id.name_film);
            start= (TextView) itemView.findViewById(R.id.start);
            progressBar= (ProgressBar) itemView.findViewById(R.id.progress);
        }
    }
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater= (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view=inflater.inflate(R.layout.item_phim,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        Picasso.with(context).load(list.get(position).getAnh()).into(holder.image, new Callback() {
            @Override
            public void onSuccess() {
                holder.progressBar.setVisibility(View.INVISIBLE);
            }

            @Override
            public void onError() {
                holder.progressBar.setVisibility(View.VISIBLE);
            }
        });
        holder.name.setText(list.get(position).getTenphim());
        holder.start.setText(list.get(position).getNgaybatdau());
        holder.image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clickListener.imageClick(v,position);
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }
    public interface ClickListener{
        public void imageClick(View v,int position);
    }
}
