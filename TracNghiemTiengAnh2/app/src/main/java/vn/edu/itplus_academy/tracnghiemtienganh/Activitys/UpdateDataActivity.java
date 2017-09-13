package vn.edu.itplus_academy.tracnghiemtienganh.Activitys;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ProgressBar;
import android.widget.TextView;

import vn.edu.itplus_academy.tracnghiemtienganh.R;
import vn.edu.itplus_academy.tracnghiemtienganh.databases.Variable_Globals;
import vn.edu.itplus_academy.tracnghiemtienganh.getdatas.GetCauHoi_LT_Data;
import vn.edu.itplus_academy.tracnghiemtienganh.getdatas.GetCauhoi_KT_Data;
import vn.edu.itplus_academy.tracnghiemtienganh.getdatas.GetChudeData;
import vn.edu.itplus_academy.tracnghiemtienganh.getdatas.GetConfigData;
import vn.edu.itplus_academy.tracnghiemtienganh.getdatas.GetDeThiData;
import vn.edu.itplus_academy.tracnghiemtienganh.getdatas.GetNhomDeData;
import vn.edu.itplus_academy.tracnghiemtienganh.getdatas.GetUngDungData;
import vn.edu.itplus_academy.tracnghiemtienganh.models.NetworkUtils;


public class UpdateDataActivity extends Activity {


    private SharedPreferences sharedPreferences;
    private TextView tv_message_loaddata;
    private ProgressBar prb_loaddata;
    private BroadcastReceiver mRegistrationBroadcastReceiver;
    private Handler splashScreenHandler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_update_data);

        tv_message_loaddata = (TextView) findViewById(R.id.tv_message_loaddata);
        prb_loaddata = (ProgressBar) findViewById(R.id.prb_loaddata);

        sharedPreferences = getSharedPreferences(Variable_Globals.FILE_CONFIG, Context.MODE_PRIVATE);
        checkConnectNetwork();
    }

    private Boolean checkRunAppfirst()
    {
        if (sharedPreferences.getBoolean("first_run", true)) {

            return true;
        }
        return false;
    }
    private void checkConnectNetwork()
    {
        boolean check = NetworkUtils.GetStateNetwork(getApplication());
        if(check)
        {
            //-----nếu có kết nội mạng-----//

            GetConfigData getConfigData = new GetConfigData(getApplicationContext());
            getConfigData.getConfig_Data();

            if(checkRunAppfirst())
            {
                //-----Nếu ứng dụng chạy lần đầu-----//
                //getdata here

                //getData chủ đề
                GetChudeData getChudeData = new GetChudeData(getApplicationContext());
                getChudeData.getChude_Data(0);
                //getData câu hỏi lý thuyết
                GetCauHoi_LT_Data getCauHoi_lt_data = new GetCauHoi_LT_Data(getApplicationContext());
                getCauHoi_lt_data.getCauhoiLT_Data(0);
                //getData câu hỏi thi
                GetCauhoi_KT_Data getCauhoi_kt_data = new GetCauhoi_KT_Data(getApplicationContext());
                getCauhoi_kt_data.getCauhoiKT_Data(0);
                //getData ứng dụng
                GetUngDungData getUngDungData = new GetUngDungData(getApplicationContext());
                getUngDungData.getUngDung_Data(0);
                //getData nhóm đề
                GetNhomDeData getNhomDeData = new GetNhomDeData(getApplicationContext());
                getNhomDeData.getNhomDe_Data(0);
                //getData đề thi
                GetDeThiData getDeThiData = new GetDeThiData(getApplicationContext());
                getDeThiData.getDeThi_Data(0);

                sharedPreferences.edit().putBoolean("first_run", false).commit();

                tv_message_loaddata.setText("Đang tải dữ liệu....");
                prb_loaddata.setVisibility(View.INVISIBLE);
                toMainActivity(15000);
            }
            else
            {
                //-----nếu ứng dụng chạy hơn 1 lần-----//
                //getdata

                //getData chủ đề
                int maxID_chude = sharedPreferences.getInt(Variable_Globals.ID_CHUDE,0);
                GetChudeData getChudeData = new GetChudeData(getApplicationContext());
                getChudeData.getChude_Data(maxID_chude);

                //getData câu hỏi lý thuyết
                int maxID_LT = sharedPreferences.getInt(Variable_Globals.ID_CAUHOI_LT,0);
                GetCauHoi_LT_Data getCauHoi_lt_data = new GetCauHoi_LT_Data(getApplicationContext());
                getCauHoi_lt_data.getCauhoiLT_Data(maxID_LT);

                //getData câu hỏi thi
                int maxID_KT = sharedPreferences.getInt(Variable_Globals.ID_CAUHOI_KT,0);
                GetCauhoi_KT_Data getCauhoi_kt_data = new GetCauhoi_KT_Data(getApplicationContext());
                getCauhoi_kt_data.getCauhoiKT_Data(maxID_KT);

                //getData ứng dụng
                int maxID_ungdung = sharedPreferences.getInt(Variable_Globals.ID_UNGDUNG,0);
                GetUngDungData getUngDungData = new GetUngDungData(getApplicationContext());
                getUngDungData.getUngDung_Data(maxID_ungdung);

                //getData nhóm đề
                int maxID_nhomde = sharedPreferences.getInt(Variable_Globals.ID_NHOMDE,0);
                GetNhomDeData getNhomDeData = new GetNhomDeData(getApplicationContext());
                getNhomDeData.getNhomDe_Data(maxID_nhomde);

                //getData ứng dụng
                int maxID_Dethi = sharedPreferences.getInt(Variable_Globals.ID_DETHI,0);
                GetDeThiData getDeThiData = new GetDeThiData(getApplicationContext());
                getDeThiData.getDeThi_Data(maxID_Dethi);

                tv_message_loaddata.setText("Đang cập nhật dữ liệu....");
                prb_loaddata.setVisibility(View.INVISIBLE);
                toMainActivity(5000);
            }
        }
        else
        {
            //-----nếu không có kết nối mạng-----//
            if(checkRunAppfirst())
            {
                //-----nếu ứng dụng được chạy lần đầu-----//
                //set textview display message
                tv_message_loaddata.setText("Not connect network....");
                prb_loaddata.setVisibility(View.INVISIBLE);

            }
            else
            {
                //-----nếu ứng dụng được chạy hơn 1 lần-----//
                Intent intent = new Intent(UpdateDataActivity.this,MainActivity.class);
                startActivity(intent);
                this.finish();
            }
        }
    }

    private void toMainActivity(long time)
    {
        splashScreenHandler = new Handler();
        splashScreenHandler.postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(UpdateDataActivity.this,MainActivity.class);
                startActivity(intent);
                finish();
            }
        }, time);

    }
}
