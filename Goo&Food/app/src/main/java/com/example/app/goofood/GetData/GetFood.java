package com.example.app.goofood.GetData;

import com.example.app.goofood.Model.Food;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class GetFood {
    @SerializedName("Food")
    @Expose
    private Food food;
    @SerializedName("success")
    @Expose
    private Integer success;
    @SerializedName("message")
    @Expose
    private String message;

    public Food getFood() {
        return food;
    }

    public void setFood(Food food) {
        this.food = food;
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
