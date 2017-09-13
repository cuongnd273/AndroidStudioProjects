package studyandroid.it.vietung.amthucbonphuong.loadfiles;

import android.os.StrictMode;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by VietUng on 31/12/2015.
 */
public class LoadFileTxt {
    private String textload;

    public LoadFileTxt() {
    }

    public String getTextload() {
        return textload;
    }

    public void LoadFileInternet(String url1) {
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
        try {
            URL url = new URL(url1);
            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            textload = readStream(con.getInputStream());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public String readStream(InputStream in) {
        BufferedReader bufreader = null;
        String strKTML = "";

        try {
            bufreader = new BufferedReader(new InputStreamReader(in));
            String line = "";
            while ((line = bufreader.readLine()) != null) {
                strKTML += line + "\n";
            }

        } catch (IOException ex) {
            ex.printStackTrace();
        } finally {
            if (bufreader != null)

            {
                try {
                    bufreader.close();
                } catch (IOException exx) {
                    exx.printStackTrace();
                }
            }
        }
        return strKTML;
    }
}
