package vn.edu.itplus_academy.tracnghiemtienganh.databases;

import android.content.Context;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by VietUng on 30/03/2016.
 */
public class DatabaseHelper extends SQLiteOpenHelper {

    DatabaseHelper(Context context) {
        super(context, Variable_Globals.DATABASE_NAME, null, Variable_Globals.DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        try {

            db.execSQL(Variable_Globals.DATABASE_CREATE_CD);
            db.execSQL(Variable_Globals.DATABASE_CREATE_CAUHOI_LT);
            db.execSQL(Variable_Globals.DATABASE_CREATE_CAUHOI_KT);
            db.execSQL(Variable_Globals.DATABASE_CREATE_UD);
            db.execSQL(Variable_Globals.DATABASE_CREATE_ND);
            db.execSQL(Variable_Globals.DATABASE_CREATE_DT);
            db.execSQL(Variable_Globals.DATABASE_CREATE_DA);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        db.execSQL("DROP TABLE IF EXISTS "+ Variable_Globals.KEY_TABLE_CD);
        db.execSQL("DROP TABLE IF EXISTS "+ Variable_Globals.KEY_TABLE_CH_LT);
        db.execSQL("DROP TABLE IF EXISTS "+ Variable_Globals.KEY_TABLE_CH_KT);
        db.execSQL("DROP TABLE IF EXISTS "+ Variable_Globals.KEY_TABLE_UD);
        db.execSQL("DROP TABLE IF EXISTS "+ Variable_Globals.KEY_TABLE_ND);
        db.execSQL("DROP TABLE IF EXISTS "+ Variable_Globals.KEY_TABLE_DT);
        db.execSQL("DROP TABLE IF EXISTS "+ Variable_Globals.KEY_TABLE_DA);
        onCreate(db);
    }
}
