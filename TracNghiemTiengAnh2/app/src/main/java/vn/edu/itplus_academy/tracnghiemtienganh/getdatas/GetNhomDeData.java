package vn.edu.itplus_academy.tracnghiemtienganh.getdatas;

import android.content.Context;
import android.content.SharedPreferences;

import java.util.ArrayList;
import java.util.List;

import retrofit.Callback;
import retrofit.RestAdapter;
import retrofit.RetrofitError;
import retrofit.client.Response;
import vn.edu.itplus_academy.tracnghiemtienganh.JsonAPIs.JsonAPI_NhomDe;
import vn.edu.itplus_academy.tracnghiemtienganh.databases.DBAdapter;
import vn.edu.itplus_academy.tracnghiemtienganh.databases.Variable_Globals;
import vn.edu.itplus_academy.tracnghiemtienganh.models.GetNhomDe;
import vn.edu.itplus_academy.tracnghiemtienganh.models.NhomDe;

/**
 * Created by VietUng on 03/06/2016.
 */
public class GetNhomDeData {
    private Context context;
    private String API;
    private String dest;
    private SharedPreferences sharedPreferences;

    public GetNhomDeData(Context context)
    {
        this.context = context;
    }


    private List<NhomDe> lst_nhomde = new ArrayList<NhomDe>();

    public void getNhomDe_Data(int max_id)
    {

        sharedPreferences = context.getSharedPreferences(Variable_Globals.FILE_CONFIG, context.MODE_PRIVATE);
        API = sharedPreferences.getString(Variable_Globals.HOST, "http://itpsoft.com.vn/WebserviesEnglish/QuanLy");
        dest = sharedPreferences.getString(Variable_Globals.CHUDE,"/getnhomde.php");

        RestAdapter restAdapter = new RestAdapter.Builder()
                .setEndpoint(API).build();
        JsonAPI_NhomDe git = restAdapter.create(JsonAPI_NhomDe.class);
        git.getFeed("get", max_id, new Callback<GetNhomDe>() {
            @Override
            public void success(GetNhomDe getNhomDe, Response response) {
                lst_nhomde = getNhomDe.getNhomDe();
                DBAdapter db = new DBAdapter(context);
                db.open();
                for (int i=0;i<lst_nhomde.size();i++)
                {
                    db.insertNhomDe(lst_nhomde.get(i).getMand(),lst_nhomde.get(i).getTenND());
                }
                db.close();
            }

            @Override
            public void failure(RetrofitError error) {

            }

        });


    }
}
