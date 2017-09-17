package free.app.com.moviebooking.activity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import free.app.com.moviebooking.R;
import free.app.com.moviebooking.api.MovieAPI;
import free.app.com.moviebooking.constant.Constants;
import free.app.com.moviebooking.response.ResponseGheNgoi;
import free.app.com.moviebooking.response.ResponseGiamGia;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class PhieuGiamGiaActivity extends AppCompatActivity implements Callback<ResponseGiamGia>,View.OnClickListener{
    EditText magiamgia;
    Button ok,huy;
    ProgressDialog dialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_phieu_giam_gia);
        getControls();
    }
    public void getControls(){
        magiamgia= (EditText) findViewById(R.id.maphieu);
        ok= (Button) findViewById(R.id.ok);
        huy= (Button) findViewById(R.id.huy);
        ok.setOnClickListener(this);
        huy.setOnClickListener(this);
        dialog=new ProgressDialog(this);
        dialog.setMessage("Loading...");
    }
    void responseAPI(String magiamgia){
        Gson gson = new GsonBuilder()
                .setLenient()
                .create();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Constants.URL)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();

        MovieAPI gerritAPI = retrofit.create(MovieAPI.class);

        Call<ResponseGiamGia> call = gerritAPI.giamgia(magiamgia);
        call.enqueue(this);
    }

    @Override
    public void onResponse(Call<ResponseGiamGia> call, Response<ResponseGiamGia> response) {
        dialog.dismiss();
        if(response.body().getStatus()==200){
            Intent intent=getIntent();
            intent.putExtra("giamgia",response.body().getGiamgia());
            setResult(1,intent);
            finish();
        }else{
            Intent intent=getIntent();
            intent.putExtra("giamgia",0);
            setResult(2,intent);
            finish();
        }
    }

    @Override
    public void onFailure(Call<ResponseGiamGia> call, Throwable t) {
        dialog.dismiss();
        Toast.makeText(this, "Có lỗi xảy ra", Toast.LENGTH_SHORT).show();
        Intent intent=getIntent();
        intent.putExtra("giamgia",0);
        setResult(3,intent);
        finish();
    }

    @Override
    public void onClick(View v) {
        if(v.getId()==R.id.ok){
            if(magiamgia.getText().length()==0){
                Toast.makeText(this, "Hãy nhập mã giảm giá", Toast.LENGTH_SHORT).show();
            }else{
                dialog.show();
                responseAPI(magiamgia.getText().toString());
            }
        }else if(v.getId()==R.id.huy){
            Intent intent=getIntent();
            intent.putExtra("giamgia",0);
            setResult(2,intent);
            finish();
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent intent=getIntent();
        intent.putExtra("giamgia",0);
        setResult(2,intent);
        finish();
    }
}
