package vn.edu.itplus_academy.thitracnghiem.mModel;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by CUONG on 7/2/2016.
 */
public class ResultGetQuestion {
    @SerializedName("Question")
    @Expose
    List<question> questionses=new ArrayList<question>();
    @SerializedName("Answer")
    @Expose
    List<answer> answers=new ArrayList<answer>();
    @SerializedName("success")
    @Expose
    String success;
    @SerializedName("message")
    @Expose
    String message;

    public List<question> getQuestionses() {
        return questionses;
    }

    public void setQuestionses(List<question> questionses) {
        this.questionses = questionses;
    }

    public List<answer> getAnswers() {
        return answers;
    }

    public void setAnswers(List<answer> answers) {
        this.answers = answers;
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
}
