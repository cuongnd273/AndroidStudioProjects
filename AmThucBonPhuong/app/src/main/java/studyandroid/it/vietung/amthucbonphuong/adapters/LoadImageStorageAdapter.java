package studyandroid.it.vietung.amthucbonphuong.adapters;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import studyandroid.it.vietung.amthucbonphuong.DTO.ImageMyFood_info;
import studyandroid.it.vietung.amthucbonphuong.R;
import studyandroid.it.vietung.amthucbonphuong.components.outside_tabhost.AddMyfoodsActivity;


/**
 * Created by VietUng on 03/01/2016.
 */
public class LoadImageStorageAdapter extends ArrayAdapter<ImageMyFood_info> {

    Activity context;
    int layout;
    List<ImageMyFood_info> objects;

    public LoadImageStorageAdapter(Activity context, int layout, List<ImageMyFood_info> objects) {
        super(context, layout, objects);
        this.context = context;
        this.layout = layout;
        this.objects = objects;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {

        View v = convertView;

        if (v == null) {
            v = LayoutInflater.from(context).inflate(layout, null);
        }

        ImageView hinhanh = (ImageView) v.findViewById(R.id.img_info);
        TextView txt_hinhanh = (TextView) v.findViewById(R.id.txt_nameimg);

        //final ImageMyFood_info image = objects.get(position);

        Bitmap bm = BitmapFactory.decodeFile(objects.get(position).getImg_path());

        hinhanh.setImageBitmap(bm);
        txt_hinhanh.setText(objects.get(position).getImg_name());

        v.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                Intent intent = new Intent(context, AddMyfoodsActivity.class);
                intent.putExtra("myfood_img", objects.get(position).getImg_path());
                context.startActivity(intent);
                context.finish();
            }
        });

        return v;
    }
}