package studyandroid.it.vietung.amthucbonphuong.components.inside_tabhost;

import android.app.Activity;
import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;

import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;

import java.util.ArrayList;
import java.util.List;

import studyandroid.it.vietung.amthucbonphuong.DTO.VideoFood;
import studyandroid.it.vietung.amthucbonphuong.R;
import studyandroid.it.vietung.amthucbonphuong.adapters.ListVideoFoodAdapter;

public class VideoAmthucActivity extends Activity {

    private ListView listVideoFoods;
    private List<ParseObject> ob;
    private ProgressDialog pDialog;
    private ListVideoFoodAdapter listvideofoodadapter;
    private ArrayList<VideoFood> arrvideofoods = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video_amthuc);

        new LoadDatacVideofood().execute();
    }

    private class LoadDatacVideofood extends AsyncTask<Void,Void,Void>
    {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            //tạo 1 progressdialog
            pDialog = new ProgressDialog(VideoAmthucActivity.this);
            //cài đặt tin nhắn cho progressdialog
            pDialog.setMessage("Loading.........");
            //hiển thị progressdialog
            pDialog.show();

        }

        @Override
        protected Void doInBackground(Void... params) {

            arrvideofoods = new ArrayList<VideoFood>();

            try {
                ParseQuery<ParseObject> query = new ParseQuery<ParseObject>("videofoods");
                ob = query.find();
                for (ParseObject video : ob)
                {
                    VideoFood mapvideo = new VideoFood();
                    mapvideo.setVideo_id((String) video.get("idvideofood"));
                    mapvideo.setNamevideo((String) video.get("namevideofoood"));
                    arrvideofoods.add(mapvideo);
                }
            }
            catch (ParseException e)
            {
                Log.e("Error", e.getMessage());
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);

            listVideoFoods = (ListView) findViewById(R.id.listVideoFoods);
            listvideofoodadapter = new ListVideoFoodAdapter(VideoAmthucActivity.this,R.layout.single_item_video_food,arrvideofoods);
            listVideoFoods.setAdapter(listvideofoodadapter);

            pDialog.dismiss();
        }
    }

}
