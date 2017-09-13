package vn.edu.itplus_academy.tracnghiemtienganh.getdatas;

import android.content.Context;
import android.content.SharedPreferences;

import java.util.ArrayList;
import java.util.List;

import retrofit.Callback;
import retrofit.RestAdapter;
import retrofit.RetrofitError;
import retrofit.client.Response;
import vn.edu.itplus_academy.tracnghiemtienganh.JsonAPIs.JsonAPI_DeThi;
import vn.edu.itplus_academy.tracnghiemtienganh.databases.DBAdapter;
import vn.edu.itplus_academy.tracnghiemtienganh.databases.Variable_Globals;
import vn.edu.itplus_academy.tracnghiemtienganh.models.DeThi;
import vn.edu.itplus_academy.tracnghiemtienganh.models.GetDeThi;

/**
 * Created by VietUng on 03/06/2016.
 */
public class GetDeThiData {

    private Context context;
    private String API;
    private String dest;
    private SharedPreferences sharedPreferences;

    public GetDeThiData(Context context)
    {
        this.context = context;
    }


    private List<DeThi> lst_dethi = new ArrayList<DeThi>();

    public void getDeThi_Data(int max_id)
    {

        sharedPreferences = context.getSharedPreferences(Variable_Globals.FILE_CONFIG, context.MODE_PRIVATE);
        API = sharedPreferences.getString(Variable_Globals.HOST, "http://itpsoft.com.vn/WebserviesEnglish/QuanLy");
        dest = sharedPreferences.getString(Variable_Globals.CHUDE,"/getdethi.php");

        RestAdapter restAdapter = new RestAdapter.Builder()
                .setEndpoint(API).build();
        JsonAPI_DeThi git = restAdapter.create(JsonAPI_DeThi.class);
        git.getFeed("get", max_id, new Callback<GetDeThi>() {
            @Override
            public void success(GetDeThi getDeThi, Response response) {
                lst_dethi = getDeThi.getDeThi();
                DBAdapter db = new DBAdapter(context);
                db.open();
                for (int i=0;i<lst_dethi.size();i++)
                {
                    db.insertDeThi(lst_dethi.get(i).getMadt(),lst_dethi.get(i).getMand(),lst_dethi.get(i).getLinkDT(),lst_dethi.get(i).getThoigian(),0,0);
                }
                db.close();
            }

            @Override
            public void failure(RetrofitError error) {

            }
        });

    }
}
