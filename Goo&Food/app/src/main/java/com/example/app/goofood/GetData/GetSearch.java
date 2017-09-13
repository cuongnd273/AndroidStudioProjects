package com.example.app.goofood.GetData;

import com.example.app.goofood.Model.Food;
import com.example.app.goofood.Model.Place;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class GetSearch {
    @SerializedName("ListFood")
    @Expose
    private List<Food> listFood = new ArrayList<>();
    @SerializedName("ListPlace")
    @Expose
    private List<Place> listPlace = new ArrayList<>();
    @SerializedName("success")
    @Expose
    private Integer success;
    @SerializedName("message")
    @Expose
    private String message;

    public List<Food> getListFood() {
        return listFood;
    }

    public void setListFood(List<Food> listFood) {
        this.listFood = listFood;
    }

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
