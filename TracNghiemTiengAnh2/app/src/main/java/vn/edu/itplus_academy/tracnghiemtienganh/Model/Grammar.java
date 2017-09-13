package vn.edu.itplus_academy.tracnghiemtienganh.Model;

/**
 * Created by tuananh on 09/03/2016.
 */
public class Grammar {
    private int imgItemGrammar;
    private String txtItemGrammar;

    public Grammar(int imgItemGrammar, String txtItemGrammar) {
        this.imgItemGrammar = imgItemGrammar;
        this.txtItemGrammar = txtItemGrammar;
    }

    public int getImgItemGrammar() {
        return imgItemGrammar;
    }

    public void setImgItemGrammar(int imgItemGrammar) {
        this.imgItemGrammar = imgItemGrammar;
    }

    public String getTxtItemGrammar() {
        return txtItemGrammar;
    }

    public void setTxtItemGrammar(String txtItemGrammar) {
        this.txtItemGrammar = txtItemGrammar;
    }
}
