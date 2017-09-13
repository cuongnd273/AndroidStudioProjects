package vn.edu.itplus_academy.tracnghiemtienganh.getdatas;

import android.content.Context;
import android.content.SharedPreferences;

import java.util.ArrayList;
import java.util.List;

import retrofit.Callback;
import retrofit.RestAdapter;
import retrofit.RetrofitError;
import retrofit.client.Response;
import vn.edu.itplus_academy.tracnghiemtienganh.JsonAPIs.JsonAPI_CauhoiLT;
import vn.edu.itplus_academy.tracnghiemtienganh.databases.DBAdapter;
import vn.edu.itplus_academy.tracnghiemtienganh.databases.Variable_Globals;
import vn.edu.itplus_academy.tracnghiemtienganh.models.CauHoiLT;
import vn.edu.itplus_academy.tracnghiemtienganh.models.GetCauhoiLT;

/**
 * Created by VietUng on 08/04/2016.
 */
public class GetCauHoi_LT_Data {

    private Context context;
    private String API;
    private String dest;
    private SharedPreferences sharedPreferences;

    public GetCauHoi_LT_Data(Context context)
    {
        this.context = context;
    }


    private List<CauHoiLT> lst_cauhoi_lt = new ArrayList<CauHoiLT>();

    public void getCauhoiLT_Data(int max_id)
    {

        sharedPreferences = context.getSharedPreferences(Variable_Globals.FILE_CONFIG, context.MODE_PRIVATE);
        API = sharedPreferences.getString(Variable_Globals.HOST, "http://itpsoft.com.vn/WebserviesEnglish/QuanLy");
        dest = sharedPreferences.getString(Variable_Globals.CHUDE,"/getcauhoilt.php");

        RestAdapter restAdapter = new RestAdapter.Builder()
                .setEndpoint(API).build();
        JsonAPI_CauhoiLT git = restAdapter.create(JsonAPI_CauhoiLT.class);
        git.getFeed("get", max_id, new Callback<GetCauhoiLT>() {
            @Override
            public void success(GetCauhoiLT getCauhoiLT, Response response) {
                lst_cauhoi_lt = getCauhoiLT.getCauHoi();

                DBAdapter db = new DBAdapter(context);
                db.open();
                for (int i=0;i<lst_cauhoi_lt.size();i++)
                {
                    db.insertCauhoi_LT(lst_cauhoi_lt.get(i).getMach(),lst_cauhoi_lt.get(i).getMacd(),lst_cauhoi_lt.get(i).getCauhoi(),lst_cauhoi_lt.get(i).getDapan_A(),lst_cauhoi_lt.get(i).getDapan_B(),lst_cauhoi_lt.get(i).getDapan_C(),lst_cauhoi_lt.get(i).getDapan_D(),lst_cauhoi_lt.get(i).getKetqua());
                }
                db.close();
            }

            @Override
            public void failure(RetrofitError error) {

            }
        });


    }
}
