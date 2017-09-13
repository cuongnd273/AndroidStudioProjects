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
import studyandroid.it.vietung.amthucbonphuong.components.outside_tabhost.ShowFoodSqlActivity;

/**
 * Created by VietUng on 19/01/2016.
 */
public class MonanSQLAdapter extends ArrayAdapter<Foods> {
    Context context;
    int layout;
    ArrayList<Foods> listsqlfood;

    public MonanSQLAdapter(Context context, int layout, ArrayList<Foods> listsqlfood) {
        super(context, layout, listsqlfood);

        this.context = context;
        this.layout = layout;
        this.listsqlfood = listsqlfood;
    }

    class ViewHolder {
        ImageView imagesqlFood;
        TextView namesqlFood;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {

        LayoutInflater inflater = LayoutInflater.from(context);

        final ViewHolder holder;

        if (convertView == null) {
            holder = new ViewHolder();
            convertView = inflater.inflate(layout, null);
            holder.namesqlFood = (TextView) convertView.findViewById(R.id.namesqlFood);
            holder.imagesqlFood = (ImageView) convertView.findViewById(R.id.imagesqlFood);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        holder.namesqlFood.setText(listsqlfood.get(position).getNamefood().toString());
        holder.imagesqlFood.setImageBitmap(BitmapFactory.decodeFile(listsqlfood.get(position).getImagefood().toString()));

        convertView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, ShowFoodSqlActivity.class);
                intent.putExtra("namesqlfood", (listsqlfood.get(position).getNamefood()));
                intent.putExtra("imagesqlfood", listsqlfood.get(position).getImagefood());
                intent.putExtra("nguyenlieusql", listsqlfood.get(position).getNguyenlieu());
                intent.putExtra("cachthuchiensql", listsqlfood.get(position).getCachthuchien());
                context.startActivity(intent);

            }
        });
        return convertView;
    }
}
