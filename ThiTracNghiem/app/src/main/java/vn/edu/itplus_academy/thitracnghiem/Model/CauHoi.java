package vn.edu.itplus_academy.thitracnghiem.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

/**
 * Created by tuananh on 09/04/2016.
 */
public class CauHoi implements Serializable {
    @Expose
    @SerializedName("id")
    private int maCauHoi;
    @Expose
    @SerializedName("answer")
    private String statusCauHoi;
    @Expose
    @SerializedName("name")
    private String txtID;
    @Expose
    @SerializedName("value")
    private String txtCauHoi;
    private List<DapAn> dapAnList;
    private int dapAn;
    private String dapAnTxt;
    @Expose
    @SerializedName("tqid")
    private int maMonThi;


    public CauHoi() {
    }

    public CauHoi(int maCauHoi, String statusCauHoi, String txtID, String txtCauHoi, List<DapAn> dapAnList, int dapAn, String dapAnTxt, int maMonThi) {
        this.maCauHoi = maCauHoi;
        this.statusCauHoi = statusCauHoi;
        this.txtID = txtID;
        this.txtCauHoi = txtCauHoi;
        this.dapAnList = dapAnList;
        this.dapAn = dapAn;
        this.dapAnTxt = dapAnTxt;
        this.maMonThi = maMonThi;
    }

    public CauHoi(String txtID, String txtCauHoi, List<DapAn> dapAnList, int dapAn, int maMonThi) {
        this.txtID = txtID;
        this.txtCauHoi = txtCauHoi;
        this.dapAnList = dapAnList;
        this.dapAn = dapAn;
        this.maMonThi = maMonThi;
    }

    public CauHoi(String txtID, String txtCauHoi, List<DapAn> dapAnList, int dapAn) {
        this.txtID = txtID;
        this.txtCauHoi = txtCauHoi;
        this.dapAnList = dapAnList;
        this.dapAn = dapAn;
    }

    public int getMaCauHoi() {
        return maCauHoi;
    }

    public void setMaCauHoi(int maCauHoi) {
        this.maCauHoi = maCauHoi;
    }

    public String getTxtID() {
        return txtID;
    }

    public void setTxtID(String txtID) {
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

    public int getDapAn() {
        return dapAn;
    }

    public void setDapAn(int dapAn) {
        this.dapAn = dapAn;
    }

    public int getMaMonThi() {
        return maMonThi;
    }

    public void setMaMonThi(int maMonThi) {
        this.maMonThi = maMonThi;
    }

    public String getStatusCauHoi() {
        return statusCauHoi;
    }

    public void setStatusCauHoi(String statusCauHoi) {
        this.statusCauHoi = statusCauHoi;
    }

    public String getDapAnTxt() {
        return dapAnTxt;
    }

    public void setDapAnTxt(String dapAnTxt) {
        this.dapAnTxt = dapAnTxt;
    }
}
