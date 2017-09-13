package com.example.app.goofood.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Place {
    @SerializedName("madiadiem")
    @Expose
    private String madiadiem;
    @SerializedName("tendiadiem")
    @Expose
    private String tendiadiem;
    @SerializedName("vitridialy")
    @Expose
    private String vitridialy;
    @SerializedName("dacdiemnoibat")
    @Expose
    private String dacdiemnoibat;
    @SerializedName("anh")
    @Expose
    private String anh;

    public String getMadiadiem() {
        return madiadiem;
    }

    public void setMadiadiem(String madiadiem) {
        this.madiadiem = madiadiem;
    }

    public String getTendiadiem() {
        return tendiadiem;
    }

    public void setTendiadiem(String tendiadiem) {
        this.tendiadiem = tendiadiem;
    }

    public String getVitridialy() {
        return vitridialy;
    }

    public void setVitridialy(String vitridialy) {
        this.vitridialy = vitridialy;
    }

    public String getDacdiemnoibat() {
        return dacdiemnoibat;
    }

    public void setDacdiemnoibat(String dacdiemnoibat) {
        this.dacdiemnoibat = dacdiemnoibat;
    }

    public String getAnh() {
        return anh;
    }

    public void setAnh(String anh) {
        this.anh = anh;
    }
}
