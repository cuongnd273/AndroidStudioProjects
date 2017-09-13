package vn.edu.itplus_academy.thitracnghiem.mModel;

import com.google.gson.annotations.Expose;

/**
 * Created by tuananh on 7/2/2016.
 */
public class ResultRegister {
    @Expose
    String code;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    @Expose
    String success;
    @Expose
    String message;

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
}
