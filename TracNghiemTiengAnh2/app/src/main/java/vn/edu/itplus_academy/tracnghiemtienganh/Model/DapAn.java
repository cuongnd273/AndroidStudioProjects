package vn.edu.itplus_academy.tracnghiemtienganh.Model;

import java.io.Serializable;

/**
 * Created by tuananh on 14/03/2016.
 */
public class DapAn implements Serializable {
    private String txtID;
    private String txtDapAn;

    public DapAn( String txtID ,String txtDapAn) {
        this.txtDapAn = txtDapAn;
        this.txtID = txtID;
    }

    public String getTxtID() {
        return txtID;
    }

    public void setTxtID(String txtID) {
        this.txtID = txtID;
    }

    public String getTxtDapAn() {
        return txtDapAn;
    }

    public void setTxtDapAn(String txtDapAn) {
        this.txtDapAn = txtDapAn;
    }
}
