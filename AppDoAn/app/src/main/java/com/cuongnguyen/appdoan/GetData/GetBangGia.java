package com.cuongnguyen.appdoan.GetData;

import com.cuongnguyen.appdoan.Model.BangGia;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by CUONG on 7/23/2016.
 */
public class GetBangGia {
    @Expose
    @SerializedName("success")
    private String success;
    @Expose
    @SerializedName("message")
    private String message;
    @Expose
    @SerializedName("BangGia")
    private List<BangGia> list=new ArrayList<>();

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

    public List<BangGia> getList() {
        return list;
    }

    public void setList(List<BangGia> list) {
        this.list = list;
    }
}
