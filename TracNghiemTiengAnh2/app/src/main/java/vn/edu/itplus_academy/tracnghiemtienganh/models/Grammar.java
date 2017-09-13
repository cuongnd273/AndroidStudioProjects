package vn.edu.itplus_academy.tracnghiemtienganh.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by VietUng on 12/03/2016.
 */
public class Grammar {

    @SerializedName("MaCD")
    @Expose
    private int imgItemGrammar;

    @SerializedName("TenCD")
    @Expose
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
