package vn.edu.itplus_academy.thitracnghiem.mModel;

import com.google.gson.annotations.Expose;

/**
 * Created by tuananh on 7/1/2016.
 */
public class answer {
    @Expose
    private int id;
    @Expose
    private int qid;
    @Expose
    private String label;

    public answer() {
    }

    public answer(int id, int qid, String label) {
        this.id = id;
        this.qid = qid;
        this.label = label;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getQid() {
        return qid;
    }

    public void setQid(int qid) {
        this.qid = qid;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }
}
