package vn.edu.itplus_academy.tracnghiemtienganh.models;

import com.google.gson.annotations.Expose;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by VietUng on 08/04/2016.
 */
public class GetCauhoiLT {

    @Expose
    private List<CauHoiLT> CauHoi = new ArrayList<CauHoiLT>();

    public List<CauHoiLT> getCauHoi() {
        return CauHoi;
    }

    public void setCauHoi(List<CauHoiLT> cauHoi) {
        CauHoi = cauHoi;
    }
}
