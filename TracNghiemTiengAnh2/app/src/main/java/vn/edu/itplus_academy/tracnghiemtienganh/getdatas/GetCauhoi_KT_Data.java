package vn.edu.itplus_academy.tracnghiemtienganh.getdatas;

import android.content.Context;
import android.content.SharedPreferences;

import java.util.ArrayList;
import java.util.List;

import retrofit.Callback;
import retrofit.RestAdapter;
import retrofit.RetrofitError;
import retrofit.client.Response;
import vn.edu.itplus_academy.tracnghiemtienganh.JsonAPIs.JsonAPI_CauhoiKT;
import vn.edu.itplus_academy.tracnghiemtienganh.databases.DBAdapter;
import vn.edu.itplus_academy.tracnghiemtienganh.databases.Variable_Globals;
import vn.edu.itplus_academy.tracnghiemtienganh.models.CauHoiKT;
import vn.edu.itplus_academy.tracnghiemtienganh.models.getCauhoiKT;

/**
 * Created by VietUng on 08/04/2016.
 */
public class GetCauhoi_KT_Data {

    private Context context;
    private String API;
    private String dest;
    private SharedPreferences sharedPreferences;

    public GetCauhoi_KT_Data(Context context)
    {
        this.context = context;
    }


    private List<CauHoiKT> lst_cauhoi_kt = new ArrayList<CauHoiKT>();

    public void getCauhoiKT_Data(int max_id)
    {

        sharedPreferences = context.getSharedPreferences(Variable_Globals.FILE_CONFIG, context.MODE_PRIVATE);
        API = sharedPreferences.getString(Variable_Globals.HOST, "http://itpsoft.com.vn/WebserviesEnglish/QuanLy");
        dest = sharedPreferences.getString(Variable_Globals.CHUDE,"/getcauhoikt.php");

        RestAdapter restAdapter = new RestAdapter.Builder()
                .setEndpoint(API).build();
        JsonAPI_CauhoiKT git = restAdapter.create(JsonAPI_CauhoiKT.class);
        git.getFeed("get", max_id, new Callback<getCauhoiKT>() {
            @Override
            public void success(getCauhoiKT getCauhoiKT, Response response) {
                lst_cauhoi_kt = getCauhoiKT.getCauHoi();

                DBAdapter db = new DBAdapter(context);
                db.open();
                for (int i=0;i<lst_cauhoi_kt.size();i++)
                {
                    db.insertCauhoi_KT(lst_cauhoi_kt.get(i).getMach(),lst_cauhoi_kt.get(i).getCauhoi(),lst_cauhoi_kt.get(i).getDapan_A(),lst_cauhoi_kt.get(i).getDapan_B(),lst_cauhoi_kt.get(i).getDapan_C(),lst_cauhoi_kt.get(i).getDapan_D(),lst_cauhoi_kt.get(i).getKetqua());
                }
                db.close();
            }

            @Override
            public void failure(RetrofitError error) {

            }
        });

    }
}
