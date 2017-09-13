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

import studyandroid.it.vietung.amthucbonphuong.DTO.Category;
import studyandroid.it.vietung.amthucbonphuong.R;
import studyandroid.it.vietung.amthucbonphuong.components.outside_tabhost.FoodActivity;


/**
 * Created by VietUng on 30/12/2015.
 */
public class ListFoodCategoryAdapter extends ArrayAdapter<Category> {

    private Context context;
    private int layout;
    private ArrayList<Category> arrCategory;

    public ListFoodCategoryAdapter(Context context, int resource, ArrayList<Category> objects) {
        super(context, resource, objects);

        this.context = context;
        layout = resource;
        arrCategory = objects;
    }

    class ViewHolder {
        ImageView imageFoodCategory;
        TextView nameFoodCategory;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {

        LayoutInflater inflater = LayoutInflater.from(context);

        final ViewHolder holder;

        if (convertView == null) {
            holder = new ViewHolder();
            convertView = inflater.inflate(layout, null);
            holder.imageFoodCategory = (ImageView) convertView.findViewById(R.id.imageFoodCategory);
            holder.nameFoodCategory = (TextView) convertView.findViewById(R.id.nameFoodCategory);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        Picasso.with(context).load(arrCategory.get(position).getImageFoodCategory()).error(R.drawable.temp_img).into(holder.imageFoodCategory);
        holder.nameFoodCategory.setText(arrCategory.get(position).getNameFoodCategoy());

        convertView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context,FoodActivity.class);
                intent.putExtra("idCategory",arrCategory.get(position).getIdCategory());
                context.startActivity(intent);
            }
        });
        return convertView;
    }
}
