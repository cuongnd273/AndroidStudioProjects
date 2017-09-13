package com.cuongnguyen.appdoan.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by CUONG on 7/20/2016.
 */
public class NhomDoAn {
    @Expose
    @SerializedName("manhom")
    private String MaNhom;
    @Expose
    @SerializedName("tennhom")
    private String TenNhom;

    public NhomDoAn(String maNhom, String tenNhom) {
        MaNhom = maNhom;
        TenNhom = tenNhom;
    }

    public NhomDoAn() {

    }

    public String getMaNhom() {

        return MaNhom;
    }

    public void setMaNhom(String maNhom) {
        MaNhom = maNhom;
    }

    public String getTenNhom() {
        return TenNhom;
    }

    public void setTenNhom(String tenNhom) {
        TenNhom = tenNhom;
    }
}
