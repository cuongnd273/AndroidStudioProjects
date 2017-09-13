package com.example.app.goofood.Activity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.app.goofood.API.MyServices;
import com.example.app.goofood.GetData.GetLogin;
import com.example.app.goofood.R;
import com.facebook.AccessToken;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;
import com.facebook.HttpMethod;
import com.facebook.login.LoginManager;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;
import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.auth.api.signin.GoogleSignInResult;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.SignInButton;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.ResultCallback;
import com.google.android.gms.common.api.Status;

import org.json.JSONObject;

import retrofit.Callback;
import retrofit.RestAdapter;
import retrofit.RetrofitError;
import retrofit.client.Response;

public class LoginActivity extends AppCompatActivity implements GoogleApiClient.OnConnectionFailedListener {

    EditText taikhoan,matkhau;
    Button btLogin,btRegis;
    RestAdapter restAdapter;
    MyServices git;
    ProgressDialog dialog;
    private LoginButton loginButton;
    private CallbackManager callbackManager;
    GoogleApiClient mGoogleApiClient;
    SignInButton signInButton;
    private static final int RC_SIGN_IN = 9001;
    SharedPreferences pre;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        FacebookSdk.sdkInitialize(getApplicationContext());
        callbackManager = CallbackManager.Factory.create();
        setContentView(R.layout.activity_login);
        GetControl();
        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .build();
        mGoogleApiClient = new GoogleApiClient.Builder(this)
                .enableAutoManage(this, this)
                .addApi(Auth.GOOGLE_SIGN_IN_API, gso)
                .build();
        signInButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                signIn();
            }
        });
        loginButton = (LoginButton)findViewById(R.id.login_button);
        loginButton.registerCallback(callbackManager, new FacebookCallback<LoginResult>() {

            @Override
            public void onSuccess(LoginResult loginResult) {
                RestAdapter restAdapter=new RestAdapter.Builder().setLogLevel(RestAdapter.LogLevel.FULL).setEndpoint(MainActivity.URL).build();
                final MyServices git=restAdapter.create(MyServices.class);
                GraphRequest request = GraphRequest.newMeRequest(
                        loginResult.getAccessToken(),
                        new GraphRequest.GraphJSONObjectCallback() {
                            @Override
                            public void onCompleted(
                                    JSONObject object,
                                    GraphResponse response) {
                                String idFace=object.optString("id");
                                String hoten=object.optString("name");
                                dialog=new ProgressDialog(LoginActivity.this);
                                dialog.setMessage("Loading...");
                                dialog.show();
                                git.Login_Facebook(idFace, hoten, new Callback<GetLogin>() {
                                    @Override
                                    public void success(GetLogin getLogin, Response response) {
                                        SharedPreferences pre=getSharedPreferences("login", MODE_PRIVATE);
                                        SharedPreferences.Editor edit=pre.edit();
                                        edit.putInt("idAcc", Integer.parseInt(getLogin.getId()));
                                        edit.putString("nameAcc",getLogin.getName());
                                        edit.commit();
                                        dialog.dismiss();
                                        Intent intentM=new Intent(LoginActivity.this,MainActivity.class);
                                        startActivity(intentM);
                                        finish();
                                    }

                                    @Override
                                    public void failure(RetrofitError error) {
                                    }
                                });
                            }
                        });
                Bundle parameters = new Bundle();
                parameters.putString("fields", "id,name,link");
                request.setParameters(parameters);
                request.executeAsync();
            }

            @Override
            public void onCancel() {
                Log.i("KQ","Cancel");
            }

            @Override
            public void onError(FacebookException e) {
                Log.i("KQ",e.toString());
            }
        });
        Intent intent=getIntent();
        restAdapter=new RestAdapter.Builder().setLogLevel(RestAdapter.LogLevel.FULL).setEndpoint(MainActivity.URL).build();
        git=restAdapter.create(MyServices.class);
        if(intent.getStringExtra("taikhoan")!=null && intent.getStringExtra("matkhau")!=null)
        {
            taikhoan.setText(intent.getStringExtra("taikhoan"));
            matkhau.setText(intent.getStringExtra("matkhau"));
        }
        btLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(taikhoan.getText().equals("") || matkhau.getText().equals(""))
                {
                    Toast.makeText(LoginActivity.this,"Hãy nhập thông tin",Toast.LENGTH_SHORT).show();
                }else{
                    dialog=new ProgressDialog(LoginActivity.this);
                    dialog.setMessage("Loading...");
                    dialog.show();
                    git.Login(taikhoan.getText().toString(), matkhau.getText().toString(), new Callback<GetLogin>() {
                        @Override
                        public void success(GetLogin getLogin, Response response) {
                            if(getLogin.getSuccess()==1)
                            {
                                pre=getSharedPreferences("login", MODE_PRIVATE);
                                SharedPreferences.Editor edit=pre.edit();
                                edit.putInt("idAcc", Integer.parseInt(getLogin.getId()));
                                edit.putString("nameAcc",getLogin.getName());
                                edit.commit();
                                dialog.dismiss();
                                Intent intentM=new Intent(LoginActivity.this,MainActivity.class);
                                startActivity(intentM);
                                finish();
                            }else{
                                dialog.dismiss();
                                Toast.makeText(LoginActivity.this,"Sai thông tin đăng nhập",Toast.LENGTH_SHORT).show();
                            }
                        }

                        @Override
                        public void failure(RetrofitError error) {
                            disconnectFromFacebook();
                            SharedPreferences.Editor edit=pre.edit();
                            edit.clear();
                            edit.commit();
                            dialog.dismiss();
                        }
                    });
                }
            }
        });
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        callbackManager.onActivityResult(requestCode, resultCode, data);
        if (requestCode == RC_SIGN_IN) {
            GoogleSignInResult result = Auth.GoogleSignInApi.getSignInResultFromIntent(data);
            handleSignInResult(result);
        }

    }
    public void GetControl()
    {
        taikhoan=(EditText)findViewById(R.id.loginAcc);
        matkhau=(EditText)findViewById(R.id.loginPass);
        btLogin=(Button)findViewById(R.id.btLogin);
        btRegis=(Button)findViewById(R.id.btRegistry);
        signInButton = (SignInButton) findViewById(R.id.btnSignIn);
        signInButton.setSize(SignInButton.SIZE_STANDARD);
    }
    public void disconnectFromFacebook() {

        if (AccessToken.getCurrentAccessToken() == null) {
            return; // already logged out
        }

        new GraphRequest(AccessToken.getCurrentAccessToken(), "/me/permissions/", null, HttpMethod.DELETE, new GraphRequest
                .Callback() {
            @Override
            public void onCompleted(GraphResponse graphResponse) {

                LoginManager.getInstance().logOut();

            }
        }).executeAsync();
    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {

    }
    private void signIn() {
        Intent signInIntent = Auth.GoogleSignInApi.getSignInIntent(mGoogleApiClient);
        startActivityForResult(signInIntent, RC_SIGN_IN);
    }
    private void handleSignInResult(GoogleSignInResult result) {
        Log.d("KQ", "handleSignInResult:" + result.isSuccess());
        if (result.isSuccess()) {
            dialog=new ProgressDialog(LoginActivity.this);
            dialog.setMessage("Loading...");
            dialog.show();
            // Signed in successfully, show authenticated UI.
            GoogleSignInAccount acct = result.getSignInAccount();
            String idAcc=acct.getId();
            String hoten=acct.getDisplayName();
            git.Login_Google(idAcc, hoten, new Callback<GetLogin>() {
                @Override
                public void success(GetLogin getLogin, Response response) {
                    SharedPreferences pre=getSharedPreferences("login", MODE_PRIVATE);
                    SharedPreferences.Editor edit=pre.edit();
                    edit.putInt("idAcc", Integer.parseInt(getLogin.getId()));
                    edit.putString("nameAcc",getLogin.getName());
                    edit.commit();
                    dialog.dismiss();
                    Intent intentM=new Intent(LoginActivity.this,MainActivity.class);
                    startActivity(intentM);
                    finish();
                }

                @Override
                public void failure(RetrofitError error) {
                    disconnectFromGoogle();
                    SharedPreferences.Editor edit=pre.edit();
                    edit.clear();
                    edit.commit();
                    dialog.dismiss();
                }
            });
        } else {
            // Signed out, show unauthenticated UI.
            Toast.makeText(this,"Có lỗi",Toast.LENGTH_LONG).show();
        }
    }
    private void disconnectFromGoogle() {
        Auth.GoogleSignInApi.revokeAccess(mGoogleApiClient).setResultCallback(
                new ResultCallback<Status>() {
                    @Override
                    public void onResult(Status status) {
                        // ...
                    }
                });
    }
}
