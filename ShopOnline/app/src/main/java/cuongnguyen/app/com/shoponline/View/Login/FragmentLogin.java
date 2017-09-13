package cuongnguyen.app.com.shoponline.View.Login;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.TextInputLayout;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.facebook.AccessToken;
import com.facebook.AccessTokenTracker;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;
import com.facebook.appevents.AppEventsLogger;
import com.facebook.login.LoginManager;
import com.facebook.login.LoginResult;
import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.auth.api.signin.GoogleSignInResult;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.ResultCallback;
import com.google.android.gms.common.api.Status;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Arrays;

import cuongnguyen.app.com.shoponline.API.InterfaceAPI;
import cuongnguyen.app.com.shoponline.CustomView.ClearEditText;
import cuongnguyen.app.com.shoponline.CustomView.PasswordEditText;
import cuongnguyen.app.com.shoponline.Model.Constant.MyServices;
import cuongnguyen.app.com.shoponline.Model.ObjectClass.Account;
import cuongnguyen.app.com.shoponline.Presenter.Login.PresenterLogicLogin;
import cuongnguyen.app.com.shoponline.R;
import cuongnguyen.app.com.shoponline.View.Home.HomeActivity;
import retrofit.Callback;
import retrofit.RestAdapter;
import retrofit.RetrofitError;
import retrofit.client.Response;

import static com.facebook.FacebookSdk.getApplicationContext;

public class FragmentLogin extends Fragment implements View.OnClickListener,View.OnFocusChangeListener,ViewLogin{
    ClearEditText email;
    PasswordEditText password;
    Button login,login_facebook,login_google;
    CallbackManager callbackManager;
    private static final int RC_SIGN_IN = 9001;
    ProgressDialog dialog;
    InterfaceAPI api;
    AccessToken accessToken;
    PresenterLogicLogin presenterLogicLogin;
    public FragmentLogin() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_login, container, false);
        getControls(view);
        FacebookSdk.sdkInitialize(getApplicationContext());
        callbackManager=CallbackManager.Factory.create();
        //Xử lý khi đăng nhập facebook
        LoginManager.getInstance().registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {
                signInFacebook();
            }

            @Override
            public void onCancel() {
                Log.i("Ketqua", "onCancel");
            }

            @Override
            public void onError(FacebookException error) {
                Log.i("Ketqua", "onError");
            }
        });
        return view;
    }
    void getControls(View v){
        email= (ClearEditText) v.findViewById(R.id.login_email);
        password= (PasswordEditText) v.findViewById(R.id.login_pass);
        login= (Button) v.findViewById(R.id.login_button);
        login_facebook= (Button) v.findViewById(R.id.login_facebook);
        login_google= (Button) v.findViewById(R.id.login_google);
        login.setOnClickListener(this);
        login_facebook.setOnClickListener(this);
        login_google.setOnClickListener(this);
        email.setOnFocusChangeListener(this);
        password.setOnFocusChangeListener(this);

        //Khai báo để gọi api
        RestAdapter adapter=new RestAdapter.Builder().setEndpoint(MyServices.URL).build();
        api=adapter.create(InterfaceAPI.class);
        presenterLogicLogin=new PresenterLogicLogin(getActivity(),this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.login_button:
                loginEmail();
                break;
            case R.id.login_facebook:
                LoginManager.getInstance().logInWithReadPermissions(FragmentLogin.this, Arrays.asList("public_profile","email"));
                break;
            case R.id.login_google:
                signInGoogle();
                break;
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        callbackManager.onActivityResult(requestCode, resultCode, data);
        //Nếu đăng nhập google thành công thì thay đổi thông số tài khoản trên thiết bị
        if (requestCode == RC_SIGN_IN) {
            presenterLogicLogin.loginGoogle(data);
        }
    }
    //Kiểm tra các trường dữ liệu đã nhập
    @Override
    public void onFocusChange(View v, boolean hasFocus) {
        if(!hasFocus){
            TextInputLayout inputLayout;
            if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.JELLY_BEAN) {
                inputLayout = (TextInputLayout) v.getParentForAccessibility();
            }else{
                inputLayout = (TextInputLayout) v.getParent();
            }
            String data="";
            switch (v.getId()){
                case R.id.login_email:
                    data=email.getText().toString();
                    if(!isValidEmail(data)){
                        inputLayout.setErrorEnabled(true);
                        inputLayout.setError(getString(R.string.error_login_email));
                    }else{
                        inputLayout.setErrorEnabled(false);
                    }
                    break;
                case R.id.login_pass:
                    data=password.getText().toString();
                    if(data.length()==0){
                        inputLayout.setErrorEnabled(true);
                        inputLayout.setError(getString(R.string.error_login_pass));
                    }else{
                        inputLayout.setErrorEnabled(false);
                    }
                    break;
            }
        }
    }
    //Hàm kiểm tra định dạng email
    public final static boolean isValidEmail(CharSequence target) {
        if (target == null) {
            return false;
        } else {
            return android.util.Patterns.EMAIL_ADDRESS.matcher(target).matches();
        }
    }
    //Hàm đăng nhập bằng email
    public void loginEmail(){
        if(isValidEmail(email.getText().toString()) && password.getText().length()!=0){
            presenterLogicLogin.login(email.getText().toString(),password.getText().toString());
        }
    }
    //Hàm đăng nhập bằng facebook được gọi sau khi đăng nhập facebook thành công.
    void signInFacebook(){
        presenterLogicLogin.loginFacebook();
    }
    //Hàm đăng nhập google,kết quả trả cho onActivityResult
    void signInGoogle() {
        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .build();
        GoogleApiClient mGoogleApiClient = new GoogleApiClient.Builder(getActivity())
                .enableAutoManage(getActivity(), new GoogleApiClient.OnConnectionFailedListener() {
                    @Override
                    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {

                    }
                })
                .addApi(Auth.GOOGLE_SIGN_IN_API, gso)
                .build();
        Intent signInIntent = Auth.GoogleSignInApi.getSignInIntent(mGoogleApiClient);
        startActivityForResult(signInIntent, RC_SIGN_IN);
    }
    @Override
    public void loginSuccess() {
        Intent intent=new Intent(getActivity(),HomeActivity.class);
        getActivity().finish();
        startActivity(intent);
    }

    @Override
    public void loginError() {
        Toast.makeText(getActivity(),getString(R.string.error_login),Toast.LENGTH_SHORT).show();
    }

    @Override
    public void errorConnect() {
        Toast.makeText(getActivity(),getString(R.string.error_internet),Toast.LENGTH_SHORT).show();
    }
}
