package vn.edu.itplus_academy.thitracnghiem.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by tuananh on 6/28/2016.
 */
public class MonThi {
    @SerializedName("id")
    @Expose
    private int maMonThi;
    @SerializedName("name")
    @Expose
    private String titleMonThi;
    private String imgMonThi;
    private String statusMonThi;

    public MonThi() {
    }

    public MonThi(int maMonThi, String titleMonThi, String statusMonThi) {
        this.maMonThi = maMonThi;
        this.titleMonThi = titleMonThi;
        this.statusMonThi = statusMonThi;
    }

    public MonThi(String titleMonThi, String imgMonThi) {
        this.titleMonThi = titleMonThi;
        this.imgMonThi = imgMonThi;
    }

    public String getTitleMonThi() {
        return titleMonThi;
    }

    public void setTitleMonThi(String titleMonThi) {
        this.titleMonThi = titleMonThi;
    }

    public String getImgMonThi() {
        return imgMonThi;
    }

    public void setImgMonThi(String imgMonThi) {
        this.imgMonThi = imgMonThi;
    }

    public int getMaMonThi() {
        return maMonThi;
    }

    public void setMaMonThi(int maMonThi) {
        this.maMonThi = maMonThi;
    }

    public String getStatusMonThi() {
        return statusMonThi;
    }

    public void setStatusMonThi(String statusMonThi) {
        this.statusMonThi = statusMonThi;
    }
}
