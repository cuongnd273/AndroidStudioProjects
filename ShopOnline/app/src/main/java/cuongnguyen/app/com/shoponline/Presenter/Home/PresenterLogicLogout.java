package cuongnguyen.app.com.shoponline.Presenter.Home;

import android.app.Activity;
import android.content.Intent;
import android.view.View;

import com.facebook.login.LoginManager;
import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.ResultCallback;
import com.google.android.gms.common.api.Status;

import cuongnguyen.app.com.shoponline.Model.ObjectClass.Account;
import cuongnguyen.app.com.shoponline.View.Home.HomeActivity;
import cuongnguyen.app.com.shoponline.View.Home.ViewLogout;

/**
 * Created by CuongNguyen on 06/08/17.
 */

public class PresenterLogicLogout implements PresenterLogout {
    Activity activity;
    ViewLogout viewLogout;
    public PresenterLogicLogout(Activity activity,ViewLogout viewLogout) {
        this.activity = activity;
        this.viewLogout=viewLogout;
    }

    @Override
    public void logoutEmail() {
        Account.setId(activity,"");
        Account.setLogged(activity,0);
        Account.setName(activity,"");
        viewLogout.logout();
    }

    @Override
    public void logoutFacebook() {
        Account.setId(activity,"");
        Account.setLogged(activity,0);
        Account.setName(activity,"");
        LoginManager.getInstance().logOut();
        viewLogout.logout();
    }

    @Override
    public void logoutGoogle(GoogleApiClient mGoogleApiClient) {
        Account.setId(activity,"");
        Account.setLogged(activity,0);
        Account.setName(activity,"");
        Auth.GoogleSignInApi.signOut(mGoogleApiClient).setResultCallback(
                new ResultCallback<Status>() {
                    @Override
                    public void onResult(Status status) {
                        viewLogout.logout();
                    }
                });

    }
}
