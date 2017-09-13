package free.app.com.moviebooking.model;

import android.content.Context;
import android.content.SharedPreferences;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by CuongNguyen on 08/23/17.
 */

public class User {
    @SerializedName("mataikhoan")
    @Expose
    private int mataikhoan;
    @SerializedName("taikhoan")
    @Expose
    private String taikhoan;
    @SerializedName("cmnd")
    @Expose
    private String cmnd;
    @SerializedName("hoten")
    @Expose
    private String hoten;
    @SerializedName("ngaysinh")
    @Expose
    private String ngaysinh;
    @SerializedName("sdt")
    @Expose
    private String sdt;
    @SerializedName("email")
    @Expose
    private String email;
    @SerializedName("diachi")
    @Expose
    private String diachi;


    public int getMataikhoan() {
        return mataikhoan;
    }

    public void setMataikhoan(int mataikhoan) {
        this.mataikhoan = mataikhoan;
    }

    public String getTaikhoan() {
        return taikhoan;
    }

    public void setTaikhoan(String taikhoan) {
        this.taikhoan = taikhoan;
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

    public static boolean setUser(Context context, User user){
        SharedPreferences sharedPreferences=context.getSharedPreferences("user",Context.MODE_PRIVATE);
        SharedPreferences.Editor editor=sharedPreferences.edit();
        editor.putInt("mataikhoan",user.getMataikhoan());
        editor.putString("taikhoan",user.getTaikhoan());
        editor.putString("cmnd",user.getCmnd());
        editor.putString("hoten",user.getHoten());
        editor.putString("ngaysinh",user.getNgaysinh());
        editor.putString("email",user.getEmail());
        editor.putString("sdt",user.getSdt());
        editor.putString("diachi",user.getDiachi());
        return editor.commit();
    }
    public static User getUser(Context context){
        SharedPreferences sharedPreferences=context.getSharedPreferences("user",Context.MODE_PRIVATE);
        User user=new User();
        user.setMataikhoan(sharedPreferences.getInt("mataikhoan",0));
        user.setTaikhoan(sharedPreferences.getString("taikhoan",""));
        user.setCmnd(sharedPreferences.getString("cmnd",""));
        user.setHoten(sharedPreferences.getString("hoten",""));
        user.setNgaysinh(sharedPreferences.getString("ngaysinh",""));
        user.setEmail(sharedPreferences.getString("email",""));
        user.setSdt(sharedPreferences.getString("sdt",""));
        user.setDiachi(sharedPreferences.getString("diachi",""));
        return user;
    }
    public static boolean deleteUser(Context context){
        SharedPreferences sharedPreferences=context.getSharedPreferences("user",Context.MODE_PRIVATE);
        SharedPreferences.Editor editor=sharedPreferences.edit();
        editor.putInt("mataikhoan",0);
        editor.putString("taikhoan","");
        editor.putString("cmnd","");
        editor.putString("hoten","");
        editor.putString("ngaysinh","");
        editor.putString("email","");
        editor.putString("sdt","");
        editor.putString("diachi","");
        return editor.commit();
    }
}
