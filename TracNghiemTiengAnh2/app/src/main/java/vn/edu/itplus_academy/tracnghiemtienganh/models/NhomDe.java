package vn.edu.itplus_academy.tracnghiemtienganh.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by VietUng on 25/05/2016.
 */
public class NhomDe {
    @SerializedName("MaND")
    @Expose
    private int mand;

    @SerializedName("TenND")
    @Expose
    private String TenND;

    public int getMand() {
        return mand;
    }

    public void setMand(int mand) {
        this.mand = mand;
    }

    public String getTenND() {
        return TenND;
    }

    public void setTenND(String tenND) {
        TenND = tenND;
    }
}
