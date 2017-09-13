package cuongnguyen.app.com.shoponline.Presenter.Login;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.facebook.AccessToken;
import com.facebook.AccessTokenTracker;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;
import com.facebook.login.LoginManager;
import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignInResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.ResultCallback;
import com.google.android.gms.common.api.Status;

import org.json.JSONException;
import org.json.JSONObject;

import cuongnguyen.app.com.shoponline.API.InterfaceAPI;
import cuongnguyen.app.com.shoponline.Model.Constant.MyServices;
import cuongnguyen.app.com.shoponline.Model.GetData.LoginResult;
import cuongnguyen.app.com.shoponline.Model.ObjectClass.Account;
import cuongnguyen.app.com.shoponline.R;
import cuongnguyen.app.com.shoponline.View.Home.HomeActivity;
import cuongnguyen.app.com.shoponline.View.Login.ViewLogin;
import retrofit.Callback;
import retrofit.RestAdapter;
import retrofit.RetrofitError;
import retrofit.client.Response;

import static android.R.attr.data;

/**
 * Created by CuongNguyen on 06/04/17.
 */

public class PresenterLogicLogin implements PresenterLogin {
    Activity activity;
    ViewLogin viewLogin;
    AccessToken accessToken;
    InterfaceAPI api;
    ProgressDialog dialog;
    public PresenterLogicLogin(Activity activity,ViewLogin viewLogin) {
        this.activity=activity;
        this.viewLogin = viewLogin;
        RestAdapter adapter=new RestAdapter.Builder().setEndpoint(MyServices.URL).build();
        api=adapter.create(InterfaceAPI.class);
    }

    @Override
    public boolean login(String email, String password) {
        api.login(1,email, password, new Callback<cuongnguyen.app.com.shoponline.Model.GetData.LoginResult>() {
            @Override
            public void success(cuongnguyen.app.com.shoponline.Model.GetData.LoginResult loginResult, Response response) {
                if(loginResult.getStatus()==1){
                    Account.setLogged(activity,Account.LOGIN_EMAIL);
                    Account.setName(activity,loginResult.getName());
                    Account.setId(activity,loginResult.getId());

                    viewLogin.loginSuccess();
                }else{
                    hideProgressDialog();
                    Toast.makeText(activity,activity.getString(R.string.error_login),Toast.LENGTH_SHORT).show();
                }
                Log.i("ResultLogin", "Thanh cong");
            }

            @Override
            public void failure(RetrofitError error) {
                hideProgressDialog();
                viewLogin.errorConnect();
            }
        });
        return false;
    }

    @Override
    public boolean loginFacebook() {
        AccessTokenTracker accessTokenTracker = new AccessTokenTracker() {
            @Override
            protected void onCurrentAccessTokenChanged(
                    AccessToken oldAccessToken,
                    AccessToken currentAccessToken) {
                accessToken=currentAccessToken;
            }
        };
        accessToken = AccessToken.getCurrentAccessToken();
        GraphRequest request = GraphRequest.newMeRequest(
                accessToken,
                new GraphRequest.GraphJSONObjectCallback() {
                    @Override
                    public void onCompleted(
                            JSONObject object,
                            GraphResponse response) {
                        try {
                            String idFacebook=object.getString("id");
                            String name=object.getString("name");
                            String email=object.getString("email");
                            api.loginFacebook(Account.LOGIN_FACEBOOK, idFacebook, email, name, new Callback<cuongnguyen.app.com.shoponline.Model.GetData.LoginResult>() {
                                @Override
                                public void success(cuongnguyen.app.com.shoponline.Model.GetData.LoginResult loginResult, Response response) {
                                    if(loginResult.getStatus()==1){
                                        Account.setLogged(activity,Account.LOGIN_FACEBOOK);
                                        Account.setName(activity,loginResult.getName());
                                        Account.setId(activity,loginResult.getId());

                                        viewLogin.loginSuccess();
                                    }
                                }

                                @Override
                                public void failure(RetrofitError error) {
                                    viewLogin.errorConnect();
                                }
                            });
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                });
        Bundle parameters = new Bundle();
        parameters.putString("fields", "id,name,link,email");
        request.setParameters(parameters);
        request.executeAsync();
        return false;
    }

    @Override
    public boolean loginGoogle(Intent data) {
        GoogleSignInResult result = Auth.GoogleSignInApi.getSignInResultFromIntent(data);
        String idGoogle=result.getSignInAccount().getId();
        String name=result.getSignInAccount().getDisplayName();
        String email=result.getSignInAccount().getEmail();
        api.loginGoogle(3, idGoogle, email, name, new Callback<cuongnguyen.app.com.shoponline.Model.GetData.LoginResult>() {
            @Override
            public void success(cuongnguyen.app.com.shoponline.Model.GetData.LoginResult loginResult, Response response) {
                if(loginResult.getStatus()==1){
                    Account.setLogged(activity,Account.LOGIN_GOOGLE_PLUS);
                    Account.setName(activity,loginResult.getName());
                    Account.setId(activity,loginResult.getId());

                    viewLogin.loginSuccess();
                }
            }

            @Override
            public void failure(RetrofitError error) {
                viewLogin.errorConnect();
            }
        });
        return false;
    }
    void showProgressDialog(){
        dialog=new ProgressDialog(activity);
        dialog.setTitle(activity.getString(R.string.login));
        dialog.setMessage(activity.getString(R.string.loading));
        dialog.show();
    }
    void hideProgressDialog(){
        if(dialog!=null)dialog.dismiss();
    }

}
