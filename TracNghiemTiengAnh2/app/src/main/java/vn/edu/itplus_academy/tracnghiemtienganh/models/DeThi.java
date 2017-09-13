package vn.edu.itplus_academy.tracnghiemtienganh.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by VietUng on 25/05/2016.
 */
public class DeThi {

    @SerializedName("MaDT")
    @Expose
    private int madt;

    @SerializedName("MaND")
    @Expose
    private int mand;

    @SerializedName("LinkDT")
    @Expose
    private String LinkDT;


    @SerializedName("ThoiGian")
    @Expose
    private int thoigian;

    public String getLinkDT() {
        return LinkDT;
    }

    public void setLinkDT(String linkDT) {
        LinkDT = linkDT;
    }

    public int getMadt() {
        return madt;
    }

    public void setMadt(int madt) {
        this.madt = madt;
    }

    public int getMand() {
        return mand;
    }

    public void setMand(int mand) {
        this.mand = mand;
    }

    public int getThoigian() {
        return thoigian;
    }

    public void setThoigian(int thoigian) {
        this.thoigian = thoigian;
    }
}
