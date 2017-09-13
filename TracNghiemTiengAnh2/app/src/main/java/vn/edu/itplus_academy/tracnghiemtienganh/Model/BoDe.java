package vn.edu.itplus_academy.tracnghiemtienganh.Model;

/**
 * Created by tuananh on 18/05/2016.
 */
public class BoDe {
    public int txtID;
    public String txtName;

    public BoDe(int txtID, String txtName) {
        this.txtID = txtID;
        this.txtName = txtName;
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
}
