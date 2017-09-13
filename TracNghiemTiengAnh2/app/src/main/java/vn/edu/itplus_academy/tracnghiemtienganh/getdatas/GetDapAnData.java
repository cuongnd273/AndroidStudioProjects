package vn.edu.itplus_academy.tracnghiemtienganh.getdatas;

import android.content.Context;
import android.content.SharedPreferences;

import java.util.ArrayList;
import java.util.List;

import retrofit.Callback;
import retrofit.RestAdapter;
import retrofit.RetrofitError;
import retrofit.client.Response;
import vn.edu.itplus_academy.tracnghiemtienganh.JsonAPIs.JsonAPI_DapAn;
import vn.edu.itplus_academy.tracnghiemtienganh.databases.DBAdapter;
import vn.edu.itplus_academy.tracnghiemtienganh.databases.Variable_Globals;
import vn.edu.itplus_academy.tracnghiemtienganh.models.DapAn;
import vn.edu.itplus_academy.tracnghiemtienganh.models.GetDapAn;

/**
 * Created by VietUng on 03/06/2016.
 */
public class GetDapAnData {
    private Context context;
    private String API;
    private String dest;
    private SharedPreferences sharedPreferences;

    public GetDapAnData(Context context)
    {
        this.context = context;
    }


    private List<DapAn> lst_dapan = new ArrayList<DapAn>();

    public void getDapAn_Data(final int madt)
    {

        sharedPreferences = context.getSharedPreferences(Variable_Globals.FILE_CONFIG, context.MODE_PRIVATE);
        API = sharedPreferences.getString(Variable_Globals.HOST, "http://itpsoft.com.vn/WebserviesEnglish/QuanLy");
        dest = sharedPreferences.getString(Variable_Globals.CHUDE,"/getdapan.php");

        RestAdapter restAdapter = new RestAdapter.Builder()
                .setEndpoint(API).build();
        JsonAPI_DapAn git = restAdapter.create(JsonAPI_DapAn.class);
        git.getFeed("get", madt, new Callback<GetDapAn>() {
            @Override
            public void success(GetDapAn getDapAn, Response response) {
                lst_dapan = getDapAn.getDapAn();
                DBAdapter db = new DBAdapter(context);
                db.open();
                for (int i=0;i<lst_dapan.size();i++)
                {
                    if(lst_dapan.get(i).getMada()!=0){

                        db.insertDapAn(lst_dapan.get(i).getMada(),madt,lst_dapan.get(i).getDa());
                    }
                }
                db.close();
            }

            @Override
            public void failure(RetrofitError error) {

            }
        });

    }
}
