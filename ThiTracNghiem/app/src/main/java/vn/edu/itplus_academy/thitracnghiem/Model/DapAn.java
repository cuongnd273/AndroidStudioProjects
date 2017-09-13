package vn.edu.itplus_academy.thitracnghiem.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by tuananh on 14/03/2016.
 */

public class DapAn implements Serializable {
    @Expose
    @SerializedName("id")
    private int maDapAn;
    @Expose
    @SerializedName("qid")
    private String txtID;
    @Expose
    @SerializedName("label")
    private String txtDapAn;

    public DapAn() {
    }

    public DapAn(int maDapAn, String txtID, String txtDapAn) {
        this.maDapAn = maDapAn;
        this.txtID = txtID;
        this.txtDapAn = txtDapAn;
    }

    public DapAn(String txtID , String txtDapAn) {
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

    public int getMaDapAn() {
        return maDapAn;
    }

    public void setMaDapAn(int maDapAn) {
        this.maDapAn = maDapAn;
    }
}
