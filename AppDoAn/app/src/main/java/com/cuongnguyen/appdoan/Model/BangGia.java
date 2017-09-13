package com.cuongnguyen.appdoan.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by CUONG on 7/20/2016.
 */
public class BangGia {
    @Expose
    @SerializedName("mamonan")
    private String MaMonAn;
    @Expose
    @SerializedName("gia")
    private String Gia;
    @Expose
    @SerializedName("soluong")
    private String SoLuong;

    public BangGia(String maMonAn, String gia, String soLuong) {
        MaMonAn = maMonAn;
        Gia = gia;
        SoLuong = soLuong;
    }

    public BangGia() {

    }

    public String getMaMonAn() {

        return MaMonAn;
    }

    public void setMaMonAn(String maMonAn) {
        MaMonAn = maMonAn;
    }

    public String getGia() {
        return Gia;
    }

    public void setGia(String gia) {
        Gia = gia;
    }

    public String getSoLuong() {
        return SoLuong;
    }

    public void setSoLuong(String soLuong) {
        SoLuong = soLuong;
    }
}
