package studyandroid.it.vietung.amthucbonphuong.components.inside_tabhost;

import android.app.Activity;
import android.app.ProgressDialog;
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

import studyandroid.it.vietung.amthucbonphuong.DTO.Category;
import studyandroid.it.vietung.amthucbonphuong.R;
import studyandroid.it.vietung.amthucbonphuong.adapters.ListFoodCategoryAdapter;

public class FoodCategoryActivity extends Activity {

    private ListView listCategory;
    private List<ParseObject> ob;
    private ProgressDialog pDialog;
    private ListFoodCategoryAdapter listfoodadapter;
    private ArrayList<Category> arrCategory = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_food_category);

        /////////////////////////////////////////////
        new LoadDatacategory().execute();

    }

    private class LoadDatacategory extends AsyncTask<Void,Void,Void>
    {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            //tạo 1 progressdialog
            pDialog = new ProgressDialog(FoodCategoryActivity.this);
            //cài đặt tin nhắn cho progressdialog
            pDialog.setMessage("Loading.........");
            //hiển thị progressdialog
            pDialog.show();

        }

        @Override
        protected Void doInBackground(Void... params) {

            arrCategory = new ArrayList<Category>();

            try {
                ParseQuery<ParseObject> query = new ParseQuery<ParseObject>("foodcategory");
                ob = query.find();
                for (ParseObject category : ob)
                {
                    ParseFile imageCategory = (ParseFile) category.get("imageFoodCategory");

                    Category mapcategory = new Category();
                    mapcategory.setNameFoodCategoy((String) category.get("nameFoodCategory"));
                    mapcategory.setImageFoodCategory(imageCategory.getUrl());
                    mapcategory.setIdCategory(category.getObjectId());
                    arrCategory.add(mapcategory);
                }
            }
            catch (ParseException e)
            {
                Log.e("Error", e.getMessage());
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);

            listCategory = (ListView) findViewById(R.id.listCategory);
            listfoodadapter = new ListFoodCategoryAdapter(FoodCategoryActivity.this,R.layout.activity_single_item_foodcategory,arrCategory);
            listCategory.setAdapter(listfoodadapter);

            pDialog.dismiss();
        }
    }

    @Override
    protected void onRestart() {
        super.onRestart();

        new LoadDatacategory().execute();
    }
}
