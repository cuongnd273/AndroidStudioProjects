package vn.edu.itplus_academy.tracnghiemtienganh.models;

import com.google.gson.annotations.Expose;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by VietUng on 08/04/2016.
 */
public class getCauhoiKT {
    @Expose
    private List<CauHoiKT> CauHoi = new ArrayList<CauHoiKT>();

    public List<CauHoiKT> getCauHoi() {
        return CauHoi;
    }

    public void setCauHoi(List<CauHoiKT> cauHoi) {
        CauHoi = cauHoi;
    }
}
