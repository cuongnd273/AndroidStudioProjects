package com.cuongnguyen.appdoan.Model;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Environment;
import android.util.Log;

import com.cuongnguyen.appdoan.Database.DBAdapter;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by cuong on 8/4/2016.
 */
public class ImageSaver extends AsyncTask<String,Void,Bitmap>{

    private String url;
    private String filePath="";
    private MonAn monan;
    private Context context;
    private DBAdapter db;
    public ImageSaver(Context context, MonAn monan) {
        this.monan=monan;
        this.context=context;
        this.db=new DBAdapter(context);
    }

    @Override
        protected Bitmap doInBackground(String... params) {
        url=params[0];
        Bitmap bitmap=null;
        File myDir = new File(Environment.getExternalStorageDirectory() + "/AppDoAnImages");
        String name = url.substring(url.lastIndexOf('/') + 1);
        myDir=new File(myDir,name);
        if(!myDir.exists())
        {
            try {
                URL url=new URL(params[0]);
                HttpURLConnection urlConnection= (HttpURLConnection) url.openConnection();
                urlConnection.setDoInput(true);
                urlConnection.connect();
                bitmap= BitmapFactory.decodeStream(urlConnection.getInputStream());
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return bitmap;
        }

        @Override
        protected void onPostExecute(Bitmap bitmap) {
            if(bitmap!=null)
            {
                try {
                    File myDir = new File(Environment.getExternalStorageDirectory() + "/AppDoAnImages");

                    if (!myDir.exists()) {
                        myDir.mkdirs();
                    }
                    String name = url.substring(url.lastIndexOf('/') + 1);
                    myDir=new File(myDir,name);
                    FileOutputStream out = new FileOutputStream(myDir);
                    bitmap.compress(Bitmap.CompressFormat.JPEG, 90, out);
                    filePath=myDir.getPath();
                    Log.i("FilePath",filePath);
                    monan.setLinkAnh(filePath);
                    db.insertMonAn(monan);
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }
            }else{
                File myDir = new File(Environment.getExternalStorageDirectory() + "/AppDoAnImages");
                String name = url.substring(url.lastIndexOf('/') + 1);
                myDir=new File(myDir,name);
                monan.setLinkAnh(myDir.getPath());
                db.insertMonAn(monan);
            }
            super.onPostExecute(bitmap);
        }
    }
