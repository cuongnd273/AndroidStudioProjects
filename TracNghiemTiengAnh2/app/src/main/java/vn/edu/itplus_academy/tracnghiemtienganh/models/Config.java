package vn.edu.itplus_academy.tracnghiemtienganh.models;

import com.google.gson.annotations.Expose;

/**
 * Created by VietUng on 04/04/2016.
 */
public class Config {

    @Expose
    private int MaCH;

    @Expose
    private String CauHinh;

    @Expose
    private String LinkCH;

    @Expose
    private int MaxID;

    public String getCauHinh() {
        return CauHinh;
    }

    public void setCauHinh(String cauHinh) {
        CauHinh = cauHinh;
    }

    public String getLinkCH() {
        return LinkCH;
    }

    public void setLinkCH(String linkCH) {
        LinkCH = linkCH;
    }

    public int getMaCH() {
        return MaCH;
    }

    public void setMaCH(int maCH) {
        MaCH = maCH;
    }

    public int getMaxID() {
        return MaxID;
    }

    public void setMaxID(int maxID) {
        MaxID = maxID;
    }
}
