package cuongnguyen.app.com.shoponline.Presenter.Home;

import com.google.android.gms.common.api.GoogleApiClient;

/**
 * Created by CuongNguyen on 06/08/17.
 */

public interface PresenterLogout {
    void logoutEmail();
    void logoutFacebook();
    void logoutGoogle(GoogleApiClient mGoogleApiClient);
}
