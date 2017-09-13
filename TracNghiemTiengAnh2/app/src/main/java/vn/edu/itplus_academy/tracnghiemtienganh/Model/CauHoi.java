package vn.edu.itplus_academy.tracnghiemtienganh.Model;

import java.io.Serializable;
import java.util.List;

/**
 * Created by tuananh on 09/04/2016.
 */
public class CauHoi implements Serializable {
    private int txtID;
    private String txtCauHoi;
    private List<DapAn> dapAnList;
    private int IDDapAnDung;
    private int ShowDapAn;
    private int DapAn;
    private int Time;

    public CauHoi(int txtID, String txtCauHoi, List<vn.edu.itplus_academy.tracnghiemtienganh.Model.DapAn> dapAnList, int IDDapAnDung, int showDapAn, int dapAn, int time) {
        this.txtID = txtID;
        this.txtCauHoi = txtCauHoi;
        this.dapAnList = dapAnList;
        this.IDDapAnDung = IDDapAnDung;
        ShowDapAn = showDapAn;
        DapAn = dapAn;
        Time = time;
    }

    public CauHoi(int txtID, String txtCauHoi, List<vn.edu.itplus_academy.tracnghiemtienganh.Model.DapAn> dapAnList, int IDDapAnDung, int showDapAn, int dapAn) {
        this.txtID = txtID;
        this.txtCauHoi = txtCauHoi;
        this.dapAnList = dapAnList;
        this.IDDapAnDung = IDDapAnDung;
        ShowDapAn = showDapAn;
        DapAn = dapAn;
    }

    public CauHoi(int txtID, String txtCauHoi, List<DapAn> dapAnList, int IDDapAnDung) {
        this.txtID = txtID;
        this.txtCauHoi = txtCauHoi;
        this.dapAnList = dapAnList;
        this.IDDapAnDung = IDDapAnDung;
    }

    public int getTxtID() {
        return txtID;
    }

    public void setTxtID(int txtID) {
        this.txtID = txtID;
    }

    public String getTxtCauHoi() {
        return txtCauHoi;
    }

    public void setTxtCauHoi(String txtCauHoi) {
        this.txtCauHoi = txtCauHoi;
    }

    public List<DapAn> getDapAnList() {
        return dapAnList;
    }

    public void setDapAnList(List<DapAn> dapAnList) {
        this.dapAnList = dapAnList;
    }

    public int getIDDapAnDung() {
        return IDDapAnDung;
    }

    public void setIDDapAnDung(int IDDapAnDung) {
        this.IDDapAnDung = IDDapAnDung;
    }

    public int getShowDapAn() {
        return ShowDapAn;
    }

    public void setShowDapAn(int showDapAn) {
        ShowDapAn = showDapAn;
    }

    public int getDapAn() {
        return DapAn;
    }

    public void setDapAn(int dapAn) {
        DapAn = dapAn;
    }

    public int getTime() {
        return Time;
    }

    public void setTime(int time) {
        Time = time;
    }
}
