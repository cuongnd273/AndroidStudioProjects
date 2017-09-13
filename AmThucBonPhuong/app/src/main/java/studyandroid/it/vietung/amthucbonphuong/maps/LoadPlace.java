package studyandroid.it.vietung.amthucbonphuong.maps;


import android.os.AsyncTask;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

/**
 * Created by VietUng on 22/01/2016.
 */
public class LoadPlace extends AsyncTask<String,Void,String> {
    //LoadPlaceFinish finish;
    LoadPlaceFinish finish;
    public LoadPlace(LoadPlaceFinish finish) {
        super();
        this.finish=finish;
    }

    @Override
    protected void onPostExecute(String s) {
        super.onPostExecute(s);
        ArrayList<Places> arr=new ArrayList<Places>();
        try {
            JSONObject object=new JSONObject(s);
            JSONArray array=object.getJSONArray("results");
            for(int i=0;i<array.length();i++)
            {
                JSONObject item=array.getJSONObject(i);
                JSONObject geometry=item.getJSONObject("geometry");
                JSONObject location=geometry.getJSONObject("location");
                double lat=location.getDouble("lat");
                double lng=location.getDouble("lng");
                String name=item.getString("name");
                String vicinity=item.getString("vicinity");
                Places place=new Places(name,lat,lng,vicinity);
                arr.add(place);
            }
            finish.Finish(arr);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected String doInBackground(String... params) {
        String json="";
        try {
            URL url=new URL(params[0]);
            HttpURLConnection conn=(HttpURLConnection)url.openConnection();
            conn.setRequestMethod("GET");
            conn.connect();
            InputStream stream=conn.getInputStream();
            BufferedReader reader=new BufferedReader(new InputStreamReader(stream));
            String line="";
            while ((line=reader.readLine())!=null)
            {
                json+=line;
            }
            reader.close();
            stream.close();
//            Log.e("JSON", json);
            return json;
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
