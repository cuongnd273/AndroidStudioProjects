package com.example.app.goofood.API;

import com.example.app.goofood.GetData.GetFood;
import com.example.app.goofood.GetData.GetListComment;
import com.example.app.goofood.GetData.GetListFood;
import com.example.app.goofood.GetData.GetListPlace;
import com.example.app.goofood.GetData.GetListProvince;
import com.example.app.goofood.GetData.GetLogin;
import com.example.app.goofood.GetData.GetPlace;
import com.example.app.goofood.GetData.GetProvince;
import com.example.app.goofood.GetData.GetRegistry;
import com.example.app.goofood.GetData.GetSearch;
import com.example.app.goofood.GetData.Result;

import retrofit.Callback;
import retrofit.http.Field;
import retrofit.http.FormUrlEncoded;
import retrofit.http.GET;
import retrofit.http.POST;
import retrofit.http.Query;

public interface MyServices {
    @GET("/api/list_province.php")
    public void ListProvince(@Query("id") String idRegion, Callback<GetListProvince> response);

    @GET("/api/info_province.php")
    public void InfoProvince(@Query("id") String idProvince, Callback<GetProvince> response);

    @GET("/api/list_place.php")
    public void ListPlace(@Query("id") String idProvince, Callback<GetListPlace> response);

    @GET("/api/info_place.php")
    public void InfoPlace(@Query("id") String idPlace, Callback<GetPlace> response);

    @GET("/api/list_food.php")
    public void ListFood(@Query("id") String idProvince, Callback<GetListFood> response);

    @GET("/api/info_food.php")
    public void InfoFood(@Query("id") String idFood, Callback<GetFood> response);

    @GET("/api/search.php")
    public void Search(@Query("search") String search, Callback<GetSearch> response);

    @GET("/api/list_comment.php")
    public void ListComment(@Query("id") String idPlace,@Query("page") int page, Callback<GetListComment> response);

    @FormUrlEncoded
    @POST("/api/registry.php")
    public void Registry(@Field("taikhoan") String taikhoan, @Field("matkhau") String matkhau,@Field("hoten") String hoten,@Field("sdt") String sdt,@Field("diachi") String diachi,@Field("email") String email,@Field("gioitinh") String gioitinh,Callback<GetRegistry> response);

    @FormUrlEncoded
    @POST("/api/login_face.php")
    public void Login_Facebook(@Field("idFace") String idFace, @Field("hoten") String hoten,Callback<GetLogin> response);

    @FormUrlEncoded
    @POST("/api/login_google.php")
    public void Login_Google(@Field("idGoogle") String idGoogle, @Field("hoten") String hoten,Callback<GetLogin> response);

    @FormUrlEncoded
    @POST("/api/login.php")
    public void Login(@Field("taikhoan") String taikhoan, @Field("matkhau") String matkhau, Callback<GetLogin> response);

    @FormUrlEncoded
    @POST("/api/comment.php")
    public void Comment(@Field("mataikhoan") String mataikhoan, @Field("madiadiem") String diadiem,@Field("binhluan") String binhluan, Callback<Result> response);
}
