package vn.edu.itplus_academy.thitracnghiem.Model;

/**
 * Created by tuananh on 6/30/2016.
 */
public class ThiSinh {
    private String maDuThi;
    private String sodienthoai;
    private String maChuyenNganh;
    private String statusThiSinh;
    public ThiSinh() {
    }

    public ThiSinh(String maDuThi, String maChuyenNganh, String statusThiSinh) {
        this.maDuThi = maDuThi;
        this.maChuyenNganh = maChuyenNganh;
        this.statusThiSinh = statusThiSinh;
    }

    public ThiSinh(String maDuThi, String sodienthoai, String maChuyenNganh, String statusThiSinh) {
        this.maDuThi = maDuThi;
        this.sodienthoai = sodienthoai;
        this.maChuyenNganh = maChuyenNganh;
        this.statusThiSinh = statusThiSinh;
    }

    public String getMaDuThi() {
        return maDuThi;
    }

    public void setMaDuThi(String maDuThi) {
        this.maDuThi = maDuThi;
    }

    public String getSodienthoai() {
        return sodienthoai;
    }

    public void setSodienthoai(String sodienthoai) {
        this.sodienthoai = sodienthoai;
    }

    public String getMaChuyenNganh() {
        return maChuyenNganh;
    }

    public void setMaChuyenNganh(String maChuyenNganh) {
        this.maChuyenNganh = maChuyenNganh;
    }

    public String getStatusThiSinh() {
        return statusThiSinh;
    }

    public void setStatusThiSinh(String statusThiSinh) {
        this.statusThiSinh = statusThiSinh;
    }


}