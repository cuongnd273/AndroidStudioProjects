package vn.edu.itplus_academy.tracnghiemtienganh.getdatas;

import android.content.Context;
import android.content.SharedPreferences;

import java.util.ArrayList;
import java.util.List;

import retrofit.Callback;
import retrofit.RestAdapter;
import retrofit.RetrofitError;
import retrofit.client.Response;
import vn.edu.itplus_academy.tracnghiemtienganh.JsonAPIs.JsonAPI_Config;
import vn.edu.itplus_academy.tracnghiemtienganh.databases.Variable_Globals;
import vn.edu.itplus_academy.tracnghiemtienganh.models.Config;
import vn.edu.itplus_academy.tracnghiemtienganh.models.getConfig;


/**
 * Created by VietUng on 06/04/2016.
 */
public class GetConfigData {

    private Context context;
    private String API;
    private String dest="";
    private SharedPreferences sharedPreferences;

    public GetConfigData(Context context)
    {
        this.context = context;
    }

    private List<Config> lst_config = new ArrayList<Config>();

    public void getConfig_Data()
    {
        sharedPreferences = context.getSharedPreferences(Variable_Globals.FILE_CONFIG, Context.MODE_PRIVATE);
        API = sharedPreferences.getString(Variable_Globals.HOST, "http://itpsoft.com.vn/WebserviesEnglish/QuanLy");
        dest = "/getcauhinh.php";
        RestAdapter restAdapter = new RestAdapter.Builder()
                .setEndpoint(API).build();
        JsonAPI_Config git = restAdapter.create(JsonAPI_Config.class);
        git.getFeed("get", new Callback<getConfig>() {
            @Override
            public void success(getConfig getConfig, Response response) {
                lst_config = getConfig.getCauHinh();


                SharedPreferences.Editor ed = sharedPreferences.edit();


                ed.putString(Variable_Globals.CAUHOI_LT, lst_config.get(3).getLinkCH()).commit();
                ed.putInt(Variable_Globals.ID_CAUHOI_LT, lst_config.get(3).getMaxID()).commit();
                ed.putString(Variable_Globals.NHOMDE, lst_config.get(5).getLinkCH()).commit();
                ed.putInt(Variable_Globals.ID_NHOMDE, lst_config.get(5).getMaxID()).commit();
                ed.putString(Variable_Globals.DETHI, lst_config.get(6).getLinkCH()).commit();
                ed.putInt(Variable_Globals.ID_DETHI, lst_config.get(6).getMaxID()).commit();
                ed.putString(Variable_Globals.CAUHOI_KT, lst_config.get(4).getLinkCH()).commit();
                ed.putInt(Variable_Globals.ID_CAUHOI_KT, lst_config.get(4).getMaxID()).commit();
                ed.putString(Variable_Globals.UNGDUNG, lst_config.get(7).getLinkCH()).commit();
                ed.putInt(Variable_Globals.ID_UNGDUNG, lst_config.get(7).getMaxID()).commit();
                ed.putString(Variable_Globals.CHUDE, lst_config.get(2).getLinkCH()).commit();
                ed.putInt(Variable_Globals.ID_CHUDE, lst_config.get(2).getMaxID()).commit();
                ed.putString(Variable_Globals.VERSION,lst_config.get(1).getLinkCH()).commit();
                ed.putString(Variable_Globals.HOST, lst_config.get(0).getLinkCH()).commit();

            }

            @Override
            public void failure(RetrofitError error) {

            }
        });


    }
}
