package studyandroid.it.vietung.amthucbonphuong.loadfiles;

import android.os.StrictMode;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;

/**
 * Created by VietUng on 31/12/2015.
 */
public class LoadData {

    public LoadData() {
    }

    public void downloadFile_Server(String url, File outputFile) {
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

        try {
            URL u = new URL(url);
            URLConnection conn = u.openConnection();
            int contentLength = conn.getContentLength();

            DataInputStream stream = new DataInputStream(u.openStream());

            byte[] buffer = new byte[contentLength];
            stream.readFully(buffer);
            stream.close();

            DataOutputStream fos = new DataOutputStream(new FileOutputStream(outputFile));
            fos.write(buffer);
            fos.flush();
            fos.close();

        } catch (FileNotFoundException e) {
            return; // swallow a 404
        } catch (IOException e) {
            return; // swallow a 404
        }
    }

    public boolean copyImage_local(String url, File outputFile) {

        try {

            File file = new File(url);
            int contentLength = (int)file.length();

            DataInputStream stream = new DataInputStream( new FileInputStream (file));

            byte[] buffer = new byte[contentLength];
            stream.readFully(buffer);
            stream.close();

            DataOutputStream fos = new DataOutputStream(new FileOutputStream(outputFile));
            fos.write(buffer);
            fos.flush();
            fos.close();

            return true;

        } catch (FileNotFoundException e) {
            return false; // swallow a 404
        } catch (IOException e) {
            return false; // swallow a 404
        }
    }

    public String ReadFile(String url) {
        BufferedReader buff = null;
        String temp = "";
        
        StringBuilder data = null;
        try {
            buff = new BufferedReader(new InputStreamReader(new FileInputStream(new File(url))));
            data = new StringBuilder();
            while ((temp = buff.readLine()) != null) {
                data.append(temp + "\n");
            }
            buff.close();
        } catch (FileNotFoundException e) {
            data.append("Quá trình đọc file bị lỗi ,có thể thiết bị của bạn đã xóa mất file của chương trình");
            e.printStackTrace();
        } catch (IOException e) {
            data.append("Quá trình đọc file bị lỗii ,có thể thết bị của bạn đã xóa mất file của chương trình");
            e.printStackTrace();
        }
        return data.toString();
    }

    public boolean WriteFile(String content,File inputFile) {
        try {
            FileOutputStream fos = new FileOutputStream( inputFile);
            fos.write(content.getBytes());
            fos.close();
            return true;
        } catch (Exception e) {
            return  false;
        }
    }

}

