package vn.edu.itplus_academy.thitracnghiem.Service;

import android.app.IntentService;
import android.content.Intent;
import android.os.IBinder;
import android.os.SystemClock;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import vn.edu.itplus_academy.thitracnghiem.DataBase.DBAdapter;
import vn.edu.itplus_academy.thitracnghiem.Model.MonThi;

public class TimeIntentService extends IntentService {
    public static final String ACTION_1 ="MY_ACTION_1";
    private final static String CHUALAM = "ChuaLam";
    private final static String DALAM = "DaLam";
    public static final int TIME_MAX = 3600;
    Intent broadcastIntent;
    public boolean stop = false;
    private DBAdapter db;
    private List<MonThi> monThiList;
    public TimeIntentService() {
        super("TimeIntentService");
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onStart(Intent intent, int startId) {
        super.onStart(intent, startId);
    }

    @Override
    public boolean onUnbind(Intent intent) {
        return super.onUnbind(intent);
    }

    @Override
    public IBinder onBind(Intent intent) {
        return super.onBind(intent);
    }

    @Override
    protected void onHandleIntent(Intent intent) {

        // Tạo một đối tượng Intent (Một đối tượng phát sóng).
        broadcastIntent = new Intent();

        // Sét tên hành động (Action) của Intent này.
        // Một Intent có thể thực hiện nhiều hành động khác nhau.
        // (Có thể coi là nhiều nhiệm vụ).
        broadcastIntent.setAction(TimeIntentService.ACTION_1);



        // Vòng lặp 3600 lần phát sóng của Intent.
            for (int i = TIME_MAX; i >= 0 && !stop ; i--) {

                // Sét đặt giá trị cho dữ liệu gửi đi.
                // (Phần trăm của công việc).
                broadcastIntent.putExtra("time", i);

                // Send broadcast
                // Phát sóng gửi đi.
                sendBroadcast(broadcastIntent);
                Log.d("service_",String.valueOf(i));
                // Sleep 1000 Milliseconds.
                // Tạm dừng 1000 Mili giây.
                SystemClock.sleep(1000);
            }

        ketthuc();
        broadcastIntent.putExtra("time", -1);
        sendBroadcast(broadcastIntent);
    }

    public void ketthuc(){
        db = new DBAdapter(this);
        monThiList = new ArrayList<>();
        monThiList = db.getAll_MonThi();
        for (int i = 0 ; i < monThiList.size() ;i++){
            if(monThiList.get(i).getStatusMonThi().equals(CHUALAM)){
                db.update_Status_MonThi(monThiList.get(i).getMaMonThi(),DALAM);
            }
        }



    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        stop = true;
        Log.d("service_","Stop");
    }
}
