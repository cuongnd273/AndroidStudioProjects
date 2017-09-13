package vn.edu.itplus_academy.tracnghiemtienganh.JsonAPIs;

import retrofit.Callback;
import retrofit.http.Field;
import retrofit.http.FormUrlEncoded;
import retrofit.http.POST;
import vn.edu.itplus_academy.tracnghiemtienganh.models.GetNhomDe;

/**
 * Created by VietUng on 03/06/2016.
 */
public interface JsonAPI_NhomDe {

    @FormUrlEncoded
    @POST("/getnhomde.php")
    public void getFeed(@Field("action") String action,@Field("maxID") int maxid, Callback<GetNhomDe> response);
}
