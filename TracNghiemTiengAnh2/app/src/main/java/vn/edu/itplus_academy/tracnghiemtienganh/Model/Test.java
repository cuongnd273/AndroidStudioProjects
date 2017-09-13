package vn.edu.itplus_academy.tracnghiemtienganh.Model;

/**
 * Created by tuananh on 06/04/2016.
 */
public class Test {
    public int txtID;
    public String txtName;
    private int txtTime;
    private int txtProgressBarTest;

    public Test(int txtID, String txtName, int txtTime, int txtProgressBarTest) {
        this.txtID = txtID;
        this.txtName = txtName;
        this.txtTime = txtTime;
        this.txtProgressBarTest = txtProgressBarTest;
    }

    public int getTxtProgressBarTest() {
        return txtProgressBarTest;
    }

    public void setTxtProgressBarTest(int txtProgressBarTest) {
        this.txtProgressBarTest = txtProgressBarTest;
    }

    public int getTxtID() {
        return txtID;
    }

    public void setTxtID(int txtID) {
        this.txtID = txtID;
    }

    public String getTxtName() {
        return txtName;
    }

    public void setTxtName(String txtName) {
        this.txtName = txtName;
    }

    public int getTxtTime() {
        return txtTime;
    }

    public void setTxtTime(int txtTime) {
        this.txtTime = txtTime;
    }
}
