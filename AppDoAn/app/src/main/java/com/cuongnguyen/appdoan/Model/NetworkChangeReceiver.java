package com.cuongnguyen.appdoan.Model;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;

import com.cuongnguyen.appdoan.Activity.SplashScreen;

/**
 * Created by CUONG on 7/26/2016.
 */
public class NetworkChangeReceiver extends BroadcastReceiver {
    private Context context;
    @Override
    public void onReceive(final Context context, final Intent intent) {
        this.context=context;
        if(SplashScreen.status==true && checkRunAppfirst(context) && NetworkUtils.getConnectivityStatus(context)!=0 )
        {
            Intent inte=new Intent(context,SplashScreen.class);
            inte.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
            context.startActivity(inte);
        }
    }
    private Boolean checkRunAppfirst(Context context)
    {
        SharedPreferences sharedPreferences = context.getSharedPreferences("file_config", Context.MODE_PRIVATE);
        if (sharedPreferences.getBoolean("first_run", true)) {

            return true;
        }
        return false;
    }
}
