package free.app.com.moviebooking.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by CuongNguyen on 08/17/17.
 */

public class Phim {
    @SerializedName("maphim")
    @Expose
    private String maphim;
    @SerializedName("tenphim")
    @Expose
    private String tenphim;
    @SerializedName("gia")
    @Expose
    private String gia;
    @SerializedName("ngaybatdau")
    @Expose
    private String ngaybatdau;
    @SerializedName("ngayketthuc")
    @Expose
    private String ngayketthuc;
    @SerializedName("daodien")
    @Expose
    private String daodien;
    @SerializedName("dienvien")
    @Expose
    private String dienvien;
    @SerializedName("thoiluong")
    @Expose
    private String thoiluong;
    @SerializedName("anh")
    @Expose
    private String anh;
    @SerializedName("tomtat")
    @Expose
    private String tomtat;

    public String getGia() {
        return gia;
    }

    public void setGia(String gia) {
        this.gia = gia;
    }

    public String getMaphim() {
        return maphim;
    }

    public void setMaphim(String maphim) {
        this.maphim = maphim;
    }

    public String getTenphim() {
        return tenphim;
    }

    public void setTenphim(String tenphim) {
        this.tenphim = tenphim;
    }

    public String getNgaybatdau() {
        return ngaybatdau;
    }

    public void setNgaybatdau(String ngaybatdau) {
        this.ngaybatdau = ngaybatdau;
    }

    public String getNgayketthuc() {
        return ngayketthuc;
    }

    public void setNgayketthuc(String ngayketthuc) {
        this.ngayketthuc = ngayketthuc;
    }

    public String getDaodien() {
        return daodien;
    }

    public void setDaodien(String daodien) {
        this.daodien = daodien;
    }

    public String getDienvien() {
        return dienvien;
    }

    public void setDienvien(String dienvien) {
        this.dienvien = dienvien;
    }

    public String getThoiluong() {
        return thoiluong;
    }

    public void setThoiluong(String thoiluong) {
        this.thoiluong = thoiluong;
    }

    public String getAnh() {
        return anh;
    }

    public void setAnh(String anh) {
        this.anh = anh;
    }

    public String getTomtat() {
        return tomtat;
    }

    public void setTomtat(String tomtat) {
        this.tomtat = tomtat;
    }

}
