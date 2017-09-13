package free.app.com.moviebooking.response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

import free.app.com.moviebooking.model.GheNgoi;

/**
 * Created by CuongNguyen on 08/26/17.
 */

public class ResponseGheNgoi {

    @SerializedName("ghengois")
    @Expose
    private List<GheNgoi> ghengois = null;
    @SerializedName("status")
    @Expose
    private Integer status;
    @SerializedName("message")
    @Expose
    private String message;
    @SerializedName("soghe")
    @Expose
    private Integer soghe;
    @SerializedName("giave")
    @Expose
    private Integer giave;

    public List<GheNgoi> getGhengois() {
        return ghengois;
    }

    public void setGhengois(List<GheNgoi> ghengois) {
        this.ghengois = ghengois;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Integer getSoghe() {
        return soghe;
    }

    public void setSoghe(Integer soghe) {
        this.soghe = soghe;
    }

    public Integer getGiave() {
        return giave;
    }

    public void setGiave(Integer giave) {
        this.giave = giave;
    }
}
