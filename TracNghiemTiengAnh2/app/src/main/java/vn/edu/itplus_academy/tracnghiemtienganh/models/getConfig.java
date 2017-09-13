package vn.edu.itplus_academy.tracnghiemtienganh.models;

import com.google.gson.annotations.Expose;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by VietUng on 04/04/2016.
 */
public class getConfig {

    @Expose
    private List<Config> CauHinh = new ArrayList<Config>();

    public List<Config> getCauHinh() {
        return CauHinh;
    }

    public void setCauHinh(List<Config> cauHinh) {
        CauHinh = cauHinh;
    }
}
