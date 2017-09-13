package vn.edu.itplus_academy.tracnghiemtienganh.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by VietUng on 25/05/2016.
 */
public class DapAn {

    @SerializedName("MaDA")
    @Expose
    private int mada;

    @SerializedName("DA")
    @Expose
    private String da;

    public String getDa() {
        return da;
    }

    public void setDa(String da) {
        this.da = da;
    }

    public int getMada() {
        return mada;
    }

    public void setMada(int mada) {
        this.mada = mada;
    }
}
