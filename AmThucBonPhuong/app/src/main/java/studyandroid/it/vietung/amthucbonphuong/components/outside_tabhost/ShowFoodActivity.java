package studyandroid.it.vietung.amthucbonphuong.components.outside_tabhost;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import studyandroid.it.vietung.amthucbonphuong.R;
import studyandroid.it.vietung.amthucbonphuong.loadfiles.LoadFileTxt;

public class ShowFoodActivity extends Activity {

    private TextView txtnamefood;
    private TextView txtnguyenlieu;
    private TextView txtcachthuchien;
    private ImageView imgFood;
    private LoadFileTxt loadnguyenlieu = new LoadFileTxt();
    private LoadFileTxt loadcachthuchien = new LoadFileTxt();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_food);

        txtnamefood = (TextView) findViewById(R.id.txtNamefood);
        txtnguyenlieu = (TextView) findViewById(R.id.txtnguyenlieu);
        txtcachthuchien = (TextView) findViewById(R.id.txtcachthuchien);
        imgFood = (ImageView) findViewById(R.id.imgFood);

        Intent intent = getIntent();

        String strnamefood = intent.getStringExtra("namefood");
        String strnguyenlieu = intent.getStringExtra("nguyenlieu");
        String strcachthuchien = intent.getStringExtra("cachthuchien");
        String strimagefood = intent.getStringExtra("imagefood");

        Picasso.with(this).load(strimagefood).error(R.drawable.temp_img).into(imgFood);
        loadnguyenlieu.LoadFileInternet(strnguyenlieu);
        txtnguyenlieu.setText(loadnguyenlieu.getTextload());
        loadcachthuchien.LoadFileInternet(strcachthuchien);
        txtcachthuchien.setText(loadcachthuchien.getTextload());
        txtnamefood.setText(strnamefood);

    }

}
