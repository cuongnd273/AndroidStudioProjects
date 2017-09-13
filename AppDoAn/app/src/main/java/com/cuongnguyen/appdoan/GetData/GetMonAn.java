package com.cuongnguyen.appdoan.GetData;

import com.cuongnguyen.appdoan.Model.MonAn;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by CUONG on 7/23/2016.
 */
public class GetMonAn {
    @Expose
    @SerializedName("success")
    private String success;
    @Expose
    @SerializedName("message")
    private  String message;
    @Expose
    @SerializedName("MonAn")
    private List<MonAn> list=new ArrayList<MonAn>();

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

    public List<MonAn> getList() {
        return list;
    }

    public void setList(List<MonAn> list) {
        this.list = list;
    }
}
