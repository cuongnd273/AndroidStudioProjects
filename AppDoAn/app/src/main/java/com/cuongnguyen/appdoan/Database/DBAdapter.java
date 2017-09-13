package com.cuongnguyen.appdoan.Database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.cuongnguyen.appdoan.Model.BangGia;
import com.cuongnguyen.appdoan.Model.MonAn;
import com.cuongnguyen.appdoan.Model.NhomDoAn;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by CUONG on 7/20/2016.
 */
public class DBAdapter {
    final Context context;
    private DataBaseHelper DBHelper;
    private SQLiteDatabase db;
    public DBAdapter(Context context)
    {
        this.context=context;
        DBHelper=new DataBaseHelper(context);
    }
    //---opens the database---
    public DBAdapter open() throws SQLException {
        //mở database nếu chưa có sẽ tạo
        db = DBHelper.getWritableDatabase();
        return this;
    }

    //---closes the database---
    public void close() {
        if(db!=null && db.isOpen())
            db.close();
    }
    public void insertNhomDoAn(NhomDoAn nhomDoAn)
    {
        open();
        ContentValues contentValues=new ContentValues();
        contentValues.put(Variable_Globals.KEY_MANHOM_NHOMDOAN,nhomDoAn.getMaNhom());
        contentValues.put(Variable_Globals.KEY_TENNHOM_NHOMDOAN,nhomDoAn.getTenNhom());
        if(db.insert(Variable_Globals.KEY_TABLE_NHOMDOAN, null, contentValues)!=-1)
        {
            Log.i("KetQua","Insert nhom mon an thanh cong");
        }else {
            Log.i("KetQua","Insert nhom mon an that bai");
        }
    }
    public List<NhomDoAn> getAllNhomDonAn()
    {
        List<NhomDoAn> list=new ArrayList<NhomDoAn>();
        open();
        String sql="SELECT * FROM "+Variable_Globals.KEY_TABLE_NHOMDOAN;
        Cursor cursor=db.rawQuery(sql,null);
        if(cursor.moveToFirst())
        {
            do{
                NhomDoAn nhomDoAn=new NhomDoAn();
                nhomDoAn.setMaNhom(cursor.getString(0));
                nhomDoAn.setTenNhom(cursor.getString(1));
                list.add(nhomDoAn);
            }while (cursor.moveToNext());
        }
        close();
        return list;
    }
    public void insertMonAn(MonAn monAn)
    {
        open();
        ContentValues contentValues=new ContentValues();
        contentValues.put(Variable_Globals.KEY_MANHOM_MONAN,monAn.getMaNhom());
        contentValues.put(Variable_Globals.KEY_MAMONAN_MONAN, monAn.getMaMonAn());
        contentValues.put(Variable_Globals.KEY_TENMONAN_MONAN,monAn.getTenMonAn());
        contentValues.put(Variable_Globals.KEY_GIOITHIEU_MONAN, monAn.getGioiThieu());
        contentValues.put(Variable_Globals.KEY_LINKANH_MONAN, monAn.getLinkAnh());
        if(db.insert(Variable_Globals.KEY_TABLE_MONAN,null,contentValues)!=-1)
        {
            Log.i("KetQua","Insert mon an thanh cong");
        }else{
            Log.i("KetQua","Insert mon an that bai");
        }
    }
    public List<MonAn> getAllMonAn(String maNhomAn)
    {
        open();
        List<MonAn> list=new ArrayList<MonAn>();
        String sql="SELECT * FROM "+Variable_Globals.KEY_TABLE_MONAN+" WHERE "+Variable_Globals.KEY_MANHOM_MONAN+"='"+maNhomAn+"'";
        Cursor cursor =db.rawQuery(sql,null);
        if(cursor.moveToFirst())
        {
            do{
                MonAn monAn=new MonAn();
                monAn.setMaNhom(cursor.getString(0));
                monAn.setMaMonAn(cursor.getString(1));
                monAn.setTenMonAn(cursor.getString(2));
                monAn.setGioiThieu(cursor.getString(3));
                monAn.setLinkAnh(cursor.getString(4));
                list.add(monAn);
            }while (cursor.moveToNext());
        }
        return list;
    }
    public List<MonAn> getMonAn()
    {
        open();
        List<MonAn> list=new ArrayList<MonAn>();
        String sql="SELECT * FROM "+Variable_Globals.KEY_TABLE_MONAN;
        Cursor cursor =db.rawQuery(sql,null);
        if(cursor.moveToFirst())
        {
            do{
                MonAn monAn=new MonAn();
                monAn.setMaNhom(cursor.getString(0));
                monAn.setMaMonAn(cursor.getString(1));
                monAn.setTenMonAn(cursor.getString(2));
                monAn.setGioiThieu(cursor.getString(3));
                monAn.setLinkAnh(cursor.getString(4));
                list.add(monAn);
            }while (cursor.moveToNext());
        }
        return list;
    }
    public void insertBangGia(BangGia bangGia)
    {
        open();
        ContentValues contentValues=new ContentValues();
        contentValues.put(Variable_Globals.KEY_MAMONAN_BANGGIA,bangGia.getMaMonAn());
        contentValues.put(Variable_Globals.KEY_GIA_BANGGIA,bangGia.getGia());
        contentValues.put(Variable_Globals.KEY_SOLUONG_BANGGIA, bangGia.getSoLuong());
        if(db.insert(Variable_Globals.KEY_TABLE_BANGGIA,null,contentValues)!=-11)
        {
            Log.i("KetQua", "Insert bang gia thanh cong");
        }else{
            Log.i("KetQua","Insert bang gia that bai");
        }
    }
    public List<BangGia> getAllBangGia(String maMonAn)
    {
        open();
        List<BangGia> list=new ArrayList<BangGia>();
        String sql="SELECT * FROM "+Variable_Globals.KEY_TABLE_BANGGIA+" WHERE "+Variable_Globals.KEY_MAMONAN_BANGGIA+"='"+maMonAn+"'";
        Cursor cursor=db.rawQuery(sql,null);
        if(cursor.moveToFirst())
        {
            do{
                BangGia bangGia=new BangGia();
                bangGia.setMaMonAn(cursor.getString(0));
                bangGia.setGia(cursor.getString(1));
                bangGia.setSoLuong(cursor.getString(2));
                list.add(bangGia);
            }while (cursor.moveToNext());
        }
        close();
        return list;
    }
    public void delNhomMonAn()
    {
        open();
        if( db.delete(Variable_Globals.KEY_TABLE_NHOMDOAN,1+" = "+1,null)!= -1){
            Log.d("DataBase__","delete nhom mon an thành công ");
        }else {
            Log.d("DataBase__","delete nhom mon an thất bại ");
        }

        close();
    }
    public void delMonAn()
    {
        open();
        if( db.delete(Variable_Globals.KEY_TABLE_MONAN,1+" = "+1,null)!= -1){
            Log.d("DataBase__","delete mon an thành công ");
        }else {
            Log.d("DataBase__","delete mon an thất bại ");
        }

        close();
    }
    public void delBangGia()
    {
        open();
        if( db.delete(Variable_Globals.KEY_TABLE_BANGGIA,1+" = "+1,null)!= -1){
            Log.d("DataBase__","delete bang gia thành công ");
        }else {
            Log.d("DataBase__","delete bang gia thất bại ");
        }

        close();
    }
}
