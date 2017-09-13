package vn.edu.itplus_academy.thitracnghiem.DataBase;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by tuananh on 6/30/2016.
 */
public class DataBaseHelper extends SQLiteOpenHelper {

    public DataBaseHelper(Context context) {
        super(context,Variable_Globals.DATABASE_NAME, null, Variable_Globals.DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(Variable_Globals.DATABASE_CREATE_TS);
        db.execSQL(Variable_Globals.DATABASE_CREATE_MT);
        db.execSQL(Variable_Globals.DATABASE_CREATE_CH);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("drop table if exists " + Variable_Globals.KEY_TABLE_TS);
        db.execSQL("drop table if exists " + Variable_Globals.KEY_TABLE_MT);
        db.execSQL("drop table if exists " + Variable_Globals.KEY_TABLE_CH);
        onCreate(db);
    }
}
