package free.app.com.moviebooking.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by CuongNguyen on 08/26/17.
 */

public class GheNgoi {
    @SerializedName("vitri")
    @Expose
    private int vitri;

    public GheNgoi(int vitri) {
        this.vitri = vitri;
    }

    public int getVitri() {
        return vitri;
    }

    public void setVitri(int vitri) {
        this.vitri = vitri;
    }
}
