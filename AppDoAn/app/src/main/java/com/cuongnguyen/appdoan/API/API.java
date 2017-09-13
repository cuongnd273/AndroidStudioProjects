package com.cuongnguyen.appdoan.API;

import com.cuongnguyen.appdoan.GetData.GetBangGia;
import com.cuongnguyen.appdoan.GetData.GetMonAn;
import com.cuongnguyen.appdoan.GetData.GetNhomMonAn;
import com.cuongnguyen.appdoan.GetData.GetThoiGian;
import com.cuongnguyen.appdoan.GetData.GetThucDon;

import retrofit.Callback;
import retrofit.http.Field;
import retrofit.http.FormUrlEncoded;
import retrofit.http.POST;

/**
 * Created by CUONG on 7/23/2016.
 */
public interface API {
    @FormUrlEncoded
    @POST("/AppDoAn/api/getnhommonan.php")
    void getnhommonan(@Field("action") String action, Callback<GetNhomMonAn> callback);
    @FormUrlEncoded
    @POST("/AppDoAn/api/getmonan.php")
    void getmonan(@Field("action") String action, Callback<GetMonAn> callback);
    @FormUrlEncoded
    @POST("/AppDoAn/api/getbanggia.php")
    void getbanggia(@Field("action") String action, Callback<GetBangGia> callback);
    @FormUrlEncoded
    @POST("/AppDoAn/api/gettimeupdate.php")
    void getthoigian(@Field("timeupdate") String timeupdate, Callback<GetThoiGian> callback);
    @FormUrlEncoded
    @POST("/AppDoAn/api/getthucdon.php")
    void getthucdon(@Field("mamonan") String mamonan, Callback<GetThucDon> callback);
}
