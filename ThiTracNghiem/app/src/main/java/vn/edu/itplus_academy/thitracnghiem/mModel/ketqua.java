package vn.edu.itplus_academy.thitracnghiem.mModel;

import com.google.gson.annotations.Expose;

import java.util.List;

/**
 * Created by tuananh on 7/2/2016.
 */
public class ketqua {
    @Expose
    private String code;
    @Expose
    private String phonenum;
    @Expose
    private List<resultchoises> resultchoises;
    @Expose
    private List<resulttexts> resulttexts;
    @Expose
    private int tqid;

    public ketqua() {
    }

    public ketqua(String code, String phonenum, List<resultchoises> resultchoises, List<resulttexts> resulttexts, int tqid) {
        this.code = code;
        this.phonenum = phonenum;
        this.resultchoises = resultchoises;
        this.resulttexts = resulttexts;
        this.tqid = tqid;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getPhonenum() {
        return phonenum;
    }

    public void setPhonenum(String phonenum) {
        this.phonenum = phonenum;
    }

    public List<resultchoises> getResultchoises() {
        return resultchoises;
    }

    public void setResultchoises(List<resultchoises> resultchoises) {
        this.resultchoises = resultchoises;
    }

    public List<resulttexts> getResulttexts() {
        return resulttexts;
    }

    public void setResulttexts(List<resulttexts> resulttexts) {
        this.resulttexts = resulttexts;
    }

    public int getTqid() {
        return tqid;
    }

    public void setTqid(int tqid) {
        this.tqid = tqid;
    }
}
