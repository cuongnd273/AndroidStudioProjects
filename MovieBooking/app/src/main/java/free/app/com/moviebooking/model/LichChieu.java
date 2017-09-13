package free.app.com.moviebooking.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by CuongNguyen on 08/20/17.
 */

public class LichChieu {
    @SerializedName("malichchieu")
    @Expose
    private String malichchieu;
    @SerializedName("ngaychieu")
    @Expose
    private String ngaychieu;
    @SerializedName("batdau")
    @Expose
    private String batdau;
    @SerializedName("ketthuc")
    @Expose
    private String ketthuc;
    @SerializedName("phongchieu")
    @Expose
    private String phongchieu;

    public String getMalichchieu() {
        return malichchieu;
    }

    public void setMalichchieu(String malichchieu) {
        this.malichchieu = malichchieu;
    }

    public String getNgaychieu() {
        return ngaychieu;
    }

    public void setNgaychieu(String ngaychieu) {
        this.ngaychieu = ngaychieu;
    }

    public String getBatdau() {
        return batdau;
    }

    public void setBatdau(String batdau) {
        this.batdau = batdau;
    }

    public String getKetthuc() {
        return ketthuc;
    }

    public void setKetthuc(String ketthuc) {
        this.ketthuc = ketthuc;
    }

    public String getPhongchieu() {
        return phongchieu;
    }

    public void setPhongchieu(String phongchieu) {
        this.phongchieu = phongchieu;
    }
}
