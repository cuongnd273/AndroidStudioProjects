package com.example.app.goofood.GetData;

import com.example.app.goofood.Model.Province;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class GetListProvince {
    @SerializedName("success")
    @Expose
    private String success;
    @SerializedName("ListProvince")
    @Expose
    private ArrayList<Province> ListProvince=new ArrayList<Province>();

    public ArrayList<Province> getListProvince() {
        return ListProvince;
    }

    public void setListProvince(ArrayList<Province> listProvince) {
        ListProvince = listProvince;
    }

    public String getSuccess() {
        return success;
    }

    public void setSuccess(String success) {
        this.success = success;
    }
}
