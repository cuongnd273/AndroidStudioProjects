package vn.edu.itplus_academy.tracnghiemtienganh.JsonAPIs;

import retrofit.Callback;
import retrofit.http.Field;
import retrofit.http.FormUrlEncoded;
import retrofit.http.POST;
import vn.edu.itplus_academy.tracnghiemtienganh.models.GetCauhoiLT;

/**
 * Created by VietUng on 08/04/2016.
 */
public interface JsonAPI_CauhoiLT {

//    @GET("/{dest}")      //here is the other url part.best way is to start using /
//    public void getFeed(@Path("dest") String dest, @Query("maxID") int maxid, Callback<GetCauhoiLT> response);

    @FormUrlEncoded
    @POST("/getcauhoilt.php")
    public void getFeed(@Field("action") String action,@Field("maxID") int maxid, Callback<GetCauhoiLT> response);
}
