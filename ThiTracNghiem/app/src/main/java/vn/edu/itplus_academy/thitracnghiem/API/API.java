package vn.edu.itplus_academy.thitracnghiem.API;
import retrofit.Callback;
import retrofit.http.Field;
import retrofit.http.FormUrlEncoded;
import retrofit.http.POST;
import vn.edu.itplus_academy.thitracnghiem.mModel.ResultAddResult;
import vn.edu.itplus_academy.thitracnghiem.mModel.ResultGetQuestion;
import vn.edu.itplus_academy.thitracnghiem.mModel.ResultLogin;
import vn.edu.itplus_academy.thitracnghiem.mModel.ResultRegister;

/**
 * Created by CUONG on 7/2/2016.
 */
public interface API {
        @FormUrlEncoded
        @POST("/login.php")
        void login(@Field("code") String code, @Field("phonenum") String phone, Callback<ResultLogin> callback);

        @FormUrlEncoded
        @POST("/register.php")
        void register(@Field("first_name") String first_name, @Field("last_name") String last_name, @Field("birthday") String birthday,
                      @Field("phonenum") String phonenum, @Field("email") String email, @Field("school") String school,
                      @Field("classicon") String classs, @Field("address") String address,
                      @Field("parentname") String parentname, @Field("parentphone") String parentphone, @Field("specialized") String specialized, Callback<ResultRegister> callback);
        @FormUrlEncoded
        @POST("/getquestion.php")
        void getquestion(@Field("code") String code, @Field("phonenum") String phone, @Field("tqid") String tqid, Callback<ResultGetQuestion> callback);
        @FormUrlEncoded
        @POST("/addresult.php")
        void addresult(@Field("result") String result, Callback<ResultAddResult> callback);
}
