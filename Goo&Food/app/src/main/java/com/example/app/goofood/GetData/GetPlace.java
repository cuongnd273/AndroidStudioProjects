package com.example.app.goofood.GetData;

import com.example.app.goofood.Model.Place;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class GetPlace {
    @SerializedName("Place")
    @Expose
    private Place place;
    @SerializedName("success")
    @Expose
    private Integer success;
    @SerializedName("message")
    @Expose
    private String message;

    public Place getPlace() {
        return place;
    }

    public void setPlace(Place place) {
        this.place = place;
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
