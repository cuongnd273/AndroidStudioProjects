package vn.edu.itplus_academy.tracnghiemtienganh.models;

/**
 * Created by VietUng on 15/03/2016.
 */
public class Question {
    private String da;
    private String answer;

    public Question() {
    }

    public Question( String da,String answer) {
        this.answer = answer;
        this.da = da;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public String getDa() {
        return da;
    }

    public void setDa(String da) {
        this.da = da;
    }
}
