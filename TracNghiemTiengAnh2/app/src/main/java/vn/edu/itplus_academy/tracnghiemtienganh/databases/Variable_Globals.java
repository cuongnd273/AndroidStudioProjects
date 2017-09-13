package vn.edu.itplus_academy.tracnghiemtienganh.databases;

/**
 * Created by VietUng on 30/03/2016.
 */
public class Variable_Globals {

    //cấu hình
    public static final String FILE_CONFIG = "file_config";
    public static final String HOST = "Host";
    public static final String CAUHOI_LT = "CauhoiLT";
    public static final String ID_CAUHOI_LT = "maxID_CauhoiLT";
    public static final String CAUHOI_KT = "CauhoiKT";
    public static final String ID_CAUHOI_KT = "maxID_CauhoiKT";
    public static final String CHUDE = "Chude";
    public static final String ID_CHUDE = "maxID_Chude";
    public static final String UNGDUNG = "Ungdung";
    public static final String ID_UNGDUNG = "maxID_Ungdung";
    public static final String DAPAN = "Dapan";
    public static final String NHOMDE = "Nhomde";
    public static final String ID_NHOMDE = "maxID_Nhomde";
    public static final String DETHI = "Dethi";
    public static final String ID_DETHI = "maxID_Dethi";
    public static final String VERSION = "Version";

    //database
    public static final String DATABASE_NAME = "DB_TracNghiemTiengAnh";
    public static final int DATABASE_VERSION = 1;

    //chủ đề
    public static final String KEY_TABLE_CD = "TB_ChuDe";
    public static final String KEY_MA_CD = "MaCD";
    public static final String KEY_TEN_CD = "TenCD";
    public static final String KEY_PATH_CD = "Path";

    public static final String DATABASE_CREATE_CD = "create table TB_ChuDe (MaCD INTEGER PRIMARY KEY, TenCD text not null, Path text not null)";

    //câu hỏi lý thuyết
    public static final String KEY_TABLE_CH_LT = "TB_Cauhoi_LT";
    public static final String KEY_MACH_LT = "MaCH";
    public static final String KEY_CAUHOI_LT = "CauHoi";
    public static final String KEY_DAPAN_A_LT = "Dapan_A";
    public static final String KEY_DAPAN_B_LT = "Dapan_B";
    public static final String KEY_DAPAN_C_LT = "Dapan_C";
    public static final String KEY_DAPAN_D_LT = "Dapan_D";
    public static final String KEY_KETQUA_LT = "Ketqua";

    public static final String DATABASE_CREATE_CAUHOI_LT = "create table TB_Cauhoi_LT (MaCH INTEGER PRIMARY KEY,MaCD INTEGER,CauHoi text not null,"
                                                            +"Dapan_A text not null,Dapan_B text not null,Dapan_C text not null,Dapan_D text not null,"
                                                            +"Ketqua text not null)";

    //- câu hỏi bài thi
    public static final String KEY_TABLE_CH_KT = "TB_Cauhoi_KT";
    public static final String KEY_ID = "ID";
    public static final String KEY_MACH_KT = "MaCH";
    public static final String KEY_CAUHOI_KT = "CauHoi";
    public static final String KEY_DAPAN_A_KT = "Dapan_A";
    public static final String KEY_DAPAN_B_KT = "Dapan_B";
    public static final String KEY_DAPAN_C_KT = "Dapan_C";
    public static final String KEY_DAPAN_D_KT = "Dapan_D";
    public static final String KEY_KETQUA_KT = "Ketqua";
    
    public static final String DATABASE_CREATE_CAUHOI_KT = "create table TB_Cauhoi_KT (ID INTEGER PRIMARY KEY autoincrement,MaCH INTEGER,CauHoi text not null,"
                                                            +"Dapan_A text not null,Dapan_B text not null,Dapan_C text not null,Dapan_D text not null,"
                                                            +"Ketqua text not null)";

    //- ứng dụng
    public static final String KEY_TABLE_UD = "TB_UngDung";
    public static final String KEY_MAUD = "MaUD";
    public static final String KEY_TENUD = "Ten_UD";
    public static final String KEY_ANHUD = "Anh_UD";
    public static final String KEY_LINKUD = "Link_UD";
    public static final String KEY_MOTA = "Mota";

    public static final String DATABASE_CREATE_UD = "create table TB_UngDung (MaUD INTEGER PRIMARY KEY,Ten_UD text not null,"
            +"Anh_UD text not null,Link_UD text not null,Mota text not null)";

    //- Nhóm đề
    public static final String KEY_TABLE_ND = "TB_NhomDe";
    public static final String KEY_MAND = "MaND";
    public static final String KEY_TENND = "TenND";

    public static final String DATABASE_CREATE_ND = "create table TB_NhomDe (MaND INTEGER PRIMARY KEY,TenND text not null)";

    //- Đề thi
    public static final String KEY_TABLE_DT = "TB_DeThi";
    public static final String KEY_MADT = "MaDT";
    public static final String KEY_LINKDT = "LinkDT";
    public static final String KEY_THOIGIAN = "ThoiGian";
    public static final String KEY_LOADED = "Loaded";
    public static final String KEY_PRG = "Prg";

    public static final String DATABASE_CREATE_DT = "create table TB_DeThi (MaDT INTEGER PRIMARY KEY,MaND INTEGER,LinkDT text not null,ThoiGian INTEGER,Loaded INTEGER,Prg INTEGER)";

    //- Đáp án
    public static final String KEY_TABLE_DA = "TB_DapAn";
    public static final String KEY_MADA = "MaDA";
    public static final String KEY_DA = "DA";

    public static final String DATABASE_CREATE_DA = "create table TB_DapAn (ID INTEGER PRIMARY KEY autoincrement,MaDA INTEGER,MaDT INTEGER,DA text not null)";
}
