package com.example.app.goofood.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Province {
    @SerializedName("matinh")
    @Expose
    private String matinh;
    @SerializedName("tentinh")
    @Expose
    private String tentinh;
    @SerializedName("gioithieu")
    @Expose
    private String gioithieu;
    @SerializedName("lichsu")
    @Expose
    private String lichsu;
    @SerializedName("khihau")
    @Expose
    private String khihau;
    @SerializedName("anh")
    @Expose
    private String anh;

    public String getMatinh() {
        return matinh;
    }

    public void setMatinh(String matinh) {
        this.matinh = matinh;
    }

    public String getTentinh() {
        return tentinh;
    }

    public void setTentinh(String tentinh) {
        this.tentinh = tentinh;
    }

    public String getGioithieu() {
        return gioithieu;
    }

    public void setGioithieu(String gioithieu) {
        this.gioithieu = gioithieu;
    }

    public String getLichsu() {
        return lichsu;
    }

    public void setLichsu(String lichsu) {
        this.lichsu = lichsu;
    }

    public String getKhihau() {
        return khihau;
    }

    public void setKhihau(String khihau) {
        this.khihau = khihau;
    }

    public String getAnh() {
        return anh;
    }

    public void setAnh(String anh) {
        this.anh = anh;
    }
}
