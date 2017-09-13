package com.example.cuong.merychrismas;

import android.app.Activity;
import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.ListView;

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
 * Created by Cuong on 21-12-2015.
 */
public class GetAllSong extends AsyncTask<String,Void,String> {
    private Activity activity;
    private ProgressDialog dialog;
    private GetAllSongFinish finish;
    private ListView listView;
    public GetAllSong(Activity activity,GetAllSongFinish finish) {
        super();
        this.activity=activity;
        this.finish=finish;
        this.listView=(ListView)activity.findViewById(R.id.listSong);
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        dialog=new ProgressDialog(activity);
        dialog.setMessage("Đợi chút nhé...");
        dialog.show();
    }

    @Override
    protected String doInBackground(String... params) {
        String json="";
        try {
            URL url=new URL(params[0]);
            HttpURLConnection conn=(HttpURLConnection)url.openConnection();
            conn.setRequestMethod("GET");
            conn.connect();
            InputStream inputStream=conn.getInputStream();
            BufferedReader reader=new BufferedReader(new InputStreamReader(inputStream));
            String line="";
            while ((line=reader.readLine())!=null)
            {
                json+=line;
            }
            reader.close();
            inputStream.close();
            return json;
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    protected void onPostExecute(String s) {
        super.onPostExecute(s);
        ArrayList<Song> list=new ArrayList<Song>();
        if(dialog.isShowing())
        {
            dialog.dismiss();
            try {
                JSONObject object=new JSONObject(s);
                JSONArray array=object.getJSONArray("songs");
                for(int i=0;i<array.length();i++)
                {
                    JSONObject item=array.getJSONObject(i);
                    String name=item.getString("name");
                    String link=item.getString("link");
                    link=link.replace("\\","");
                    Log.e("link",link);
                    Song song=new Song(name,link);
                    list.add(song);
                }
                finish.Finish(list);
                MyAdapter adapter=new MyAdapter(activity,R.layout.item_song,list);
                listView.setAdapter(adapter);
            } catch (JSONException e) {
                e.printStackTrace();
            }

        }
    }
}
