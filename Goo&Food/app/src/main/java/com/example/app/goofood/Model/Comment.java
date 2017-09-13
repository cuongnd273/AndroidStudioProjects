package com.example.app.goofood.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Comment {
    @SerializedName("tennguoidang")
    @Expose
    private String tennguoidang;
    @SerializedName("binhluan")
    @Expose
    private String binhluan;

    public String getBinhluan() {
        return binhluan;
    }

    public void setBinhluan(String binhluan) {
        this.binhluan = binhluan;
    }

    public String getTennguoidang() {
        return tennguoidang;
    }

    public void setTennguoidang(String tennguoidang) {
        this.tennguoidang = tennguoidang;
    }
}
