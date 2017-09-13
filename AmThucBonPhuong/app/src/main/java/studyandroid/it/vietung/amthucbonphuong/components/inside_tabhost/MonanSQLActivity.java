package studyandroid.it.vietung.amthucbonphuong.components.inside_tabhost;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

import studyandroid.it.vietung.amthucbonphuong.DTO.Foods;
import studyandroid.it.vietung.amthucbonphuong.R;
import studyandroid.it.vietung.amthucbonphuong.adapters.MonanSQLAdapter;
import studyandroid.it.vietung.amthucbonphuong.components.outside_tabhost.AddMyfoodsActivity;
import studyandroid.it.vietung.amthucbonphuong.databases.DBAdapter;

public class MonanSQLActivity extends Activity {

    private ImageButton image_addfood;
    private ArrayList<Foods> arrmonanSQL;
    private DBAdapter dba;
    private ListView listsqlFood;
    private MonanSQLAdapter listsqlfoodadapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_monan_sql);

        image_addfood = (ImageButton) findViewById(R.id.image_addfood);
        dba = new DBAdapter(MonanSQLActivity.this);
        arrmonanSQL = new ArrayList<Foods>();
        listsqlFood = (ListView) findViewById(R.id.listsqlFood);

        try {

            getAlldatafoodlike();
        }catch (Exception e)
        {
            Toast.makeText(this, e.toString(), Toast.LENGTH_LONG).show();
        }

        try
        {
            listsqlfoodadapter = new MonanSQLAdapter(this, R.layout.activity_single_item_sqlfood, arrmonanSQL);
            listsqlFood.setAdapter(listsqlfoodadapter);

        } catch (Exception e) {
            Toast.makeText(this, e.toString(), Toast.LENGTH_LONG).show();
        }
        listsqlfoodadapter.notifyDataSetChanged();

        image_addfood.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MonanSQLActivity.this, AddMyfoodsActivity.class);
                startActivity(intent);
            }
        });
    }

    @Override
    protected void onRestart() {
        super.onRestart();

        arrmonanSQL = new ArrayList<Foods>();
        try {

        getAlldatafoodlike();
        }catch (Exception e)
        {
            Toast.makeText(this, e.toString(), Toast.LENGTH_LONG).show();
        }

        try
        {
            listsqlfoodadapter = new MonanSQLAdapter(this, R.layout.activity_single_item_sqlfood, arrmonanSQL);

        } catch (Exception e) {
            Toast.makeText(this, e.toString(), Toast.LENGTH_LONG).show();
        }
        listsqlFood.setAdapter(listsqlfoodadapter);
        listsqlfoodadapter.notifyDataSetChanged();
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    private void getAlldatafoodlike() {
        dba.open();
        Cursor c = dba.getAllFoods(2);
        if (c.moveToFirst()) {
            do {
                arrmonanSQL.add(Foodlike(c));
            } while (c.moveToNext());
        }
        dba.close();
    }

    private Foods Foodlike(Cursor c) {
        Foods food = new Foods();

//        food.setId(c.getInt(c.getColumnIndex(Variable_Global.KEY_ID)));
//        food.setIdfood(c.getString(c.getColumnIndex(Variable_Global.KEY_IDFOOD)));
//        food.setNamefood(c.getString(c.getColumnIndex(Variable_Global.KEY_NAME)));
//        food.setNguyenlieu(c.getString(c.getColumnIndex(Variable_Global.KEY_NGUYENLIEU)));
//        food.setCachthuchien(c.getString(c.getColumnIndex(Variable_Global.KEY_CACHTHUCHIEN)));
//        food.setImagefood(c.getString(c.getColumnIndex(Variable_Global.KEY_IMAGEFOOD)));
        food.setId(c.getInt(0));
        food.setIdfood(c.getString(1));
        food.setNamefood(c.getString(2));
        food.setNguyenlieu(c.getString(3));
        food.setCachthuchien(c.getString(4));
        food.setImagefood(c.getString(5));

        return food;
    }

}
