package com.cuongnguyen.appdoan.Database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by CUONG on 7/20/2016.
 */
public class DataBaseHelper extends SQLiteOpenHelper {
    public DataBaseHelper(Context context) {
        super(context, Variable_Globals.DATABASE_NAME, null, Variable_Globals.DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(Variable_Globals.CREATE_TABLE_NHOMDOAN);
        db.execSQL(Variable_Globals.CREATE_TABLE_MONAN);
        db.execSQL(Variable_Globals.CREATE_TABLE_BANGGIA);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("drop table if exists " + Variable_Globals.KEY_TABLE_BANGGIA);
        db.execSQL("drop table if exists " + Variable_Globals.KEY_TENMONAN_MONAN);
        db.execSQL("drop table if exists " + Variable_Globals.KEY_TABLE_BANGGIA);
        onCreate(db);
    }
}

