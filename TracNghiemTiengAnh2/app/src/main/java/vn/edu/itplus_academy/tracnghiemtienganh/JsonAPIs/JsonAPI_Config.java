package vn.edu.itplus_academy.tracnghiemtienganh.JsonAPIs;

import retrofit.Callback;
import retrofit.http.Field;
import retrofit.http.FormUrlEncoded;
import retrofit.http.POST;
import vn.edu.itplus_academy.tracnghiemtienganh.models.getConfig;

/**
 * Created by VietUng on 05/04/2016.
 */
public interface JsonAPI_Config {
//    @GET("/{dest}")      //here is the other url part.best way is to start using /
//    public void getFeed(@Path("dest") String dest, Callback<getConfig> response);

    @FormUrlEncoded
    @POST("/getcauhinh.php")
    public void getFeed(@Field("action") String action, Callback<getConfig> response);
}
