package com.example.app.goofood.GetData;

import com.example.app.goofood.Model.Place;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class GetListPlace {
    @SerializedName("ListPlace")
    @Expose
    private List<Place> listPlace = null;
    @SerializedName("success")
    @Expose
    private Integer success;
    @SerializedName("message")
    @Expose
    private String message;

    public List<Place> getListPlace() {
        return listPlace;
    }

    public void setListPlace(List<Place> listPlace) {
        this.listPlace = listPlace;
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
