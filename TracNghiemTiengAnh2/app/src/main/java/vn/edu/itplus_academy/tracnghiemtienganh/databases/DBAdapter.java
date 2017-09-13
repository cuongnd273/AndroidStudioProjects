package vn.edu.itplus_academy.tracnghiemtienganh.databases;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

import vn.edu.itplus_academy.tracnghiemtienganh.Model.DeThiDB;
import vn.edu.itplus_academy.tracnghiemtienganh.models.CauHoiKT;
import vn.edu.itplus_academy.tracnghiemtienganh.models.CauHoiLT;
import vn.edu.itplus_academy.tracnghiemtienganh.models.ChuDe;
import vn.edu.itplus_academy.tracnghiemtienganh.models.DapAn;
import vn.edu.itplus_academy.tracnghiemtienganh.models.NhomDe;
import vn.edu.itplus_academy.tracnghiemtienganh.models.UngDung;

/**
 * Created by VietUng on 30/03/2016.
 */
public class DBAdapter {

    final Context context;

    DatabaseHelper DBHelper;
    SQLiteDatabase db;

    public DBAdapter(Context ctx) {
        this.context = ctx;
        DBHelper = new DatabaseHelper(context);
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


    //-------*****Chủ Đề*****-------

    //---insert a cd into the database---
    public long insertChuDe( int ma, String tencd, String path) {
        ContentValues initialValues = new ContentValues();

        initialValues.put(Variable_Globals.KEY_MA_CD, ma);
        initialValues.put(Variable_Globals.KEY_TEN_CD, tencd);
        initialValues.put(Variable_Globals.KEY_PATH_CD, path);

        return db.insert(Variable_Globals.KEY_TABLE_CD, null, initialValues);
    }

//    //---retrieves all the cd---
//    public Cursor getAll_ChuDe() {
//        return db.query(Variable_Globals.KEY_TABLE_CD, new String[]{Variable_Globals.KEY_MA_CD, Variable_Globals.KEY_TEN_CD, Variable_Globals.KEY_PATH_CD},
//                null, null, null,
//                null, null);
//    }

    //-----Getting All chủ đề-----
    public List<ChuDe> getAll_ChuDe()
    {
        List<ChuDe> lst_ChuDe = new ArrayList<ChuDe>();
        // Select All Query
        String selectQuery = "SELECT * FROM " + Variable_Globals.KEY_TABLE_CD;

        Cursor cursor = db.rawQuery(selectQuery, null);
        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                ChuDe cd = new ChuDe();

                cd.setMacd(cursor.getInt(0));
                cd.setTenCD(cursor.getString(1));
                cd.setLink(cursor.getString(2));

                // Adding contact to list
                lst_ChuDe.add(cd);
            } while (cursor.moveToNext());
        }
        // return contact list
        return lst_ChuDe;
    }

    //-----Getting chủ đề Count-----
    public int getChuDe_Count() {
        String countQuery = "SELECT * FROM " + Variable_Globals.KEY_TABLE_CD;
        Cursor cursor = db.rawQuery(countQuery, null);
        cursor.close();
        // return count
        return cursor.getCount();
    }

    //-------*****Câu hỏi lý thuyết*****-------

    //---insert a ch into the database---
    public long insertCauhoi_LT( int mach,int macd,String cauhoi, String dapan_a, String dapan_b,String dapan_c,String dapan_d,String ketqua) {
        ContentValues initialValues = new ContentValues();

        initialValues.put(Variable_Globals.KEY_MACH_LT, mach);
        initialValues.put(Variable_Globals.KEY_MA_CD, macd);
        initialValues.put(Variable_Globals.KEY_CAUHOI_LT, cauhoi);
        initialValues.put(Variable_Globals.KEY_DAPAN_A_LT, dapan_a);
        initialValues.put(Variable_Globals.KEY_DAPAN_B_LT, dapan_b);
        initialValues.put(Variable_Globals.KEY_DAPAN_C_LT, dapan_c);
        initialValues.put(Variable_Globals.KEY_DAPAN_D_LT, dapan_d);
        initialValues.put(Variable_Globals.KEY_KETQUA_LT, ketqua);

        return db.insert(Variable_Globals.KEY_TABLE_CH_LT, null, initialValues);
    }

    //-----Getting All câu hỏi lý thuyết-----
    public List<CauHoiLT> getAll_Cauhoi_LT(int macd)
    {
        List<CauHoiLT> lst_Cauhoi_LT = new ArrayList<CauHoiLT>();
        // Select All Query
        String selectQuery = "SELECT * FROM " + Variable_Globals.KEY_TABLE_CH_LT +" WHERE "+ Variable_Globals.KEY_MA_CD + "="+macd;

        Cursor cursor = db.rawQuery(selectQuery, null);
        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                CauHoiLT ch_lt = new CauHoiLT();

                ch_lt.setMach(cursor.getInt(0));
                ch_lt.setMacd(cursor.getInt(1));
                ch_lt.setCauhoi(cursor.getString(2));
                ch_lt.setDapan_A(cursor.getString(3));
                ch_lt.setDapan_B(cursor.getString(4));
                ch_lt.setDapan_C(cursor.getString(5));
                ch_lt.setDapan_D(cursor.getString(6));
                ch_lt.setKetqua(cursor.getString(7));

                // Adding contact to list
                lst_Cauhoi_LT.add(ch_lt);
            } while (cursor.moveToNext());
        }
        // return contact list
        return lst_Cauhoi_LT;
    }

    //-----Getting câu hỏi lý thuyết Count-----
    public int getCauhoi_LT_Count() {
        String countQuery = "SELECT * FROM " + Variable_Globals.KEY_TABLE_CH_LT;
        Cursor cursor = db.rawQuery(countQuery, null);
        cursor.close();
        // return count
        return cursor.getCount();
    }

    //-------*****Câu hỏi kiểm tra*****-------

    //---insert a ch into the database---
    public long insertCauhoi_KT( int mach,String cauhoi, String dapan_a, String dapan_b,String dapan_c,String dapan_d,String ketqua) {
        ContentValues initialValues = new ContentValues();

        Integer temp = null;
        initialValues.put(Variable_Globals.KEY_ID, temp);
        initialValues.put(Variable_Globals.KEY_KETQUA_KT, ketqua);
        initialValues.put(Variable_Globals.KEY_DAPAN_B_KT, dapan_b);
        initialValues.put(Variable_Globals.KEY_DAPAN_A_KT, dapan_a);
        initialValues.put(Variable_Globals.KEY_DAPAN_D_KT, dapan_d);
        initialValues.put(Variable_Globals.KEY_DAPAN_C_KT, dapan_c);
        initialValues.put(Variable_Globals.KEY_CAUHOI_KT, cauhoi);
        initialValues.put(Variable_Globals.KEY_MACH_KT, mach);

        return db.insert(Variable_Globals.KEY_TABLE_CH_KT, null, initialValues);
    }

    //-----Getting All câu hỏi kiểm tra-----
    public List<CauHoiKT> getAll_Cauhoi_KT(int start_id)
    {

        int end_id = start_id+49;

        List<CauHoiKT> lst_Cauhoi_KT = new ArrayList<CauHoiKT>();
        // Select All Query
        String selectQuery = "SELECT * FROM " + Variable_Globals.KEY_TABLE_CH_KT + " WHERE " + Variable_Globals.KEY_ID +">="+start_id + " AND " + Variable_Globals.KEY_ID +"<="+end_id;
        //Log.d("Query : --->", "getCauhoi_KT_Count: " + selectQuery);
        Cursor cursor = db.rawQuery(selectQuery, null);
        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                CauHoiKT ch_kt = new CauHoiKT();

                ch_kt.setMach(cursor.getInt(1));
                ch_kt.setCauhoi(cursor.getString(2));
                ch_kt.setDapan_A(cursor.getString(3));
                ch_kt.setDapan_B(cursor.getString(4));
                ch_kt.setDapan_C(cursor.getString(5));
                ch_kt.setDapan_D(cursor.getString(6));
                ch_kt.setKetqua(cursor.getString(7));

                // Adding contact to list
                lst_Cauhoi_KT.add(ch_kt);
            } while (cursor.moveToNext());
        }


        // return contact list
        return lst_Cauhoi_KT;
    }

    //-----Getting câu hỏi thi Count-----
    public int getCauhoi_KT_Count() {
        String countQuery = "SELECT * FROM " + Variable_Globals.KEY_TABLE_CH_KT;
        //Log.d("Query : --->", "getCauhoi_KT_Count: " + countQuery);

        Cursor cursor = db.rawQuery(countQuery, null);
        int i = cursor.getCount();

        cursor.close();
        // return count
        return i;
    }

    //-------*****Ứng dụng*****-------

    //---insert a ud into the database---
    public long insertUngDung( int maud,String tenud,String anh, String linkud, String mota) {
        ContentValues initialValues = new ContentValues();

        initialValues.put(Variable_Globals.KEY_MAUD, maud);
        initialValues.put(Variable_Globals.KEY_TENUD, tenud);
        initialValues.put(Variable_Globals.KEY_ANHUD, anh);
        initialValues.put(Variable_Globals.KEY_LINKUD, linkud);
        initialValues.put(Variable_Globals.KEY_MOTA, mota);

        return db.insert(Variable_Globals.KEY_TABLE_UD, null, initialValues);
    }

    //-----Getting All Ung dung-----
    public List<UngDung> getAll_UngDung()
    {
        List<UngDung> lst_UngDung = new ArrayList<UngDung>();
        // Select All Query
        String selectQuery = "SELECT * FROM " + Variable_Globals.KEY_TABLE_UD;

        Cursor cursor = db.rawQuery(selectQuery, null);
        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                UngDung ud = new UngDung();

                ud.setMaud(cursor.getInt(0));
                ud.setTenud(cursor.getString(1));
                ud.setAnh(cursor.getString(2));
                ud.setLinkud(cursor.getString(3));
                ud.setMota(cursor.getString(4));
                // Adding contact to list
                lst_UngDung.add(ud);
            } while (cursor.moveToNext());
        }
        // return contact list
        return lst_UngDung;
    }

    //-----Getting ung dung Count-----
    public int getUngDungs_Count() {
        String countQuery = "SELECT * FROM " + Variable_Globals.KEY_TABLE_UD;
        Cursor cursor = db.rawQuery(countQuery, null);
        cursor.close();
        // return count
        return cursor.getCount();
    }

    //-------*****Nhóm Đề*****-------

    //---insert a nd into the database---
    public long insertNhomDe( int mand,String tennd) {
        ContentValues initialValues = new ContentValues();

        initialValues.put(Variable_Globals.KEY_MAND, mand);
        initialValues.put(Variable_Globals.KEY_TENND, tennd);

        return db.insert(Variable_Globals.KEY_TABLE_ND, null, initialValues);
    }

    //-----Getting All Nhom de-----
    public List<NhomDe> getAll_NhomDe()
    {
        List<NhomDe> lst_NhomDe = new ArrayList<NhomDe>();
        // Select All Query
        String selectQuery = "SELECT * FROM " + Variable_Globals.KEY_TABLE_ND;

        Cursor cursor = db.rawQuery(selectQuery, null);
        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                NhomDe nd = new NhomDe();

                nd.setMand(cursor.getInt(0));
                nd.setTenND(cursor.getString(1));

                // Adding contact to list
                lst_NhomDe.add(nd);
            } while (cursor.moveToNext());
        }
        // return contact list
        return lst_NhomDe;
    }

    //-----Getting Nhom De Count-----
    public int getNhomDes_Count() {
        String countQuery = "SELECT * FROM " + Variable_Globals.KEY_TABLE_ND;
        Cursor cursor = db.rawQuery(countQuery, null);
        cursor.close();
        // return count
        return cursor.getCount();
    }

    //-------*****Đề Thi*****-------

    //---insert a dt into the database---
    public long insertDeThi( int madt,int mand,String linkdt,int thoigian,int loaded,int prg) {
        ContentValues initialValues = new ContentValues();

        initialValues.put(Variable_Globals.KEY_MADT, madt);
        initialValues.put(Variable_Globals.KEY_MAND, mand);
        initialValues.put(Variable_Globals.KEY_LINKDT, linkdt);
        initialValues.put(Variable_Globals.KEY_THOIGIAN, thoigian);
        initialValues.put(Variable_Globals.KEY_LOADED, loaded);
        initialValues.put(Variable_Globals.KEY_PRG, prg);

        return db.insert(Variable_Globals.KEY_TABLE_DT, null, initialValues);
    }


    //-----Getting All De Thi-----
    public List<DeThiDB> getAll_DeThi(int nhomde)
    {
        List<DeThiDB> lst_DeThi = new ArrayList<DeThiDB>();
        // Select All Query
        String selectQuery = "SELECT * FROM " + Variable_Globals.KEY_TABLE_DT + " WHERE " + Variable_Globals.KEY_MAND +"="+nhomde;

        Cursor cursor = db.rawQuery(selectQuery, null);
        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                DeThiDB dt = new DeThiDB();

                dt.setMadt(cursor.getInt(0));
                dt.setMand(cursor.getInt(1));
                dt.setPath(cursor.getString(2));
                dt.setThoigian(cursor.getInt(3));
                dt.setLoaded(cursor.getInt(4));
                dt.setPrg(cursor.getInt(5));

                // Adding contact to list
                lst_DeThi.add(dt);
            } while (cursor.moveToNext());
        }
        // return contact list
        return lst_DeThi;
    }

    public long update_Dethi(int madt,int mand,String linkdt,int thoigian,int loaded,int prg)
    {

        ContentValues initialValues = new ContentValues();

        initialValues.put(Variable_Globals.KEY_MAND, mand);
        initialValues.put(Variable_Globals.KEY_LINKDT, linkdt);
        initialValues.put(Variable_Globals.KEY_THOIGIAN, thoigian);
        initialValues.put(Variable_Globals.KEY_LOADED, loaded);
        initialValues.put(Variable_Globals.KEY_PRG, prg);
        return db.update(Variable_Globals.KEY_TABLE_DT, initialValues, Variable_Globals.KEY_MADT+"="+madt,null);
    }

    //-----Getting De thi Count-----
    public int getDeThis_Count() {
        String countQuery = "SELECT * FROM " + Variable_Globals.KEY_TABLE_DT;
        Cursor cursor = db.rawQuery(countQuery, null);
        cursor.close();
        // return count
        return cursor.getCount();
    }

    //-------*****Đáp Án*****-------

    //---insert a da into the database---
    public long insertDapAn( int mada,int madt,String da) {
        ContentValues initialValues = new ContentValues();

        Integer temp = null;
        initialValues.put(Variable_Globals.KEY_ID, temp);
        initialValues.put(Variable_Globals.KEY_MADA, mada);
        initialValues.put(Variable_Globals.KEY_MADT, madt);
        initialValues.put(Variable_Globals.KEY_DA, da);

        return db.insert(Variable_Globals.KEY_TABLE_DA, null, initialValues);
    }

    //-----Getting Dap an theo de thi----
    public ArrayList<DapAn> getAll_DapAn(int madt)
    {
        ArrayList<DapAn> lst_DapAn = new ArrayList<DapAn>();
        // Select All Query
        String selectQuery = "SELECT * FROM " + Variable_Globals.KEY_TABLE_DA + " WHERE " + Variable_Globals.KEY_MADT +"="+madt;

        Cursor cursor = db.rawQuery(selectQuery, null);
        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {


                DapAn da = new DapAn();

                da.setMada(cursor.getInt(1));
                da.setDa(cursor.getString(3));

                // Adding contact to list
                lst_DapAn.add(da);
            } while (cursor.moveToNext());
        }
        // return contact list
        return lst_DapAn;
    }

    //-----Getting Nhom De Count-----
    public int getDapAns_Count() {
        String countQuery = "SELECT * FROM " + Variable_Globals.KEY_TABLE_DA;
        Cursor cursor = db.rawQuery(countQuery, null);
        cursor.close();
        // return count
        return cursor.getCount();
    }
}
