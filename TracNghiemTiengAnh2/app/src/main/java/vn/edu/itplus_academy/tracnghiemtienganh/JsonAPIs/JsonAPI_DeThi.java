package vn.edu.itplus_academy.tracnghiemtienganh.JsonAPIs;

import retrofit.Callback;
import retrofit.http.Field;
import retrofit.http.FormUrlEncoded;
import retrofit.http.POST;
import vn.edu.itplus_academy.tracnghiemtienganh.models.GetDeThi;

/**
 * Created by VietUng on 03/06/2016.
 */
public interface JsonAPI_DeThi {
    @FormUrlEncoded
    @POST("/getdethi.php")
    public void getFeed(@Field("action") String action,@Field("maxID") int maxid, Callback<GetDeThi> response);
}
