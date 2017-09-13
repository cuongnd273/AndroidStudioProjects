package vn.edu.itplus_academy.tracnghiemtienganh.JsonAPIs;

import retrofit.Callback;
import retrofit.http.Field;
import retrofit.http.FormUrlEncoded;
import retrofit.http.POST;
import vn.edu.itplus_academy.tracnghiemtienganh.models.GetDapAn;

/**
 * Created by VietUng on 03/06/2016.
 */
public interface JsonAPI_DapAn {
    @FormUrlEncoded
    @POST("/getdapan.php")
    public void getFeed(@Field("action") String action,@Field("maDT") int maxdt, Callback<GetDapAn> response);
}
