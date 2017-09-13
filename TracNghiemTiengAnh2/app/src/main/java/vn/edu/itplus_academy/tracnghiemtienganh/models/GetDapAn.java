package vn.edu.itplus_academy.tracnghiemtienganh.models;

import com.google.gson.annotations.Expose;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by VietUng on 25/05/2016.
 */
public class GetDapAn {

    @Expose
    private List<DapAn> DapAn = new ArrayList<DapAn>();

    public List<DapAn> getDapAn() {
        return DapAn;
    }

    public void setDapAn(List<DapAn> dapAn) {
        DapAn = dapAn;
    }
}
