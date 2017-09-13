package vn.edu.itplus_academy.thitracnghiem.mModel;

import com.google.gson.annotations.Expose;

/**
 * Created by tuananh on 7/2/2016.
 */
public class resulttexts {
    @Expose
    private int qid;
    @Expose
    private String value;

    public resulttexts() {
    }

    public resulttexts(int qid, String value) {
        this.qid = qid;
        this.value = value;
    }

    public int getQid() {
        return qid;
    }

    public void setQid(int qid) {
        this.qid = qid;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
