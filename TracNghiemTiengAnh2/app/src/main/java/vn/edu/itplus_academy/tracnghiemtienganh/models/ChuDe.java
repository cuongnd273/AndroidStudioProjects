package vn.edu.itplus_academy.tracnghiemtienganh.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by VietUng on 30/03/2016.
 */
public class ChuDe {

    @SerializedName("MaCD")
    @Expose
    private int macd;

    @SerializedName("ChuDe")
    @Expose
    private String TenCD;


    @SerializedName("LinkHTML")
    @Expose
    private String link;

    public int getMacd() {
        return macd;
    }

    public void setMacd(int macd) {
        this.macd = macd;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }


    public String getTenCD() {
        return TenCD;
    }

    public void setTenCD(String tenCD) {
        TenCD = tenCD;
    }
}
