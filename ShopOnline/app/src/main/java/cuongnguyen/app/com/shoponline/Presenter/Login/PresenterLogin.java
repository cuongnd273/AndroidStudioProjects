package cuongnguyen.app.com.shoponline.Presenter.Login;

import android.content.Intent;

import com.google.android.gms.common.api.GoogleApiClient;

/**
 * Created by CuongNguyen on 06/04/17.
 */

public interface PresenterLogin {
    boolean login(String email,String password);
    boolean loginFacebook();
    boolean loginGoogle(Intent data);
}
