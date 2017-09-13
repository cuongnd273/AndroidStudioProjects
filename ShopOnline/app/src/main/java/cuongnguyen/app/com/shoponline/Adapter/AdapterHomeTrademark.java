package cuongnguyen.app.com.shoponline.Adapter;

import android.content.Context;
import android.content.Intent;
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

import cuongnguyen.app.com.shoponline.Model.Constant.MyServices;
import cuongnguyen.app.com.shoponline.Model.ObjectClass.Trademark;
import cuongnguyen.app.com.shoponline.R;
import cuongnguyen.app.com.shoponline.View.ListProduct.ListProductActivity;

/**
 * Created by CuongNguyen on 06/14/17.
 */

public class AdapterHomeTrademark extends RecyclerView.Adapter<AdapterHomeTrademark.MyViewHolder> {
    private Context context;
    private ArrayList<Trademark> list;
    public class MyViewHolder extends RecyclerView.ViewHolder {
        public ImageView image;
        public TextView name;
        public ProgressBar progressBar;
        public MyViewHolder(View itemView) {
            super(itemView);
            image= (ImageView) itemView.findViewById(R.id.image_trademark);
            name=(TextView)itemView.findViewById(R.id.name_trademark);
            progressBar= (ProgressBar) itemView.findViewById(R.id.progress_download);
        }
    }

    public AdapterHomeTrademark(Context context, ArrayList<Trademark> list) {
        this.context = context;
        this.list = list;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater= (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view=inflater.inflate(R.layout.item_home_trademark,parent,false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, final int position) {
        Picasso.with(context).load(MyServices.URL+list.get(position).getImage()).into(holder.image, new Callback() {
            @Override
            public void onSuccess() {
                holder.progressBar.setVisibility(View.INVISIBLE);
            }

            @Override
            public void onError() {
                holder.progressBar.setVisibility(View.VISIBLE);
            }
        });
        holder.name.setText(list.get(position).getName());
        holder.image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(context, ListProductActivity.class);
                intent.putExtra("name",list.get(position).getName());
                intent.putExtra("type",1);
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }
}
