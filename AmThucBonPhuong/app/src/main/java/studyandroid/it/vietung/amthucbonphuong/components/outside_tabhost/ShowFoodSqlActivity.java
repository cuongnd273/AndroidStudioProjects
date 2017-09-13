package studyandroid.it.vietung.amthucbonphuong.components.outside_tabhost;

import android.content.Intent;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;

import studyandroid.it.vietung.amthucbonphuong.R;
import studyandroid.it.vietung.amthucbonphuong.loadfiles.LoadData;

public class ShowFoodSqlActivity extends AppCompatActivity {

    private TextView txtnamefoodsql ;
    private TextView txtnguyenlieusql;
    private TextView txtcachthuchiensql;
    private ImageView imgFoodsql;
    private LoadData loaddata = new LoadData();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_food_sql);

        txtnamefoodsql = (TextView) findViewById(R.id.txtNamefoodsql);
        txtnguyenlieusql = (TextView) findViewById(R.id.txtnguyenlieusql);
        txtcachthuchiensql = (TextView) findViewById(R.id.txtcachthuchiensql);
        imgFoodsql = (ImageView) findViewById(R.id.imgFoodsql);


        Intent intentsql = getIntent();

        String strnamefoodsql = intentsql.getStringExtra("namesqlfood");
        String strnguyenlieusql = intentsql.getStringExtra("nguyenlieusql");
        String strcachthuchiensql = intentsql.getStringExtra("cachthuchiensql");
        String strimagefoodsql = intentsql.getStringExtra("imagesqlfood");

        imgFoodsql.setImageBitmap(BitmapFactory.decodeFile(strimagefoodsql));
        txtnamefoodsql.setText(strnamefoodsql);
        txtnguyenlieusql.setText(loaddata.ReadFile(strnguyenlieusql));
        txtcachthuchiensql.setText(loaddata.ReadFile(strcachthuchiensql));

    }

}
