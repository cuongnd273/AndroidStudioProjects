package vn.edu.itplus_academy.tracnghiemtienganh.models;

import android.content.Context;
import android.net.ConnectivityManager;

/**
 * Created by VietUng on 01/04/2016.
 */
public class NetworkUtils {

    public static boolean GetStateNetwork(Context context)
    {


        ConnectivityManager conMgr = (ConnectivityManager) context.getSystemService (Context.CONNECTIVITY_SERVICE);
        if (conMgr.getActiveNetworkInfo() != null
                && conMgr.getActiveNetworkInfo().isAvailable()
                &&    conMgr.getActiveNetworkInfo().isConnected()) {
            return true;
        }
        return false;
    }
}
