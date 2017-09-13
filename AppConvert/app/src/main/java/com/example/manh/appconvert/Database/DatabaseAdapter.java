package com.example.manh.appconvert.Database;

import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by cuong on 9/29/2016.
 */
public class DatabaseAdapter {
    final Context context;
    private DataBaseHelper DBHelper;
    private SQLiteDatabase db;
    public DatabaseAdapter(Context context)
    {
        this.context=context;
        try {
            DBHelper=new DataBaseHelper(context);
            DBHelper.createDataBase();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    //---opens the database---
    public DatabaseAdapter open() throws SQLException {
        //mở database nếu chưa có sẽ tạo
        db = DBHelper.openDataBase();
        return this;
    }

    //---closes the database---
    public void close() {
        if(db!=null && db.isOpen())
            db.close();
    }
    //Lấy danh mục của bảng tỉ lệ tiền tệ
    public List<String> getTitleCurrency()
    {
        open();
        List<String> title=new ArrayList<String>();
        String sql="SELECT * FROM "+Variable_Globals.TABLE_CURRENCY;
        Cursor cursor =db.rawQuery(sql,null);
        if(cursor.moveToFirst())
        {
            do{
                title.add(cursor.getString(0));
            }while (cursor.moveToNext());
        }
        return title;
    }
    //Lấy tỉ lệ tiền tệ
    public List<Double> getRatioCurrency()
    {
        open();
        List<Double> ratio=new ArrayList<Double>();
        String sql="SELECT * FROM "+Variable_Globals.TABLE_CURRENCY;
        Cursor cursor =db.rawQuery(sql,null);
        if(cursor.moveToFirst())
        {
            do{
                ratio.add(cursor.getDouble(1));
            }while (cursor.moveToNext());
        }
        return ratio;
    }
    //Lấy danh mục bảng độ dài
    public List<String> getTitleLength()
    {
        open();
        List<String> title=new ArrayList<String>();
        String sql="SELECT * FROM "+Variable_Globals.TABLE_LENGTH;
        Cursor cursor =db.rawQuery(sql,null);
        if(cursor.moveToFirst())
        {
            do{
                title.add(cursor.getString(0));
            }while (cursor.moveToNext());
        }
        return title;
    }
    //Lấy tỉ lệ bảng độ dài
    public List<Double> getRatioLength()
    {
        open();
        List<Double> ratio=new ArrayList<Double>();
        String sql="SELECT * FROM "+Variable_Globals.TABLE_LENGTH;
        Cursor cursor =db.rawQuery(sql,null);
        if(cursor.moveToFirst())
        {
            do{
                ratio.add(cursor.getDouble(1));
            }while (cursor.moveToNext());
        }
        return ratio;
    }
    //Lấy danh mục bảng khối lượng
    public List<String> getTitleWeight()
    {
        open();
        List<String> title=new ArrayList<String>();
        String sql="SELECT * FROM "+Variable_Globals.TABLE_WEIGHT;
        Cursor cursor =db.rawQuery(sql,null);
        if(cursor.moveToFirst())
        {
            do{
                title.add(cursor.getString(0));
            }while (cursor.moveToNext());
        }
        return title;
    }
    //Lấy tỉ lệ bảng khối lượng
    public List<Double> getRatioWeight()
    {
        open();
        List<Double> ratio=new ArrayList<Double>();
        String sql="SELECT * FROM "+Variable_Globals.TABLE_WEIGHT;
        Cursor cursor =db.rawQuery(sql,null);
        if(cursor.moveToFirst())
        {
            do{
                ratio.add(cursor.getDouble(1));
            }while (cursor.moveToNext());
        }
        return ratio;
    }
    //Lấy danh mục bảng thể tích
    public List<String> getTitleVolume()
    {
        open();
        List<String> title=new ArrayList<String>();
        String sql="SELECT * FROM "+Variable_Globals.TABLE_VOLUME;
        Cursor cursor =db.rawQuery(sql,null);
        if(cursor.moveToFirst())
        {
            do{
                title.add(cursor.getString(0));
            }while (cursor.moveToNext());
        }
        return title;
    }
    //lấy tỉ lệ bảng thể tích
    public List<Double> getRatioVolume()
    {
        open();
        List<Double> ratio=new ArrayList<Double>();
        String sql="SELECT * FROM "+Variable_Globals.TABLE_VOLUME;
        Cursor cursor =db.rawQuery(sql,null);
        if(cursor.moveToFirst())
        {
            do{
                ratio.add(cursor.getDouble(1));
            }while (cursor.moveToNext());
        }
        return ratio;
    }
}
