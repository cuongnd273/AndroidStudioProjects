package studyandroid.it.vietung.amthucbonphuong.databases;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

/**
 * Created by VietUng on 30/12/2015.
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
        DBHelper.close();
    }

    //---insert a food into the database---
    public long insertFood(String idfood, String namefood, String nguyenlieu,String cachthuchien,String imagefood,int type) {
        ContentValues initialValues = new ContentValues();
        Integer temp = null;
        initialValues.put(Variable_Global.KEY_ID, temp);
        initialValues.put(Variable_Global.KEY_CACHTHUCHIEN, cachthuchien);
        initialValues.put(Variable_Global.KEY_IMAGEFOOD, imagefood);


        initialValues.put(Variable_Global.KEY_NGUYENLIEU, nguyenlieu);
        initialValues.put(Variable_Global.KEY_NAME, namefood);
        initialValues.put(Variable_Global.KEY_TYPEFOOD, type);
        initialValues.put(Variable_Global.KEY_IDFOOD, idfood);


        return db.insert(Variable_Global.DATABASE_TABLE, null, initialValues);
    }

    //---deletes a particular food favourite---
    public boolean deleteFood_favourite(String rowId) {
        return db.delete(Variable_Global.DATABASE_TABLE, Variable_Global.KEY_IDFOOD + "=?", new String[]{rowId})>0;
    }

    //---deletes a particular my food ---
    public boolean deleteFood_mine(int id) {
        return db.delete(Variable_Global.DATABASE_TABLE, Variable_Global.KEY_ID + "=?", new String[]{String.valueOf(id)})>0;
    }

    //---retrieves all the foods---
    public Cursor getAllFoods(int type) {
        return db.query(Variable_Global.DATABASE_TABLE, new String[]{Variable_Global.KEY_ID,Variable_Global.KEY_IDFOOD, Variable_Global.KEY_NAME,
                Variable_Global.KEY_NGUYENLIEU,Variable_Global.KEY_CACHTHUCHIEN,Variable_Global.KEY_IMAGEFOOD}, Variable_Global.KEY_TYPEFOOD +"="+type,null, null, null, null);
    }

    //---retrieves a particular food favourite---
    public Cursor getFoods_favourite(String rowId) throws SQLException {

            Cursor mCursor =
                    db.query(true, Variable_Global.DATABASE_TABLE, new String[]{Variable_Global.KEY_ID,Variable_Global.KEY_IDFOOD,
                                    Variable_Global.KEY_NAME,Variable_Global.KEY_NGUYENLIEU,Variable_Global.KEY_CACHTHUCHIEN,Variable_Global.KEY_IMAGEFOOD}, Variable_Global.KEY_IDFOOD + "= \"" + rowId +"\"", null,
                            null, null, null, null);
            if (mCursor != null) {
                mCursor.moveToFirst();
            }
            return mCursor;


    }

    //---retrieves a particular my food---
    public Cursor getFoods_mine(int id) throws SQLException {
        Cursor mCursor =
                db.query(true, Variable_Global.DATABASE_TABLE, new String[]{Variable_Global.KEY_ID,Variable_Global.KEY_ID,
                                Variable_Global.KEY_NAME,Variable_Global.KEY_NGUYENLIEU,Variable_Global.KEY_CACHTHUCHIEN,Variable_Global.KEY_IMAGEFOOD}, Variable_Global.KEY_ID + "=" + id, null,
                        null, null, null, null);
        if (mCursor != null) {
            mCursor.moveToFirst();
        }
        return mCursor;
    }
}
