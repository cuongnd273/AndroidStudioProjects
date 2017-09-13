package com.cuongnguyen.appdoan.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by CUONG on 7/20/2016.
 */
public class MonAn implements Serializable{
    @Expose
    @SerializedName("manhom")
    private String MaNhom;
    @Expose
    @SerializedName("mamonan")
    private String MaMonAn;
    @Expose
    @SerializedName("tenmonan")
    private String TenMonAn;
    @Expose
    @SerializedName("gioithieu")
    private String GioiThieu;
    @Expose
    @SerializedName("linkanh")
    private String LinkAnh;

    public MonAn() {
    }

    public MonAn(String maNhom, String maMonAn, String tenMonAn, String gioiThieu, String linkAnh) {

        MaNhom = maNhom;
        MaMonAn = maMonAn;
        TenMonAn = tenMonAn;
        GioiThieu = gioiThieu;
        LinkAnh = linkAnh;
    }

    public String getMaNhom() {

        return MaNhom;
    }

    public void setMaNhom(String maNhom) {
        MaNhom = maNhom;
    }

    public String getMaMonAn() {
        return MaMonAn;
    }

    public void setMaMonAn(String maMonAn) {
        MaMonAn = maMonAn;
    }

    public String getTenMonAn() {
        return TenMonAn;
    }

    public void setTenMonAn(String tenMonAn) {
        TenMonAn = tenMonAn;
    }

    public String getGioiThieu() {
        return GioiThieu;
    }

    public void setGioiThieu(String gioiThieu) {
        GioiThieu = gioiThieu;
    }

    public String getLinkAnh() {
        return LinkAnh;
    }

    public void setLinkAnh(String linkAnh) {
        LinkAnh = linkAnh;
    }
}
