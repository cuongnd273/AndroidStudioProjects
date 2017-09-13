package studyandroid.it.vietung.amthucbonphuong.adapters;

import android.content.Context;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

import studyandroid.it.vietung.amthucbonphuong.DTO.Foods;
import studyandroid.it.vietung.amthucbonphuong.R;
import studyandroid.it.vietung.amthucbonphuong.components.outside_tabhost.ShowFoodLike;


/**
 * Created by VietUng on 03/01/2016.
 */
public class MonanUathichAdapter extends ArrayAdapter<Foods> {

    Context context;
    int layout;
    ArrayList<Foods> listfoodlikes;

    public MonanUathichAdapter(Context context, int resource, ArrayList<Foods> listfoodlikes) {
        super(context, resource, listfoodlikes);
        this.context = context;
        layout = resource;
        this.listfoodlikes = listfoodlikes;

    }

    class ViewHolder {
        ImageView imageFoodLike;
        TextView nameFoodLike;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {

        LayoutInflater inflater = LayoutInflater.from(context);

        final ViewHolder holder;

        if (convertView == null) {
            holder = new ViewHolder();
            convertView = inflater.inflate(layout, null);
            holder.nameFoodLike = (TextView) convertView.findViewById(R.id.nameFoodLike);
            holder.imageFoodLike = (ImageView) convertView.findViewById(R.id.imageFoodLike);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        holder.nameFoodLike.setText(listfoodlikes.get(position).getNamefood().toString());
        holder.imageFoodLike.setImageBitmap(BitmapFactory.decodeFile(listfoodlikes.get(position).getImagefood().toString()));

        convertView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, ShowFoodLike.class);
                intent.putExtra("namefoodlike", (listfoodlikes.get(position).getNamefood()));
                intent.putExtra("imagefoodlike", listfoodlikes.get(position).getImagefood());
                intent.putExtra("nguyenlieulike", listfoodlikes.get(position).getNguyenlieu());
                intent.putExtra("cachthuchienlike", listfoodlikes.get(position).getCachthuchien());
                context.startActivity(intent);

            }
        });

        return convertView;
    }
}
