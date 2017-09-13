package vn.edu.itplus_academy.tracnghiemtienganh.models;

/**
 * Created by VietUng on 12/03/2016.
 */
public class Test {

    private int txtItemTestID;
    private String txtItemTest;
    private int txtProgressBarItemTest;

    public Test(int txtItemTestID, String txtItemTest, int txtProgressBarItemTest) {
        this.txtItemTestID = txtItemTestID;
        this.txtItemTest = txtItemTest;
        this.txtProgressBarItemTest = txtProgressBarItemTest;
    }

    public int getTxtItemTestID() {
        return txtItemTestID;
    }

    public void setTxtItemTestID(int txtItemTestID) {
        this.txtItemTestID = txtItemTestID;
    }

    public String getTxtItemTest() {
        return txtItemTest;
    }

    public void setTxtItemTest(String txtItemTest) {
        this.txtItemTest = txtItemTest;
    }

    public int getTxtProgressBarItemTest() {
        return txtProgressBarItemTest;
    }

    public void setTxtProgressBarItemTest(int txtProgressBarItemTest) {
        this.txtProgressBarItemTest = txtProgressBarItemTest;
    }
}
