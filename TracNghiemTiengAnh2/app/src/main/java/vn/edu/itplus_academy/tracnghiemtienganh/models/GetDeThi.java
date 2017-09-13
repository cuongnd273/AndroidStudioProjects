package vn.edu.itplus_academy.tracnghiemtienganh.models;

import com.google.gson.annotations.Expose;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by VietUng on 25/05/2016.
 */
public class GetDeThi {

    @Expose
    private List<DeThi> DeThi = new ArrayList<DeThi>();

    public List<DeThi> getDeThi() {
        return DeThi;
    }

    public void setDeThi(List<DeThi> deThi) {
        DeThi = deThi;
    }
}
