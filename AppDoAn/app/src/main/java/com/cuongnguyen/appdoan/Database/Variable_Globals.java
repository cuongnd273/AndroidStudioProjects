package com.cuongnguyen.appdoan.Database;

/**
 * Created by CUONG on 7/20/2016.
 */
public class Variable_Globals {
    public static String DATABASE_NAME="DB_AppDoAn";
    public static int DATABASE_VERSION=1;

    public static String KEY_TABLE_NHOMDOAN="TB_NhomDoAn";
    public static String KEY_MANHOM_NHOMDOAN="MaNhom";
    public static String KEY_TENNHOM_NHOMDOAN="TenNhom";
    public static String CREATE_TABLE_NHOMDOAN="create table "+KEY_TABLE_NHOMDOAN+" ( "+KEY_MANHOM_NHOMDOAN+" text PRIMARY_KEY, "+KEY_TENNHOM_NHOMDOAN+" text not null)";

    public static String KEY_TABLE_MONAN="TB_MonAn";
    public static String KEY_MANHOM_MONAN="MaNhom";
    public static String KEY_MAMONAN_MONAN="MaMonAn";
    public static String KEY_TENMONAN_MONAN="TenMonAn";
    public static String KEY_GIOITHIEU_MONAN="GioiThieu";
    public static String KEY_LINKANH_MONAN="LinkAnh";
    public static String CREATE_TABLE_MONAN="create table "+KEY_TABLE_MONAN+" ( "+KEY_MANHOM_MONAN+" text, "+KEY_MAMONAN_MONAN+" text PRIMARY_KEY, "+KEY_TENMONAN_MONAN+" text not null,"+KEY_GIOITHIEU_MONAN+" text not null,"+KEY_LINKANH_MONAN+" text not null)";

    public static String KEY_TABLE_BANGGIA="TB_BangGia";
    public static String KEY_MAMONAN_BANGGIA="MaMonAn";
    public static String KEY_GIA_BANGGIA="Gia";
    public static String KEY_SOLUONG_BANGGIA="SoLuong";
    public static String CREATE_TABLE_BANGGIA="create table "+KEY_TABLE_BANGGIA+" ( "+KEY_MAMONAN_BANGGIA+" text not null, "+KEY_GIA_BANGGIA+" text not null, "+KEY_SOLUONG_BANGGIA+" text not null)";

}

