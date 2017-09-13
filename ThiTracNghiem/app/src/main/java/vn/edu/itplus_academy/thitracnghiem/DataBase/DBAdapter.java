package vn.edu.itplus_academy.thitracnghiem.DataBase;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import vn.edu.itplus_academy.thitracnghiem.Model.CauHoi;
import vn.edu.itplus_academy.thitracnghiem.Model.DapAn;
import vn.edu.itplus_academy.thitracnghiem.Model.MonThi;
import vn.edu.itplus_academy.thitracnghiem.Model.ThiSinh;

/**
 * Created by tuananh on 6/30/2016.
 */
public class DBAdapter {
    final Context context;
    private final static String CAUHOIIMG = "img";
    private final static String CAUHOITXT = "txt";
    DataBaseHelper DBHelper;
    SQLiteDatabase db;

    public DBAdapter(Context context) {
        this.context = context;
        DBHelper = new DataBaseHelper(context);
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


    //-------*****ThiSinh*****-------

    public void insert_ThiSinh( ThiSinh thiSinh) {
        open();
        ContentValues initialValues = new ContentValues();

        initialValues.put(Variable_Globals.KEY_MADUTHI_TS, thiSinh.getMaDuThi());
        initialValues.put(Variable_Globals.KEY_SODIENTHOAI_TS,thiSinh.getSodienthoai());
        initialValues.put(Variable_Globals.KEY_MACHUYENNGANH_TS,thiSinh.getMaChuyenNganh());
        initialValues.put(Variable_Globals.KEY_STATUS_TS, thiSinh.getStatusThiSinh());
        if(db.insert(Variable_Globals.KEY_TABLE_TS, null, initialValues)!= -1){
            Log.d("DataBase__","insert Thi Sinh thành công ");
        }else {
            Log.d("DataBase__","insert Thi Sinh thất bại ");
        }
        close();
    }

    public List<ThiSinh> getAll_ThiSinh(){
        List<ThiSinh> list = new ArrayList<>();
        open();
        //Select All Query
        String selectQuery = "SELECT * FROM " + Variable_Globals.KEY_TABLE_TS;

        Cursor cursor = db.rawQuery(selectQuery, null);
        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                ThiSinh thiSinh = new ThiSinh();

                thiSinh.setMaDuThi(cursor.getString(0));
                thiSinh.setSodienthoai(cursor.getString(1));
                thiSinh.setMaChuyenNganh(cursor.getString(2));
                thiSinh.setStatusThiSinh(cursor.getString(3));
                // Adding contact to list
                list.add(thiSinh);
            } while (cursor.moveToNext());
        }
        close();
        if(list.size() != 0){
            Log.d("DataBase__","getAll Thi Sinh thành công ");
        }else {
            Log.d("DataBase__","getAll Thi Sinh thất bại ");
        }
        return list;
    }

    public void update_ThiSinh(ThiSinh thiSinh){
        open();
        ContentValues initialValues = new ContentValues();

//        initialValues.put(Variable_Globals.KEY_MACHUYENNGANH_TS,thiSinh.getMaChuyenNganh());
        initialValues.put(Variable_Globals.KEY_STATUS_TS, thiSinh.getStatusThiSinh());
        thiSinh.getMaDuThi();
        if( db.update(Variable_Globals.KEY_TABLE_TS, initialValues, Variable_Globals.KEY_MADUTHI_TS+" = '"+thiSinh.getMaDuThi().toString().trim()+"' " ,null )!= -1){
            Log.d("DataBase__","update Thi Sinh thành công ");
        }else {
            Log.d("DataBase__","update Thi Sinh thất bại ");
        }
        close();
    }

    public void deleteAll_ThiSinh() {
        open();
        if(db.delete(Variable_Globals.KEY_TABLE_TS, 1+ "="+1,null)!= -1){
            Log.d("DataBase__","deleteAll Thi Sinh thành công ");
        }else {
            Log.d("DataBase__","deleteAll Thi Sinh thất bại ");
        }
        close();
    }

    //-------*****MonThi*****-------

    public void insert_MonThi( MonThi monThi) {
        open();
        ContentValues initialValues = new ContentValues();

        initialValues.put(Variable_Globals.KEY_MAMONTHI_MT, monThi.getMaMonThi());
        initialValues.put(Variable_Globals.KEY_TITLEMONTHI_MT, monThi.getTitleMonThi());
        initialValues.put(Variable_Globals.KEY_IMGMONTHI_MT, monThi.getImgMonThi());
        initialValues.put(Variable_Globals.KEY_STATUSMONTHI_MT, monThi.getStatusMonThi());
        if(db.insert(Variable_Globals.KEY_TABLE_MT, null, initialValues)!= -1){
            Log.d("DataBase__","insert Mon Thi thành công ");
        }else {
            Log.d("DataBase__","insert Mon Thi thất bại ");
        }
        close();
    }

    public List<MonThi> getAll_MonThi(){
        List<MonThi> list = new ArrayList<>();
        open();
        //Select All Query
        String selectQuery = "SELECT * FROM " + Variable_Globals.KEY_TABLE_MT;

        Cursor cursor = db.rawQuery(selectQuery, null);
        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                MonThi monThi = new MonThi();

                monThi.setMaMonThi(cursor.getInt(0));
                monThi.setTitleMonThi(cursor.getString(1));
                monThi.setImgMonThi(cursor.getString(2));
                monThi.setStatusMonThi(cursor.getString(3));

                // Adding contact to list
                list.add(monThi);
            } while (cursor.moveToNext());
        }
        close();
        if(list.size() != 0){
            Log.d("DataBase__","getAll Mon Thi thành công ");
        }else {
            Log.d("DataBase__","getAll Mon Thi thất bại ");
        }
        return list;
    }

    public MonThi get_MonThi(int maMonThi){
        open();
        String selectQuery = "SELECT * FROM " + Variable_Globals.KEY_TABLE_MT + " WHERE " + Variable_Globals.KEY_MAMONTHI_MT +" = "+maMonThi ;

        Cursor cursor = db.rawQuery(selectQuery, null);
        MonThi monThi = new MonThi();
        if (cursor.moveToFirst()) {

            monThi.setMaMonThi(cursor.getInt(0));
            monThi.setTitleMonThi(cursor.getString(1));
            monThi.setImgMonThi(cursor.getString(2));
            monThi.setStatusMonThi(cursor.getString(3));
        }
        close();
        if(monThi == null){
            Log.d("DataBase__","get Mon Thi thất bại ");
        }else {
            Log.d("DataBase__","get Mon Thi thành công ");
        }
        return monThi;
    }

    public void deleteAll_MonThi() {
        open();
        if( db.delete(Variable_Globals.KEY_TABLE_MT,1+" = "+1,null)!= -1){
            Log.d("DataBase__","deleteAll Mon Thi thành công ");
        }else {
            Log.d("DataBase__","deleteAll Mon Thi thất bại ");
        }

        close();
    }

    public void update_MonThi(MonThi monThi){
        open();
        ContentValues initialValues = new ContentValues();

        initialValues.put(Variable_Globals.KEY_TITLEMONTHI_MT, monThi.getTitleMonThi());
        initialValues.put(Variable_Globals.KEY_IMGMONTHI_MT, monThi.getImgMonThi());
        initialValues.put(Variable_Globals.KEY_STATUSMONTHI_MT, monThi.getStatusMonThi());

        if( db.delete(Variable_Globals.KEY_TABLE_MT,1+" = "+1,null)!= -1){
            Log.d("DataBase__","update Mon Thi thành công ");
        }else {
            Log.d("DataBase__","update Mon Thi thất bại ");
        }
        db.update(Variable_Globals.KEY_TABLE_MT, initialValues, Variable_Globals.KEY_MAMONTHI_MT+" = ? ", new String[]{String.valueOf(monThi.getMaMonThi())} );
        close();
    }
    public void update_Status_MonThi(int maMonThi, String status){
        open();
        ContentValues initialValues = new ContentValues();


        initialValues.put(Variable_Globals.KEY_STATUSMONTHI_MT, status);

        if( db.update(Variable_Globals.KEY_TABLE_MT, initialValues, Variable_Globals.KEY_MAMONTHI_MT+" = ? ", new String[]{String.valueOf(maMonThi)} )!= -1){
            Log.d("DataBase__","update Status Mon Thi thành công ");
        }else {
            Log.d("DataBase__","update Status Mon Thi thất bại ");
        }
        db.update(Variable_Globals.KEY_TABLE_MT, initialValues, Variable_Globals.KEY_MAMONTHI_MT+" = ? ", new String[]{String.valueOf(maMonThi)} );
        close();
    }

    //-------*****CauHoi*****-------

    public void insert_CauHoi(CauHoi cauHoi) {
        open();
        ContentValues initialValues = new ContentValues();

        initialValues.put(Variable_Globals.KEY_MACAUHOI_CH, cauHoi.getMaCauHoi());
        initialValues.put(Variable_Globals.KEY_STATUS_CH, cauHoi.getStatusCauHoi().toString().trim());
        initialValues.put(Variable_Globals.KEY_ID_CH,cauHoi.getTxtID() );
        initialValues.put(Variable_Globals.KEY_CAUHOI_CH, cauHoi.getTxtCauHoi());
        if(cauHoi.getDapAnList().size()!=0) {
            initialValues.put(Variable_Globals.KEY_MA_DAPAN_A_CH, cauHoi.getDapAnList().get(0).getMaDapAn());
            initialValues.put(Variable_Globals.KEY_DAPAN_A_CH, cauHoi.getDapAnList().get(0).getTxtDapAn());
            initialValues.put(Variable_Globals.KEY_MA_DAPAN_B_CH, cauHoi.getDapAnList().get(1).getMaDapAn());
            initialValues.put(Variable_Globals.KEY_DAPAN_B_CH, cauHoi.getDapAnList().get(1).getTxtDapAn());
            initialValues.put(Variable_Globals.KEY_MA_DAPAN_C_CH, cauHoi.getDapAnList().get(2).getMaDapAn());
            initialValues.put(Variable_Globals.KEY_DAPAN_C_CH, cauHoi.getDapAnList().get(2).getTxtDapAn());
            initialValues.put(Variable_Globals.KEY_MA_DAPAN_D_CH, cauHoi.getDapAnList().get(3).getMaDapAn());
            initialValues.put(Variable_Globals.KEY_DAPAN_D_CH, cauHoi.getDapAnList().get(3).getTxtDapAn());
        }
        initialValues.put(Variable_Globals.KEY_KETQUA_CH, cauHoi.getDapAn());
        initialValues.put(Variable_Globals.KEY_KETQUA_TXT_CH, cauHoi.getDapAnTxt());
        initialValues.put(Variable_Globals.KEY_MAMONTHI_CH, cauHoi.getMaMonThi());
        if(db.insert(Variable_Globals.KEY_TABLE_CH, null, initialValues)!= -1){
            Log.d("DataBase__","insert Cau Hoi thành công ");
        }else {
            Log.d("DataBase__","insert Cau Hoi thất bại ");
        }
        close();
    }

    public List<CauHoi> get_CauHoi_MT(int maMonThi){
        List<CauHoi> list = new ArrayList<>();
        open();
        //Select All Query
        String selectQuery = "SELECT * FROM " + Variable_Globals.KEY_TABLE_CH + " WHERE " + Variable_Globals.KEY_MAMONTHI_CH +" = "+maMonThi ;

        Cursor cursor = db.rawQuery(selectQuery, null);
        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                CauHoi cauHoi = new CauHoi();
                cauHoi.setMaCauHoi(cursor.getInt(0));
                cauHoi.setStatusCauHoi(cursor.getString(1));
                cauHoi.setTxtID(cursor.getString(2));
                cauHoi.setTxtCauHoi(cursor.getString(3));
                List<DapAn> dapAnList = new ArrayList<>();
                if(cursor.getString(4) != null){
                    for(int i = 0; i < 4; i++ ){
                        DapAn dapAn = new DapAn();
                        if(i == 0 ){
                            dapAn.setMaDapAn(cursor.getInt(4));
                            dapAn.setTxtID("A");
                            dapAn.setTxtDapAn(cursor.getString(5));
                        }else if(i == 1 ){
                            dapAn.setMaDapAn(cursor.getInt(6));
                            dapAn.setTxtID("B");
                            dapAn.setTxtDapAn(cursor.getString(7));
                        }else if(i == 2 ){
                            dapAn.setMaDapAn(cursor.getInt(8));
                            dapAn.setTxtID("C");
                            dapAn.setTxtDapAn(cursor.getString(9));
                        }else{
                            dapAn.setMaDapAn(cursor.getInt(10));
                            dapAn.setTxtID("D");
                            dapAn.setTxtDapAn(cursor.getString(11));
                        }
                        dapAnList.add(dapAn);

                    }
                }
                cauHoi.setDapAnList(dapAnList);
                cauHoi.setDapAn(Integer.valueOf(cursor.getString(12)));
                cauHoi.setDapAnTxt(cursor.getString(13));
                // Adding contact to list
                list.add(cauHoi);
            } while (cursor.moveToNext());
        }
        close();
        if(list.size() != 0){
            Log.d("DataBase__","getAll Cau Hoi thành công ");
        }else {
            Log.d("DataBase__","getAll Cau Hoi thất bại ");
        }
        return list;
    }



    public void deleteAll_CauHoi() {
        open();
        if(db.delete(Variable_Globals.KEY_TABLE_CH,1+ " = "+1,null)!= -1){
            Log.d("DataBase__","deleteAll Cau Hoi thành công ");
        }else {
            Log.d("DataBase__","deleteAll Cau Hoi thất bại ");
        }

        close();
    }

    public void update_CauHoi(CauHoi cauHoi){
        open();
        ContentValues initialValues = new ContentValues();

        initialValues.put(Variable_Globals.KEY_ID_CH, cauHoi.getTxtID());
        initialValues.put(Variable_Globals.KEY_CAUHOI_CH, cauHoi.getTxtCauHoi());
        initialValues.put(Variable_Globals.KEY_DAPAN_A_CH, cauHoi.getDapAnList().get(0).getTxtDapAn());
        initialValues.put(Variable_Globals.KEY_DAPAN_B_CH, cauHoi.getDapAnList().get(1).getTxtDapAn());
        initialValues.put(Variable_Globals.KEY_DAPAN_C_CH, cauHoi.getDapAnList().get(2).getTxtDapAn());
        initialValues.put(Variable_Globals.KEY_DAPAN_D_CH, cauHoi.getDapAnList().get(3).getTxtDapAn());
        initialValues.put(Variable_Globals.KEY_KETQUA_CH, cauHoi.getDapAn());
        initialValues.put(Variable_Globals.KEY_MAMONTHI_CH,cauHoi.getMaMonThi());
        if(db.update(Variable_Globals.KEY_TABLE_CH, initialValues, Variable_Globals.KEY_MACAUHOI_CH+" = " +cauHoi.getMaCauHoi(),null )!= -1){
            Log.d("DataBase__","update Cau Hoi thành công ");
        }else {
            Log.d("DataBase__","update Cau Hoi thất bại ");
        }

        close();
    }

    public void update_DapAn_CauHoi(int maCauHoi,int dapan){
        open();
        ContentValues initialValues = new ContentValues();

        initialValues.put(Variable_Globals.KEY_KETQUA_CH, dapan);
        if(db.update(Variable_Globals.KEY_TABLE_CH, initialValues, Variable_Globals.KEY_MACAUHOI_CH+" = " +maCauHoi,null )!= -1){
            Log.d("DataBase__","update DapAn Cau Hoi thành công ");
        }else {
            Log.d("DataBase__","update DapAn Cau Hoi thất bại ");
        }

        close();
    }

    public void update_DapAn_TxT_CauHoi(int maCauHoi,String dapanTxt){
        open();
        ContentValues initialValues = new ContentValues();

        initialValues.put(Variable_Globals.KEY_KETQUA_TXT_CH, dapanTxt);
        if(db.update(Variable_Globals.KEY_TABLE_CH, initialValues, Variable_Globals.KEY_MACAUHOI_CH+" = " +maCauHoi,null )!= -1){
            Log.d("DataBase__","update DapAn Cau Hoi thành công ");
        }else {
            Log.d("DataBase__","update DapAn Cau Hoi thất bại ");
        }

        close();
    }

}