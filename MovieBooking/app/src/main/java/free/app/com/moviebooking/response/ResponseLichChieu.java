package free.app.com.moviebooking.response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

import free.app.com.moviebooking.model.LichChieu;

/**
 * Created by CuongNguyen on 08/24/17.
 */

public class ResponseLichChieu {
    @SerializedName("status")
    @Expose
    private Integer status;
    @SerializedName("message")
    @Expose
    private String message;
    @SerializedName("lichchieu")
    @Expose
    private List<List<LichChieu>> lichchieu = null;

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

    public List<List<LichChieu>> getLichchieu() {
        return lichchieu;
    }

    public void setLichchieu(List<List<LichChieu>> lichchieu) {
        this.lichchieu = lichchieu;
    }
}
