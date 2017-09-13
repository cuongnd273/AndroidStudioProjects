package com.cuongnguyen.appdoan.GetData;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by CUONG on 7/25/2016.
 */
public class GetThoiGian {
    @Expose
    @SerializedName("success")
    private String success;
    @Expose
    @SerializedName("message")
    private String message;
    @Expose
    @SerializedName("time")
    private String time;

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

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
}
