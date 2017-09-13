package com.example.app.goofood.Activity;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.app.goofood.API.MyServices;
import com.example.app.goofood.GetData.GetListProvince;
import com.example.app.goofood.Model.Province;
import com.example.app.goofood.R;
import com.facebook.AccessToken;
import com.facebook.FacebookSdk;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;
import com.facebook.HttpMethod;
import com.facebook.login.LoginManager;
import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.ResultCallback;
import com.google.android.gms.common.api.Status;

import java.util.ArrayList;
import java.util.List;

import retrofit.Callback;
import retrofit.RestAdapter;
import retrofit.RetrofitError;
import retrofit.client.Response;

public class MainActivity extends AppCompatActivity implements View.OnClickListener, GoogleApiClient.OnConnectionFailedListener {
    public static String URL="http://appdulich.esy.es/dulich";
    TextView name,thoat;
    EditText timkiem;
    Button search,login,registry,info,map;
    Spinner spinMien,spinTinh;
    ArrayAdapter adapterTinh,adapterMien;
    String[] mien={"Miền Bắc","Miền Nam","Miền Trung"};
    String[] idMien={"MB","MN","MT"};
    RestAdapter restAdapter;
    List<String> listTinh;
    ArrayList<Province> listProvince;
    MyServices  git;
    String idTinh;
    int idAcc;
    String nameAcc="";
    SharedPreferences pre;
    ProgressDialog dialog;
    boolean doubleBackToExitPressedOnce = false;
    GoogleApiClient mGoogleApiClient;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        FacebookSdk.sdkInitialize(getApplicationContext());
        setContentView(R.layout.activity_main);
        GetControl();
        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .build();
        mGoogleApiClient = new GoogleApiClient.Builder(this)
                .enableAutoManage(this, this)
                .addApi(Auth.GOOGLE_SIGN_IN_API, gso)
                .build();
        dialog=new ProgressDialog(this);
        dialog.setMessage("Loading...");
        pre=getSharedPreferences("login", MODE_PRIVATE);
        idAcc=pre.getInt("idAcc",0);
        nameAcc=pre.getString("nameAcc","");
        if(idAcc!=0)
        {
            login.setVisibility(View.INVISIBLE);
            registry.setVisibility(View.INVISIBLE);
            name.setVisibility(View.VISIBLE);
            name.setText("Xin chào: "+nameAcc);
            thoat.setVisibility(View.VISIBLE);

        }
        restAdapter = new RestAdapter.Builder().setLogLevel(RestAdapter.LogLevel.FULL).setEndpoint(URL).build();
        git = restAdapter.create(MyServices.class);
        spinMien.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                dialog.show();
                git.ListProvince(idMien[position], new Callback<GetListProvince>() {
                    @Override
                    public void success(GetListProvince getListProvince, Response response) {
                        if(getListProvince.getSuccess().equals("1"))
                        {
                            listTinh.clear();
                            listProvince= getListProvince.getListProvince();
                            for(Province item : getListProvince.getListProvince())
                            {
                                listTinh.add(item.getTentinh());
                            }
                            idTinh=getListProvince.getListProvince().get(0).getMatinh();
                            adapterTinh.notifyDataSetChanged();
                        }
                        dialog.dismiss();
                    }

                    @Override
                    public void failure(RetrofitError error) {
                        dialog.dismiss();
                    }
                });
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        spinTinh.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                idTinh=listProvince.get(position).getMatinh();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                idTinh="";
            }
        });
        thoat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                builder.setMessage("Bạn có muốn thoát không?").setTitle("Đăng xuất");
                builder.setPositiveButton("Có", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        disconnectFromFacebook();
                        disconnectFromGoogle();
                        SharedPreferences.Editor edit=pre.edit();
                        edit.clear();
                        edit.commit();
                        Intent intent = getIntent();
                        finish();
                        startActivity(intent);
                    }
                });
                builder.setNegativeButton("Không", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel();
                    }
                });
                builder.create().show();

            }
        });

    }
    public void GetControl()
    {
        search=(Button)findViewById(R.id.btSearch);
        search.setOnClickListener(this);
        login=(Button)findViewById(R.id.btLogin);
        login.setOnClickListener(this);
        registry=(Button)findViewById(R.id.btRegistry);
        registry.setOnClickListener(this);
        spinMien=(Spinner)findViewById(R.id.spinMien);
        spinTinh=(Spinner)findViewById(R.id.spinTinh);
        info=(Button)findViewById(R.id.btInfo);
        info.setOnClickListener(this);
        listTinh=new ArrayList<String>();
        adapterMien=new ArrayAdapter(this,android.R.layout.simple_dropdown_item_1line,mien);
        adapterTinh=new ArrayAdapter(this,android.R.layout.simple_dropdown_item_1line,listTinh);
        listProvince=new ArrayList<Province>();
        spinMien.setAdapter(adapterMien);
        spinTinh.setAdapter(adapterTinh);
        name=(TextView)findViewById(R.id.name);
        thoat=(TextView)findViewById(R.id.logout);
        timkiem=(EditText)findViewById(R.id.timkiem);
        map=(Button)findViewById(R.id.btMap);
        map.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if(v.getId()==search.getId())
        {
            if(timkiem.getText().length()==0)
            {
                Toast.makeText(this,"Hãy nhập địa danh hoặc món ăn cần tìm kiếm",Toast.LENGTH_SHORT).show();
            }else{
                Intent intent=new Intent(MainActivity.this,SearchActivity.class);
                intent.putExtra("search",timkiem.getText().toString());
                startActivity(intent);
            }
        }else if(v.getId()==login.getId()){
            Intent intent=new Intent(MainActivity.this,LoginActivity.class);
            startActivity(intent);
            finish();
        }else if(v.getId()==registry.getId())
        {
            Intent intent=new Intent(MainActivity.this,RegistryActivity.class);
            startActivity(intent);
        }else if(v.getId()==info.getId())
        {
            if(idTinh.equals(""))
            {
                Toast.makeText(this,"Hãy chọn 1 tỉnh",Toast.LENGTH_SHORT).show();
            }else{
                Intent intent=new Intent(this,ProvinceActivity.class);
                intent.putExtra("id",idTinh);
                startActivity(intent);
            }
        }else if(v.getId()==map.getId())
        {
            Intent intent=new Intent(this,MapsActivity.class);
            startActivity(intent);
        }
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
    public void onBackPressed() {
        if (doubleBackToExitPressedOnce) {
            System.exit(0);
        }

        this.doubleBackToExitPressedOnce = true;
        Toast.makeText(this, "Nhấn lần nữa để thoát", Toast.LENGTH_SHORT).show();

        new Handler().postDelayed(new Runnable() {

            @Override
            public void run() {
                doubleBackToExitPressedOnce=false;
            }
        }, 2000);
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

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {

    }
}
