package free.app.com.moviebooking.api;

import java.util.List;

import free.app.com.moviebooking.model.Phim;
import free.app.com.moviebooking.response.ResponseDangKy;
import free.app.com.moviebooking.response.ResponseDatVe;
import free.app.com.moviebooking.response.ResponseGheNgoi;
import free.app.com.moviebooking.response.ResponseGiamGia;
import free.app.com.moviebooking.response.ResponseLichChieu;
import free.app.com.moviebooking.response.ResponseDangNhap;
import free.app.com.moviebooking.response.ResponseSuaThongTin;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

/**
 * Created by CuongNguyen on 08/21/17.
 */

public interface MovieAPI {
    @GET("phim_dang_chieu.php")
    Call<List<Phim>> phimdangchieu();

    @GET("phim_sap_chieu.php")
    Call<List<Phim>> phimsapchieu();

    @GET("phim.php")
    Call<Phim> phim(@Query("maphim") int maphim);

    @FormUrlEncoded
    @POST("dang_nhap.php")
    Call<ResponseDangNhap> login(@Field("taikhoan") String taikhoan, @Field("matkhau") String matkhau);

    @GET("lich_chieu.php")
    Call<ResponseLichChieu> lichchieu(@Query("maphim")int maphim);

    @FormUrlEncoded
    @POST("dang_ky.php")
    Call<ResponseDangKy> dangky(@Field("cmnd") String cmnd, @Field("taikhoan") String taikhoan, @Field("matkhau") String matkhau, @Field("hoten") String hoten, @Field("email") String email, @Field("sdt") String sdt, @Field("diachi") String diachi, @Field("ngaysinh") String ngaysinh);

    @FormUrlEncoded
    @POST("sua_thong_tin.php")
    Call<ResponseSuaThongTin> suathongtin(@Field("mataikhoan") int mataikhoan,@Field("matkhaucu") String matkhau,@Field("matkhaumoi") String matkhaumoi, @Field("hoten") String hoten, @Field("sdt") String sdt, @Field("diachi") String diachi, @Field("ngaysinh") String ngaysinh);

    @GET("ghe_ngoi.php")
    Call<ResponseGheNgoi> ghengoi(@Query("malichchieu") int malichlieu);

    @FormUrlEncoded
    @POST("giam_gia.php")
    Call<ResponseGiamGia> giamgia(@Field("magiamgia") String ma);

    @FormUrlEncoded
    @POST("dat_ve.php")
    Call<ResponseDatVe> datve(@Field("mataikhoan") int mataikhoan,@Field("malichchieu") int malichchieu,@Field("giave") int giave,@Field("giamgia") int giamga,@Field("ghes") String ghes);

}
