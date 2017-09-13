package studyandroid.it.vietung.amthucbonphuong.adapters;

import android.app.Activity;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import java.io.File;
import java.util.ArrayList;

import studyandroid.it.vietung.amthucbonphuong.DTO.Foods;
import studyandroid.it.vietung.amthucbonphuong.R;
import studyandroid.it.vietung.amthucbonphuong.components.outside_tabhost.ShowFoodActivity;
import studyandroid.it.vietung.amthucbonphuong.databases.DBAdapter;
import studyandroid.it.vietung.amthucbonphuong.databases.Variable_Global;
import studyandroid.it.vietung.amthucbonphuong.loadfiles.LoadData;

/**
 * Created by VietUng on 31/12/2015.
 */
public class ListFoodAdapter extends ArrayAdapter<Foods> {

    Activity context;
    int layout;
    ArrayList<Foods> listfood;
    private File directory;
    private DBAdapter dba;

    public ListFoodAdapter(Activity context, int resource, ArrayList<Foods> objects) {
        super(context, resource, objects);
        this.context = context;
        layout = resource;
        listfood = objects;
    }

    class ViewHolder {
        ImageView imageFood;
        TextView nameFood;
        ImageButton img_favourite;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {

        LayoutInflater inflater = LayoutInflater.from(context);

        final ViewHolder holder;

        if (convertView == null) {
            holder = new ViewHolder();
            convertView = inflater.inflate(layout, null);
            holder.imageFood = (ImageView) convertView.findViewById(R.id.imageFood);
            holder.nameFood = (TextView) convertView.findViewById(R.id.nameFood);
            holder.img_favourite = (ImageButton) convertView.findViewById(R.id.img_favourite);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        Picasso.with(context).load(listfood.get(position).getImagefood()).error(R.drawable.temp_img).into(holder.imageFood);
        holder.nameFood.setText(listfood.get(position).getNamefood());
        directory = Variable_Global.getDirectory();

        convertView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, ShowFoodActivity.class);
                intent.putExtra("namefood", (listfood.get(position).getNamefood()));
                intent.putExtra("imagefood", listfood.get(position).getImagefood());
                intent.putExtra("nguyenlieu", listfood.get(position).getNguyenlieu());
                intent.putExtra("cachthuchien", listfood.get(position).getCachthuchien());
                context.startActivity(intent);

            }
        });


        holder.img_favourite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                String idfood = listfood.get(position).getIdfood().trim();
                String namefood = listfood.get(position).getNamefood().trim();
                String nguyenlieu = directory + "/" + listfood.get(position).getIdfood().trim() + "_nguyenlieu.db";
                String cachthuchien = directory + "/" + listfood.get(position).getIdfood().trim() + "_cachthuchien.db";
                String imagefood = directory + "/" + listfood.get(position).getIdfood().trim() + ".jpg";


                dba = new DBAdapter(context);
                dba.open();


                long id = dba.insertFood( idfood, namefood, nguyenlieu, cachthuchien, imagefood, 1);
                if (id == -1) {
                    Toast.makeText(context, "Xin lỗi!!! Thêm món ăn thất bại.", Toast.LENGTH_LONG).show();
                    dba.close();
                } else {
                    dba.close();
                    Toast.makeText(context, "đã thêm món ăn vào mục ưa thích của bạn !!!", Toast.LENGTH_LONG).show();
                    LoadData load = new LoadData();
                    load.downloadFile_Server(listfood.get(position).getImagefood(), new File(directory, listfood.get(position).getIdfood().trim() + ".jpg"));
                    load.downloadFile_Server(listfood.get(position).getNguyenlieu(), new File(directory, listfood.get(position).getIdfood().trim() + "_nguyenlieu.db"));
                    load.downloadFile_Server(listfood.get(position).getCachthuchien(), new File(directory, listfood.get(position).getIdfood().trim() + "_cachthuchien.db"));
                }


            }
        });

        return convertView;
    }
}
