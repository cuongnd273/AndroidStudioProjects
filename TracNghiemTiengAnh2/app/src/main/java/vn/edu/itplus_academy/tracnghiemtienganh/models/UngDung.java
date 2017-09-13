package vn.edu.itplus_academy.tracnghiemtienganh.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by VietUng on 09/04/2016.
 */
public class UngDung {

    @SerializedName("MaUD")
    @Expose
    private int maud;

    @SerializedName("UngDung")
    @Expose
    private String tenud;

    @SerializedName("Anh")
    @Expose
    private String anh;

    public String getAnh() {
        return anh;
    }

    public void setAnh(String anh) {
        this.anh = anh;
    }



    @SerializedName("LinkUD")
    @Expose
    private String linkud;

    @SerializedName("MoTa")
    @Expose
    private String mota;

    public String getLinkud() {
        return linkud;
    }

    public void setLinkud(String linkud) {
        this.linkud = linkud;
    }

    public int getMaud() {
        return maud;
    }

    public void setMaud(int maud) {
        this.maud = maud;
    }

    public String getMota() {
        return mota;
    }

    public void setMota(String mota) {
        this.mota = mota;
    }

    public String getTenud() {
        return tenud;
    }

    public void setTenud(String tenud) {
        this.tenud = tenud;
    }
}
