package vn.edu.itplus_academy.tracnghiemtienganh.getdatas;

import android.content.Context;
import android.content.SharedPreferences;

import java.util.ArrayList;
import java.util.List;

import retrofit.Callback;
import retrofit.RestAdapter;
import retrofit.RetrofitError;
import retrofit.client.Response;
import vn.edu.itplus_academy.tracnghiemtienganh.JsonAPIs.JsonAPI_UngDung;
import vn.edu.itplus_academy.tracnghiemtienganh.databases.DBAdapter;
import vn.edu.itplus_academy.tracnghiemtienganh.databases.Variable_Globals;
import vn.edu.itplus_academy.tracnghiemtienganh.models.GetUngDung;
import vn.edu.itplus_academy.tracnghiemtienganh.models.UngDung;

/**
 * Created by VietUng on 09/04/2016.
 */
public class GetUngDungData {

    private Context context;
    private String API;
    private String dest;
    private SharedPreferences sharedPreferences;

    public GetUngDungData(Context context)
    {
        this.context = context;
    }


    private List<UngDung> lst_ungdung = new ArrayList<UngDung>();

    public void getUngDung_Data(int max_id)
    {

        sharedPreferences = context.getSharedPreferences(Variable_Globals.FILE_CONFIG, context.MODE_PRIVATE);
        API = sharedPreferences.getString(Variable_Globals.HOST, "http://itpsoft.com.vn/WebserviesEnglish/QuanLy");
        dest = sharedPreferences.getString(Variable_Globals.CHUDE,"/getungdung.php");

        RestAdapter restAdapter = new RestAdapter.Builder()
                .setEndpoint(API).build();
        JsonAPI_UngDung git = restAdapter.create(JsonAPI_UngDung.class);
        git.getFeed("get", max_id, new Callback<GetUngDung>() {
            @Override
            public void success(GetUngDung getUngDung, Response response) {
                lst_ungdung = getUngDung.getUngDung();
                DBAdapter db = new DBAdapter(context);
                db.open();
                for (int i=0;i<lst_ungdung.size();i++)
                {
                    db.insertUngDung(lst_ungdung.get(i).getMaud(),lst_ungdung.get(i).getTenud(),lst_ungdung.get(i).getAnh(),lst_ungdung.get(i).getLinkud(),lst_ungdung.get(i).getMota());
                }
                db.close();

            }

            @Override
            public void failure(RetrofitError error) {

            }
        });

    }
}
