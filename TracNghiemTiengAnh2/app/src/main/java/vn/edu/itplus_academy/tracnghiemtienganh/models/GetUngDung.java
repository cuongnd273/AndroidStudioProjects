package vn.edu.itplus_academy.tracnghiemtienganh.models;

import com.google.gson.annotations.Expose;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by VietUng on 09/04/2016.
 */
public class GetUngDung {

    @Expose
    private List<UngDung> UngDung = new ArrayList<UngDung>();

    public List<UngDung> getUngDung() {
        return UngDung;
    }

    public void setUngDung(List<UngDung> ungDung) {
        UngDung = ungDung;
    }
}
