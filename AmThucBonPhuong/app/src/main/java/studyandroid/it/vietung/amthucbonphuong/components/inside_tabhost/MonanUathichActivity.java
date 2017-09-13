package studyandroid.it.vietung.amthucbonphuong.components.inside_tabhost;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

import studyandroid.it.vietung.amthucbonphuong.DTO.Foods;
import studyandroid.it.vietung.amthucbonphuong.R;
import studyandroid.it.vietung.amthucbonphuong.adapters.MonanUathichAdapter;
import studyandroid.it.vietung.amthucbonphuong.components.outside_tabhost.ShowFoodLike;
import studyandroid.it.vietung.amthucbonphuong.databases.DBAdapter;

public class MonanUathichActivity extends Activity {

    private MonanUathichAdapter listfoodadapterlike;
    private ArrayList<Foods> arrFoodlike = null;
    private ListView listFoodLike;
    private DBAdapter dba;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_monan_uathich);

        dba = new DBAdapter(MonanUathichActivity.this);
        arrFoodlike = new ArrayList<Foods>();
        listFoodLike = (ListView) findViewById(R.id.listFoodLike);


        getAlldatafoodlike();
        try
        {
            listfoodadapterlike = new MonanUathichAdapter(this, R.layout.activity_single_item_foodfavourite, arrFoodlike);

        } catch (Exception e) {
            Toast.makeText(this, e.toString(), Toast.LENGTH_LONG).show();
        }
        listFoodLike.setAdapter(listfoodadapterlike);
        listfoodadapterlike.notifyDataSetChanged();


        listFoodLike.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(MonanUathichActivity.this, ShowFoodLike.class);
                intent.putExtra("namefoodlike", (arrFoodlike.get(position).getNamefood()));
                intent.putExtra("imagefoodlike", arrFoodlike.get(position).getImagefood());
                intent.putExtra("nguyenlieulike", arrFoodlike.get(position).getNguyenlieu());
                intent.putExtra("cachthuchienlike", arrFoodlike.get(position).getCachthuchien());
                startActivity(intent);
            }
        });


        listFoodLike.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, final int position, long id) {

                dba.open();

                AlertDialog.Builder b = new AlertDialog.Builder(MonanUathichActivity.this);
                b.setTitle("Question");
                b.setMessage("Ban that su muon xoa du lieu nay ?" + "\n '" + arrFoodlike.get(position).getIdfood() + "'");
                b.setPositiveButton("yes", new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface arg0, int arg1) {

                        dba.deleteFood_favourite(arrFoodlike.get(position).getIdfood().trim());
                        arrFoodlike.remove(position);

                        listfoodadapterlike.notifyDataSetChanged();
                    }
                });

                b.setNegativeButton("no", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface arg0, int arg1) {
                        arg0.cancel();
                    }
                });
                b.create().show();

                return false;
            }
        });

        dba.close();
    }

    @Override
    protected void onRestart() {
        super.onRestart();

        arrFoodlike = new ArrayList<Foods>();
        getAlldatafoodlike();
        try
        {
            listfoodadapterlike = new MonanUathichAdapter(this, R.layout.activity_single_item_foodfavourite, arrFoodlike);

        } catch (Exception e) {
            Toast.makeText(this, e.toString(), Toast.LENGTH_LONG).show();
        }
        listFoodLike.setAdapter(listfoodadapterlike);
        listfoodadapterlike.notifyDataSetChanged();
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    private void getAlldatafoodlike() {
        dba.open();
        Cursor c = dba.getAllFoods(1);
        if (c.moveToFirst()) {
            do {
                arrFoodlike.add(Foodlike(c));
            } while (c.moveToNext());
        }
        dba.close();
    }

    private Foods Foodlike(Cursor c) {
        Foods food = new Foods();

        food.setId(c.getInt(0));
        food.setIdfood(c.getString(1));
        food.setNamefood(c.getString(2));
        food.setNguyenlieu(c.getString(3));
        food.setCachthuchien(c.getString(4));
        food.setImagefood(c.getString(5));

        return food;

    }


}
