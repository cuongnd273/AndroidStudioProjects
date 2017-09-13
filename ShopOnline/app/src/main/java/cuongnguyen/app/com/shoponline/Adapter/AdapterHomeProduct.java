package cuongnguyen.app.com.shoponline.Adapter;

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

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.ArrayList;

import cuongnguyen.app.com.shoponline.Model.Constant.MyServices;
import cuongnguyen.app.com.shoponline.Model.ObjectClass.Product;
import cuongnguyen.app.com.shoponline.R;

/**
 * Created by CuongNguyen on 06/14/17.
 */

public class AdapterHomeProduct extends RecyclerView.Adapter<AdapterHomeProduct.MyViewHolder> {
    private Context context;
    private ArrayList<Product> list;
    public class MyViewHolder extends RecyclerView.ViewHolder{
        public TextView name,price;
        public ImageView image;
        public ProgressBar progressBar;
        public MyViewHolder(View itemView) {
            super(itemView);
            image= (ImageView) itemView.findViewById(R.id.image_product);
            name= (TextView) itemView.findViewById(R.id.name_product);
            price= (TextView) itemView.findViewById(R.id.price_product);
            progressBar= (ProgressBar) itemView.findViewById(R.id.progress_download_product);
        }
    }

    public AdapterHomeProduct(Context context, ArrayList<Product> list) {
        this.context = context;
        this.list = list;
    }

    @Override
    public AdapterHomeProduct.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater= (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view=inflater.inflate(R.layout.item_home_product,parent,false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final AdapterHomeProduct.MyViewHolder holder, int position) {
        Picasso.with(context).load(MyServices.URL+list.get(position).getImageBig()).into(holder.image, new Callback() {
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
        NumberFormat numberFormat=new DecimalFormat("###,###");
        holder.price.setText(numberFormat.format(Long.parseLong(list.get(position).getPrice())));
    }

    @Override
    public int getItemCount() {
        return list.size();
    }
}
