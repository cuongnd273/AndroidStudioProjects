package vn.edu.itplus_academy.tracnghiemtienganh.Model;

/**
 * Created by tuananh on 12/04/2016.
 */
public class UngDung {
    private int ing;
    private String txtName;
    private String txtThongTin;
    private String link;

    public UngDung(int ing, String txtName, String txtThongTin, String link) {
        this.ing = ing;
        this.txtName = txtName;
        this.txtThongTin = txtThongTin;
        this.link = link;
    }

    public int getIng() {
        return ing;
    }

    public void setIng(int ing) {
        this.ing = ing;
    }

    public String getTxtName() {
        return txtName;
    }

    public void setTxtName(String txtName) {
        this.txtName = txtName;
    }

    public String getTxtThongTin() {
        return txtThongTin;
    }

    public void setTxtThongTin(String txtThongTin) {
        this.txtThongTin = txtThongTin;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }
}
