package cuongnguyen.app.com.shoponline.API;

import cuongnguyen.app.com.shoponline.Model.GetData.GetGroupProduct;
import cuongnguyen.app.com.shoponline.Model.GetData.GetProduct;
import cuongnguyen.app.com.shoponline.Model.GetData.GetTrademark;
import cuongnguyen.app.com.shoponline.Model.GetData.LoginResult;
import retrofit.Callback;
import retrofit.http.Field;
import retrofit.http.FormUrlEncoded;
import retrofit.http.GET;
import retrofit.http.POST;
import retrofit.http.Path;

import static android.R.attr.id;


/**
 * Created by CuongNguyen on 05/31/17.
 */

public interface InterfaceAPI {
    //Lấy danh sách nhóm sản phẩm
    @GET("/get_group_product.php")
    void getListGroupProduct(Callback<GetGroupProduct> response);
    //type:1  Login by email
    //type:2 Login by facebbok
    //type:3 Login by google
    //Đăng nhập bằng email
    @FormUrlEncoded
    @POST("/login.php")
    void login(@Field("typeLogin") int typeLogin, @Field("email")String email, @Field("password") String password, Callback<LoginResult> response);
    //Đăng nhập bằng facebook
    @FormUrlEncoded
    @POST("/login.php")
    void loginFacebook(@Field("typeLogin") int typeLogin,@Field("id") String idFacebook,@Field("email") String email,@Field("name") String name,Callback<LoginResult> response);
    //Đăng nhập bằng google
    @FormUrlEncoded
    @POST("/login.php")
    void loginGoogle(@Field("typeLogin") int typeLogin,@Field("id") String idGoogle,@Field("email") String email,@Field("name") String name,Callback<LoginResult> response);
    //Lấy thương hiệu bán chạy của nhóm sản phẩm
    @FormUrlEncoded
    @POST("/get_trade.php")
    void getTrademarkOfGroup(@Field("idGroup") int idGroup, Callback<GetTrademark> callback);
    //Lấy sản phẩm bán chạy theo mã nhóm sản phẩm
    @FormUrlEncoded
    @POST("/get_product.php")
    void getBestSellingProduct(@Field("idGroup") int idGroup, Callback<GetProduct> callback);
}
