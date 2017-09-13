package com.example.app.goofood.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Food {
    @SerializedName("mamonan")
    @Expose
    private String mamonan;
    @SerializedName("tenmonan")
    @Expose
    private String tenmonan;
    @SerializedName("thongtinmonan")
    @Expose
    private String thongtinmonan;
    @SerializedName("anh")
    @Expose
    private String anh;

    public String getMamonan() {
        return mamonan;
    }

    public void setMamonan(String mamonan) {
        this.mamonan = mamonan;
    }

    public String getTenmonan() {
        return tenmonan;
    }

    public void setTenmonan(String tenmonan) {
        this.tenmonan = tenmonan;
    }

    public String getThongtinmonan() {
        return thongtinmonan;
    }

    public void setThongtinmonan(String thongtinmonan) {
        this.thongtinmonan = thongtinmonan;
    }

    public String getAnh() {
        return anh;
    }

    public void setAnh(String anh) {
        this.anh = anh;
    }
}
