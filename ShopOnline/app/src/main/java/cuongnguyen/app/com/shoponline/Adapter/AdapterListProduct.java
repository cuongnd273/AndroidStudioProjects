package cuongnguyen.app.com.shoponline.Adapter;

import android.content.Context;
import android.graphics.Paint;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.text.Spanned;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import cuongnguyen.app.com.shoponline.Model.Constant.MyServices;
import cuongnguyen.app.com.shoponline.Model.ObjectClass.Product;
import cuongnguyen.app.com.shoponline.R;

/**
 * Created by CuongNguyen on 07/08/17.
 */

public class AdapterListProduct extends RecyclerView.Adapter<AdapterListProduct.MyViewHolder> {
    private Context context;
    private ArrayList<Product> list;

    public AdapterListProduct(Context context, ArrayList<Product> list) {
        this.context = context;
        this.list = list;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{
        ImageView image;
        TextView name,price_old,price_new;
        public MyViewHolder(View itemView) {
            super(itemView);
            image= (ImageView) itemView.findViewById(R.id.image_product);
            name= (TextView) itemView.findViewById(R.id.name_product);
            price_old= (TextView) itemView.findViewById(R.id.price_old);
            price_new= (TextView) itemView.findViewById(R.id.price_new);
        }
    }
    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater= (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        return new MyViewHolder(inflater.inflate(R.layout.item_list_product,parent,false));
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        Picasso.with(context).load(MyServices.URL+list.get(position).getImageBig()).into(holder.image, new Callback() {
            @Override
            public void onSuccess() {

            }

            @Override
            public void onError() {

            }
        });
        holder.name.setText(list.get(position).getName());
        holder.price_new.setText(list.get(position).getPrice()+" VND");
        holder.price_old.setPaintFlags(holder.price_old.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }
}
