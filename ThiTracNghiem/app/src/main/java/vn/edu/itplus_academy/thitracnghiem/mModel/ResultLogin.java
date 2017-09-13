package vn.edu.itplus_academy.thitracnghiem.mModel;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

import vn.edu.itplus_academy.thitracnghiem.Model.MonThi;

/**
 * Created by CUONG on 6/24/2016.
 */
public class ResultLogin {
    @SerializedName("name")
    @Expose
    String name;
    @SerializedName("status")
    @Expose
    String status;
    @SerializedName("success")
    @Expose
    String success;
    @SerializedName("message")
    @Expose
    String message;
    @SerializedName("MonThi")
    @Expose
    List<MonThi> monThis=new ArrayList<MonThi>();

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
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

    public List<MonThi> getMonThis() {
        return monThis;
    }

    public void setMonThis(List<MonThi> monThis) {
        this.monThis = monThis;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
