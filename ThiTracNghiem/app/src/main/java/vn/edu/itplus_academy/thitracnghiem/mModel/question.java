package vn.edu.itplus_academy.thitracnghiem.mModel;

import com.google.gson.annotations.Expose;

/**
 * Created by tuananh on 7/1/2016.
 */
public class question {
    @Expose
    private int id ;
    @Expose
    private String name;
    @Expose
    private String value;
    @Expose
    private int eid;
    @Expose
    private int tqid;
    @Expose
    private String answer;

    public question() {
    }

    public question(int id, String name, String value, int eid, int tqid, String answer) {
        this.id = id;
        this.name = name;
        this.value = value;
        this.eid = eid;
        this.tqid = tqid;
        this.answer = answer;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public int getEid() {
        return eid;
    }

    public void setEid(int eid) {
        this.eid = eid;
    }

    public int getTqid() {
        return tqid;
    }

    public void setTqid(int tqid) {
        this.tqid = tqid;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }
}
