package com.example.app.goofood.GetData;

import com.example.app.goofood.Model.Province;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class GetProvince {
    @SerializedName("Province")
    @Expose
    private Province province;
    @SerializedName("success")
    @Expose
    private Integer success;
    @SerializedName("message")
    @Expose
    private String message;

    public Province getProvince() {
        return province;
    }

    public void setProvince(Province province) {
        this.province = province;
    }

    public Integer getSuccess() {
        return success;
    }

    public void setSuccess(Integer success) {
        this.success = success;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
