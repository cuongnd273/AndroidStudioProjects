package studyandroid.it.vietung.amthucbonphuong.components.outside_tabhost;

import android.content.ContentResolver;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.widget.GridView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import studyandroid.it.vietung.amthucbonphuong.DTO.ImageMyFood_info;
import studyandroid.it.vietung.amthucbonphuong.R;
import studyandroid.it.vietung.amthucbonphuong.adapters.LoadImageStorageAdapter;

public class LoadImage_External extends AppCompatActivity {

    Uri uri1 = MediaStore.Images.Media.EXTERNAL_CONTENT_URI;

    List<ImageMyFood_info> list_imgfood;
    LoadImageStorageAdapter adapter_imgfood;

    GridView gridView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_load_image__external);

        list_imgfood = new ArrayList<ImageMyFood_info>();
        gridView = (GridView) findViewById(R.id.gridView_imgfood);


        ContentResolver resolver = getContentResolver();
        Cursor cursor1 = resolver.query(uri1, null, null, null, null);
        cursor1.moveToFirst();

        while (!cursor1.isAfterLast()) {
            String img_path = cursor1.getString(cursor1.getColumnIndex(MediaStore.Images.Media.DATA));
            String img_name = cursor1.getString(cursor1.getColumnIndex(MediaStore.Images.Media.DISPLAY_NAME));

            ImageMyFood_info image = new ImageMyFood_info();
            image.setImg_path(img_path);
            image.setImg_name(img_name);

            list_imgfood.add(image);
            cursor1.moveToNext();
        }

        try {
            adapter_imgfood = new LoadImageStorageAdapter(LoadImage_External.this, R.layout.activity_load_image_storage_adapter, list_imgfood);
        } catch (Exception e) {
            Toast.makeText(LoadImage_External.this, e.toString(), Toast.LENGTH_LONG).show();
        }

        gridView.setAdapter(adapter_imgfood);


    }
}


