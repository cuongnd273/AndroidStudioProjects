package studyandroid.it.vietung.amthucbonphuong.databases;

import java.io.File;

/**
 * Created by VietUng on 19/01/2016.
 */
public class Variable_Global {

    public static final String KEY_ID = "Id";
    public static final String KEY_IDFOOD = "IdFood";
    public static final String KEY_NAME = "NameFood";
    public static final String KEY_NGUYENLIEU = "NguyenLieu";
    public static final String KEY_CACHTHUCHIEN = "CachThucHien";
    public static final String KEY_IMAGEFOOD = "ImageFood";
    public static final String KEY_TYPEFOOD = "Type";
    public static final String TAG = "DBAdapter";

    public static final String DATABASE_NAME = "MyDBAmThuc";
    public static final String DATABASE_TABLE = "Foods";
    public static final int FAV0URITE_FOODS = 1;
    public static final int MY_FOODS = 2;
    public static final int DATABASE_VERSION = 1;

    public static final String DATABASE_CREATE =
            "create table Foods (Id INTEGER PRIMARY KEY autoincrement, IdFood text UNIQUE , "
                    + "NameFood text not null, NguyenLieu text not null, CachThucHien text not null, ImageFood text,Type int not null);";

    public static File getDirectory() {
        return directory;
    }

    public static void setDirectory(File directory) {
        Variable_Global.directory = directory;
    }

    private static File directory;
}
