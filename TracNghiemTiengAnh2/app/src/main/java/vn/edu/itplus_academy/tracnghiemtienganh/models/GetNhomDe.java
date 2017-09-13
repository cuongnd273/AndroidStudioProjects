package vn.edu.itplus_academy.tracnghiemtienganh.models;

import com.google.gson.annotations.Expose;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by VietUng on 25/05/2016.
 */
public class GetNhomDe {

    @Expose
    private List<NhomDe> NhomDe = new ArrayList<NhomDe>();

    public List<NhomDe> getNhomDe() {
        return NhomDe;
    }

    public void setNhomDe(List<NhomDe> nhomDe) {
        NhomDe = nhomDe;
    }
}
