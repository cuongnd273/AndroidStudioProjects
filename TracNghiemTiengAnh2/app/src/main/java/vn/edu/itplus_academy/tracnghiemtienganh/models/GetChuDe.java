package vn.edu.itplus_academy.tracnghiemtienganh.models;

import com.google.gson.annotations.Expose;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by VietUng on 30/03/2016.
 */
public class GetChuDe {

    @Expose
    private List<ChuDe> ChuDe = new ArrayList<ChuDe>();

    public List<ChuDe> getChuDe() {
        return ChuDe;
    }

    public void setChuDe(List<ChuDe> chuDe) {
        ChuDe = chuDe;
    }
}
