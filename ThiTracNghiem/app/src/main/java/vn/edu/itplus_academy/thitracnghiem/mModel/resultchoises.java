package vn.edu.itplus_academy.thitracnghiem.mModel;

import com.google.gson.annotations.Expose;

/**
 * Created by tuananh on 7/2/2016.
 */
public class resultchoises {
    @Expose
    private int aid;
    @Expose
    private int qid;

    public resultchoises() {
    }

    public resultchoises(int aid, int qid) {
        this.aid = aid;
        this.qid = qid;
    }

    public int getAid() {
        return aid;
    }

    public void setAid(int aid) {
        this.aid = aid;
    }

    public int getQid() {
        return qid;
    }

    public void setQid(int qid) {
        this.qid = qid;
    }
}
