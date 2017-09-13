package studyandroid.it.vietung.amthucbonphuong.components.outside_tabhost;

import android.app.Activity;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import studyandroid.it.vietung.amthucbonphuong.R;
import studyandroid.it.vietung.amthucbonphuong.loadfiles.LoadData;

public class ShowFoodLike extends Activity {

    private TextView txtnamefoodlike ;
    private TextView txtnguyenlieulike;
    private TextView txtcachthuchienlike;
    private ImageView imgFoodlike;
    private LoadData loaddata = new LoadData();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_food_like);

        txtnamefoodlike = (TextView) findViewById(R.id.txtNamefoodlike);
        txtnguyenlieulike = (TextView) findViewById(R.id.txtnguyenlieulike);
        txtcachthuchienlike = (TextView) findViewById(R.id.txtcachthuchienlike);
        imgFoodlike = (ImageView) findViewById(R.id.imgFoodlike);


        Intent intentlike = getIntent();

        String strnamefoodlike = intentlike.getStringExtra("namefoodlike");
        String strnguyenlieulike = intentlike.getStringExtra("nguyenlieulike");
        String strcachthuchienlike = intentlike.getStringExtra("cachthuchienlike");
        String strimagefoodlike = intentlike.getStringExtra("imagefoodlike");

        imgFoodlike.setImageBitmap(BitmapFactory.decodeFile(strimagefoodlike));
        txtnamefoodlike.setText(strnamefoodlike);
        txtnguyenlieulike.setText(loaddata.ReadFile(strnguyenlieulike));
        txtcachthuchienlike.setText(loaddata.ReadFile(strcachthuchienlike));
    }

}
