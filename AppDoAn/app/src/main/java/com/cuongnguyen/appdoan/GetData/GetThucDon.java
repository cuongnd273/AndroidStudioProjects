package com.cuongnguyen.appdoan.GetData;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by CUONG on 7/25/2016.
 */
public class GetThucDon {
    @Expose
    @SerializedName("soluong")
    private String soluong;
    @Expose
    @SerializedName("success")
    private String success;
    @Expose
    @SerializedName("message")
    private String message;

    public String getSoluong() {
        return soluong;
    }

    public void setSoluong(String soluong) {
        this.soluong = soluong;
    }

    public String getSuccess() {
        return success;
    }

    public void setSuccess(String success) {
        this.success = success;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
