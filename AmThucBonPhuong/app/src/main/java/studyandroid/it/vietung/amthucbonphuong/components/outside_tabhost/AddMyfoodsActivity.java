package studyandroid.it.vietung.amthucbonphuong.components.outside_tabhost;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.File;
import java.util.Calendar;

import studyandroid.it.vietung.amthucbonphuong.R;
import studyandroid.it.vietung.amthucbonphuong.databases.DBAdapter;
import studyandroid.it.vietung.amthucbonphuong.databases.Variable_Global;
import studyandroid.it.vietung.amthucbonphuong.loadfiles.LoadData;

public class AddMyfoodsActivity extends AppCompatActivity {

    private ImageView imageView;
    private EditText ed_namefood_mine;
    private EditText ed_nguyenlieu_mine;
    private EditText ed_cachthuchien_mine;
    private Button add;

    private File directory;
    private DBAdapter dba;
    String image_path = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_myfoods);

        imageView = (ImageView) findViewById(R.id.imageView);
        ed_namefood_mine = (EditText) findViewById(R.id.ed_namefood_mine);
        ed_nguyenlieu_mine = (EditText) findViewById(R.id.ed_nguyenlieu_mine);
        ed_cachthuchien_mine = (EditText) findViewById(R.id.ed_cachthuchien_mine);
        add = (Button) findViewById(R.id.btnAdd);

        directory = Variable_Global.getDirectory();

        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(AddMyfoodsActivity.this, LoadImage_External.class);
                startActivity(intent);
            }
        });
        //String image_path = null;
        //String image_name = null;
        image_path = getIntent().getStringExtra("myfood_img");
        if (image_path != null) {
            Bitmap bitmap = BitmapFactory.decodeFile(image_path);
            imageView.setImageBitmap(bitmap);
        }

        //final String finalImage_path = image_path;
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                String namefood = ed_namefood_mine.getText().toString();
                String nguyenlieu = ed_nguyenlieu_mine.getText().toString();
                String cachthuchien = ed_cachthuchien_mine.getText().toString();

                String path_image = null;
                String path_nguyenlieu = null;
                String path_cachthuchien = null;

                LoadData load = new LoadData();

                Calendar now = Calendar.getInstance();

                String time_now = now.get(Calendar.YEAR) +"_"+(now.get(Calendar.MONTH)+1) +"_"+now.get(Calendar.DATE) +"_"+now.get(Calendar.HOUR_OF_DAY) +"_"+now.get(Calendar.MINUTE) +"_"+now.get(Calendar.SECOND);

                if (load.copyImage_local(image_path, new File(directory, "mine_" + time_now + ".jpg"))) {
                    path_image = directory + "/" + "mine_" + time_now + ".jpg";
                    Log.d("path",path_image);
                    if (load.WriteFile(nguyenlieu, new File(directory, "mine_" + time_now + "_nguyenlieu.txt"))) {
                        path_nguyenlieu = directory + "/" + "mine_" + time_now + "_nguyenlieu.txt";
                        Log.d("path",path_nguyenlieu);
                        if (load.WriteFile(cachthuchien, new File(directory, "mine_" + time_now + "_cachthuchien.txt"))) {
                            path_cachthuchien = directory + "/" + "mine_" + time_now + "_cachthuchien.txt";
                            Log.d("path",path_cachthuchien);
                            dba = new DBAdapter(AddMyfoodsActivity.this);
                            dba.open();
                            String idfood = null;
                            long id = dba.insertFood(idfood, namefood, path_nguyenlieu, path_cachthuchien, path_image, 2);
                            if (id == -1) {
                                Toast.makeText(AddMyfoodsActivity.this, "Xin lỗi!!! Thêm món ăn thất bại.OMG", Toast.LENGTH_LONG).show();
                                dba.close();
                            } else {
                                Toast.makeText(AddMyfoodsActivity.this, "đã thêm món ăn vào mục ưa thích của bạn !!!", Toast.LENGTH_LONG).show();
                                dba.close();
                            }

                        } else {
                            Toast.makeText(AddMyfoodsActivity.this, "Thêm món ăn thất bại", Toast.LENGTH_LONG).show();
                        }
                    } else {
                        Toast.makeText(AddMyfoodsActivity.this, "Thêm món ăn thất bại", Toast.LENGTH_LONG).show();
                    }

                } else {
                    Toast.makeText(AddMyfoodsActivity.this, "Thêm món ăn thất bại", Toast.LENGTH_LONG).show();
                }

                AddMyfoodsActivity.this.finish();
            }
        });
    }

}
