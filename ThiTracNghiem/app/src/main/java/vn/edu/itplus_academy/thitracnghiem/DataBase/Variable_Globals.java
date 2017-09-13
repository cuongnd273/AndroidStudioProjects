package vn.edu.itplus_academy.thitracnghiem.DataBase;

/**
 * Created by tuananh on 6/30/2016.
 */
public class Variable_Globals {
    //database
    public static final String DATABASE_NAME = "DB_ThiTuyenSinh";
    public static final int DATABASE_VERSION = 1;
    //ThiSinh
    public static final String KEY_TABLE_TS = "TB_ThiSinh";
    public static final String KEY_MADUTHI_TS = "MaDuThi";
    public static final String KEY_SODIENTHOAI_TS = "SoDienThoai";
    public static final String KEY_MACHUYENNGANH_TS = "MaChuyenNganh";
    public static final String KEY_STATUS_TS = "StatusThiSinh";

    public static final String DATABASE_CREATE_TS = "create table "+KEY_TABLE_TS+" ( "+ KEY_MADUTHI_TS +" text PRIMARY KEY , "+KEY_SODIENTHOAI_TS+" text not null , "+KEY_MACHUYENNGANH_TS+" text , "+KEY_STATUS_TS+" text not null ) ";
    //Mon Thi
    public static final String KEY_TABLE_MT = "TB_MonThi";
    public static final String KEY_MAMONTHI_MT = "MaMonThi";
    public static final String KEY_TITLEMONTHI_MT = "TitleMonThi";
    public static final String KEY_IMGMONTHI_MT = "ImgMonThi";
    public static final String KEY_STATUSMONTHI_MT = "StatusMonThi";

    public static final String DATABASE_CREATE_MT = "create table "+KEY_TABLE_MT+" ( "+ KEY_MAMONTHI_MT +" INTEGER PRIMARY KEY , "+KEY_TITLEMONTHI_MT+" text , "+KEY_IMGMONTHI_MT+" text , "+KEY_STATUSMONTHI_MT+" text  ) ";
    //Cau Hoi
    public static final String KEY_TABLE_CH = "TB_CauHoi";
    public static final String KEY_MACAUHOI_CH = "MaCauHoi";
    public static final String KEY_STATUS_CH = "StatusCauHoi";
    public static final String KEY_ID_CH = "IDCauHoi";
    public static final String KEY_CAUHOI_CH = "CauHoi";
    public static final String KEY_MA_DAPAN_A_CH = "MaDapan_A";
    public static final String KEY_DAPAN_A_CH = "Dapan_A";
    public static final String KEY_MA_DAPAN_B_CH = "MaDapan_B";
    public static final String KEY_DAPAN_B_CH = "Dapan_B";
    public static final String KEY_MA_DAPAN_C_CH = "MaDapan_C";
    public static final String KEY_DAPAN_C_CH = "Dapan_C";
    public static final String KEY_MA_DAPAN_D_CH = "MaDapan_D";
    public static final String KEY_DAPAN_D_CH = "Dapan_D";
    public static final String KEY_KETQUA_CH = "Ketqua";
    public static final String KEY_KETQUA_TXT_CH = "Ketqua_TxT";
    public static final String KEY_MAMONTHI_CH = "MaMonThi";

    public static final String DATABASE_CREATE_CH = "create table "+KEY_TABLE_CH+" ( "+ KEY_MACAUHOI_CH +" INTEGER PRIMARY KEY , "+KEY_STATUS_CH+" text , "+KEY_ID_CH+" text  , "+KEY_CAUHOI_CH+" text , "+KEY_MA_DAPAN_A_CH+" INTEGER  , "+KEY_DAPAN_A_CH+" text  , "+KEY_MA_DAPAN_B_CH+" INTEGER  , "+KEY_DAPAN_B_CH+" text  , "+KEY_MA_DAPAN_C_CH+" INTEGER  , "+KEY_DAPAN_C_CH+" text , "+KEY_MA_DAPAN_D_CH+" INTEGER  , "+KEY_DAPAN_D_CH+" text  , "+KEY_KETQUA_CH+" text  , "+KEY_KETQUA_TXT_CH+" text , "+KEY_MAMONTHI_CH+" INTEGER not null ) ";


}
