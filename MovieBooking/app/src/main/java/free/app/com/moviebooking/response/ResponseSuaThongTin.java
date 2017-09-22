package free.app.com.moviebooking.response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by cuong on 9/22/2017.
 */

public class ResponseSuaThongTin {
    @SerializedName("status")
    @Expose
    private int status;
    @SerializedName("mataikhoan")
    @Expose
    private int mataikhoan;
    @SerializedName("taikhoan")
    @Expose
    private String tentaikhoan;
    @SerializedName("cmnd")
    @Expose
    private String cmnd;
    @SerializedName("hoten")
    @Expose
    private String hoten;
    @SerializedName("ngaysinh")
    @Expose
    private String ngaysinh;
    @SerializedName("email")
    @Expose
    private String email;
    @SerializedName("sdt")
    @Expose
    private String sdt;
    @SerializedName("diachi")
    @Expose
    private String diachi;

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public int getMataikhoan() {
        return mataikhoan;
    }

    public void setMataikhoan(int mataikhoan) {
        this.mataikhoan = mataikhoan;
    }

    public String getTentaikhoan() {
        return tentaikhoan;
    }

    public void setTentaikhoan(String tentaikhoan) {
        this.tentaikhoan = tentaikhoan;
    }

    public String getCmnd() {
        return cmnd;
    }

    public void setCmnd(String cmnd) {
        this.cmnd = cmnd;
    }

    public String getHoten() {
        return hoten;
    }

    public void setHoten(String hoten) {
        this.hoten = hoten;
    }

    public String getNgaysinh() {
        return ngaysinh;
    }

    public void setNgaysinh(String ngaysinh) {
        this.ngaysinh = ngaysinh;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getDiachi() {
        return diachi;
    }

    public void setDiachi(String diachi) {
        this.diachi = diachi;
    }

    public String getSdt() {
        return sdt;
    }

    public void setSdt(String sdt) {
        this.sdt = sdt;
    }
}
