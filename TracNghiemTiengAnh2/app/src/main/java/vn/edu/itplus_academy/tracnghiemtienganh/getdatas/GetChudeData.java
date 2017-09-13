package vn.edu.itplus_academy.tracnghiemtienganh.getdatas;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import retrofit.Callback;
import retrofit.RestAdapter;
import retrofit.RetrofitError;
import retrofit.client.Response;
import vn.edu.itplus_academy.tracnghiemtienganh.JsonAPIs.JsonAPI_Chude;
import vn.edu.itplus_academy.tracnghiemtienganh.databases.DBAdapter;
import vn.edu.itplus_academy.tracnghiemtienganh.databases.Variable_Globals;
import vn.edu.itplus_academy.tracnghiemtienganh.models.ChuDe;
import vn.edu.itplus_academy.tracnghiemtienganh.models.GetChuDe;

/**
 * Created by VietUng on 05/04/2016.
 */
public class GetChudeData {

    private Context context;
    private String API;
    private String dest;
    private SharedPreferences sharedPreferences;

    public GetChudeData(Context context)
    {
        this.context = context;
    }


    private List<ChuDe> lst_chude = new ArrayList<ChuDe>();

    public void getChude_Data(int max_id)
    {

        sharedPreferences = context.getSharedPreferences(Variable_Globals.FILE_CONFIG, context.MODE_PRIVATE);
        API = sharedPreferences.getString(Variable_Globals.HOST, "http://itpsoft.com.vn/WebserviesEnglish/QuanLy");
        dest = sharedPreferences.getString(Variable_Globals.CHUDE,"/getchude.php");

        RestAdapter restAdapter = new RestAdapter.Builder()
                .setEndpoint(API).build();
        JsonAPI_Chude git = restAdapter.create(JsonAPI_Chude.class);
        git.getFeed("get", max_id, new Callback<GetChuDe>() {
            @Override
            public void success(GetChuDe getChuDe, Response response) {
                lst_chude = getChuDe.getChuDe();
                DBAdapter db = new DBAdapter(context);
                db.open();
                for (int i=0;i<lst_chude.size();i++)
                {
                    File dir = context.getFilesDir();
                    File directory = new File(dir.getAbsolutePath() + "/html_file");
                    if (!directory.exists())
                        directory.mkdirs();

                    File file =  new File(directory,lst_chude.get(i).getMacd() + ".html");

                    String path = directory + "/" + lst_chude.get(i).getMacd() + ".html";

                    String link_tmp = "http://itpsoft.com.vn/WebserviesEnglish"+lst_chude.get(i).getLink().toString().substring(2);

                    Log.d("LinkDownload ---->  ", link_tmp);
                    new LoadData().downloadFile_Server(link_tmp, file);


                    db.insertChuDe(lst_chude.get(i).getMacd(),lst_chude.get(i).getTenCD(),path);
                }
                db.close();
            }

            @Override
            public void failure(RetrofitError error) {

            }
        });


    }
}
