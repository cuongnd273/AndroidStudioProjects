package studyandroid.it.vietung.amthucbonphuong.components.outside_tabhost;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;

import com.parse.ParseException;
import com.parse.ParseFile;
import com.parse.ParseObject;
import com.parse.ParseQuery;

import java.util.ArrayList;
import java.util.List;

import studyandroid.it.vietung.amthucbonphuong.DTO.Foods;
import studyandroid.it.vietung.amthucbonphuong.R;
import studyandroid.it.vietung.amthucbonphuong.adapters.ListFoodAdapter;

public class FoodActivity extends Activity {

    private ListView listFood;
    private List<ParseObject> ob;
    private ProgressDialog pDialog;
    private ListFoodAdapter listfoodadapter;
    private ArrayList<Foods> arrFood = null;
    private String idCategory;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_food);

        Intent intent = getIntent();
        idCategory = intent.getStringExtra("idCategory").trim();
        new LoadDatacategory().execute();
    }

    private class LoadDatacategory extends AsyncTask<Void, Void, Void> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            //tạo 1 progressdialog
            pDialog = new ProgressDialog(FoodActivity.this);
            //cài đặt tin nhắn cho progressdialog
            pDialog.setMessage("Loading.........");
            //hiển thị progressdialog
            pDialog.show();

        }

        @Override
        protected Void doInBackground(Void... params) {

            arrFood = new ArrayList<Foods>();

            try {
                ParseQuery<ParseObject> query = new ParseQuery<ParseObject>("cachnauan");
                query.whereEqualTo("idCategory", idCategory.toString());
                ob = query.find();
                for (ParseObject category : ob) {
                    ParseFile imageFood = (ParseFile) category.get("anhmonan");
                    ParseFile textNguyenLieu = (ParseFile) category.get("nguyenlieu");
                    ParseFile textCachThucHien = (ParseFile) category.get("cachthuchien");

                    Foods mapfood = new Foods();
                    mapfood.setNamefood((String) category.get("nameFood"));
                    mapfood.setImagefood(imageFood.getUrl());
                    mapfood.setNguyenlieu(textNguyenLieu.getUrl());
                    mapfood.setCachthuchien(textCachThucHien.getUrl());
                    mapfood.setIdfood(category.getObjectId());
                    arrFood.add(mapfood);
                }
            } catch (ParseException e) {
                Log.e("Error", e.getMessage());
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);

            listFood = (ListView) findViewById(R.id.listFood);
            listfoodadapter = new ListFoodAdapter(FoodActivity.this, R.layout.activity_single_item_food, arrFood);
            listFood.setAdapter(listfoodadapter);
            pDialog.dismiss();
        }
    }

}
