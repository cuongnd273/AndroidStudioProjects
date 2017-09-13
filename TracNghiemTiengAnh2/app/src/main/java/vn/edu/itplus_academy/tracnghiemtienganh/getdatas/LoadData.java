package vn.edu.itplus_academy.tracnghiemtienganh.getdatas;

import android.os.StrictMode;
import android.util.Log;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by VietUng on 31/12/2015.
 */
public class LoadData {

    public LoadData() {
    }

    public boolean downloadFile_Server(String url, File outputFile){
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
        boolean download = true;
        try {

            Log.i("LoadFile ---->  ", url);
//            URL u = new URL(url);
//            URLConnection conn = u.openConnection();
//            int contentLength = conn.getContentLength();

            //DataInputStream stream = new DataInputStream(u.openStream());

            InputStream input = null;
            try {
                URL u = new URL(url); // link of the song which you want to download like (http://...)
                input = u.openStream();
                OutputStream output = new FileOutputStream(outputFile);
                download = true;

                    byte[] buffer = new byte[1024];
                    int bytesRead = 0;
                    while ((bytesRead = input.read(buffer, 0, buffer.length)) >= 0) {
                        output.write(buffer, 0, bytesRead);
                        download = true;
                    }
                    output.close();
                    input.close();

            } catch (MalformedURLException e) {
                download = false;
                e.printStackTrace();
            }

            } catch (FileNotFoundException e) {
                download = false;
                e.printStackTrace();
            } catch (IOException e) {
                download = false;
                e.printStackTrace();

            }



//            byte[] buffer = new byte[contentLength];
//            stream.readFully(buffer);
//            stream.close();
//
//            DataOutputStream fos = new DataOutputStream(new FileOutputStream(outputFile));
//            fos.write(buffer);
//            fos.flush();
//            fos.close();


            return download;

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

